package fr.univ_amu.iut.exercice7;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import fr.univ_amu.iut.exercice2.BaseDeDonnees;
import fr.univ_amu.iut.exercice4.SiteDao;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.sql.DataSource;

/**
 * Module Guice du capstone : il fournit la couche de persistance à l'application.
 *
 * <p>On utilise des méthodes {@code @Provides} (plutôt que {@code bind(...).to(...)}) car on a
 * besoin d'un peu de logique pour construire la {@link DataSource} (créer le fichier de base,
 * charger le schéma). La {@code DataSource} est un {@code @Singleton} : une seule base pour toute
 * l'application. Le {@link SiteDao} n'a pas d'annotation Guice ; c'est cette méthode qui sait
 * l'assembler à partir de la {@code DataSource}.
 */
public class PersistanceModule extends AbstractModule {

  @Provides
  @Singleton
  DataSource fournirDataSource() {
    try {
      // Une base neuve par lancement (fichier temporaire) : la démo repart propre.
      Path fichier = Files.createTempFile("vigiechiro-", ".db");
      DataSource source = BaseDeDonnees.surFichier(fichier.toString());
      BaseDeDonnees.initialiser(source);
      return source;
    } catch (IOException e) {
      throw new UncheckedIOException("Impossible de créer la base de démonstration", e);
    }
  }

  @Provides
  SiteDao fournirSiteDao(DataSource source) {
    return new SiteDao(source);
  }
}
