package projet.approche.objet.domain.valueObject.needs;

import static projet.approche.objet.domain.valueObject.resource.ResourceType.GOLD;

import java.util.List;

import projet.approche.objet.domain.valueObject.resource.Resource;

public class ConstructionNeeds extends Needs {
	public ConstructionNeeds(int time, int goldAmount, List<Resource> resources) {
		super(time, new Resource(GOLD, goldAmount), resources);
	}
}
