package projet.approche.objet.domain.valueObject.resource;

import java.util.Iterator;
import java.util.List;

public class ResourceList implements Iterable<Resource> {

	private final List<Resource> resources;

	public ResourceList(Resource... resources) {
		// TODO: bug ici il faut changer le Resource... en List c'est chiant dans tous
		// les cas
		this.resources = List.of(resources);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ResourceList) {
			ResourceList other = (ResourceList) obj;
			return this.resources.equals(other.resources);
		}
		return false;
	}

	/**
	 * Adds a resource to the list, if the list already have the same resourceType
	 * only add the amount.
	 *
	 * @param resource the resource to add
	 * @return a new ResourceList object with the added resource
	 */
	public ResourceList add(Resource resource) {
		int size = this.resources.size();
		Resource[] copy = new Resource[size + 1];
		int j = 0;
		boolean added = false;
		for (int i = 0; i < size; i++) {
			if (this.resources.get(i).isSameType(resource)) {
				copy[j] = this.resources.get(i).add(resource);
				j++;
				added = true;
			} else {
				copy[j] = this.resources.get(i);
				j++;
			}
		}
		if (!added) {
			copy[j] = resource;
		}
		return new ResourceList(copy);
	}

	/**
	 * Adds a list of resources to the list.
	 *
	 * @param resources the list of resources to add
	 * @return a new ResourceList object with the added resources
	 */
	public ResourceList add(ResourceList resources) {
		ResourceList result = new ResourceList();
		for (Resource resource : this) {
			result = result.add(resource);
		}
		for (Resource resource : resources) {
			result = result.add(resource);
		}
		return result;
	}

	/**
	 * Removes a resource from the list.
	 *
	 * @param toRemove the resource to remove
	 * @return a new ResourceList object with the removed resource
	 */
	public ResourceList remove(Resource toRemove) {
		int size = this.resources.size();
		Resource[] copy = new Resource[size];
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (this.resources.get(i).isSameType(toRemove)) {
				if (this.resources.get(i).isGreaterOrEqual(toRemove)) {
					copy[j] = this.resources.get(i).sub(toRemove);
					j++;
				}
				if (this.resources.get(i).isLess(toRemove)) {
					throw new IllegalArgumentException("Not enough resources");
				}
			} else {
				copy[j] = this.resources.get(i);
				j++;
			}
		}
		return new ResourceList(copy);
	}

	/**
	 * Removes a list of resources from the list.
	 *
	 * @param toRemove the list of resources to remove
	 * @return a new ResourceList object with the removed resources
	 */
	public ResourceList remove(ResourceList toRemove) {
		ResourceList result = new ResourceList();
		for (Resource resource : this) {
			result = result.add(resource);
		}
		for (Resource resource : toRemove) {
			result = result.remove(resource);
		}
		return result;
	}

	/**
	 * Checks if the list contains the resource.
	 *
	 * @param resource the resource to check
	 * @return true if the list contains the resource, false otherwise
	 */
	public boolean contains(Resource resource) {
		return this.resources.contains(resource);
	}

	/**
	 * Checks if the list contains all the resources.
	 *
	 * @param resources the resources to check
	 * @return true if the list contains all the resources, false otherwise
	 */
	public boolean contains(ResourceList resources) {
		return this.resources.containsAll(resources.resources);
	}

	public boolean isEmpty() {
		return this.resources.isEmpty();
	}

	public int size() {
		return this.resources.size();
	}

	public Resource get(int index) {
		return this.resources.get(index);
	}

	public ResourceAmount getAmount(ResourceType type) {
		ResourceAmount result = new ResourceAmount(0);
		for (Resource resource : this.resources) {
			if (resource.type.equals(type)) {
				result = result.add(resource.amount);
			}
		}
		return result;
	}

	public Resource get(ResourceType type) {
		for (Resource resource : this.resources) {
			if (resource.type.equals(type)) {
				return resource;
			}
		}
		return null;
	}

	@Override
	public Iterator<Resource> iterator() {
		return this.resources.iterator();
	}
}
