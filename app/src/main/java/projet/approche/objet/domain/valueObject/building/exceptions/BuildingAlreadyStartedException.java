package projet.approche.objet.domain.valueObject.building.exceptions;

public class BuildingAlreadyStartedException extends BuildingException {

	private static final long serialVersionUID = 1L;

	public BuildingAlreadyStartedException() {
		super();
	}

	public BuildingAlreadyStartedException(String message) {
		super(message);
	}

}
