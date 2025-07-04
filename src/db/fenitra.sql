-- Donnees completes pour la base de donnees bibliotheque
-- Sans apostrophes ni accents

-- Insertion des donnees dans la table Livre
INSERT INTO Livre (titre, auteur) VALUES
('Le Petit Prince', 'Antoine de Saint-Exupery'),
('1984', 'George Orwell'),
('Pride and Prejudice', 'Jane Austen'),
('To Kill a Mockingbird', 'Harper Lee'),
('The Great Gatsby', 'F. Scott Fitzgerald'),
('One Hundred Years of Solitude', 'Gabriel Garcia Marquez'),
('Brave New World', 'Aldous Huxley'),
('The Catcher in the Rye', 'J.D. Salinger'),
('Lord of the Flies', 'William Golding'),
('Animal Farm', 'George Orwell'),
('The Hobbit', 'J.R.R. Tolkien'),
('Harry Potter and the Philosophers Stone', 'J.K. Rowling'),
('The Da Vinci Code', 'Dan Brown'),
('The Alchemist', 'Paulo Coelho'),
('Gone with the Wind', 'Margaret Mitchell');

-- Insertion des donnees dans la table Exemplaire
INSERT INTO Exemplaire (code, id_livre) VALUES
('EX001', 1),
('EX002', 1),
('EX003', 2),
('EX004', 2),
('EX005', 3),
('EX006', 3),
('EX007', 4),
('EX008', 5),
('EX009', 6),
('EX010', 7),
('EX011', 8),
('EX012', 9),
('EX013', 10),
('EX014', 11),
('EX015', 12),
('EX016', 13),
('EX017', 14),
('EX018', 15),
('EX019', 1),
('EX020', 2);

-- Insertion des donnees dans la table Type
INSERT INTO Type (nom) VALUES
('Roman'),
('Scientifique'),
('Histoire'),
('Biographie'),
('Fiction'),
('Non-fiction'),
('Philosophie'),
('Poesie'),
('Theatre'),
('Encyclopedie');

-- Insertion des donnees dans la table Regle
INSERT INTO Regle (nb_jour_duree_pret_max, nb_livre_preter_max, nb_prolengement_pret_max, nb_jour_prolongement_max) VALUES
(14, 3, 2, 7),
(21, 5, 3, 10),
(30, 10, 5, 14),
(7, 2, 1, 3),
(45, 15, 10, 21);

-- Insertion des donnees dans la table Statut
INSERT INTO Statut (nom) VALUES
('Active'),
('Inactive'),
('En attente'),
('Confirme'),
('Annule'),
('Suspendue'),
('Terminee'),
('En cours'),
('Expiree'),
('Reportee');

-- Insertion des donnees dans la table Role
INSERT INTO Role (nom) VALUES
('bibliothecaire'),
('adherent'),
('admin');

-- Insertion des donnees dans la table Abonnement
INSERT INTO Abonnement (mois, annee, tarif) VALUES
(1, 2024, 15.00),
(3, 2024, 40.00),
(6, 2024, 75.00),
(12, 2024, 140.00),
(1, 2023, 12.00),
(3, 2023, 35.00),
(6, 2023, 65.00),
(12, 2023, 120.00),
(1, 2025, 18.00),
(6, 2025, 85.00);

-- Insertion des donnees dans la table Jour_Ferie
INSERT INTO Jour_Ferie (description, date_jf) VALUES
('Nouvel An', '2024-01-01'),
('Fete du Travail', '2024-05-01'),
('Fete Nationale', '2024-06-26'),
('Assomption', '2024-08-15'),
('Toussaint', '2024-11-01'),
('Noel', '2024-12-25'),
('Paques', '2024-03-31'),
('Lundi de Paques', '2024-04-01'),
('Ascension', '2024-05-09'),
('Pentecote', '2024-05-19');

