-- Schéma de la base VigieChiro (sous-ensemble du MCD de la SAÉ 2.01).
-- Cible : SQLite. Fourni : vous n'avez pas à l'écrire, mais lisez-le pour
-- comprendre les tables, leurs colonnes et leurs relations (clés étrangères).

-- C14 - Taxon : une espèce (ou catégorie) identifiable par Tadarida.
CREATE TABLE taxon (
  code             TEXT PRIMARY KEY,        -- ex : 'Pippip' (6 caractères)
  nom_latin        TEXT,
  nom_vernaculaire TEXT NOT NULL
);

-- C2 - Site de suivi : un carré géographique suivi par l'utilisateur.
CREATE TABLE site (
  numero_carre  TEXT PRIMARY KEY,           -- 6 chiffres, ex : '640380'
  nom_convivial TEXT,
  protocole     TEXT NOT NULL,              -- 'PointFixeStandard' | 'PointFixeRecherche'
  commentaire   TEXT,
  date_creation TEXT NOT NULL               -- date ISO 'AAAA-MM-JJ'
);

-- C3 - Point d'écoute : un emplacement précis dans un site.
CREATE TABLE point_ecoute (
  numero_carre TEXT NOT NULL,               -- FK vers site
  code         TEXT NOT NULL,               -- 2 caractères, ex : 'Z1'
  descriptif   TEXT,
  PRIMARY KEY (numero_carre, code),
  FOREIGN KEY (numero_carre) REFERENCES site(numero_carre)
);

-- C5 - Passage : une campagne d'enregistrement sur un point d'écoute.
CREATE TABLE passage (
  id                   INTEGER PRIMARY KEY AUTOINCREMENT,
  numero_carre         TEXT NOT NULL,
  code_point           TEXT NOT NULL,
  numero_passage       INTEGER NOT NULL,
  annee                INTEGER NOT NULL,
  date_enregistrement  TEXT,
  statut_workflow      TEXT NOT NULL,       -- 'Importé' | 'Transformé' | 'Vérifié' | ...
  verdict_verification TEXT,                -- 'À vérifier' | 'OK' | 'Douteux' | 'À jeter'
  FOREIGN KEY (numero_carre, code_point) REFERENCES point_ecoute(numero_carre, code)
);

-- C13 - Observation : une ligne de résultat Tadarida rattachée à un passage.
CREATE TABLE observation (
  id                INTEGER PRIMARY KEY AUTOINCREMENT,
  passage_id        INTEGER NOT NULL,
  temps_debut       REAL NOT NULL,
  temps_fin         REAL NOT NULL,
  frequence_mediane INTEGER NOT NULL,
  code_taxon        TEXT,
  probabilite       REAL,
  FOREIGN KEY (passage_id) REFERENCES passage(id),
  FOREIGN KEY (code_taxon) REFERENCES taxon(code)
);
