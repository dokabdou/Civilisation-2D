package projet.approche.objet.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.approche.objet.ui.view.EditorView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		EditorView editorView = new EditorView(primaryStage);

		primaryStage.setTitle("Approche Objet");
		primaryStage.setScene(new Scene(editorView));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
