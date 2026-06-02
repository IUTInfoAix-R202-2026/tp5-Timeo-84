package fr.univ_amu.iut.bonus11;

import static org.assertj.core.api.Assertions.assertThat;

import fr.univ_amu.iut.exercice2.BaseDeDonnees;
import fr.univ_amu.iut.exercice6.ObservationAImporter;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Test du bonus 11 : l'import par lot insère bien toutes les observations rattachées au passage.
 */
class ImportObservationsLotTest {

  @TempDir Path dossier;
  private DataSource source;
  private ImportObservationsLot service;

  @BeforeEach
  void preparer() {
    source = BaseDeDonnees.surFichier(dossier.resolve("test.db").toString());
    BaseDeDonnees.initialiser(source);
    service = new ImportObservationsLot(source);
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void importer_un_lot_insere_toutes_les_observations() throws SQLException {
    // Le passage seedé porte l'id 1 et a déjà 2 observations.
    int avant = nombreObservationsDuPassage(1);

    int inseres =
        service.importerLot(
            1,
            List.of(
                new ObservationAImporter(0.1, 0.6, 41000, "Pippip", 0.88),
                new ObservationAImporter(1.0, 1.5, 23000, "Nyclei", 0.62),
                new ObservationAImporter(2.0, 2.4, 88000, "Rhihip", 0.55)));

    assertThat(inseres).isEqualTo(3);
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
