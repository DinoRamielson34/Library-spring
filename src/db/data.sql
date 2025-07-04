-- Insertion de 3 livres
INSERT INTO
    Livre (titre, auteur)
VALUES
    ('Le Petit Prince', 'Antoine de Saint-Exupéry'),
    ('1984', 'George Orwell'),
    ('Les Misérables', 'Victor Hugo');

-- Insertion de 3 exemplaires par livre
INSERT INTO
    Exemplaire (code, id_livre)
VALUES
    ('EX-0001', 1),
    ('EX-0002', 1),
    ('EX-0003', 1),
    ('EX-0004', 2),
    ('EX-0005', 2),
    ('EX-0006', 2),
    ('EX-0007', 3),
    ('EX-0008', 3),
    ('EX-0009', 3);

-- Insertion de 2 états possibles pour les exemplaires
INSERT INTO
    Etat (nom)
VALUES
    ('disponible'),
    ('en pret');

-- Insertion de 2 types (exemple)
INSERT INTO
    Type (nom)
VALUES
    ('Maison'),
    ('Sur place');

-- Insertion de 3 rôles d'utilisateurs
INSERT INTO
    Role (nom)
VALUES
    ('bibliothequaire'),
    ('adherent'),
    ('admin');

-- Insertion de 3 utilisateurs avec leur rôle associé
INSERT INTO
    Utilisateur (username, mdp, id_role)
VALUES
    ('user_biblio', 'mdp1', 1), -- bibliothequaire
    ('user_adherent', 'mdp2', 2), -- adherent
    ('user_admin', 'mdp3', 3);

-- admin
-- Insertion d'une règle générale pour les profils
INSERT INTO
    Regle (
        nb_jour_duree_pret_max,
        nb_livre_preter_max,
        nb_prolengement_pret_max,
        nb_jour_prolongement_max
    )
VALUES
    (
        30, -- durée max d'un prêt (en jours)
        5, -- nombre max de livres à prêter simultanément
        2, -- nombre max de prolongements par prêt
        15 -- nombre max de jours par prolongement
    );

-- Insertion de 3 profils avec la règle d'id 1
INSERT INTO
    Profil (nom, id_regle)
VALUES
    ('etudiant', 1),
    ('professeur', 1),
    ('autre', 1);

-- Insertion de 3 adhérents, liés ou non à un utilisateur et à un profil
INSERT INTO
    Adherent (
        nom,
        prenom,
        date_de_naissance,
        id_utilisateur,
        id_profil
    )
VALUES
    ('Rakoto', 'Jean', '1990-05-15', 2, 1), -- user_adherent, profil etudiant
    ('Rabe', 'Marie', '1985-09-20', NULL, 2), -- pas d'utilisateur, profil professeur
    ('Andria', 'Paul', '1975-12-30', NULL, 3);

-- pas d'utilisateur, profil autre
INSERT INTO
    Statut (nom)
VALUES
    ('en attente'),
    ('valider');

-- Insérer 2 jours fériés
INSERT INTO
    Jour_Ferie (description, date_jf)
VALUES
    ('Nouvel An', '2025-01-01'),
    ('Fête du Travail', '2025-05-01');

INSERT INTO
    Regle_Jour_Ferie (comportement_, date_modif) -- comportement = 1 si apres date jour ferier quon doit rendre , = 0 si avant date
VALUES
    (1, NOW ());

INSERT INTO
    Penalite (nb_jour_de_penalite)
VALUES
    (3), -- 3 jours de pénalité
    (7), -- 7 jours de pénalité
    (14);

-- Exemple avec date_modif = maintenant
INSERT INTO
    Penalite_Profil (date_modif, id_penalite, id_profil)
VALUES
    (NOW (), 3, 1),
    (NOW (), 2, 2),
    (NOW (), 1, 3);

INSERT INTO
    Abonnement (mois, annee, tarif)
VALUES
    (12, 2025, 10000),
    (11, 2025, 10000),
    (10, 2025, 10000),
    (9, 2025, 10000),
    (8, 2025, 10000),
    (7, 2025, 10000),
    (6, 2025, 10000),
    (5, 2025, 10000),
    (4, 2025, 10000),
    (3, 2025, 10000),
    (2, 2025, 10000),
    (1, 2025, 10000);

INSERT INTO
    Etat_Exemplaire (date_modif, id_exemplaire, id_etat)
VALUES
    (NOW (), 1, 1), -- EX-0001 - disponible
    (NOW (), 2, 1), -- EX-0002 - disponible
    (NOW (), 3, 1), -- EX-0003 - disponible
    (NOW (), 4, 1), -- EX-0004 - disponible
    (NOW (), 5, 1), -- EX-0005 - disponible
    (NOW (), 6, 1), -- EX-0006 - disponible
    (NOW (), 7, 2), -- EX-0007 - en pret
    (NOW (), 8, 2), -- EX-0008 - en pret
    (NOW (), 9, 2);

-- EX-0009 - en pret

-- Emprunter EX-0007 et EX-0008 pour l'adhérent 'Rakoto Jean' (id_adherent = 1)
INSERT INTO
    Pret (date_debut, date_fin, id_exemplaire, id_adherent, id_type)
VALUES
    (NOW (), NOW () + INTERVAL '15 days', 7, 1, 1),
    (NOW (), NOW () + INTERVAL '10 days', 8, 1, 2);
