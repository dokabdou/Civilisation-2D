package projet.approche.objet.ui.view.infoBar;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import projet.approche.objet.application.App;
import projet.approche.objet.ui.view.GameView;
import projet.approche.objet.ui.view.Updateable;

public class InfoBar extends BorderPane implements Updateable {

	private final Inventory inventory;
	private final Button button;
	private final App app;
	private Text day = new Text();

	public InfoBar(GameView gv, App app) {
		this.inventory = new Inventory(app);
		this.button = new Button(gv, app);
		day.setFill(javafx.scene.paint.Color.BLACK);
		day.setFont(new Font(20));
		day.getStyleClass().add("number");
		day.setCache(true);
		this.app = app;
		this.setRight(inventory);
		this.setTop(button);
		// show a text with the current day of the game
	}

	public void update() {
		this.inventory.update();
		this.button.update();
		this.updateDay();
	}

	public void updateDay() {
		day.setText("Day " + app.getDay());
		this.setLeft(day);
	}
}
