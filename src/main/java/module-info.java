/**
 * Module JavaFX pour le TP TP5.
 *
 * <p>Ce module exporte les paquetages nécessaires pour les exercices. Ajoutez les exports des
 * nouveaux paquetages d'exercices au fur et à mesure.
 */
open module tp5.javafx {
  // JavaFX dependencies
  requires transitive javafx.base;
  requires transitive javafx.controls;
  requires transitive javafx.graphics;
  requires transitive javafx.fxml;

  // Persistance : API JDBC + driver SQLite + injection de dependances
  requires java.sql;
  requires org.xerial.sqlitejdbc;
  requires com.google.guice;
  requires com.zaxxer.hikari;

  // Binding SLF4J silencieux (NOP). HikariCP journalise via SLF4J ; ce requires
  // place le binding dans le graphe de modules pour que org.slf4j charge son
  // StaticLoggerBinder et cesse d'afficher "Failed to load StaticLoggerBinder".
  requires org.slf4j.nop;

  // Export base package
  exports fr.univ_amu.iut;

// ========== EXERCICES - Ajouter les exports ici ==========
// exports fr.univ_amu.iut.exercice1;
// exports fr.univ_amu.iut.exercice2;
// ...
}
