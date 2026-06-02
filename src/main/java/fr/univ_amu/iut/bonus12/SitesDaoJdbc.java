package fr.univ_amu.iut.bonus12;

import com.google.inject.Inject;
import fr.univ_amu.iut.exercice4.Site;
import fr.univ_amu.iut.exercice4.SiteDao;
import java.util.List;

/**
 * Implémentation JDBC de {@link SitesDao} : elle délègue au {@link SiteDao} de l'exercice 4.
 *
 * <p>C'est l'implémentation « de production » : elle parle vraiment à la base. Guice l'injecte là
 * où un {@link SitesDao} est demandé (voir {@link DaoModule}).
 */
public class SitesDaoJdbc implements SitesDao {

  private final SiteDao siteDao;

  @Inject
  public SitesDaoJdbc(SiteDao siteDao) {
    this.siteDao = siteDao;
  }

  @Override
  public List<Site> findAll() {
    return siteDao.findAll();
  }

  @Override
  public void insert(Site site) {
    siteDao.insert(site);
  }
}
