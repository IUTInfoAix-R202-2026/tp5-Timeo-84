package fr.univ_amu.iut.exercice6;

/**
 * Données d'une observation à importer (exercice 6).
 *
 * <p>{@code codeTaxon} doit référencer un taxon existant : sinon, la contrainte de clé étrangère de
 * la base fera échouer l'insertion (et donc, dans une transaction, annulera tout l'import).
 */
public record ObservationAImporter(
    double tempsDebut,
    double tempsFin,
    int frequenceMediane,
    String codeTaxon,
    double probabilite) {}
