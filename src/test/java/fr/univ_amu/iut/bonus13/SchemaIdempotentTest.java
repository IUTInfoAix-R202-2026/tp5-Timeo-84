package fr.univ_amu.iut.bonus13;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import fr.univ_amu.iut.exercice2.BaseDeDonnees;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Test du bonus 13 : initialiser deux fois la même base ne doit ni échouer ni dupliquer les
 * données.
 */
class SchemaIdempotentTest {

  @TempDir Path dossier;
  private DataSource source;

  @BeforeEach
  void preparer() {
    source = BaseDeDonnees.surFichier(dossier.resolve("test.db").toString());
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void deux_initialisations_successives_ne_levent_pas_d_erreur() {
    SchemaIdempotent.initialiserSiNecessaire(source);

    assertThatCode(() -> SchemaIdempotent.initialiserSiNecessaire(source))
        .doesNotThrowAnyException();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void deux_initialisations_ne_dupliquent_pas_les_donnees() throws SQLException {
    SchemaIdempotent.initialiserSiNecessaire(source);
    SchemaIdempotent.initialiserSiNecessaire(source);

    try (Connection connexion = source.getConnection();
        Statement st = connexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM taxon")) {
      rs.next();
      assertThat(rs.getInt(1)).as("les 4 taxons, pas 8").isEqualTo(4);
    }
  }
}
