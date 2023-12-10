package projet.approche.objet.ui.view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projet.approche.objet.application.App;
import projet.approche.objet.domain.aggregates.Manager;
import projet.approche.objet.domain.repository.Repository;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.game.PremadeLevel;
import projet.approche.objet.infrastructure.Infrastructure;

public class GameStarterView extends BorderPane {

	public GameStarterView(Stage stage) {
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");

		MenuItem easyGame = new MenuItem("Load EASY game");
		MenuItem normalGame = new MenuItem("Load NORMAL game");
		MenuItem hardGame = new MenuItem("Load HARD game");
		MenuItem loadGame = new MenuItem("Load game");

		fileMenu.getItems().addAll(
				easyGame, normalGame, hardGame, loadGame);
		menuBar.getMenus().addAll(fileMenu);
		this.setTop(menuBar);

		easyGame.setOnAction(e -> {
			startGame(stage, new GameStarter(PremadeLevel.EASY));
		});

		normalGame.setOnAction(e -> {
			startGame(stage, new GameStarter(PremadeLevel.NORMAL));
		});

		hardGame.setOnAction(e -> {
			Infrastructure is = new Infrastructure();
			GameStarter gs = is.load();
			startGame(stage, gs);
		});

		loadGame.setOnAction(e -> {

		});
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
