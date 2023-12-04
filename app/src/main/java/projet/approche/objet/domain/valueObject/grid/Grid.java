package projet.approche.objet.domain.valueObject.grid;

public class Grid {

    private int gridSize;
    private String[][] grid;
    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new String[gridSize][gridSize];
    }

    public void setGrid(int x, int y, String value) {
        this.grid[x][y] = value;
    }
}
