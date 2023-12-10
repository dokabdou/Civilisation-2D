package projet.approche.objet.ui.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projet.approche.objet.application.App;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.game.PremadeLevel;
import projet.approche.objet.infrastructure.Infrastructure;
import projet.approche.objet.ui.view.imageResource.LevelButtonImageResource;

public class LevelButtons extends VBox {

	private static final ImageView easy = new ImageView(LevelButtonImageResource.EASY.getImage());
	private static final ImageView normal = new ImageView(LevelButtonImageResource.NORMAL.getImage());
	private static final ImageView hard = new ImageView(LevelButtonImageResource.HARD.getImage());
	private static final ImageView load = new ImageView(LevelButtonImageResource.LOAD.getImage());

	public LevelButtons(Stage stage) {
		HBox levels = new HBox();
		this.setAlignment(javafx.geometry.Pos.CENTER);
		levels.setAlignment(javafx.geometry.Pos.CENTER);
		levels.setPadding(new Insets(20));
		levels.setSpacing(30);

		easy.setOnMouseClicked(e -> {
			startGame(stage, new GameStarter(PremadeLevel.EASY));
		});

		normal.setOnMouseClicked(e -> {
			startGame(stage, new GameStarter(PremadeLevel.NORMAL));
		});

		hard.setOnMouseClicked(e -> {
			startGame(stage, new GameStarter(PremadeLevel.HARD));
		});

		load.setOnMouseClicked(e -> {
			Infrastructure is = new Infrastructure();
			GameStarter gs = is.load();
			startGame(stage, gs);
		});

		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);
		easy.setOnMouseEntered(e -> {
			easy.setEffect(colorAdjust);
		});
		easy.setOnMouseExited(e -> {
			easy.setEffect(null);
		});
		normal.setOnMouseEntered(e -> {
			normal.setEffect(colorAdjust);
		});
		normal.setOnMouseExited(e -> {
			normal.setEffect(null);
		});
		hard.setOnMouseEntered(e -> {
			hard.setEffect(colorAdjust);
		});
		hard.setOnMouseExited(e -> {
			hard.setEffect(null);
		});
		load.setOnMouseEntered(e -> {
			load.setEffect(colorAdjust);
		});
		load.setOnMouseExited(e -> {
			load.setEffect(null);
		});
		levels.getChildren().addAll(easy, normal, hard);

		this.getChildren().addAll(levels, load);
	}

	private static void startGame(Stage stage, GameStarter gs) {
		App app = new App(gs);
		GameView gameView = new GameView(stage, app);
		Scene gameScene = new Scene(gameView);
		stage.setTitle(gs.toString());
		stage.setScene(gameScene);
		stage.sizeToScene();
		stage.show();
	}
}
