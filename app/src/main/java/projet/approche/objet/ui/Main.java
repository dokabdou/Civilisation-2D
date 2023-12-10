package projet.approche.objet.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import projet.approche.objet.ui.view.GameInfoView;
import projet.approche.objet.ui.view.GameStarterView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		GameStarterView gameStarterView = new GameStarterView(primaryStage);
		ImageView imageView = new ImageView(new Image("images/Game.png"));
		GameInfoView gameInfoView = new GameInfoView(primaryStage, imageView);
		gameStarterView.setCenter(gameInfoView);
		gameStarterView.prefWidthProperty().bind(imageView.fitWidthProperty());
		gameStarterView.prefHeightProperty().bind(imageView.fitHeightProperty());

		primaryStage.setTitle("Approche Objet");
		primaryStage.setScene(new Scene(gameStarterView));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
