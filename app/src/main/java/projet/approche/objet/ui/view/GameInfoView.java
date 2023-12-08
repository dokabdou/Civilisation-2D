package projet.approche.objet.ui.view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projet.approche.objet.application.App;
import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.ui.view.imageResource.BuildingImageResource;
import projet.approche.objet.ui.view.imageResource.ResourceImageResource;

public class GameInfoView extends StackPane {

	public GameInfoView(Stage stage) {
		/**
		 * BackgroundImage myBI = new BackgroundImage(
		 * new Image("images/Game.png"),
		 * BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
		 * BackgroundPosition.DEFAULT,
		 * BackgroundSize.DEFAULT);
		 * this.setBackground(new Background(myBI));
		 * // Image Background = new
		 * // Image("app\\src\\main\\resources\\images\\buildings\\Farm.png");
		 * this.setSpacing(15);
		 * this.setPadding(new Insets(15));
		 * Text title = new Text("Village Manager");
		 * title.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-padding:
		 * 10;");
		 * 
		 * title.setFont(new Font(20));
		 * this.getChildren().add(title);
		 * 
		 * Text goal = new Text(
		 * "The goal of the game is to manage resources, inhabitants and buildings, all
		 * while trying to make your village prosper.");
		 * this.getChildren().add(goal);
		 */
		ImageView imageView = new ImageView(new Image("images/Game.png", 700, 700, false, true)); // Replace with your
		BorderPane pane = new BorderPane();
		// Create a Label for your text
		Label title = new Label("Village Manager");
		title.setFont(new Font(30));
		title.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6);-fx-padding: 7;");
		pane.setTop(title);
		BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);
		VBox text = new VBox();
		Label goal = new Label(
				"The goal of the game is to manage resources, inhabitants and buildings, all while trying to make your village prosper.");
		// Create a StackPane to layer the text over the image
		goal.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		goal.setWrapText(true);

		Label resources = new Label(
				"Resources:\nThere are 10 different resources available in the game. Each resource is used to build buildings or to feed your inhabitants.");
		// Create a StackPane to layer the text over the image
		resources.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		resources.setWrapText(true);

		Label buildings = new Label(
				"Buildings:\nThere are 9 types of buildings available in the game. Each building has a cost in resources and a number of workers required to be built. Buildings can be built on empty tiles.");
		// Create a StackPane to layer the text over the image
		buildings.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		buildings.setWrapText(true);

		Label howToPlay = new Label(
				"How to play:\nYou will have to manage your village to try and make it as profitable as possible. If you have the necessary, you can add new buildings. Each inhabitant will consume 1 food per day. But be careful, if you run out of food, it's GAME OVER!");
		howToPlay.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-padding: 7;");
		howToPlay.setWrapText(true);

		text.getChildren().addAll(goal, resources, buildings, howToPlay);
		text.setAlignment(javafx.geometry.Pos.CENTER);
		pane.setCenter(text);
		this.getChildren().addAll(imageView, pane);

		// Centering the text (optional)
		// StackPane.setAlignment(textLabel, javafx.geometry.Pos.CENTER);

	}

}
