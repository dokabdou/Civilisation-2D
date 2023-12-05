package projet.approche.objet.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.approche.objet.ui.view.GameLauncherView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		GameLauncherView gameLauncherView = new GameLauncherView();

		primaryStage.setTitle("Approche Objet");
		primaryStage.setScene(new Scene(gameLauncherView));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