-- Insertion des donnees dans la table Regle_Jour_Ferie
INSERT INTO Regle_Jour_Ferie (comportement, date_modif) VALUES
(1, '2024-01-01 00:00:00'),
(0, '2024-02-15 10:30:00'),
(1, '2024-03-01 14:45:00'),
(2, '2024-04-15 09:20:00'),
(1, '2024-05-01 00:00:00'),
(0, '2024-06-15 16:30:00'),
(1, '2024-07-01 12:00:00'),
(2, '2024-08-15 00:00:00'),
(1, '2024-09-01 08:15:00'),
(0, '2024-10-15 17:45:00');

-- Insertion des donnees dans la table Etat
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

-- Insertion des donnees dans la table Etat_Exemplaire
INSERT INTO Etat_Exemplaire (date_modif, id_exemplaire, id_etat) VALUES
('2024-01-15 10:00:00', 1, 1),
('2024-01-16 11:30:00', 2, 2),
('2024-01-17 14:20:00', 3, 1),
('2024-01-18 09:45:00', 4, 3),
('2024-01-19 16:15:00', 5, 1),
('2024-01-20 13:30:00', 6, 2),
('2024-01-21 08:00:00', 7, 1),
('2024-01-22 15:45:00', 8, 4),
('2024-01-23 12:10:00', 9, 1),
('2024-01-24 17:30:00', 10, 2);

-- Insertion des donnees dans la table Penalite
INSERT INTO Penalite (nb_jour_de_penalite) VALUES
(3),
(7),
(14),
(30),
(1),
(5),
(10),
(21),
(45),
(60);

-- Insertion des donnees dans la table Profil
INSERT INTO Profil (nom, id_regle) VALUES
('etudiant', 1),
('professeur', 1),
('autre', 1);

-- Insertion des donnees dans la table Utilisateur
INSERT INTO Utilisateur (username, mdp, id_role) VALUES
('admin', 'admin123', 3),
('biblio1', 'biblio123', 1),
('biblio2', 'biblio456', 1),
('user1', 'user123', 2),
('user2', 'user456', 2),
('user3', 'user789', 2),
('admin2', 'admin456', 3),
('biblio3', 'biblio789', 1),
('user4', 'user101', 2),
('user5', 'user202', 2),
('biblio4', 'biblio101', 1),
('user6', 'user303', 2),
('admin3', 'admin789', 3),
('biblio5', 'biblio202', 1),
('user7', 'user404', 2);

-- Insertion des donnees dans la table Penalite_Profil
INSERT INTO Penalite_Profil (date_modif, id_penalite, id_profil) VALUES
('2024-01-01 00:00:00', 1, 1),
('2024-01-15 10:30:00', 2, 2),
('2024-02-01 14:45:00', 3, 3),
('2024-02-15 09:20:00', 1, 1),
('2024-03-01 16:30:00', 4, 2),
('2024-03-15 12:00:00', 2, 3),
('2024-04-01 08:15:00', 5, 1),
('2024-04-15 17:45:00', 3, 2),
('2024-05-01 11:20:00', 1, 3),
('2024-05-15 15:30:00', 6, 1);

-- Insertion des donnees dans la table Adherent
INSERT INTO Adherent (nom, prenom, date_de_naissance, id_utilisateur, id_profil) VALUES
('Dupont', 'Jean', '1990-05-15', 4, 1),
('Martin', 'Marie', '1985-08-22', 5, 2),
('Bernard', 'Pierre', '1978-12-03', 6, 3),
('Durand', 'Sophie', '1992-03-18', NULL, 1),
('Moreau', 'Luc', '1988-07-09', NULL, 2),
('Simon', 'Claire', '1995-11-12', NULL, 3),
('Michel', 'Paul', '1983-04-27', NULL, 1),
('Lefebvre', 'Anne', '1991-09-14', NULL, 2),
('Fournier', 'Marc', '1987-01-30', NULL, 3),
('Girard', 'Julie', '1993-06-25', NULL, 1),
('Morel', 'David', '1982-10-08', NULL, 2),
('Nicolas', 'Laura', '1989-02-17', NULL, 3),
('Petit', 'Thomas', '1994-08-05', NULL, 1),
('Rousseau', 'Emma', '1986-12-19', NULL, 2),
('Robert', 'Alexandre', '1990-04-12', NULL, 3);

