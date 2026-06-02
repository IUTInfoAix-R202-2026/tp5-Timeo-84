package fr.univ_amu.iut.bonus10;

import static org.assertj.core.api.Assertions.assertThat;

import fr.univ_amu.iut.exercice2.BaseDeDonnees;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Test du bonus 10 : l'import lit le CSV (en-tête ignoré) et insère chaque observation. */
class ImportCsvObservationsTest {

  private static final String CSV =
      """
      temps_debut;temps_fin;frequence_mediane;code_taxon;probabilite
      0.4;1.2;45000;Pippip;0.92
      2.1;2.8;22000;Nyclei;0.71
      3.0;3.5;88000;Rhihip;0.50
      """;

  @TempDir Path dossier;
  private DataSource source;
  private ImportCsvObservations service;

  @BeforeEach
  void preparer() {
    source = BaseDeDonnees.surFichier(dossier.resolve("test.db").toString());
    BaseDeDonnees.initialiser(source);
    service = new ImportCsvObservations(source);
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void importer_lit_le_csv_et_insere_les_observations() throws SQLException {
    int avant = nombreObservationsDuPassage(1);

    int inseres = service.importer(1, CSV);

    assertThat(inseres).as("3 lignes de données, l'en-tête est ignoré").isEqualTo(3);
    assertThat(nombreObservationsDuPassage(1)).isEqualTo(avant + 3);
  }

  private int nombreObservationsDuPassage(long passageId) throws SQLException {
    try (Connection connexion = source.getConnection();
        PreparedStatement ps =
            connexion.prepareStatement("SELECT COUNT(*) FROM observation WHERE passage_id = ?")) {
      ps.setLong(1, passageId);
      try (ResultSet rs = ps.executeQuery()) {
        rs.next();
        return rs.getInt(1);
      }
    }
  }
}
