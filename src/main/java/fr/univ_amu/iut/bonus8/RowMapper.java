package fr.univ_amu.iut.bonus8;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface fonctionnelle qui transforme la <b>ligne courante</b> d'un {@link ResultSet} en un
 * objet du domaine (bonus 8).
 *
 * <p>Dans les exercices 3 à 6, on a réécrit à chaque fois la même boucle {@code while (rs.next())}.
 * Le {@code RowMapper} permet de factoriser cette répétition : on décrit une seule fois « comment
 * lire une ligne », et un requêteur générique s'occupe du reste.
 *
 * @param <T> le type d'objet produit pour chaque ligne
 */
@FunctionalInterface
public interface RowMapper<T> {

  T mapper(ResultSet rs) throws SQLException;
}
