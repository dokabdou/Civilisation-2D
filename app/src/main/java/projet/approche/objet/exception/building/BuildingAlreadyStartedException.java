package projet.approche.objet.exception.building;

public class BuildingAlreadyStartedException extends BuildingException {

	private static final long serialVersionUID = 1L;

	public BuildingAlreadyStartedException() {
		super();
	}

	public BuildingAlreadyStartedException(String message) {
		super(message);
	}

}
