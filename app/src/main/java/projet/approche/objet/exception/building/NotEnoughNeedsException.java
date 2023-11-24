package projet.approche.objet.exception.building;

public class NotEnoughNeedsException extends BuildingException {

	private static final long serialVersionUID = 1L;

	public NotEnoughNeedsException() {
		this("");
	}

	public NotEnoughNeedsException(String message) {
		super("not enough needs " + message);
	}

}
