package fr.univ_amu.iut.bonus12;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fr.univ_amu.iut.exercice4.Site;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Test du bonus 12 : le faux dépôt (en mémoire) se teste sans base, et Guice fournit bien
 * l'implémentation JDBC en « production ».
 */
class SitesDaoTest {

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void le_faux_depot_en_memoire_fonctionne_sans_base() {
    SitesDao depot = new SitesDaoEnMemoire();

    depot.insert(
        new Site("640380", "Étang de la Tuilière", "PointFixeStandard", null, "2026-04-20"));
    depot.insert(new Site("752204", "ZAC Nord", "PointFixeRecherche", null, "2026-05-01"));

    assertThat(depot.findAll()).extracting(Site::numeroCarre).containsExactly("640380", "752204");
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void guice_fournit_l_implementation_jdbc_en_production() {
    Injector injector = Guice.createInjector(new DaoModule());

    SitesDao depot = injector.getInstance(SitesDao.class);

    assertThat(depot).isInstanceOf(SitesDaoJdbc.class);
    assertThat(depot.findAll()).extracting(Site::numeroCarre).contains("640380");
  }
}
