package projet.approche.objet.domain.valueObject.game;

import static projet.approche.objet.domain.valueObject.building.BuildingType.*;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.*;

import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;

public enum GameStarter {

	EASY(10, 6,
			new ResourceList(new Resource(GOLD, 15), new Resource(WOOD, 15), new Resource(FOOD, 15)),
			WOODENCABIN, WOODENCABIN, HOUSE),
	NORMAL(6, 2,
			new ResourceList(new Resource(GOLD, 10), new Resource(WOOD, 10), new Resource(FOOD, 10)),
			WOODENCABIN, HOUSE),
	HARD(2, 2,
			new ResourceList(new Resource(GOLD, 5), new Resource(WOOD, 5), new Resource(FOOD, 5)),
			WOODENCABIN);

	public final int inhabitants;
	public final int workers;
	public final ResourceList startingResources;
	public final BuildingType[] startingBuildings;

	GameStarter(int inhabitants, int workers, ResourceList startingResources, BuildingType... startingBuildings) {
		this.inhabitants = inhabitants;
		this.workers = workers;
		this.startingResources = startingResources;
		this.startingBuildings = startingBuildings;
	}

}
