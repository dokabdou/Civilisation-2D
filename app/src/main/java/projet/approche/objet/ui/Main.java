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

		GameStarterView editorView = new GameStarterView(primaryStage);
		ImageView imageView = new ImageView(new Image("images/Game.png"));
		GameInfoView gameInfoView = new GameInfoView(primaryStage, imageView);
		editorView.setCenter(gameInfoView);
		editorView.prefWidthProperty().bind(imageView.fitWidthProperty());
		editorView.prefHeightProperty().bind(imageView.fitHeightProperty());

		primaryStage.setTitle("Approche Objet");
		primaryStage.setScene(new Scene(editorView));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
