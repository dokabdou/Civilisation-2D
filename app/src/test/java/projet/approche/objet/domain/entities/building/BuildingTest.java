// FILEPATH: /home/nessar/Projet-Approche-Objet/app/src/test/java/projet/approche/objet/domain/entities/building/BuildingTest.java

package projet.approche.objet.domain.entities.building;

import org.junit.jupiter.api.Test;

import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;
import projet.approche.objet.domain.valueObject.resource.ResourceType;
import projet.approche.objet.exception.building.BuildingAlreadyStartedException;
import projet.approche.objet.exception.building.NotEnoughNeedsException;

import static org.junit.jupiter.api.Assertions.*;

import org.checkerframework.checker.units.qual.s;

class BuildingTest {
	@Test
	void testConstructor() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		assertEquals(type, building.type);
		assertFalse(building.isBuildStarted());
		assertFalse(building.isBuilt());
	}

	@Test
	void testStartBuild() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);
		ResourceList resources = new ResourceList(new Resource(ResourceType.fromString("Wood"), 100));
		assertThrows(NotEnoughNeedsException.class, () -> building.startBuild(resources));
		ResourceList resources2 = new ResourceList(
				new Resource(ResourceType.fromString("Wood"), 100),
				new Resource(ResourceType.fromString("Stone"), 100));
		assertThrows(NotEnoughNeedsException.class, () -> building.startBuild(resources2));
		ResourceList resources3 = new ResourceList(
				new Resource(ResourceType.fromString("Wood"), 100),
				new Resource(ResourceType.fromString("Stone"), 100),
				new Resource(ResourceType.fromString("Gold"), 100));
		assertDoesNotThrow(() -> building.startBuild(resources3));
		assertTrue(building.isBuildStarted());
	}

	@Test
	void testStartBuildAlreadyStarted() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		ResourceList resources = new ResourceList(
				new Resource(ResourceType.fromString("Wood"), 100),
				new Resource(ResourceType.fromString("Stone"), 100),
				new Resource(ResourceType.fromString("Gold"), 100));

		assertDoesNotThrow(() -> building.startBuild(resources));
		assertThrows(BuildingAlreadyStartedException.class, () -> building.startBuild(resources));
	}

	@Test
	void testUpdateBuilding() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		ResourceList resources = new ResourceList(
				new Resource(ResourceType.fromString("Wood"), 100),
				new Resource(ResourceType.fromString("Stone"), 100),
				new Resource(ResourceType.fromString("Gold"), 100));

		assertDoesNotThrow(() -> building.startBuild(resources)); // start building
		for (int i = 0; i < type.constructionNeeds.time; i++) {
			assertDoesNotThrow(() -> building.update(resources));
		}
		assertTrue(building.isBuilt()); // building is built
	}

	@Test
	void testUpdateBuildingProduction() {
		BuildingType type = BuildingType.fromString("Lumber Mill");
		Building building = new Building(type);

		ResourceList resourcesForBuild = new ResourceList(
				new Resource(ResourceType.fromString("Wood"), 100),
				new Resource(ResourceType.fromString("Stone"), 100),
				new Resource(ResourceType.fromString("Gold"), 100));

		assertDoesNotThrow(() -> building.startBuild(resourcesForBuild));
		for (int i = 0; i < type.constructionNeeds.time; i++) {
			assertDoesNotThrow(() -> building.update(resourcesForBuild));
		}
		assertTrue(building.isBuilt()); // building is built

		ResourceList resources2 = new ResourceList(new Resource(ResourceType.fromString("Wood"), 5));
		ResourceList resources3 = building.update(resources2);
		assertEquals(resources2, resources3); // no production as there is no worker / inhabitant

		building.addInhabitantToBuilding(100);
		resources3 = building.update(resources2);
		assertEquals(resources2, resources3); // no production as there is no worker

		building.addWorkerToBuilding(100);
		resources3 = building.update(resources2);
		ResourceList shouldBe = new ResourceList(
				new Resource(ResourceType.fromString("Wood"), 1),
				new Resource(ResourceType.fromString("Lumber"), 4));
		assertNotEquals(resources2, resources3); // production
		assertTrue(resources3.contains(shouldBe)); // production and verification of the remaining resources

		ResourceList resources4 = new ResourceList(new Resource(ResourceType.fromString("Wood"), 3));
		ResourceList resources5 = building.update(resources4);
		assertEquals(resources4, resources5); // no production as there is not enough resources
	}

	@Test
	void testAddInhabitantToBuilding() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		building.addInhabitantToBuilding(5);
		assertEquals(5, building.getInhabitants());
	}

	@Test
	void testRemoveInhabitantFromBuilding() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		building.addInhabitantToBuilding(5);
		building.addInhabitantToBuilding(-2);
		assertEquals(3, building.getInhabitants());
	}

	@Test
	void testAddWorkerToBuilding() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		building.addWorkerToBuilding(5);
		assertEquals(5, building.getWorkers());
	}

	@Test
	void testRemoveWorkerFromBuilding() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		building.addWorkerToBuilding(5);
		building.addWorkerToBuilding(-2);
		assertEquals(3, building.getWorkers());
	}

	@Test
	void testToString() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		assertEquals(type.name + ":" + building.id, building.toString());
	}

	@Test
	void testToShortString() {
		BuildingType type = BuildingType.fromString("House");
		Building building = new Building(type);

		assertEquals(type.shortName + ":" + building.id, building.toShortString());
	}
}