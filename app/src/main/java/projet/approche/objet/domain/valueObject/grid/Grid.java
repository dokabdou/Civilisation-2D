package projet.approche.objet.domain.valueObject.grid;

import java.util.Map;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotFreeException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotInGridException;

public class Grid {

	public final int gridSize;
	private Map<Coordinate, Building> buildings;

	public Grid(int gridSize) {
		this.gridSize = gridSize;
		this.buildings = new java.util.HashMap<>();
	}

	public boolean isInGrid(Coordinate c) {
		return c.x >= 0 && c.x < gridSize && c.y >= 0 && c.y < gridSize;
	}

	public boolean isFree(Coordinate c) {
		return isInGrid(c) && !buildings.containsKey(c);
	}

	public void setBuilding(Building b, Coordinate c) throws NotInGridException, NotFreeException {
		if (!isInGrid(c))
			throw new NotInGridException("The coordinate " + c + " is not in the grid");
		if (!isFree(c))
			throw new NotFreeException("The coordinate " + c + " is not free");
		buildings.put(c, b);
	}

	public Building getBuilding(Coordinate c) {
		return buildings.get(c);
	}

	public void removeBuilding(Coordinate c) {
		buildings.remove(c);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < gridSize; i++) {
			sb.append("|");
			for (int j = 0; j < gridSize; j++) {
				Coordinate c = new Coordinate(i, j);
				if (buildings.containsKey(c)) {
					sb.append(buildings.get(c).type.shortName);
				} else {
					sb.append(" ");
				}
				sb.append("|");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
