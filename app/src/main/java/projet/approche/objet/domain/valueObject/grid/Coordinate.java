package projet.approche.objet.domain.valueObject.grid;

public class Coordinate {
	public final int xStart;
	public final int yStart;
	public final int xEnd;
	public final int yEnd;

	public Coordinate(int xStart, int yStart, int xEnd, int yEnd) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}

	public boolean isInside(int x, int y) {
		return x >= xStart && x <= xEnd && y >= yStart && y <= yEnd;
	}

	public boolean isInside(Coordinate c) {
		return isInside(c.xStart, c.yStart) && isInside(c.xEnd, c.yEnd);
	}

	public boolean isOverlapping(Coordinate c) {
		return isInside(c.xStart, c.yStart) || isInside(c.xEnd, c.yEnd);
	}

}
