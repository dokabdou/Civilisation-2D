package projet.approche.objet.domain.aggregates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.building.exceptions.NotBuiltException;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.game.GameState;
import projet.approche.objet.domain.valueObject.game.exceptions.GameAlreadyStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.GameEnded;
import projet.approche.objet.domain.valueObject.game.exceptions.GameNotStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughInhabitants;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughWorkers;
import projet.approche.objet.domain.valueObject.grid.Coordinate;
import projet.approche.objet.domain.valueObject.grid.exceptions.NoBuildingHereException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotFreeException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotInGridException;
import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;
import projet.approche.objet.domain.valueObject.resource.ResourceType;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ManagerTest {
	private Manager manager;
	private GameStarter gameStarter;
	private ResourceList inventory;

	@BeforeEach
	void setUp() {
		gameStarter = GameStarter.EASY;
		manager = new Manager(gameStarter, 10);
		this.inventory = new ResourceList(
				List.of(new Resource(ResourceType.GOLD, 100), new Resource(ResourceType.WOOD, 100),
						new Resource(ResourceType.STONE, 100), new Resource(ResourceType.FOOD, 100)));

	}

	@Test
	void testBuildBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertDoesNotThrow(() -> manager.getGrid().getBuilding(coordinate));
	}

	@Test
	void testBuildBuildingNotInGrid() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(10, 10);
		assertThrows(NotInGridException.class, () -> manager.buildBuilding(building, coordinate));
		assertThrows(NotInGridException.class, () -> manager.getGrid().getBuilding(coordinate));
	}

	@Test
	void testBuildBuildingNotFree() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertThrows(NotFreeException.class, () -> manager.buildBuilding(building, coordinate));
		assertDoesNotThrow(() -> manager.getGrid().getBuilding(coordinate));
	}

	@Test
	void testDestroyBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertDoesNotThrow(() -> manager.destroyBuilding(coordinate));
		assertThrows(NoBuildingHereException.class, () -> manager.getGrid().getBuilding(coordinate));
	}

	@Test
	void testDestroyBuildingNotInGrid() {
		Coordinate coordinate = new Coordinate(10, 10);
		assertThrows(NotInGridException.class, () -> manager.destroyBuilding(coordinate));
	}

	@Test
	void testDestroyBuildingNoBuildingHere() {
		Coordinate coordinate = new Coordinate(0, 0);
		assertThrows(NoBuildingHereException.class, () -> manager.destroyBuilding(coordinate));
	}

	@Test
	void testAddInhabitantToBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertThrows(NotBuiltException.class, () -> manager.addInhabitantToBuilding(building, 5));
		assertDoesNotThrow(() -> building.startBuild(inventory));
		while (!building.isBuilt())
			building.update(inventory);
		assertDoesNotThrow(() -> manager.addInhabitantToBuilding(building, 5));
		assertEquals(5, building.getInhabitants());
	}

	@Test
	void testRemoveInhabitantFromBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertDoesNotThrow(() -> building.startBuild(inventory));
		while (!building.isBuilt())
			building.update(inventory);
		assertThrows(NotEnoughInhabitants.class, () -> manager.removeInhabitantFromBuilding(building, 5));
		assertDoesNotThrow(() -> manager.addInhabitantToBuilding(building, 5));
		assertDoesNotThrow(() -> manager.removeInhabitantFromBuilding(building, 3));
		assertEquals(2, building.getInhabitants());
	}

	@Test
	void testAddWorkerToBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertThrows(NotBuiltException.class, () -> manager.addWorkerToBuilding(building, 5));
		assertDoesNotThrow(() -> building.startBuild(inventory));
		while (!building.isBuilt())
			building.update(inventory);
		assertDoesNotThrow(() -> manager.addWorkerToBuilding(building, 5));
		assertEquals(5, building.getWorkers());
	}

	@Test
	void testRemoveWorkerFromBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertDoesNotThrow(() -> building.startBuild(inventory));
		while (!building.isBuilt())
			building.update(inventory);
		assertThrows(NotEnoughWorkers.class, () -> manager.removeWorkerFromBuilding(building, 5));
		assertDoesNotThrow(() -> manager.addWorkerToBuilding(building, 5));
		assertDoesNotThrow(() -> manager.removeWorkerFromBuilding(building, 3));
		assertEquals(2, building.getWorkers());
	}

	@Test
	void testStartGame() {
		try {
			manager.startGame();
		} catch (GameAlreadyStarted | GameEnded e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		assertEquals(GameState.RUNNING, manager.getState());
	}

	@Test
	void testPauseGame() {
		try {
			manager.startGame();
		} catch (GameAlreadyStarted | GameEnded e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		try {
			manager.pauseGame();
		} catch (GameNotStarted | GameEnded e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		assertEquals(GameState.PAUSED, manager.getState());
	}

	@Test
	void testEndGame() {
		try {
			manager.startGame();
		} catch (GameAlreadyStarted | GameEnded e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		try {
			manager.endGame();
		} catch (GameNotStarted e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		assertEquals(GameState.ENDED, manager.getState());
	}
}