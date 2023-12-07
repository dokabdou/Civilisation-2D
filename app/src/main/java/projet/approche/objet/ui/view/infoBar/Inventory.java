package projet.approche.objet.ui.view.infoBar;

import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import projet.approche.objet.application.App;
import projet.approche.objet.ui.view.Updateable;
import projet.approche.objet.ui.view.imageResource.ResourceImageResource;

public class Inventory extends VBox implements Updateable {
	private final App app;
	private final Text gold = new Text();
	private final Text food = new Text();
	private final Text wood = new Text();
	private final Text stone = new Text();
	private final Text coal = new Text();
	private final Text iron = new Text();
	private final Text steel = new Text();
	private final Text cement = new Text();
	private final Text lumber = new Text();
	private final Text tools = new Text();
	private final Text person = new Text();
	private final Text worker = new Text();

	public Inventory(App app) {
		this.app = app;
		this.setSpacing(5);
		this.setPadding(new Insets(5));

		HBox goldGroup = infoGroup("Gold", this.gold);
		HBox foodGroup = infoGroup("Food", this.food);
		HBox woodGroup = infoGroup("Wood", this.wood);
		HBox stoneGroup = infoGroup("Stone", this.stone);
		HBox coalGroup = infoGroup("Coal", this.coal);
		HBox ironGroup = infoGroup("Iron", this.iron);
		HBox steelGroup = infoGroup("Steel", this.steel);
		HBox cementGroup = infoGroup("Cement", this.cement);
		HBox lumberGroup = infoGroup("Lumber", this.lumber);

		HBox toolsGroup = infoGroup("Tools", this.tools);
		HBox inhabitantsGroup = infoGroup("Inhabitants", this.person);
		HBox workersGroup = infoGroup("Workers", this.worker);

		getChildren().addAll(goldGroup, foodGroup, woodGroup, stoneGroup, coalGroup, ironGroup, steelGroup, cementGroup,
				lumberGroup, toolsGroup, inhabitantsGroup, workersGroup);
	}

	private HBox infoGroup(String kind, Text number) {
		HBox group = new HBox();
		ImageView img = new ImageView(ResourceImageResource.get(kind));
		Tooltip tooltip = new Tooltip(kind);
		Tooltip.install(img, tooltip);
		group.setSpacing(4);
		number.setCache(true);
		number.setFill(Color.BLACK);
		number.getStyleClass().add("number");
		group.getChildren().addAll(img, number);
		return group;
	}

	public void update() {
		gold.setText(Integer.toString(this.app.getResourceQuantity("GOLD")));
		food.setText(Integer.toString(this.app.getResourceQuantity("FOOD")));
		wood.setText(Integer.toString(this.app.getResourceQuantity("WOOD")));
		stone.setText(Integer.toString(this.app.getResourceQuantity("STONE")));
		coal.setText(Integer.toString(this.app.getResourceQuantity("COAL")));
		iron.setText(Integer.toString(this.app.getResourceQuantity("IRON")));
		steel.setText(Integer.toString(this.app.getResourceQuantity("STEEL")));
		cement.setText(Integer.toString(this.app.getResourceQuantity("CEMENT")));
		lumber.setText(Integer.toString(this.app.getResourceQuantity("LUMBER")));
		tools.setText(Integer.toString(this.app.getResourceQuantity("TOOLS")));
		person.setText(Integer.toString(this.app.getInhabitantsNumber()));
		worker.setText(Integer.toString(this.app.getWorkersNumber()));
	}
}
