package projet.approche.objet.domain.entities.building;

import projet.approche.objet.domain.valueObject.Needs;
import projet.approche.objet.domain.valueObject.resource.ResourceList;
import projet.approche.objet.exception.building.BuildingAlreadyStartedException;
import projet.approche.objet.exception.building.NotEnoughNeedsException;

public class Building implements BuildingItf {

	private static Long count = Long.valueOf(0);
	public final Long id = ++count;

	private ResourceList inventory = new ResourceList();
	public final BuildingType type;
	private boolean buildStarted;
	private boolean isBuilt;
	private int timeBuilt;

	public Building(BuildingType buildingType) {
		this.type = buildingType;
	}

	public boolean isBuildStarted() {
		return buildStarted;
	}

	public boolean isBuilt() {
		return isBuilt;
	}

	public void update() {
		if (buildStarted && !isBuilt) {
			timeBuilt++;
			if (timeBuilt >= type.constructionNeeds.getCost().time) {
				isBuilt = true;
			}
		}
	}

	public ResourceList startBuild(ResourceList resources)
			throws NotEnoughNeedsException, BuildingAlreadyStartedException {
		if (this.buildStarted)
			throw new BuildingAlreadyStartedException("building of " + this + " already started");
		if (this.type.constructionNeeds.getCost().isAffordable(resources)) {
			this.buildStarted = true;
			return this.type.constructionNeeds.getCost().getRemainingResources(resources);
		}
		throw new NotEnoughNeedsException("to build " + this);
	}

	public void addInhabitanttoBuilding(int inhabitantsAdd){
		//this.type.inhabitants = this.type.inhabitants + inhabitantsAdd;
	}

	public void removeInhabitanttoBuilding(int inhabitantsRem){
		//this.type.inhabitants = this.type.inhabitants + inhabitantsRem;
	}

	public Needs getCostToBuild() {
		return this.type.constructionNeeds.getCost();
	}

	public int getTimeToBuild() {
		return this.type.constructionNeeds.getCost().time;
	}

	public String toString() {
		return this.type.name + ":" + this.id;
	}

	public String toShortString() {
		return this.type.shortName + ":" + this.id;
	}
}
