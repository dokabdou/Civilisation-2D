package projet.approche.objet.domain.valueObject.building;

import projet.approche.objet.domain.valueObject.needs.ConstructionNeeds;
import projet.approche.objet.domain.valueObject.needs.Consumption;
import projet.approche.objet.domain.valueObject.needs.Production;
import projet.approche.objet.domain.valueObject.resource.Resource;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.*;

public enum BuildingType {
	WOODENCABIN("Wooden Cabin",
			"WC",
			new ConstructionNeeds(2,
					new Resource(GOLD, 1),
					new Resource(WOOD, 1)),
			2,
			2,
			new Consumption(1), // needs for production
			new Production(1, // prfoduction
					new Resource(WOOD, 2),
					new Resource(FOOD, 2)),
			1),
	HOUSE("House",
			"H",
			new ConstructionNeeds(4,
					new Resource(GOLD, 1),
					new Resource(WOOD, 2),
					new Resource(STONE, 2)),
			0,
			4,
			new Consumption(1), // needs for production
			new Production(1), // plroduction
			2),
	APARTMENTBUILDING("Apartment Building",
			"AB",
			new ConstructionNeeds(6,
					new Resource(GOLD, 4),
					new Resource(WOOD, 50),
					new Resource(STONE, 50)),
			0,
			60,
			new Consumption(1), // needs for production
			new Production(1), // production
			4),
	FARM("Farm",
			"F",
			new ConstructionNeeds(2,
					new Resource(GOLD, 4),
					new Resource(WOOD, 5),
					new Resource(STONE, 5)),
			3,
			5,
			new Consumption(1), // needs for production
			new Production(1, // production
					new Resource(FOOD, 10)),
			3),
	QUARRY("Quarry",
			"Q",
			new ConstructionNeeds(2,
					new Resource(GOLD, 4),
					new Resource(WOOD, 50)),
			30,
			2,
			new Consumption(1), // needs for production
			new Production(1, // production
					new Resource(STONE, 4),
					new Resource(IRON, 1),
					new Resource(COAL, 4),
					new Resource(GOLD, 2)),
			4),
	LUMBERMILL("Lumber Mill",
			"LM",
			new ConstructionNeeds(4,
					new Resource(GOLD, 6),
					new Resource(WOOD, 50),
					new Resource(STONE, 50)),
			10,
			0,
			new Consumption(1, // needs for production
					new Resource(WOOD, 4)),
			new Production(1, // production
					new Resource(LUMBER, 4)),
			4),
	CEMENTPLANT("Cement Plant",
			"CP",
			new ConstructionNeeds(4,
					new Resource(GOLD, 6),
					new Resource(WOOD, 50),
					new Resource(STONE, 50)),
			10,
			0,
			new Consumption(1, // needs for production
					new Resource(STONE, 4),
					new Resource(COAL, 4)),
			new Production(1, // production
					new Resource(CEMENT, 4)),
			4),
	STEELMILL("Steel Mill",
			"SM",
			new ConstructionNeeds(6,
					new Resource(GOLD, 6),
					new Resource(WOOD, 100),
					new Resource(STONE, 50)),
			40,
			0,
			new Consumption(1, // needs for production
					new Resource(IRON, 4),
					new Resource(COAL, 2)),
			new Production(1, // production
					new Resource(STEEL, 4)),
			5),
	TOOLFACTORY("Tool Factory",
			"TF",
			new ConstructionNeeds(8,
					new Resource(GOLD, 8),
					new Resource(WOOD, 50),
					new Resource(STONE, 50)),
			12,
			0,
			new Consumption(1, // needs for production
					new Resource(STEEL, 4),
					new Resource(COAL, 4)),
			new Production(1, // production
					new Resource(TOOLS, 4)),
			4);

	public final String name;
	public final String shortName;
	public final ConstructionNeeds constructionNeeds; // needs for construction
	public final int workersNeeded; // min number of workers
	public final int workersMax; // max number of workers
	public final int inhabitantsNeeded; // min number of inhabitants
	public final int inhabitantsMax; // max number of inhabitants
	public final Consumption consumption; // needs for production
	public final Production production; // prloduction
	public final int buildingSize; // size of building on grid

	private BuildingType(String name, String shortName, ConstructionNeeds constructionNeeds, int workersNeeded,
			int inhabitantsNeeded, Consumption consumption, Production production, int buildingSize) {
		this.name = name;
		this.shortName = shortName;
		this.constructionNeeds = constructionNeeds;
		this.workersNeeded = workersNeeded;
		this.workersMax = 2 * workersNeeded;
		this.inhabitantsNeeded = inhabitantsNeeded;
		this.inhabitantsMax = 2 * inhabitantsNeeded;
		this.consumption = consumption;
		this.production = production;
		this.buildingSize = buildingSize;
	}

	public static BuildingType fromString(String name) {
		for (BuildingType type : BuildingType.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return null;
	}
}
