package projet.approche.objet;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.entities.building.BuildingList;
import projet.approche.objet.domain.entities.building.BuildingType;
import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;
import projet.approche.objet.domain.valueObject.resource.ResourceType;
import projet.approche.objet.exception.building.BuildingAlreadyStartedException;
import projet.approche.objet.exception.building.NotEnoughNeedsException;

public class Manager {
    public final int inhabitants;

    BuildingList buildings;

    public Manager(){
        this.inhabitants = 0;
        this.buildings = new BuildingList(null);
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public void removeBuilding(Building building) {
        this.buildings.remove(building);
    }

    public void addInhabitanttoBuilding() {
        //given a bulding name, a string maybe, it will search in the BuildingList and increase its inhabitants
        // if it does not exceed the max number of inhabitants
    }

    public void removeInhabitantfromBuilding(Building building) {
    }

}
