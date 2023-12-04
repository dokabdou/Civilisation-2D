package projet.approche.objet.domain.valueObject.resource;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ResourceListTest {

	@Test
	void testConstructor() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList = new ResourceList(gold);
		assertEquals(1, resourceList.size());
		assertTrue(resourceList.contains(gold));
	}

	@Test
	void testEquals() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList1 = new ResourceList(gold);
		ResourceList resourceList2 = new ResourceList(gold);
		assertTrue(resourceList1.equals(resourceList2));
	}

	@Test
	void testAddResource() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList = new ResourceList();
		ResourceList result = resourceList.add(gold);
		assertEquals(1, result.size());
		assertTrue(result.contains(gold));
	}

	@Test
	void testAddSameResource() {
		Resource gold10 = new Resource(ResourceType.fromString("Gold"), 10);
		Resource gold20 = new Resource(ResourceType.fromString("Gold"), 20);
		ResourceList resourceList = new ResourceList(gold10);
		ResourceList result = resourceList.add(gold20);
		assertEquals(1, result.size());
		assertTrue(result.contains(new Resource(ResourceType.fromString("Gold"), 30)));
	}

	@Test
	void testAddResourceList() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList1 = new ResourceList(gold);
		ResourceList resourceList2 = new ResourceList();
		ResourceList result = resourceList2.add(resourceList1);
		assertEquals(1, result.size());
		assertTrue(result.contains(gold));
	}

	@Test
	void testRemoveResource() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList = new ResourceList(gold);
		ResourceList result = resourceList.remove(gold);
		assertEquals(1, result.size());
		assertFalse(result.contains(gold));
		assertEquals(0, result.get(0).amount.value);
	}

	@Test
	void testRemoveResourceNotEnough() {
		Resource gold10 = new Resource(ResourceType.fromString("Gold"), 10);
		Resource gold20 = new Resource(ResourceType.fromString("Gold"), 20);
		ResourceList resourceList = new ResourceList(gold20);
		ResourceList result = resourceList.remove(gold10);
		assertEquals(1, result.size());
		assertTrue(result.contains(gold10));
	}

	@Test
	void testRemoveResourceNotSameType() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		Resource wood = new Resource(ResourceType.fromString("Wood"), 10);
		ResourceList resourceList = new ResourceList(gold);
		ResourceList result = resourceList.remove(wood);
		assertEquals(1, result.size());
		assertTrue(result.contains(gold));
	}

	@Test
	void testRemoveResourceEnough() {
		Resource gold10 = new Resource(ResourceType.fromString("Gold"), 10);
		Resource gold20 = new Resource(ResourceType.fromString("Gold"), 20);
		ResourceList resourceList = new ResourceList(gold20);
		ResourceList result = resourceList.remove(gold10);
		assertEquals(1, result.size());
		assertTrue(result.contains(new Resource(ResourceType.fromString("Gold"), 10)));
	}

	@Test
	void testRemoveResourceList() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList1 = new ResourceList(gold);
		ResourceList resourceList2 = new ResourceList(gold);
		ResourceList result = resourceList2.remove(resourceList1);
		assertEquals(1, result.size());
		assertFalse(result.contains(gold));
		assertEquals(0, result.get(0).amount.value);
	}

	@Test
	void testContainsResource() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList = new ResourceList(gold);
		assertTrue(resourceList.contains(gold));
	}

	@Test
	void testContainsResourceList() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList1 = new ResourceList(gold);
		ResourceList resourceList2 = new ResourceList(gold);
		assertTrue(resourceList2.contains(resourceList1));
	}

	@Test
	void testIsEmpty() {
		ResourceList resourceList = new ResourceList();
		assertTrue(resourceList.isEmpty());
	}

	@Test
	void testSize() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList = new ResourceList(gold);
		assertEquals(1, resourceList.size());
	}

	@Test
	void testGet() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList = new ResourceList(gold);
		assertEquals(gold, resourceList.get(0));
	}

	@Test
	void testIterator() {
		Resource gold = new Resource(ResourceType.fromString("Gold"), 10);
		ResourceList resourceList = new ResourceList(gold);
		Iterator<Resource> iterator = resourceList.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(gold, iterator.next());
	}
}