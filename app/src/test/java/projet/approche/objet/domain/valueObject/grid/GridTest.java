package projet.approche.objet.domain.valueObject.grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotFreeException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotInGridException;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
	private Grid grid;
	private Building building;
	private Coordinate coordinate;

	@BeforeEach
	void setUp() {
		grid = new Grid(10);
		building = new Building(BuildingType.HOUSE, 1);
		coordinate = new Coordinate(1, 1);
	}

	@Test
	void testSetBuilding_NotInGrid() {
		Coordinate outOfGridCoordinate = new Coordinate(-1, -1);
		assertThrows(NotInGridException.class, () -> grid.setBuilding(building, outOfGridCoordinate));
	}

	@Test
	void testSetBuilding_NotFree() {
		assertDoesNotThrow(() -> grid.setBuilding(building, coordinate));
		Building anotherBuilding = new Building(BuildingType.HOUSE, 2);
		assertThrows(NotFreeException.class, () -> grid.setBuilding(anotherBuilding, coordinate));
	}

	@Test
	void testSetBuilding_Success() {
		assertDoesNotThrow(() -> grid.setBuilding(building, coordinate));
		assertEquals(building, grid.getBuilding(coordinate));
	}

	@Test
	void testRemoveBuilding() {
		assertDoesNotThrow(() -> grid.setBuilding(building, coordinate));
		grid.removeBuilding(coordinate);
		assertNull(grid.getBuilding(coordinate));
	}
}