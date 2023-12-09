package projet.approche.objet.ui.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projet.approche.objet.application.App;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.ui.view.imageResource.LevelButtonImageResource;

public class LevelButtons extends HBox {

	private static final ImageView easy = new ImageView(LevelButtonImageResource.EASY.getImage());
	private static final ImageView medium = new ImageView(LevelButtonImageResource.MEDIUM.getImage());
	private static final ImageView hard = new ImageView(LevelButtonImageResource.HARD.getImage());

	public LevelButtons(Stage stage) {
		this.setAlignment(javafx.geometry.Pos.CENTER);
		setPadding(new Insets(40));
		setSpacing(30);

		Button easyButton = new Button();
		easyButton.setGraphic(easy);
		Button mediumButton = new Button();
		mediumButton.setGraphic(medium);
		Button hardButton = new Button();
		hardButton.setGraphic(hard);
		easyButton.setOnAction(e -> {
			startGame(stage, GameStarter.EASY, 10);
		});

		mediumButton.setOnAction(e -> {
			startGame(stage, GameStarter.NORMAL, 10);
		});

		hardButton.setOnAction(e -> {
			startGame(stage, GameStarter.HARD, 10);
		});
		getChildren().addAll(easy, medium, hard);
	}

	private static void startGame(Stage stage, GameStarter gs, int gridSize) {
		App app = new App(gs, gridSize);
		GameView gameView = new GameView(stage, app);
		Scene gameScene = new Scene(gameView);

		stage.setTitle(gs.toString());
		stage.setScene(gameScene);
		stage.sizeToScene();
		stage.show();
	}
}
