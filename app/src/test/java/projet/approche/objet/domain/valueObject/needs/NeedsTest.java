package projet.approche.objet.domain.valueObject.needs;

import org.junit.jupiter.api.Test;

import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;
import projet.approche.objet.domain.valueObject.resource.ResourceType;

import static org.junit.jupiter.api.Assertions.*;

class NeedsTest {
	@Test
	void testConstructor() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		Needs needs = new Needs(5, new Resource[] { gold });
		assertEquals(5, needs.time);
		assertTrue(needs.resources.contains(gold));
	}

	@Test
	void testEquals() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		Needs needs1 = new Needs(5, new Resource[] { gold });
		Needs needs2 = new Needs(5, new Resource[] { gold });

		assertTrue(needs1.equals(needs2));
	}

	@Test
	void testAdd() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		Needs needs1 = new Needs(5, new Resource[] { gold });
		Needs needs2 = new Needs(5, new Resource[] { gold });

		Needs result = needs1.add(needs2);

		assertEquals(10, result.time);
		assertTrue(result.resources.contains(new Resource(ResourceType.fromString("Gold"), 10)));
	}

	@Test
	void testSub() {
		Resource gold10 = new Resource(ResourceType.fromString("Gold"), 10);
		Resource gold20 = new Resource(ResourceType.fromString("Gold"), 20);
		Needs needs1 = new Needs(10, new Resource[] { gold20 });
		Needs needs2 = new Needs(5, new Resource[] { gold10 });

		Needs result = needs1.sub(needs2);

		assertEquals(5, result.time);
		assertTrue(result.resources.contains(new Resource(ResourceType.fromString("Gold"), 10)));
	}

	@Test
	void testIsAffordable() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		Needs needs = new Needs(5, new Resource[] { gold });
		ResourceList resources = new ResourceList(new Resource(ResourceType.fromString("Gold"), 20));

		assertTrue(needs.isAffordable(resources));
	}

	@Test
	void testGetMissingResources() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 20);
		Needs needs = new Needs(5, new Resource[] { gold });
		ResourceList resources = new ResourceList(new Resource(ResourceType.fromString("Gold"), 10));

		ResourceList result = needs.getMissingResources(resources);

		assertTrue(result.contains(new Resource(ResourceType.fromString("Gold"), 10)));
	}

	@Test
	void testGetRemainingResources() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		Needs needs = new Needs(5, new Resource[] { gold });
		ResourceList resources = new ResourceList(new Resource(ResourceType.fromString("Gold"), 20));

		ResourceList result = needs.getRemainingResources(resources);

		assertTrue(result.contains(new Resource(ResourceType.fromString("Gold"), 10)));
	}
}