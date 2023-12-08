package projet.approche.objet.repository;


public interface RepositoryItf {

    void save(Manager m);

    Manager load(String file);

}

