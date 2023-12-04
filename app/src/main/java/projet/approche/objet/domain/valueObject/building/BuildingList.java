package projet.approche.objet.domain.valueObject.building;

import java.util.Iterator;
import java.util.List;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.resource.ResourceAmount;

public class BuildingList implements Iterable<Building> {

	private final List<Building> buildings;

	public BuildingList(Building... buildings) {
		this.buildings = List.of(buildings);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BuildingList) {
			BuildingList other = (BuildingList) obj;
			return this.buildings.equals(other.buildings);
		}
		return false;
	}

	/**
	 * Adds a building to the list.
	 *
	 * @param building the building to add
	 * @return a new BuildingList object with the added building
	 */
	public BuildingList add(Building building) {
		int size = this.buildings.size();
		Building[] copy = new Building[size + 1];
		for (int i = 0; i < size; i++) {
			copy[i] = this.buildings.get(i);
		}
		copy[size] = building;
		return new BuildingList(copy);
	}

	/**
	 * Adds a list of buildings to the list.
	 *
	 * @param buildings the list of buildings to add
	 * @return a new BuildingList object with the added buildings
	 */
	public BuildingList add(BuildingList buildings) {
		int size = this.buildings.size();
		Building[] copy = new Building[size + buildings.buildings.size()];
		for (int i = 0; i < size; i++) {
			copy[i] = this.buildings.get(i);
		}
		for (int i = 0; i < buildings.buildings.size(); i++) {
			copy[size + i] = buildings.buildings.get(i);
		}
		return new BuildingList(copy);
	}

	/**
	 * Removes a building from the list.
	 *
	 * @param building the building to remove
	 * @return a new BuildingList object without the removed building
	 */
	public BuildingList remove(Building building) {
		int size = this.buildings.size();
		Building[] copy = new Building[size - 1];
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (!this.buildings.get(i).equals(building)) {
				copy[j] = this.buildings.get(i);
				j++;
			}
		}
		return new BuildingList(copy);
	}

	/**
	 * Removes a list of buildings from the list.
	 *
	 * @param buildings the list of buildings to remove
	 * @return a new BuildingList object without the removed buildings
	 */
	public BuildingList remove(BuildingList buildings) {
		int size = this.buildings.size();
		Building[] copy = new Building[size - buildings.buildings.size()];
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (!buildings.contains(this.buildings.get(i))) {
				copy[j] = this.buildings.get(i);
				j++;
			}
		}
		return new BuildingList(copy);
	}

	public ResourceAmount foodConsumption() {
		// minimum food needed for the next day
		ResourceAmount foodConsumption = new ResourceAmount(0);
		for(Building building : this.buildings) {
			if(building.getInhabitants() + building.getWorkers() > 0) {
				// assume that each inhabitant/worker consumes 1 food per turn
				foodConsumption = foodConsumption.add(new ResourceAmount(1*(building.getInhabitants() + building.getWorkers())));
			}
		}
		return foodConsumption;
	}

	public boolean contains(Building building) {
		return this.buildings.contains(building);
	}

	public boolean contains(BuildingList buildings) {
		return this.buildings.containsAll(buildings.buildings);
	}

	public boolean isEmpty() {
		return this.buildings.isEmpty();
	}

	public int size() {
		return this.buildings.size();
	}

	public Building get(int index) {
		return this.buildings.get(index);
	}

	@Override
	public Iterator<Building> iterator() {
		return this.buildings.iterator();
	}

}
