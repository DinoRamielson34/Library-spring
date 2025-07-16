INSERT INTO Livre (titre, auteur) VALUES
('Les Misérables', 'Victor Hugo'),
('LÉtranger', 'Albert Camus'),
('Harry Potter à lécole des sorciers', 'J.K. Rowling');

INSERT INTO Exemplaire (code, id_livre) VALUES
('MIS001', 1),
('MIS002', 1),
('MIS003', 1),
('ETR001', 2),
('ETR002', 2),
('HAR001', 3);


INSERT INTO Role (nom) VALUES
('bibliothecaire'),
('adherent'),
('admin');


INSERT INTO Etat (nom) VALUES
('Disponible'),
('Emprunte'),
('Reserve'),
('En reparation'),
('Perdu'),
('Endommage'),
('En commande'),
('Retire'),
('En traitement'),
('Archive');


INSERT INTO Profil (nom, id_regle) VALUES
('etudiant', 1),
('enseignant', 3),
('professionel', 2);

INSERT INTO
    Statut (nom)
VALUES
    ('en attente'),
    ('valider');


    INSERT INTO
    Regle (
        nb_jour_duree_pret_max,
        nb_livre_preter_max,
        nb_prolengement_pret_max,
        nb_jour_prolongement_max
    )
VALUES
    (
        7, -- durée max d'un prêt (en jours)
        2, -- nombre max de livres à prêter simultanément
        3, -- nombre max de prolongements par prêt
        7 -- nombre max de jours par prolongement
    );


    INSERT INTO
    Regle (
        nb_jour_duree_pret_max,
        nb_livre_preter_max,
        nb_prolengement_pret_max,
        nb_jour_prolongement_max
    )
VALUES
    (
        12, -- durée max d'un prêt (en jours)
        4, -- nombre max de livres à prêter simultanément
        7, -- nombre max de prolongements par prêt
        12 -- nombre max de jours par prolongement
    );


    INSERT INTO
    Regle (
        nb_jour_duree_pret_max,
        nb_livre_preter_max,
        nb_prolengement_pret_max,
        nb_jour_prolongement_max
    )
VALUES
    (
        9, -- durée max d'un prêt (en jours)
        3, -- nombre max de livres à prêter simultanément
        5, -- nombre max de prolongements par prêt
        9 -- nombre max de jours par prolongement
    );


INSERT INTO
    Jour_Ferie (description, date_jf)
VALUES
    ('Ferie1', '2025-07-26'),
    ('Ferie2', '2025-07-19');


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



INSERT INTO
    Utilisateur (username, mdp, id_role)
VALUES
    ('user_biblio', 'mdp1', 1), -- bibliothequaire
    ('user_adherent', 'mdp2', 2), -- adherent
    ('user_admin', 'mdp3', 3),
    ('user_adherent2', 'mdp4', 2),
    ('user_adherent3', 'mdp5', 2),
    ('user_adherent4', 'mdp6', 2),
    ('user_adherent5', 'mdp7', 2),
    ('user_adherent6', 'mdp8', 2),
    ('user_adherent7', 'mdp9', 2);


INSERT INTO
    Utilisateur (username, mdp, id_role)
VALUES
    ('user_adherent8', 'mdp10', 2);



INSERT INTO
    Adherent (
        nom,
        prenom,
        date_de_naissance,
        id_utilisateur,
        id_profil
    )
VALUES
    ('Amine Bensaïd', 'ETU001', '1990-05-15', 2, 1), -- user_adherent, profil etudiant
    ('Sarah El Khattabi', 'ETU002', '1985-09-20', 4, 1), -- pas d'utilisateur, profil professeur
    ('Youssef Moujahid', 'ETU003', '1975-12-30', 5, 1),
    ('Nadia Benali', 'ENS001', '1975-12-30', 6, 2),
    ('Karim Haddadi', 'ENS002', '1975-12-30', 7, 2),
    ('Salima Touhami', 'ENS003', '1975-12-30', 8, 2),
    ('Rachid El Mansouri', 'PROF001', '1975-12-30', 9, 3),
    ('Amina Zerouali', 'PROF002', '1975-12-30', 10, 3);
    

INSERT INTO
    Type (nom)
VALUES
    ('Maison'),
    ('Sur place');


INSERT INTO Regle_Jour_Ferie (comportement, date_modif) VALUES
(1, '2024-10-15 17:45:00');


INSERT INTO Penalite (nb_jour_de_penalite) VALUES
(10),
(9),
(8);


INSERT INTO Penalite_Profil (date_modif, id_penalite, id_profil) VALUES
('2024-04-15 17:45:00', 1, 1),
('2024-05-01 11:20:00', 2, 2),
('2024-05-15 15:30:00', 2, 3);


INSERT INTO Abonnement_Adherent (id_adherent, id_abonnement, date_de_payement) VALUES
(11, 1, '2025-02-01 10:00:00'),  -- ETU001
(12, 2, '2025-02-01 10:00:00'),  -- ETU002
(13, 3, '2025-04-01 10:00:00'),  -- ETU003
(14, 4, '2025-07-01 10:00:00'),  -- ENS001
(15, 5, '2025-08-01 10:00:00'),  -- ENS002
(16, 6, '2025-07-01 10:00:00'),  -- ENS003
(17, 7, '2025-06-01 10:00:00'),  -- PROF001
(18, 8, '2024-10-01 10:00:00');  -- PROF002


INSERT INTO Abonnement (id_abonnement, mois, annee, tarif) VALUES
(1, 6, 2025, 75.00),     -- ETU001 → du 01/02/25 au 24/07/25 ≈ 6 mois
(2, 5, 2025, 65.00),     -- ETU002 → du 01/02/25 au 01/07/25 ≈ 5 mois
(3, 8, 2025, 90.00),     -- ETU003 → 8 mois (avril → décembre)
(4, 12, 2025, 140.00),   -- ENS001 → 12 mois
(5, 9, 2025, 120.00),    -- ENS002 → août 25 à mai 26 ≈ 9 mois
(6, 11, 2025, 130.00),   -- ENS003 → juillet 25 à juin 26 ≈ 11 mois
(7, 6, 2025, 85.00),     -- PROF001 → juin à décembre
(8, 8, 2024, 95.00);     -- PROF002 → octobre 24 à juin 25


-- Supposons que les exemplaires sont numérotés de 1 à 8
-- Et que chaque ligne correspond à un exemplaire en état "Disponible" (id_etat = 1)

INSERT INTO Etat_Exemplaire (date_modif, id_exemplaire, id_etat) VALUES
('2025-07-01 10:00:00', 1, 1), -- MIS001
('2025-07-01 10:00:00', 2, 1), -- MIS002
('2025-07-01 10:00:00', 3, 1), -- MIS003
('2025-07-01 10:00:00', 4, 1), -- ETR001
('2025-07-01 10:00:00', 5, 1), -- ETR002
('2025-07-01 10:00:00', 6, 1); -- HAR001
