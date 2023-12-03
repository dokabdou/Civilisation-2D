package projet.approche.objet.exception.building;

public class BuildingException extends Exception {

	private static final long serialVersionUID = 1L;

	public BuildingException() {
		this("");
	}

	public BuildingException(String message) {
		super("Building exception: " + message);
	}

}
