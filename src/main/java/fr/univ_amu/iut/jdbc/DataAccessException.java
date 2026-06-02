package fr.univ_amu.iut.jdbc;

import java.sql.SQLException;

/**
 * Exception non vérifiée (unchecked) qui enveloppe une {@link SQLException}.
 *
 * <p>L'API JDBC lève des {@link SQLException} <b>vérifiées</b> (checked) un peu partout. Les
 * propager telles quelles obligerait chaque appelant (ViewModel, service...) à les attraper, ce qui
 * polluerait tout le code au-dessus de la couche DAO. La convention de la couche de persistance est
 * donc de <b>traduire</b> ces exceptions techniques en une {@link RuntimeException} dédiée : le
 * code métier reste lisible, et peut choisir de l'attraper uniquement là où c'est pertinent.
 */
public class DataAccessException extends RuntimeException {

  public DataAccessException(String message, SQLException cause) {
    super(message, cause);
  }
}
