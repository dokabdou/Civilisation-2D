package projet.approche.objet.domain.valueObject;

import projet.approche.objet.domain.valueObject.resource.Resource;

public class ConstructionNeeds extends Needs{
	public ConstructionNeeds(int time, Resource gold, Resource... resources) {
		super(time, gold, resources);
	}	
}
