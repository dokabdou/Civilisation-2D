package projet.approche.objet.domain.valueObject.resource;

import java.util.Iterator;
import java.util.List;

public class ResourceList implements Iterable<Resource> {

	private final List<Resource> resources;

	public ResourceList(Resource... resources) {
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

	public ResourceList add(Resource resource) {
		int size = this.resources.size();
		Resource[] copy = new Resource[size + 1];
		for (int i = 0; i < size; i++) {
			copy[i] = this.resources.get(i);
		}
		copy[size] = resource;
		return new ResourceList(copy);
	}

	public ResourceList add(ResourceList resources) {
		int size = this.resources.size();
		Resource[] copy = new Resource[size + resources.resources.size()];
		for (int i = 0; i < size; i++) {
			copy[i] = this.resources.get(i);
		}
		for (int i = 0; i < resources.resources.size(); i++) {
			copy[size + i] = resources.resources.get(i);
		}
		return new ResourceList(copy);
	}

	public ResourceList remove(Resource resource) {
		int size = this.resources.size();
		Resource[] copy = new Resource[size - 1];
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (!this.resources.get(i).equals(resource)) {
				copy[j] = this.resources.get(i);
				j++;
			}
		}
		return new ResourceList(copy);
	}

	public ResourceList remove(ResourceList resources) {
		int size = this.resources.size();
		Resource[] copy = new Resource[size - resources.resources.size()];
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (!resources.contains(this.resources.get(i))) {
				copy[j] = this.resources.get(i);
				j++;
			}
		}
		return new ResourceList(copy);
	}

	public boolean contains(Resource resource) {
		return this.resources.contains(resource);
	}

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

	@Override
	public Iterator<Resource> iterator() {
		return this.resources.iterator();
	}
}
