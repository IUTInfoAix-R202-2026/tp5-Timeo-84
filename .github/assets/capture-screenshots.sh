#!/usr/bin/env bash
#
# Regenere les captures "Resultat attendu" des apps GUI du TP5 (branche
# solution) dans .github/assets/apercu-*.png.
#
# Outil de MAINTENANCE (enseignant) : a relancer apres modification des
# exercices. Le TP5 porte sur la persistance : les exercices 1 a 6 sont des
# exercices "back-end" sans fenetre (valides par ./mvnw test). Seul le capstone
# (exercice 7, SitesApp) a une interface graphique -> une seule capture.
# Le capstone est autonome : il cree une base SQLite temporaire seedee a chaque
# lancement, donc la capture montre des donnees sans Docker ni base externe.
#
# Prerequis : xvfb (xvfb-run) + ImageMagick (import, convert).
# Usage (depuis la racine du TP) :
#   ./.github/assets/capture-screenshots.sh            # toutes les captures (ex7)
#   ./.github/assets/capture-screenshots.sh ex7        # une seule
#   ./.github/assets/capture-screenshots.sh lanceur    # le lanceur (optionnel)
#
set -uo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
cd "$ROOT"
ASSETS=.github/assets
mkdir -p "$ASSETS"
SCREEN="-screen 0 1366x900x24"
WAIT=24

declare -A APP=(
  [lanceur]="tp5.javafx/fr.univ_amu.iut.App|apercu-lanceur"
  [ex7]="tp5.javafx/fr.univ_amu.iut.exercice7.SitesApp|apercu-ex7-sites"
)
# Seul l'ex7 a une fenetre : c'est la seule capture generee par defaut.
ORDER=(ex7)

# $1 = mainClass, $2 = nom de sortie. Variables passees par l'environnement
# -> bash -c en quotes simples (pas d'echappement).
capture() {
  local main="$1" out="$2"
  MAIN="$main" OUT="$out" WAITS="$WAIT" \
    xvfb-run -a --server-args="$SCREEN" bash -c '
      ./mvnw -q -Djavafx.mainClass="$MAIN" javafx:run >"/tmp/cap-$OUT.log" 2>&1 &
      M=$!
      sleep "$WAITS"
      import -window root "/tmp/$OUT-raw.png" 2>/dev/null
      ap=$(pgrep -f "enable-native-access=javafx.graphics --module-path" | head -1)
      [ -n "$ap" ] && kill -9 "$ap" 2>/dev/null
      kill -9 "$M" 2>/dev/null
      exit 0
    '
  convert "/tmp/$out-raw.png" -trim +repage "$ASSETS/$out.png"
  echo "  $out.png  ($(identify -format '%wx%h' "$ASSETS/$out.png" 2>/dev/null))"
}

# Capture une cle (gere le cas special de l'ex7 : on met en scene l'ecran).
capture_key() {
  local key="$1" entry main out
  entry="${APP[$key]:-}"
  if [ -z "$entry" ]; then echo "Cle inconnue : $key (attendu : ${!APP[*]})" >&2; return 1; fi
  main="${entry%%|*}"; out="${entry##*|}"
  if [ "$key" = ex7 ]; then
    # Met en scene le capstone le temps de la capture : on pre-remplit le
    # formulaire d'ajout et on selectionne la 1re ligne, pour que les deux
    # affordances soient visibles (bouton "Ajouter" vert, "Supprimer" rouge).
    # Restauration par copie (cp) : preserve d'eventuelles modifs non commitees.
    local SC=src/main/java/fr/univ_amu/iut/exercice7/SitesController.java
    (
      BAK=$(mktemp); cp "$SC" "$BAK"
      trap 'cp "$BAK" "$SC"; rm -f "$BAK"; ./mvnw -q compile >/dev/null 2>&1' EXIT
      sed -i 's#choiceProtocole.getItems().setAll("PointFixeStandard", "PointFixeRecherche");#&\n    champNumero.setText("810915"); champNom.setText("Mare des Aulnes"); choiceProtocole.setValue("PointFixeRecherche"); tableSites.getSelectionModel().selectFirst();#' "$SC"
      ./mvnw -q compile
      capture "$main" "$out"
    )
  else
    capture "$main" "$out"
  fi
}

echo "Pre-compilation..."
./mvnw -q compile

if [ "$#" -ge 1 ]; then
  echo "Captures demandees : $* -> $ASSETS/"
  for k in "$@"; do capture_key "$k"; done
else
  echo "Toutes les captures -> $ASSETS/"
  for k in "${ORDER[@]}"; do capture_key "$k"; done
fi

pgrep -f "enable-native-access=javafx.graphics --module-path" | xargs -r kill -9 2>/dev/null
echo "Termine."
