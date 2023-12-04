package projet.approche.objet.domain.valueObject.building;

import org.junit.jupiter.api.Test;

import projet.approche.objet.domain.entities.building.Building;
import static org.junit.jupiter.api.Assertions.*;

class BuildingList2Test {
	private static int id = 0;

	@Test
	void testAddBuilding() {
		Building building1 = new Building(BuildingType.WOODENCABIN, id++);
		BuildingList list = new BuildingList(building1);
		Building building2 = new Building(BuildingType.WOODENCABIN, id++);
		list = list.add(building2);

		assertTrue(list.contains(building1));
		assertTrue(list.contains(building2));
	}

	@Test
	void testAddBuildingList() {
		Building building1 = new Building(BuildingType.WOODENCABIN, id++);
		Building building2 = new Building(BuildingType.WOODENCABIN, id++);
		BuildingList list1 = new BuildingList(building1);
		BuildingList list2 = new BuildingList(building2);
		list1 = list1.add(list2);

		assertTrue(list1.contains(building1));
		assertTrue(list1.contains(building2));
	}

	@Test
	void testRemoveBuilding() {
		Building building1 = new Building(BuildingType.WOODENCABIN, id++);
		Building building2 = new Building(BuildingType.WOODENCABIN, id++);
		BuildingList list = new BuildingList(building1, building2);
		list = list.remove(building1);

		assertFalse(list.contains(building1));
		assertTrue(list.contains(building2));
	}

	@Test
	void testRemoveBuildingList() {
		Building building1 = new Building(BuildingType.WOODENCABIN, id++);
		Building building2 = new Building(BuildingType.WOODENCABIN, id++);
		BuildingList list1 = new BuildingList(building1, building2);
		BuildingList list2 = new BuildingList(building1);
		list1 = list1.remove(list2);

		assertFalse(list1.contains(building1));
		assertTrue(list1.contains(building2));
	}

	@Test
	void testContainsBuilding() {
		Building building = new Building(BuildingType.WOODENCABIN, id++);
		BuildingList list = new BuildingList(building);

		assertTrue(list.contains(building));
	}

	@Test 
	void testFoodConsumptionBuildingList(){
		Building building1 = new Building(BuildingType.WOODENCABIN, id++);
		building1.addInhabitantToBuilding(1);
		Building building2 = new Building(BuildingType.WOODENCABIN, id++);
		building2.addInhabitantToBuilding(1);
		BuildingList list = new BuildingList(building1, building2);

		assertEquals(2, list.foodConsumption().value);
	}

	@Test
	void testContainsBuildingList() {
		Building building1 = new Building(BuildingType.WOODENCABIN, id++);
		Building building2 = new Building(BuildingType.WOODENCABIN, id++);
		BuildingList list1 = new BuildingList(building1, building2);
		BuildingList list2 = new BuildingList(building1);

		assertTrue(list1.contains(list2));
	}

	@Test
	void testSize() {
		Building building1 = new Building(BuildingType.WOODENCABIN, id++);
		Building building2 = new Building(BuildingType.WOODENCABIN, id++);
		BuildingList list = new BuildingList(building1, building2);

		assertEquals(2, list.size());
	}
}