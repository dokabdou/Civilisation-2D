package projet.approche.objet.ui.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

		easy.setOnMouseClicked(e -> {
			startGame(stage, GameStarter.EASY, 10);
		});

		medium.setOnMouseClicked(e -> {
			startGame(stage, GameStarter.NORMAL, 10);
		});

		hard.setOnMouseClicked(e -> {
			startGame(stage, GameStarter.HARD, 10);
		});
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);
		easy.setOnMouseEntered(e -> {
			easy.setEffect(colorAdjust);
		});
		easy.setOnMouseExited(e -> {
			easy.setEffect(null);
		});
		medium.setOnMouseEntered(e -> {
			medium.setEffect(colorAdjust);
		});
		medium.setOnMouseExited(e -> {
			medium.setEffect(null);
		});
		hard.setOnMouseEntered(e -> {
			hard.setEffect(colorAdjust);
		});
		hard.setOnMouseExited(e -> {
			hard.setEffect(null);
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
