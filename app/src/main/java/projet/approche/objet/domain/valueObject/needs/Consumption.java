package projet.approche.objet.domain.valueObject.needs;

import projet.approche.objet.domain.valueObject.resource.Resource;

public class Consumption extends Needs {
	public Consumption(int time, Resource... resources) {
		super(time, resources);
	}
}
