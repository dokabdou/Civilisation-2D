package projet.approche.objet.domain.aggregates;

import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.building.BuildingList;
import projet.approche.objet.domain.valueObject.building.BuildingType;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.game.GameState;
import projet.approche.objet.domain.valueObject.game.exceptions.GameAlreadyStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.GameEnded;
import projet.approche.objet.domain.valueObject.game.exceptions.GameNotStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughInhabitants;
import projet.approche.objet.domain.valueObject.game.exceptions.NotEnoughWorkers;
import projet.approche.objet.domain.valueObject.resource.ResourceList;

public class Manager {
	private static Long count = Long.valueOf(0);
	private static Long idBuildings = Long.valueOf(0);

	private final Long id = ++count;

	private int inhabitants; // number of inhabitants
	private int workers; // number of workers

	private GameState state = GameState.NOTSTARTED;

	private BuildingList buildings = new BuildingList();
	private ResourceList resources = new ResourceList();

	private int gridSize;
	private String[][] grid; 

	/**
	 * Manager of the game, it will manage the buildings, the resources, the
	 * inhabitants and the workers
	 * 
	 * @param gameStarter the game starter
	 */
	public Manager(GameStarter gameStarter, int gridSize) {
		this.inhabitants = gameStarter.inhabitants;
		this.workers = gameStarter.workers;
		this.resources = gameStarter.startingResources;
		for (BuildingType buildingType : gameStarter.startingBuildings) {
			this.buildings = this.buildings.add(new Building(buildingType, ++idBuildings));
		}

		this.gridSize = gridSize;
		this.grid = new String [gridSize][gridSize];

		for(int i = 0; i < gridSize; i++){
			for(int j = 0; j < gridSize; j++){
				if(gameStarter == GameStarter.EASY){
					if((i== 0 && j==0) || (i== 1 && j==0)){
						this.grid[i][j] = "C";
					}
					if((i== 0 && j==2) || (i== 0 && j==3)){
						this.grid[i][j] = "H";
					}
				} else if (gameStarter == GameStarter.NORMAL){
					if((i== 0 && j==0)){
						this.grid[i][j] = "C";
					}
					if((i== 0 && j==2) || (i== 0 && j==3)){
						this.grid[i][j] = "H";
					}
				} else {
					if((i== 0 && j==0)){
						this.grid[i][j] = "C";
					}
				}

				
			}
		}
	}

	public Long getId() {
		return id;
	}

	public int getInhabitants() {
		return inhabitants;
	}

	public int getWorkers() {
		return workers;
	}

	public GameState getState() {
		return state;
	}

	public BuildingList getBuildings() {
		return buildings;
	}

	public ResourceList getResources() {
		return resources;
	}

	public void buildBuilding(Building building) {
		this.buildings = this.buildings.add(building);
	}

	public void destroyBuilding(Building building) {
		this.buildings = this.buildings.remove(building);
	}

	public void addInhabitantToBuilding(Building building, int inhabitantsToAdd) throws NotEnoughInhabitants {
		if (this.inhabitants >= inhabitantsToAdd) {
			building.addInhabitantToBuilding(inhabitantsToAdd);
			this.inhabitants -= inhabitantsToAdd;
		} else {
			throw new NotEnoughInhabitants();
		}
	}

	public void removeInhabitantFromBuilding(Building building, int inhabitantsToRemove) throws NotEnoughInhabitants {
		if (building.getInhabitants() >= inhabitantsToRemove) {
			building.addInhabitantToBuilding(-inhabitantsToRemove);
			this.inhabitants += inhabitantsToRemove;
		} else {
			throw new NotEnoughInhabitants();
		}
	}

	public void addWorkerToBuilding(Building building, int workersToAdd) throws NotEnoughWorkers {
		if (this.workers >= workersToAdd) {
			building.addWorkerToBuilding(workersToAdd);
			this.workers -= workersToAdd;
		} else {
			throw new NotEnoughWorkers();
		}
	}

	public void removeWorkerFromBuilding(Building building, int workersToRemove) throws NotEnoughWorkers {
		if (building.getWorkers() >= workersToRemove) {
			building.addWorkerToBuilding(-workersToRemove);
			this.workers += workersToRemove;
		} else {
			throw new NotEnoughWorkers();
		}
	}

	public void startGame() throws GameAlreadyStarted, GameEnded {
		if (state == GameState.NOTSTARTED)
			state = GameState.RUNNING;
		else if (state == GameState.ENDED)
			throw new GameEnded();
		else if (state == GameState.RUNNING || state == GameState.PAUSED)
			throw new GameAlreadyStarted();
	}

	public void pauseGame() throws GameNotStarted, GameEnded {
		if (state == GameState.RUNNING) {
			state = GameState.PAUSED;
		} else if (state == GameState.PAUSED) {
			state = GameState.RUNNING;
		} else if (state == GameState.NOTSTARTED) {
			throw new GameNotStarted();
		} else if (state == GameState.ENDED) {
			throw new GameEnded();
		}
	}

	public void endGame() throws GameNotStarted {
		if (state == GameState.RUNNING || state == GameState.PAUSED) {
			state = GameState.ENDED;
		} else if (state == GameState.NOTSTARTED) {
			throw new GameNotStarted();
		}
	}

	public void update() {
		if (state == GameState.RUNNING) {
			for (Building building : buildings) {
				resources = building.update(resources);
			}
		}
	}

	public boolean updateGrid(int c_i; int c_j, Building building){
		// everytime a building is created the grid is updated
		/*for(int i = 0; i < this.gridSize; i++){
			for(int j = 0; j < this.gridSize; j++){

			}
		}*/
		if( c_i >= this.gridSize){
			// c_i too big
			return false;
		}

		if( c_j >= this.gridSize){
			// c_j too big

		}

		//think of size of building, if the j coordinate is to close to the right edge the building can not be built on this line
		// and if there is no building overlap
		if(this.gridSize-c_j-1 > building.buildingSize()){
			// can building the building on this line
		}
		return true;
	}
}
