package projet.approche.objet.domain.repository;

import projet.approche.objet.domain.aggregates.Manager;
import projet.approche.objet.domain.valueObject.game.GameStarter;

public interface Repository {

	void save(Manager m);

	GameStarter load();

}
