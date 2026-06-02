package fr.univ_amu.iut;

import fr.univ_amu.iut.exercice7.SitesApp;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Lanceur du TP5. La plupart des exercices (1 à 6) sont des exercices de persistance « back-end »
 * sans interface graphique : ils se valident avec {@code ./mvnw test}. Seul le capstone (exercice
 * 7) possède une interface, accessible par un bouton ci-dessous.
 *
 * <p>Point d'entrée par défaut de {@code ./mvnw javafx:run}.
 */
public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    Label titre = new Label("TP5 - Persistance JDBC");
    titre.setId("titre-tp5");
    titre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    Label note = new Label("Exercices 1 à 6 : sans interface, validés par ./mvnw test.");
    note.setStyle("-fx-text-fill: #6a737d;");

    VBox root = new VBox(10);
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.CENTER);
    root.getChildren().add(titre);
    root.getChildren().add(note);
    root.getChildren()
        .add(bouton("Exercice 7 - Mes sites de suivi (capstone, persisté)", SitesApp::new));

    primaryStage.setTitle("TP5 - Persistance - Lanceur");
    primaryStage.setScene(new Scene(root, 480, 600));
    primaryStage.show();
  }

  Button bouton(String libelle, Supplier<Application> fabrique) {
    Button btn = new Button(libelle);
    btn.setMaxWidth(Double.MAX_VALUE);
    btn.setOnAction(e -> lancerExercice(libelle, fabrique));
    return btn;
  }

  private void lancerExercice(String libelle, Supplier<Application> fabrique) {
    int fenetresAvant = Window.getWindows().size();
    try {
      fabrique.get().start(new Stage());
    } catch (Exception ex) {
      StringWriter sw = new StringWriter();
      ex.printStackTrace(new PrintWriter(sw));
      afficherAlerte(
          AlertType.ERROR,
          "Exception pendant le lancement de " + libelle,
          "L'exercice a levé une exception. Extrait :\n\n" + sw.toString().split("\n")[0],
          sw.toString());
      return;
    }
    int fenetresApres = Window.getWindows().size();
    if (fenetresApres == fenetresAvant) {
      afficherAlerte(
          AlertType.INFORMATION,
          libelle + " - rien à afficher",
          "La méthode start() de cet exercice ne produit aucune fenêtre.",
          "C'est normal si vous n'avez pas encore implémenté l'exercice.");
    }
  }

  private void afficherAlerte(AlertType type, String titre, String entete, String contenu) {
    Alert alert = new Alert(type);
    alert.setTitle(titre);
    alert.setHeaderText(entete);
    alert.setContentText(contenu);
    alert.setResizable(true);
    alert.getDialogPane().setPrefWidth(520);
    alert.showAndWait();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
