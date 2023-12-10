package projet.approche.objet.domain.valueObject.game;

import static projet.approche.objet.domain.valueObject.building.BuildingType.*;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.grid.Coordinate;
import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;

public enum PremadeLevel {

	EASY(10, 10, 6,
			new ResourceList(List.of(
					new Resource(GOLD, 15),
					new Resource(WOOD, 15),
					new Resource(FOOD, 150))),
			List.of(
					new SimpleEntry<>(new Coordinate(0, 0), WOODENCABIN),
					new SimpleEntry<>(new Coordinate(1, 0), HOUSE))),
	NORMAL(10, 6, 2,
			new ResourceList(List.of(
					new Resource(GOLD, 10),
					new Resource(WOOD, 10),
					new Resource(FOOD, 100))),
			List.of(
					new SimpleEntry<>(new Coordinate(0, 0), WOODENCABIN),
					new SimpleEntry<>(new Coordinate(1, 0), HOUSE))),
	HARD(10, 2, 2,
			new ResourceList(List.of(
					new Resource(GOLD, 5),
					new Resource(WOOD, 5),
					new Resource(FOOD, 50))),
			List.of(new SimpleEntry<>(new Coordinate(0, 0), WOODENCABIN)));

	public final int inhabitants;
	public final int workers;
	public final ResourceList startingResources;
	public final int gridSize;
	public final Map<Coordinate, BuildingType> startingBuildings;

	PremadeLevel(int gridSize, int inhabitants, int workers, ResourceList startingResources,
			List<SimpleEntry<Coordinate, BuildingType>> entries) {
		this.gridSize = gridSize;
		this.inhabitants = inhabitants;
		this.workers = workers;
		this.startingResources = startingResources;
		var tmp = new HashMap<Coordinate, BuildingType>();
		for (Entry<Coordinate, BuildingType> entry : entries) {
			tmp.put(entry.getKey(), entry.getValue());
		}
		this.startingBuildings = tmp;
	}

}