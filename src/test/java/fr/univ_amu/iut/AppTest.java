package fr.univ_amu.iut;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 * Smoke test : vérifie que le lanceur du TP5 s'affiche correctement et que les --add-opens
 * nécessaires à TestFX (cf. pom.xml argLine) sont OK.
 */
@ExtendWith(ApplicationExtension.class)
class AppTest {

  private Stage stage;

  @Start
  void start(Stage stage) {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new App().start(stage);
  }

  @Test
  void le_lanceur_affiche_le_titre_du_tp(FxRobot robot) {
    Label titre = robot.lookup("#titre-tp5").queryAs(Label.class);
    assertThat(titre).as("le label titre du TP5 doit être présent").isNotNull();
    assertThat(titre.getText()).as("le titre doit mentionner TP5").contains("TP5");
  }

  @Test
  void la_fenetre_du_lanceur_est_visible(FxRobot robot) {
    assertThat(stage.isShowing()).as("le Stage doit être affiché").isTrue();
  }
}
