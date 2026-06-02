package fr.univ_amu.iut.bonus12;

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
 * Module Guice du bonus 12 : choisit l'implémentation de {@link SitesDao}.
 *
 * <p>En production, on lie l'interface à l'implémentation JDBC. Pour un test, il suffirait d'un
 * autre module liant {@code SitesDao} à {@link SitesDaoEnMemoire} : le reste de l'application ne
 * changerait pas d'une ligne.
 */
public class DaoModule extends AbstractModule {

  @Override
  protected void configure() {
    // TODO bonus 12 : lier l'interface SitesDao à son implémentation JDBC.
    // Astuce : bind(SitesDao.class).to(SitesDaoJdbc.class);
  }

  @Provides
  @Singleton
  DataSource fournirDataSource() {
    try {
      Path fichier = Files.createTempFile("vigiechiro-depot-", ".db");
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
