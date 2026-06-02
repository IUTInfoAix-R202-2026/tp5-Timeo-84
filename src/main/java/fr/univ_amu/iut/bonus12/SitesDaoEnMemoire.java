package fr.univ_amu.iut.bonus12;

import fr.univ_amu.iut.exercice4.Site;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation EN MÉMOIRE de {@link SitesDao} (un faux dépôt, sans base de données).
 *
 * <p>Idéale pour tester un ViewModel sans monter de base : rapide, déterministe, isolée. C'est ce
 * qu'on injecterait dans un test à la place de l'implémentation JDBC.
 */
public class SitesDaoEnMemoire implements SitesDao {

  private final List<Site> sites = new ArrayList<>();

  @Override
  public List<Site> findAll() {
    return List.copyOf(sites);
  }

  @Override
  public void insert(Site site) {
    sites.add(site);
  }
}
