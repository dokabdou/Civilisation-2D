package projet.approche.objet;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.entities.building.BuildingList;

public class Manager {
	public final int inhabitants; // initial number of inhabitants

	BuildingList buildings;

	public Manager() {
		this.inhabitants = 0;
		this.buildings = new BuildingList();
	}

	public void addBuilding(Building building) {
		this.buildings.add(building);
	}

	public void removeBuilding(Building building) {
		this.buildings.remove(building);
	}

	public void addInhabitantToBuilding() {
		// given a bulding name, a string maybe, it will search in the BuildingList and
		// increase its inhabitants
		// if it does not exceed the max number of inhabitants

	}

	public void removeInhabitantFromBuilding(Building building) {
	}

}
