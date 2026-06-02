-- Données initiales du fil rouge VigieChiro (PR 1925492, carré 640380).
-- Fourni : sert à peupler la base pour les exercices et les tests.

-- Taxons du fil rouge (codes Tadarida sur 6 caractères).
INSERT INTO taxon (code, nom_latin, nom_vernaculaire) VALUES
  ('Pippip', 'Pipistrellus pipistrellus', 'Pipistrelle commune'),
  ('Nyclei', 'Nyctalus leisleri',         'Noctule de Leisler'),
  ('Tadten', 'Tadarida teniotis',         'Molosse de Cestoni'),
  ('Rhihip', 'Rhinolophus hipposideros',  'Petit rhinolophe');

-- Un site déjà déclaré.
INSERT INTO site (numero_carre, nom_convivial, protocole, commentaire, date_creation) VALUES
  ('640380', 'Étang de la Tuilière', 'PointFixeStandard', NULL, '2026-04-20');

-- Un point d'écoute sur ce site.
INSERT INTO point_ecoute (numero_carre, code, descriptif) VALUES
  ('640380', 'Z1', 'Lisière est, près de l''étang');

-- Un passage sur ce point.
INSERT INTO passage
  (numero_carre, code_point, numero_passage, annee, date_enregistrement, statut_workflow, verdict_verification)
VALUES
  ('640380', 'Z1', 2, 2026, '2026-04-22', 'Vérifié', 'OK');

-- Deux observations rattachées à ce passage (id = 1).
INSERT INTO observation (passage_id, temps_debut, temps_fin, frequence_mediane, code_taxon, probabilite) VALUES
  (1, 0.4, 1.2, 45000, 'Pippip', 0.92),
  (1, 2.1, 2.8, 22000, 'Nyclei', 0.71);
