package projet.approche.objet.domain.valueObject.building;

import projet.approche.objet.domain.valueObject.needs.ConstructionNeeds;
import projet.approche.objet.domain.valueObject.needs.Consumption;
import projet.approche.objet.domain.valueObject.needs.Production;
import projet.approche.objet.domain.valueObject.resource.Resource;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.*;

import java.util.List;

public enum BuildingType {
	WOODENCABIN("Wooden Cabin",
			"WC",
			new ConstructionNeeds(2, 1, List.of(new Resource(WOOD, 1))),
			2,
			2,
			new Consumption(1),
			new Production(1, List.of(
					new Resource(WOOD, 2),
					new Resource(FOOD, 2)))),
	HOUSE("House",
			"H",
			new ConstructionNeeds(4, 1,
					List.of(
							new Resource(WOOD, 2),
							new Resource(STONE, 2))),
			0,
			4,
			new Consumption(1),
			new Production(1)),
	APARTMENTBUILDING("Apartment Building",
			"A",
			new ConstructionNeeds(6,
					4, List.of(
							new Resource(WOOD, 50),
							new Resource(STONE, 50))),
			0,
			60,
			new Consumption(1),
			new Production(1)),
	FARM("Farm",
			"F",
			new ConstructionNeeds(2,
					4, List.of(
							new Resource(WOOD, 5),
							new Resource(STONE, 5))),
			3,
			5,
			new Consumption(1),
			new Production(1, List.of(
					new Resource(FOOD, 10)))),
	QUARRY("Quarry",
			"Q",
			new ConstructionNeeds(2,
					4, List.of(
							new Resource(WOOD, 50))),
			30,
			2,
			new Consumption(1),
			new Production(1, List.of(
					new Resource(STONE, 4),
					new Resource(IRON, 1),
					new Resource(COAL, 4),
					new Resource(GOLD, 2)))),
	LUMBERMILL("Lumber Mill",
			"L",
			new ConstructionNeeds(4,
					6, List.of(
							new Resource(WOOD, 50),
							new Resource(STONE, 50))),
			10,
			0,
			new Consumption(1, List.of(
					new Resource(WOOD, 4))),
			new Production(1, List.of(
					new Resource(LUMBER, 4)))),
	CEMENTPLANT("Cement Plant",
			"C",
			new ConstructionNeeds(4,
					6, List.of(
							new Resource(WOOD, 50),
							new Resource(STONE, 50))),
			10,
			0,
			new Consumption(1, List.of(
					new Resource(STONE, 4),
					new Resource(COAL, 4))),
			new Production(1, List.of(
					new Resource(CEMENT, 4)))),
	STEELMILL("Steel Mill",
			"S",
			new ConstructionNeeds(6,
					6, List.of(
							new Resource(WOOD, 100),
							new Resource(STONE, 50))),
			40,
			0,
			new Consumption(1, List.of(
					new Resource(IRON, 4),
					new Resource(COAL, 2))),
			new Production(1, List.of(
					new Resource(STEEL, 4)))),
	TOOLFACTORY("Tool Factory",
			"T",
			new ConstructionNeeds(8,
					8, List.of(
							new Resource(WOOD, 50),
							new Resource(STONE, 50))),
			12,
			0,
			new Consumption(1, List.of(
					new Resource(STEEL, 4),
					new Resource(COAL, 4))),
			new Production(1, List.of(
					new Resource(TOOLS, 4))));

	public final String name;
	public final String shortName;
	public final ConstructionNeeds constructionNeeds; // needs for construction
	public final int workersNeeded; // min number of workers
	public final int workersMax; // max number of workers
	public final int inhabitantsNeeded; // min number of inhabitants
	public final int inhabitantsMax; // max number of inhabitants
	public final Consumption consumption;
	public final Production production;

	private BuildingType(String name, String shortName, ConstructionNeeds constructionNeeds, int workersNeeded,
			int inhabitantsNeeded, Consumption consumption, Production production) {
		this.name = name;
		this.shortName = shortName;
		this.constructionNeeds = constructionNeeds;
		this.workersNeeded = workersNeeded;
		this.workersMax = 2 * workersNeeded;
		this.inhabitantsNeeded = inhabitantsNeeded;
		this.inhabitantsMax = 2 * inhabitantsNeeded;
		this.consumption = consumption;
		this.production = production;
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
