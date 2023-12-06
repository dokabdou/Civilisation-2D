package projet.approche.objet.ui.view;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projet.approche.objet.application.App;
import projet.approche.objet.domain.valueObject.game.exceptions.GameEnded;
import projet.approche.objet.domain.valueObject.game.exceptions.GameNotStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.GamePaused;
import projet.approche.objet.ui.view.infoBar.InfoBar;

public class GameView extends BorderPane {
	private final PickerView pickerView;
	private final GridView gridView;
	private final InfoBar infoBar;
	private Task<Void> updateTask;
	private Thread thread;

	public GameView(Stage stage, App app) {
		// Tile picker
		this.pickerView = new PickerView(app);
		this.setTop(pickerView);
		// Info bar
		this.infoBar = new InfoBar(this, app);
		this.infoBar.update();
		this.setRight(infoBar);
		// Grid

		List<Updateable> updateables = new ArrayList<>();
		updateables.add(infoBar);
		updateables.add(pickerView);

		this.gridView = new GridView(app, pickerView, updateables);
		this.setCenter(gridView);

		updateables.add(gridView);

		updateTask = new Task<>() {
			@Override
			protected Void call() throws InterruptedException {
				while (true) {
					Thread.sleep(1000);
					try {
						app.update();
						updateables.forEach(updateable -> updateable.update());
					} catch (GameNotStarted | GameEnded | GamePaused e) {
						updateMessage(e.getMessage());
						break;
					}
				}
				return null;
			}
		};
	}

	public void startUpdateLoop() {
		thread = new Thread(updateTask, "UpdateTask");
		thread.start();
	}

	public void stopUpdateLoop() {
		thread.interrupt();
	}
}
