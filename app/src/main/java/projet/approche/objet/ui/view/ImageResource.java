package projet.approche.objet.ui.view;

import javafx.scene.image.Image;
import projet.approche.objet.domain.valueObject.building.BuildingType;

public enum ImageResource {

	APARTMENTBUILDING("ApartmentBuilding.png"),
	HOUSE("House.png"),
	CEMENTPLANT("CementPlnat.png"),
	FARM("Farm.png"),
	LUMBERMILL("LumberMill.png"),
	QUARRY("Quarry.png"),
	STEELMILL("SteelMill.png"),
	TOOLFACTORY("ToolFactory.png"),
	WOODENCABIN("WoodenCabin.png");

	private final Image image;

	public static final int size = 1024;

	ImageResource(String file) {
		try {
			this.image = new Image(ImageResource.class.getResourceAsStream("/assets/" + file));
			if (image.getWidth() != size && image.getHeight() != size) {
				String msg = "File " + file + " does not have the correct size " + image.getWidth() + " x "
						+ image.getHeight();
				throw new RuntimeException(msg);
			}
		} catch (NullPointerException e) {
			System.err.println("Resource not found : " + file);
			throw e;
		}
	}

	public Image getImage() {
		return image;
	}

	public static Image get(BuildingType kind) {
		if (kind != null)
			return ImageResource.valueOf(kind.toString()).image;
		return null;
	}

}