-- Insertion des donnees dans la table Reservation
INSERT INTO Reservation (date_reservation, date_debut_reservation, date_fin_reservation, id_exemplaire, id_adherent) VALUES
('2024-01-10 09:00:00', '2024-01-15 00:00:00', '2024-01-22 23:59:59', 1, 1),
('2024-01-11 10:30:00', '2024-01-16 00:00:00', '2024-01-23 23:59:59', 3, 2),
('2024-01-12 14:15:00', '2024-01-17 00:00:00', '2024-01-24 23:59:59', 5, 3),
('2024-01-13 11:45:00', '2024-01-18 00:00:00', '2024-01-25 23:59:59', 7, 4),
('2024-01-14 16:20:00', '2024-01-19 00:00:00', '2024-01-26 23:59:59', 9, 5),
('2024-01-15 08:30:00', '2024-01-20 00:00:00', '2024-01-27 23:59:59', 11, 6),
('2024-01-16 13:10:00', '2024-01-21 00:00:00', '2024-01-28 23:59:59', 13, 7),
('2024-01-17 15:45:00', '2024-01-22 00:00:00', '2024-01-29 23:59:59', 15, 8),
('2024-01-18 12:00:00', '2024-01-23 00:00:00', '2024-01-30 23:59:59', 17, 9),
('2024-01-19 17:30:00', '2024-01-24 00:00:00', '2024-01-31 23:59:59', 19, 10);

-- Insertion des donnees dans la table Sanction
INSERT INTO Sanction (date_debut, date_fin, date_sanction, motif, id_adherent) VALUES
('2024-01-15 00:00:00', '2024-01-18 23:59:59', '2024-01-15 10:00:00', 'Retard de restitution', 1),
('2024-01-20 00:00:00', '2024-01-27 23:59:59', '2024-01-20 14:30:00', 'Livre endommage', 3),
('2024-02-01 00:00:00', '2024-02-15 23:59:59', '2024-02-01 09:15:00', 'Non respect des regles', 5),
('2024-02-10 00:00:00', '2024-02-13 23:59:59', '2024-02-10 16:45:00', 'Retard prolonge', 7),
('2024-02-15 00:00:00', '2024-03-01 23:59:59', '2024-02-15 11:20:00', 'Comportement inapproprie', 9),
('2024-02-20 00:00:00', '2024-02-25 23:59:59', '2024-02-20 13:30:00', 'Livre perdu', 2),
('2024-03-01 00:00:00', '2024-03-08 23:59:59', '2024-03-01 08:45:00', 'Retard multiple', 4),
('2024-03-05 00:00:00', '2024-03-12 23:59:59', '2024-03-05 15:10:00', 'Non restitution', 6),
('2024-03-10 00:00:00', '2024-03-17 23:59:59', '2024-03-10 12:30:00', 'Degat materiel', 8),
('2024-03-15 00:00:00', '2024-03-30 23:59:59', '2024-03-15 17:00:00', 'Violation des conditions', 10);

-- Insertion des donnees dans la table Statut_Reservation
INSERT INTO Statut_Reservation (date_modif, id_reservation, id_statut) VALUES
('2024-01-10 09:00:00', 1, 3),
('2024-01-15 00:00:00', 1, 4),
('2024-01-11 10:30:00', 2, 3),
('2024-01-16 00:00:00', 2, 4),
('2024-01-12 14:15:00', 3, 3),
('2024-01-13 11:45:00', 4, 3),
('2024-01-18 00:00:00', 4, 5),
('2024-01-14 16:20:00', 5, 3),
('2024-01-19 00:00:00', 5, 4),
('2024-01-15 08:30:00', 6, 3);

