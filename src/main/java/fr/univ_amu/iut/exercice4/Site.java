package fr.univ_amu.iut.exercice4;

/**
 * Modèle d'un site de suivi (entité C2 du MCD SAÉ).
 *
 * <p>Le {@code numeroCarre} (6 chiffres, ex {@code 640380}) est la clé primaire naturelle. Le
 * {@code protocole} vaut {@code "PointFixeStandard"} ou {@code "PointFixeRecherche"}. {@code
 * nomConvivial} et {@code commentaire} peuvent être absents ({@code null}). {@code dateCreation}
 * est une date au format ISO ({@code "AAAA-MM-JJ"}).
 */
public record Site(
    String numeroCarre,
    String nomConvivial,
    String protocole,
    String commentaire,
    String dateCreation) {}
