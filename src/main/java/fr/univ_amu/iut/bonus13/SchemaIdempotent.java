package fr.univ_amu.iut.bonus13;

import fr.univ_amu.iut.jdbc.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Bonus 13 : initialisation <b>idempotente</b> du schéma.
 *
 * <p>Le capstone (exercice 7) repart d'une base jetable à chaque lancement. Une vraie application
 * (la SAÉ) garde au contraire <b>une base persistante</b> entre deux lancements : il faut donc
 * créer le schéma <b>une seule fois</b>, au premier démarrage, et ne rien faire les fois suivantes
 * (sinon {@code CREATE TABLE} échouerait sur une table déjà existante, et le seed dupliquerait les
 * données).
 *
 * <p>L'idée : avant d'initialiser, on vérifie si la base est déjà peuplée.
 */
public class SchemaIdempotent {

  private SchemaIdempotent() {}

  /** Initialise le schéma et les données seulement si la base ne contient pas encore les tables. */
  public static void initialiserSiNecessaire(DataSource source) {
    // TODO bonus 13 : n'initialiser que si la table `taxon` n'existe pas encore.
    //
    // - si tableExiste(source, "taxon") est faux, appeler BaseDeDonnees.initialiser(source) ;
    // - sinon, ne rien faire (la base est déjà prête).
  }

  /** Indique si une table de ce nom existe déjà (interroge le catalogue SQLite). Fourni. */
  private static boolean tableExiste(DataSource source, String nom) {
    String sql = "SELECT name FROM sqlite_master WHERE type = 'table' AND name = ?";
    try (Connection connexion = source.getConnection();
        PreparedStatement ps = connexion.prepareStatement(sql)) {
      ps.setString(1, nom);
      try (ResultSet rs = ps.executeQuery()) {
        return rs.next();
      }
    } catch (SQLException e) {
      throw new DataAccessException("Impossible de vérifier l'existence de la table " + nom, e);
    }
  }
}
