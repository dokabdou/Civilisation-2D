package projet.approche.objet.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.approche.objet.ui.view.GameInfoView;
import projet.approche.objet.ui.view.GameStarterView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		GameStarterView editorView = new GameStarterView(primaryStage);
		GameInfoView GameInfoView = new GameInfoView(primaryStage);
		editorView.setCenter(GameInfoView);
		primaryStage.setTitle("Approche Objet");
		primaryStage.setScene(new Scene(editorView));
		primaryStage.setHeight(700);
		primaryStage.setWidth(700);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
