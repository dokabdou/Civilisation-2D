package projet.approche.objet;

import projet.approche.objet.domain.valueObject.building.BuildingType;
//import projet.approche.objet.domain.*;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.game.exceptions.GameAlreadyStarted;
import projet.approche.objet.domain.valueObject.game.exceptions.GameEnded;
import projet.approche.objet.domain.valueObject.game.exceptions.GameNotStarted;
import projet.approche.objet.domain.aggregates.*;

public class App {
	public static void main(String[] args) throws GameNotStarted, GameAlreadyStarted, GameEnded {

		Manager gm = new Manager(GameStarter.EASY, 4);
		// gm.buildBuilding(BuildingType.HOUSE, 0, 0);
		System.out.println(gm.getGrid());
	}
}
