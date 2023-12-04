package projet.approche.objet.domain.valueObject.grid;

import java.util.Map;

public class Grid {

	private final int gridSize;
	private char[][] grid;
	private Map<Character, Coordinate> buildings;

	public Grid(int gridSize) {
		this.gridSize = gridSize;
		this.grid = new char[gridSize][gridSize];
	}

	public void setGrid(int x, int y, char value) {
		this.grid[x][y] = value;
	}
}
