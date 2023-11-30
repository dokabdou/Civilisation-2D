package projet.approche.objet.domain.aggregates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.game.GameState;
import projet.approche.objet.domain.valueObject.game.exceptions.GameAlreadyStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.GameEnded;
import projet.approche.objet.domain.valueObject.game.exceptions.GameNotStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughInhabitants;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughWorkers;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
	private Manager manager;
	private GameStarter gameStarter;

	@BeforeEach
	void setUp() {
		gameStarter = GameStarter.EASY;
		manager = new Manager(gameStarter);
	}

	@Test
	void testBuildBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		manager.buildBuilding(building);
		assertTrue(manager.getBuildings().contains(building));
	}

	@Test
	void testDestroyBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		manager.buildBuilding(building);
		manager.destroyBuilding(building);
		assertFalse(manager.getBuildings().contains(building));
	}

	@Test
	void testAddInhabitantToBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		manager.buildBuilding(building);
		try {
			manager.addInhabitantToBuilding(building, 5);
		} catch (NotEnoughInhabitants e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		assertEquals(5, building.getInhabitants());
	}

	@Test
	void testRemoveInhabitantFromBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		manager.buildBuilding(building);
		try {
			manager.addInhabitantToBuilding(building, 5);
		} catch (NotEnoughInhabitants e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		try {
			manager.removeInhabitantFromBuilding(building, 3);
		} catch (NotEnoughInhabitants e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		assertEquals(2, building.getInhabitants());
	}

	@Test
	void testAddWorkerToBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		manager.buildBuilding(building);
		try {
			manager.addWorkerToBuilding(building, 5);
		} catch (NotEnoughWorkers e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		assertEquals(5, building.getWorkers());
	}

	@Test
	void testRemoveWorkerFromBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, 1L);
		manager.buildBuilding(building);
		try {
			manager.addWorkerToBuilding(building, 5);
		} catch (NotEnoughWorkers e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
		try {
			manager.removeWorkerFromBuilding(building, 3);
		} catch (NotEnoughWorkers e) {
			assertFalse(true); // SHOULD NOT HAPPEN
		}
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