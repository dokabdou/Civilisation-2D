package projet.approche.objet.domain.valueObject;

public class ConstructionNeeds {

	private final Needs cost; // cost in resources and time

	public ConstructionNeeds(Needs costToBuild, boolean buildStarted, boolean isBuilt) {
		this.cost = costToBuild;
	}

	public ConstructionNeeds(Needs costToBuild) {
		this(costToBuild, false, false);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConstructionNeeds) {
			ConstructionNeeds constructionTime = (ConstructionNeeds) obj;
			return this.cost.time == constructionTime.cost.time
					&& this.cost.resources.equals(constructionTime.cost.resources);
		}
		return false;
	}

	public Needs getCost() {
		return cost;
	}
}
