package projet.approche.objet.ui.view.infoBar;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import projet.approche.objet.application.App;
import projet.approche.objet.ui.view.GameView;
import projet.approche.objet.ui.view.Updateable;

public class InfoBar extends BorderPane implements Updateable {

	private final Inventory inventory;
	private final Button button;

	public InfoBar(GameView gv, App app) {
		this.inventory = new Inventory(app);
		this.button = new Button(gv, app);
		this.setRight(inventory);
		this.setLeft(button);
		// show a text with the current day of the game
		Text day = new Text();
		day.setCache(true);
		day.setFill(javafx.scene.paint.Color.BLACK);
		day.getStyleClass().add("number");
		day.setText("Day " + app.getDay());
		this.setCenter(day);
	}

	public void update() {
		this.inventory.update();
		this.button.update();
	}
}
