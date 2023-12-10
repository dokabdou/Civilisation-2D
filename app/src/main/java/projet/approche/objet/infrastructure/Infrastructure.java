package projet.approche.objet.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.awt.FileDialog;
import java.awt.Frame;

import projet.approche.objet.domain.aggregates.Manager;
import projet.approche.objet.domain.repository.Repository;
import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.grid.Coordinate;
import projet.approche.objet.domain.valueObject.resource.Resource;
import projet.approche.objet.domain.valueObject.resource.ResourceList;
import static projet.approche.objet.domain.valueObject.resource.ResourceType.*;

public class Infrastructure implements Repository {

	public void save(Manager m) {
		// TODO implement here

	}

	public GameStarter load() {
		FileDialog dialog = new FileDialog((Frame) null, "Select File to Open", FileDialog.LOAD);
		dialog.setVisible(true);
		String file = dialog.getDirectory() + dialog.getFile();
		dialog.dispose();
		String line = "";
		String[] lineContents;
		String splitBy = ",";
		int inhabitants, workers;
		ResourceList startingResources;
		int size;
		List<SimpleEntry<Coordinate, BuildingType>> entries = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			line = br.readLine(); // reading number of inhabitants and workers
			lineContents = line.split(splitBy);
			inhabitants = Integer.parseInt(lineContents[0]);
			workers = Integer.parseInt(lineContents[1]);
			line = br.readLine(); // reading the resources amounts
			lineContents = line.split(splitBy);
			startingResources = new ResourceList(List.of(
					new Resource(GOLD, Integer.parseInt(lineContents[0])),
					new Resource(FOOD, Integer.parseInt(lineContents[1])),
					new Resource(WOOD, Integer.parseInt(lineContents[2])),
					new Resource(STONE, Integer.parseInt(lineContents[3])),
					new Resource(COAL, Integer.parseInt(lineContents[4])),
					new Resource(IRON, Integer.parseInt(lineContents[5])),
					new Resource(STEEL, Integer.parseInt(lineContents[6])),
					new Resource(CEMENT, Integer.parseInt(lineContents[7])),
					new Resource(LUMBER, Integer.parseInt(lineContents[8])),
					new Resource(TOOLS, Integer.parseInt(lineContents[9]))));
			line = br.readLine(); // reading the size of the grid
			size = Integer.parseInt(line);
			while ((line = br.readLine()) != null) // reading the grid
			{
				lineContents = line.split(splitBy);
				entries.add(new SimpleEntry<>(
						new Coordinate(Integer.parseInt(lineContents[0]), Integer.parseInt(lineContents[1])),
						BuildingType.fromShortString(lineContents[2])));
			}
			br.close();
			GameStarter gameStarter = new GameStarter(size, inhabitants, workers, startingResources, entries);
			return gameStarter;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
