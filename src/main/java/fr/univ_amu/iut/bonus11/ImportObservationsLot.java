package fr.univ_amu.iut.bonus11;

import fr.univ_amu.iut.exercice6.ObservationAImporter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 * Bonus 11 : insertion par lot avec {@link PreparedStatement#executeBatch()}.
 *
 * <p>La SAÉ importe des <b>milliers</b> d'observations par nuit. Les insérer une par une (un {@code
 * executeUpdate} par ligne, comme à l'exercice 6) déclenche un aller-retour réseau/disque à chaque
 * appel : c'est lent. Le mode <b>batch</b> accumule les ordres avec {@code addBatch()} puis les
 * envoie tous d'un coup avec {@code executeBatch()}, le tout dans une seule transaction.
 *
 * <p>On réutilise le record {@link ObservationAImporter} de l'exercice 6.
 */
public class ImportObservationsLot {

  private final DataSource source;

  public ImportObservationsLot(DataSource source) {
    this.source = source;
  }

  /**
   * Insère toutes les observations d'un lot, rattachées au passage donné, en une seule transaction
   * par lot.
   *
   * @return le nombre d'observations insérées
   */
  public int importerLot(long passageId, List<ObservationAImporter> observations) {
    int inseres = 0;
    String sql =
        "INSERT INTO observation"
            + " (passage_id, temps_debut, temps_fin, frequence_mediane, code_taxon, probabilite)"
            + " VALUES (?, ?, ?, ?, ?, ?)";

    // TODO bonus 11 : insérer le lot avec addBatch / executeBatch dans une transaction.
    //
    // - ouvrir une connexion (avant le try, pour pouvoir rollback) ; setAutoCommit(false) ;
    // - préparer la requête ; pour chaque observation : positionner les paramètres puis
    // ps.addBatch() ;
    // - exécuter le lot : int[] resultats = ps.executeBatch() ; commit ;
    //   `inseres` = resultats.length ;
    // - en cas de SQLException : rollback puis lever une DataAccessException ; finally : fermer.

    return inseres;
  }

  private static void annulerSilencieusement(Connection connexion) {
    if (connexion != null) {
      try {
        connexion.rollback();
      } catch (SQLException ignore) {
        // l'exception d'origine est déjà en cours de remontée
      }
    }
  }

  private static void fermerSilencieusement(Connection connexion) {
    if (connexion != null) {
      try {
        connexion.close();
      } catch (SQLException ignore) {
        // connexion déjà inutilisable
      }
    }
  }
}
