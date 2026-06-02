package fr.univ_amu.iut.bonus12;

import fr.univ_amu.iut.exercice4.Site;
import java.util.List;

/**
 * Bonus 12 : le DAO vu comme une <b>interface</b>.
 *
 * <p>Jusqu'ici, {@code SiteDao} était une classe concrète. En faire une interface permet d'en avoir
 * plusieurs implémentations interchangeables : une vraie (JDBC, {@link SitesDaoJdbc}) en
 * production, et une fausse en mémoire ({@link SitesDaoEnMemoire}) pour tester les ViewModels
 * <b>sans base de données</b>. C'est la même idée qu'au TP4 (dépendre d'une interface, injecter
 * l'implémentation) appliquée à la persistance : la couche au-dessus ne change pas, on remplace
 * seulement l'implémentation injectée.
 *
 * <p>Attention au nom : {@code SitesDao} (l'interface, au pluriel) ne doit pas être confondue avec
 * le {@code SiteDao} concret de l'exercice 4 (au singulier), que l'implémentation JDBC réutilise
 * sous le capot (voir {@link SitesDaoJdbc}).
 */
public interface SitesDao {

  List<Site> findAll();

  void insert(Site site);
}
