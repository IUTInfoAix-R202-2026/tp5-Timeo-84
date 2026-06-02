package fr.univ_amu.iut.exercice3;

/**
 * Modèle d'un taxon (entité C14 du MCD SAÉ) : une espèce de chauve-souris identifiable par
 * Tadarida.
 *
 * <p>{@code record} immuable : un porteur de données. Le {@code code} (6 caractères, ex {@code
 * Pippip}) est l'identifiant ; {@code nomLatin} peut être absent.
 */
public record Taxon(String code, String nomLatin, String nomVernaculaire) {}
