package fr.univ_amu.iut.exercice7;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entrée du capstone : l'écran « Mes sites de suivi », alimenté par la base SQLite.
 *
 * <p>Toute la pile du module y est assemblée par Guice : Vue (FXML) -> {@link SitesController} ->
 * {@link SitesViewModel} -> {@code SiteDao} -> {@code DataSource} -> SQLite. C'est exactement
 * l'architecture en couches du CM4, désormais réalisée de bout en bout.
 */
public class SitesApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Injector injector = Guice.createInjector(new PersistanceModule());

    FXMLLoader loader = new FXMLLoader(getClass().getResource("SitesView.fxml"));
    loader.setControllerFactory(injector::getInstance);
    Parent racine = loader.load();

    stage.setTitle("VigieChiro - Mes sites de suivi");
    stage.setScene(new Scene(racine));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
