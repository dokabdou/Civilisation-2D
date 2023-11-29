package projet.approche.objet.domain.valueObject.needs;

import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;

/**
 * Needs in time and resources
 */
public class Needs {
	public final ResourceList resources;
	public final int time;

	public Needs(int time, Resource... resources) {
		this.resources = new ResourceList(resources);
		this.time = time;
	}

	public Needs(int time, Resource gold, Resource... resources) {
		var tmp = new ResourceList(resources);
		this.resources = tmp.add(gold);
		this.time = time;
	}

	private Needs(int time, ResourceList resources) {
		this.resources = resources;
		this.time = time;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Needs) {
			Needs needs = (Needs) obj;
			return this.time == needs.time && this.resources.equals(needs.resources);
		}
		return false;
	}

	public Needs add(Needs needs) {
		return new Needs(this.time + needs.time, this.resources.add(needs.resources));
	}

	public Needs sub(Needs needs) {
		return new Needs(this.time - needs.time, this.resources.remove(needs.resources));
	}

	public Needs update() {
		return new Needs(this.time - 1, this.resources);
	}

	public boolean isAffordable(ResourceList resources) {
		int cpt = 0;
		for (Resource resource : resources) {
			for (Resource needs : this.resources) {
				if (resource.type.equals(needs.type)) {
					if (resource.amount.isLess(needs.amount))
						return false;
					else
						cpt++;

				}
			}
		}
		if (cpt == this.resources.size())
			return true;
		return false;
	}

	public ResourceList getMissingResources(ResourceList resources) {
		ResourceList missingResources = new ResourceList();
		for (Resource needs : this.resources) {
			for (Resource resource : resources) {
				if (resource.type.equals(needs.type)) {
					if (resource.amount.isLess(needs.amount))
						missingResources = missingResources
								.add(new Resource(needs.type, needs.amount.sub(resource.amount)));
				}
			}
		}
		return missingResources;
	}

	public ResourceList getRemainingResources(ResourceList resources) {
		ResourceList remainingResources = new ResourceList();
		for (Resource needs : this.resources) {
			for (Resource resource : resources) {
				if (resource.type.equals(needs.type)) {
					if (resource.amount.isGreater(needs.amount))
						remainingResources = remainingResources
								.add(new Resource(needs.type, resource.amount.sub(needs.amount)));
				}
			}
		}
		return remainingResources;
	}
}
