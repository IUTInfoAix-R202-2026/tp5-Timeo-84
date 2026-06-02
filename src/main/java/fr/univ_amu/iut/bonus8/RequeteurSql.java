package fr.univ_amu.iut.bonus8;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Petit requêteur générique (bonus 8) : exécute un {@code SELECT} et délègue le mapping de chaque
 * ligne à un {@link RowMapper}.
 *
 * <p>C'est l'aboutissement de la couche DAO : au lieu de répéter la boucle {@code ResultSet} dans
 * chaque méthode, on l'écrit <b>une seule fois</b> ici. Les DAO n'ont plus qu'à fournir leur
 * requête et leur fonction de mapping. (C'est, à petite échelle, ce que font des outils comme
 * Spring JDBC.)
 */
public class RequeteurSql {

  private final DataSource source;

  public RequeteurSql(DataSource source) {
    this.source = source;
  }

  /** Exécute {@code sql} et renvoie une ligne mappée par élément. */
  public <T> List<T> query(String sql, RowMapper<T> mapper) {
    List<T> resultats = new ArrayList<>();

    // TODO bonus 8 : exécuter la requête et remplir `resultats` en appelant le mapper.
    //
    // - ouvrir une connexion, préparer et exécuter `sql` ;
    // - pour chaque ligne, resultats.add(mapper.mapper(rs)) ;
    // - envelopper toute SQLException dans une DataAccessException.

    return resultats;
  }
}
