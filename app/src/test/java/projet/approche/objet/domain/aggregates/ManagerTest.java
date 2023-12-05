package projet.approche.objet.domain.aggregates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.building.exceptions.BuildingAlreadyStartedException;
import projet.approche.objet.domain.valueObject.building.exceptions.NotBuiltException;
import projet.approche.objet.domain.valueObject.building.exceptions.NotEnoughNeedsException;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.game.GameState;
import projet.approche.objet.domain.valueObject.game.exceptions.GameAlreadyStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.GameEnded;
import projet.approche.objet.domain.valueObject.game.exceptions.GameNotStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.NoMoreSpace;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughInhabitants;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughWorkers;
import projet.approche.objet.domain.valueObject.grid.Coordinate;
import projet.approche.objet.domain.valueObject.grid.exceptions.NoBuildingHereException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotFreeException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotInGridException;
import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;
import projet.approche.objet.domain.valueObject.resource.ResourceType;
import projet.approche.objet.domain.valueObject.resource.exceptions.NotEnoughResourceException;
import projet.approche.objet.domain.valueObject.resource.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.FOOD;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.GOLD;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.STONE;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.WOOD;

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
		assertDoesNotThrow(() -> manager.addInhabitantToBuilding(building, 2));
		assertDoesNotThrow(() -> manager.addInhabitantToBuilding(building, 2));
		assertThrows(NoMoreSpace.class, () -> manager.addInhabitantToBuilding(building, 2));
		assertEquals(4, building.getInhabitants());
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
		assertDoesNotThrow(() -> manager.addInhabitantToBuilding(building, 4));
		assertDoesNotThrow(() -> manager.removeInhabitantFromBuilding(building, 3));
		assertEquals(1, building.getInhabitants());
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
		assertDoesNotThrow(() -> manager.addWorkerToBuilding(building, 4));
		assertThrows(NoMoreSpace.class, () -> manager.addWorkerToBuilding(building, 2));
		assertEquals(4, building.getWorkers());
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
		assertDoesNotThrow(() -> manager.addWorkerToBuilding(building, 4));
		assertDoesNotThrow(() -> manager.removeWorkerFromBuilding(building, 3));
		assertEquals(1, building.getWorkers());
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

	@Test
	void testGetProduction() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertDoesNotThrow(() -> manager.startBuildBuilding(coordinate));
		assertEquals(0, manager.getProduction(WOOD));
		assertEquals(0, manager.getProduction(FOOD));
		assertEquals(0, manager.getProduction(GOLD));
		assertEquals(0, manager.getProduction(STONE));
		assertThrows(NotBuiltException.class, () -> manager.addWorkerToBuilding(building, 4));
		assertThrows(NotBuiltException.class, () -> manager.addInhabitantToBuilding(building, 4));
		while (!building.isBuilt())
			building.update(inventory);
		assertEquals(0, manager.getProduction(WOOD));
		assertEquals(0, manager.getProduction(FOOD));
		assertEquals(0, manager.getProduction(GOLD));
		assertEquals(0, manager.getProduction(STONE));
		assertDoesNotThrow(() -> manager.addWorkerToBuilding(building, 4));
		assertEquals(0, manager.getProduction(WOOD));
		assertEquals(0, manager.getProduction(FOOD));
		assertEquals(0, manager.getProduction(GOLD));
		assertEquals(0, manager.getProduction(STONE));
		assertDoesNotThrow(() -> manager.addInhabitantToBuilding(building, 4));
		assertEquals(2, manager.getProduction(WOOD));
		assertEquals(2, manager.getProduction(FOOD));
		assertEquals(0, manager.getProduction(GOLD));
		assertEquals(0, manager.getProduction(STONE));
		Building building2 = new Building(BuildingType.FARM, 2L);
		Coordinate coordinate2 = new Coordinate(1, 1);
		assertDoesNotThrow(() -> manager.buildBuilding(building2, coordinate2));
		assertDoesNotThrow(() -> building2.startBuild(inventory));
		while (!building2.isBuilt())
			building2.update(inventory);
		manager.setWorkers(100);
		manager.setInhabitants(100);
		assertDoesNotThrow(() -> manager.addWorkerToBuilding(building2, 4));
		assertDoesNotThrow(() -> manager.addInhabitantToBuilding(building2, 6));
		assertEquals(2, manager.getProduction(WOOD));
		assertEquals(12, manager.getProduction(FOOD));
		assertEquals(0, manager.getProduction(GOLD));
		assertEquals(0, manager.getProduction(STONE));
	}

	@Test
	void testStartBuildBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		Coordinate coordinate = new Coordinate(0, 0);
		assertDoesNotThrow(() -> manager.buildBuilding(building, coordinate));
		assertDoesNotThrow(() -> manager.startBuildBuilding(coordinate));
		assertThrows(BuildingAlreadyStartedException.class, () -> manager.startBuildBuilding(coordinate));
		Coordinate coordinate2 = new Coordinate(1, 1);
		Building building2 = new Building(BuildingType.WOODENCABIN, 2L);
		try {
			manager.setResources(manager.getResources().remove(new Resource(GOLD, 14)));
		} catch (NotEnoughResourceException | ResourceNotFoundException e) {
			fail("Not enough resources");
		}
		assertDoesNotThrow(() -> manager.buildBuilding(building2, coordinate2));
		assertThrows(NotEnoughNeedsException.class, () -> manager.startBuildBuilding(coordinate2));
		assertThrows(NotInGridException.class, () -> manager.startBuildBuilding(new Coordinate(10, 10)));
		assertThrows(NoBuildingHereException.class, () -> manager.startBuildBuilding(new Coordinate(0, 1)));
	}

	// TODO: update() test
}