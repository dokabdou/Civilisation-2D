package projet.approche.objet.domain.valueObject.needs;

import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;

public class Production extends Needs {
	public Production(int time, Resource... resources) {
		super(time, resources);
	}

	public ResourceList getProduction() {
		return this.resources;
	}

	public ResourceList getProduction(ResourceList toAdd) {
		return this.resources.add(toAdd);
	}
}
