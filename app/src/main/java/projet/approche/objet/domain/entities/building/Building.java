package projet.approche.objet.domain.entities.building;

import projet.approche.objet.domain.valueObject.building.BuildingType;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.*;
import projet.approche.objet.domain.valueObject.building.exceptions.BuildingAlreadyStartedException;
import projet.approche.objet.domain.valueObject.building.exceptions.NotEnoughNeedsException;
import projet.approche.objet.domain.valueObject.needs.ConstructionNeeds;
import projet.approche.objet.domain.valueObject.needs.Needs;
import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;

import java.util.List;
public class Building implements BuildingItf {

	public final long id;

	public final BuildingType type;
	private boolean buildStarted;
	private boolean isBuilt;
	private int time = 0; // time since last production / time since construction started
	private int inhabitants = 0; // current number of inhabitants in the building
	private int workers = 0; // current number of workers in the building
	private int level = 1; // current level of the building | levels are 1, 2, 3

	public int getInhabitants() {
		return inhabitants;
	}

	public int getWorkers() {
		return workers;
	}

	public int getLevel() {
		return level;
	}

	public Building(BuildingType buildingType, long id) {
		this.type = buildingType;
		this.id = id;
	}

	public boolean isBuildStarted() {
		return buildStarted;
	}

	public boolean isBuilt() {
		return isBuilt;
	}

	/**
	 * Updates the building. If the building is built, it will produce and consume
	 * resources if possible. If the building is not built but the construction
	 * started, it will continue the construction. If the building is not built and
	 * the construction did not start, nothing is done.
	 * 
	 * @param inventory the resources available to produce and consume
	 * @return the remaining resources after the production and consumption
	 */
	public ResourceList update(ResourceList inventory) {
		if (isBuilt) { // if the building is built verify if it can produce
			if (this.workers >= this.type.workersNeeded && this.inhabitants >= this.type.inhabitantsNeeded) {
				time++;
				if (time >= this.type.production.time && time >= this.type.consumption.time) { // even if it should be
																								// the same value
					if (this.type.consumption.isAffordable(inventory)) { // verify if the building have enough resources
						inventory = this.type.consumption.getRemainingResources(inventory); // consume resources from
																							// inventory
						inventory = this.type.production.havestProduction(inventory); // produce resources in inventory
						this.time = 0; // reset the time since last production
						return inventory;
					}
				}
			}
		} else if (buildStarted) { // if the building is not built but the construction started
			time++;
			if (time >= type.constructionNeeds.time) {
				isBuilt = true;
			}
		}
		// else the building is not built and the construction did not start so nothing
		// is done
		return inventory;
	}

	/**
	 * Starts the construction of the building.
	 * 
	 * @param resources the resources available to start the construction
	 * @return the remaining resources after the construction started
	 * @throws NotEnoughNeedsException         if the resources are not enough to
	 *                                         start the
	 *                                         construction
	 * @throws BuildingAlreadyStartedException if the building is already started
	 */
	public ResourceList startBuild(ResourceList resources)
			throws NotEnoughNeedsException, BuildingAlreadyStartedException {
		if (this.buildStarted)
			throw new BuildingAlreadyStartedException("building of " + this + " already started");
		if (this.type.constructionNeeds.isAffordable(resources)) {
			this.buildStarted = true;
			return this.type.constructionNeeds.getRemainingResources(resources);
		}
		throw new NotEnoughNeedsException("to build " + this);
	}

	public void addInhabitantToBuilding(int inhabitantsAdd) {
		this.inhabitants += inhabitantsAdd;
	}

	public void addWorkerToBuilding(int workersAdd) {
		this.workers += workersAdd;
	}

	public Needs getCostToBuild() {
		return this.type.constructionNeeds;
	}

	public int getTimeToBuild() {
		return this.type.constructionNeeds.time;
	}

	public boolean canUpgrade(ResourceList inventory) {

		if (this.level < 3) {
			// 
			ResourceList missingResources = this.type.constructionNeeds.getMissingResources(inventory);
			// technically an upgrade consumes resources so use getRemainingResources
			//turn into a list 
			List<Resource> constructionNeeds = this.type.constructionNeeds.resources.getResources();
			 
			ConstructionNeeds needsForUpgrade = new ConstructionNeeds(this.type.constructionNeeds.time * this.level,
					this.type.constructionNeeds.goldAmountForConstruction * this.level, 
					constructionNeeds
					);
			if (this.type.constructionNeeds.isAffordable(inventory)) {
				this.level++;
				return true;
			}
		}
		return true;
	}

	public String toString() {
		return this.type.name + ":" + this.id;
	}

	public String toShortString() {
		return this.type.shortName + ":" + this.id;
	}
}
