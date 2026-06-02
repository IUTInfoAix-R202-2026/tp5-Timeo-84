package fr.univ_amu.iut.exercice5;

/**
 * Modèle d'un point d'écoute (entité C3 du MCD SAÉ) : un emplacement précis au sein d'un site.
 *
 * <p>Identifié par le couple ({@code numeroCarre}, {@code code}) ; {@code code} fait 2 caractères
 * (ex {@code Z1}). C'est une relation « appartient à un site » matérialisée par la clé étrangère
 * {@code numero_carre}.
 */
public record PointEcoute(String numeroCarre, String code, String descriptif) {}
