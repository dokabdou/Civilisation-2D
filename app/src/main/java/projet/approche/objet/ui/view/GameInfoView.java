package projet.approche.objet.ui.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameInfoView extends StackPane {

	public GameInfoView(Stage stage) {
		ImageView imageView = new ImageView(new Image("images/Game.png", 700, 700, false, true)); // Replace with your

		BorderPane pane = new BorderPane();

		Label title = new Label("Village Manager");
		title.setFont(new Font(30));
		title.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6);-fx-padding: 7;");
		pane.setTop(title);
		BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);

		VBox text = new VBox();

		Label goal = new Label(
				"The goal of the game is to manage resources, inhabitants and buildings, all while trying to make your village prosper.");
		goal.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		goal.setWrapText(true);

		Label resources = new Label(
				"Resources:\nThere are 10 different resources available in the game. Each resource is used to build buildings or to feed your inhabitants.");
		resources.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		resources.setWrapText(true);

		Label buildings = new Label(
				"Buildings:\nThere are 9 types of buildings available in the game. Each building has a cost in resources and a number of workers required to be built. Buildings can be built on empty tiles.");
		buildings.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		buildings.setWrapText(true);

		Label howToPlay = new Label(
				"How to play:\nYou will have to manage your village to try and make it as profitable as possible. If you have the necessary resources, you can add new buildings on empty tiles. Each inhabitant will consume 1 food per day. But be careful, if you run out of food, it's GAME OVER!");
		howToPlay.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		howToPlay.setWrapText(true);

		text.getChildren().addAll(goal, resources, buildings, howToPlay);
		text.setAlignment(javafx.geometry.Pos.CENTER);
		text.setPadding(new Insets(50));
		pane.setCenter(text);
		this.getChildren().addAll(imageView, pane);
	}

}
