package fr.univ_amu.iut.bonus10;

import javax.sql.DataSource;

/**
 * Bonus 10 : importer des observations depuis un CSV (le geste central du parcours P2 de la SAÉ).
 *
 * <p>Tadarida produit un fichier CSV d'observations. Importer une nuit, c'est lire ce CSV ligne par
 * ligne et insérer chaque observation en base. Ici, le CSV (simplifié) a pour colonnes :
 *
 * <pre>temps_debut;temps_fin;frequence_mediane;code_taxon;probabilite</pre>
 *
 * <p>La première ligne est un en-tête (à ignorer). Le séparateur est le point-virgule.
 */
public class ImportCsvObservations {

  private final DataSource source;

  public ImportCsvObservations(DataSource source) {
    this.source = source;
  }

  /**
   * Parse le contenu CSV et insère chaque ligne comme une observation du passage donné.
   *
   * @return le nombre d'observations importées
   */
  public int importer(long passageId, String contenuCsv) {
    int inseres = 0;
    String sql =
        "INSERT INTO observation"
            + " (passage_id, temps_debut, temps_fin, frequence_mediane, code_taxon, probabilite)"
            + " VALUES (?, ?, ?, ?, ?, ?)";

    // TODO bonus 10 : parser le CSV et insérer chaque observation.
    //
    // - découper en lignes : contenuCsv.strip().split("\\R") ;
    // - IGNORER la première ligne (en-tête) et les lignes vides ;
    // - pour chaque ligne : String[] col = ligne.split(";") avec
    //     col[0]=temps_debut, col[1]=temps_fin, col[2]=frequence_mediane,
    //     col[3]=code_taxon, col[4]=probabilite (Double.parseDouble / Integer.parseInt) ;
    // - insérer la ligne (executeUpdate) et incrémenter `inseres` ;
    // - envelopper toute SQLException dans une DataAccessException.

    return inseres;
  }
}