-- Insertion des donnees dans la table Pret
INSERT INTO Pret (date_debut, date_fin, id_exemplaire, id_adherent, id_type) VALUES
('2024-01-15 10:00:00', '2024-01-29 23:59:59', 2, 1, 1),
('2024-01-16 11:30:00', '2024-01-30 23:59:59', 4, 2, 1),
('2024-01-17 14:20:00', '2024-02-07 23:59:59', 6, 3, 2),
('2024-01-18 09:45:00', '2024-02-08 23:59:59', 8, 4, 1),
('2024-01-19 16:15:00', '2024-02-09 23:59:59', 10, 5, 3),
('2024-01-20 13:30:00', '2024-02-10 23:59:59', 12, 6, 1),
('2024-01-21 08:00:00', '2024-02-11 23:59:59', 14, 7, 2),
('2024-01-22 15:45:00', '2024-02-12 23:59:59', 16, 8, 1),
('2024-01-23 12:10:00', '2024-02-13 23:59:59', 18, 9, 4),
('2024-01-24 17:30:00', '2024-02-14 23:59:59', 20, 10, 1),
('2024-01-25 09:15:00', '2024-02-15 23:59:59', 1, 11, 1),
('2024-01-26 14:45:00', '2024-02-16 23:59:59', 3, 12, 2),
('2024-01-27 11:20:00', '2024-02-17 23:59:59', 5, 13, 1),
('2024-01-28 16:30:00', '2024-02-18 23:59:59', 7, 14, 3),
('2024-01-29 13:10:00', '2024-02-19 23:59:59', 9, 15, 1);

-- Insertion des donnees dans la table Rendu
INSERT INTO Rendu (date_du_rendu, id_pret) VALUES
('2024-01-29 14:30:00', 1),
('2024-01-30 16:15:00', 2),
('2024-02-07 10:45:00', 3),
('2024-02-08 13:20:00', 4),
('2024-02-09 11:30:00', 5),
('2024-02-10 15:45:00', 6),
('2024-02-11 09:15:00', 7),
('2024-02-12 17:30:00', 8),
('2024-02-13 12:45:00', 9);

-- Insertion des donnees dans la table Prolongement
INSERT INTO Prolongement (nouveau_date_fin_pret, date_prolongement, id_pret) VALUES
('2024-02-21 23:59:59', '2024-02-14 10:00:00', 10),
('2024-02-22 23:59:59', '2024-02-15 11:30:00', 11),
('2024-02-23 23:59:59', '2024-02-16 14:15:00', 12),
('2024-02-24 23:59:59', '2024-02-17 16:45:00', 13),
('2024-02-25 23:59:59', '2024-02-18 13:20:00', 14),
('2024-02-26 23:59:59', '2024-02-19 15:30:00', 15);

-- Insertion des donnees dans la table Type_Exemplaire_Pret
INSERT INTO Type_Exemplaire_Pret (id_exemplaire, id_type) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 1),
(6, 3),
(7, 1),
(8, 4),
(9, 5),
(10, 1),
(11, 2),
(12, 1),
(13, 3),
(14, 1),
(15, 4),
(16, 1),
(17, 5),
(18, 1),
(19, 1),
(20, 2);

-- Insertion des donnees dans la table Abonnement_Adherent
INSERT INTO Abonnement_Adherent (id_adherent, id_abonnement, date_de_payement) VALUES
(1, 1, '2024-01-01 10:00:00'),
(2, 2, '2024-01-15 14:30:00'),
(3, 3, '2024-01-10 09:15:00'),
(4, 1, '2024-01-05 16:45:00'),
(5, 4, '2024-01-20 11:20:00'),
(6, 2, '2024-01-12 13:30:00'),
(7, 1, '2024-01-08 15:45:00'),
(8, 3, '2024-01-25 08:30:00'),
(9, 2, '2024-01-18 17:15:00'),
(10, 1, '2024-01-22 12:45:00'),
(11, 4, '2024-01-30 10:30:00'),
(12, 2, '2024-01-14 14:00:00'),
(13, 1, '2024-01-28 16:20:00'),
(14, 3, '2024-01-16 11:45:00'),
(15, 2, '2024-01-26 13:15:00');