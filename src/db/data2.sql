DROP DATABASE if exists pg2;
CREATE DATABASE pg2;
\c pg2;

CREATE TABLE Restriction (
    id_restriction SERIAL PRIMARY KEY,
    age_min INT NOT NULL
);


CREATE TABLE Livre (
    id_livre SERIAL PRIMARY KEY,
    titre VARCHAR(50) NOT NULL,
    auteur VARCHAR(50),
    id_restriction INT UNIQUE,
    FOREIGN KEY (id_restriction) REFERENCES Restriction(id_restriction)
);

CREATE TABLE
    Exemplaire (
        id_exemplaire SERIAL,
        code VARCHAR(250),
        id_livre INT NOT NULL,
        PRIMARY KEY (id_exemplaire),
        UNIQUE (code),
        FOREIGN KEY (id_livre) REFERENCES Livre (id_livre)
    );

CREATE TABLE
    Type (
        id_type SERIAL,
        nom VARCHAR(50) NOT NULL,
        PRIMARY KEY (id_type)
    );

CREATE TABLE
    Regle (
        id_regle SERIAL,
        nb_jour_duree_pret_max INT NOT NULL,
        nb_livre_preter_max INT NOT NULL,
        nb_prolengement_pret_max INT NOT NULL,
        nb_jour_prolongement_max INT NOT NULL,
        PRIMARY KEY (id_regle)
    );

CREATE TABLE
    Statut (
        id_statut SERIAL,
        nom VARCHAR(50) NOT NULL,
        PRIMARY KEY (id_statut),
        UNIQUE (nom)
    );

CREATE TABLE
    Role (
        id_role SERIAL,
        nom VARCHAR(50) NOT NULL,
        PRIMARY KEY (id_role),
        UNIQUE (nom)
    );

CREATE TABLE
    Abonnement (
        id_abonnement SERIAL,
        mois INT NOT NULL,
        annee INT NOT NULL,
        tarif DECIMAL(25, 2) NOT NULL,
        PRIMARY KEY (id_abonnement)
    );

CREATE TABLE
    Jour_Ferie (
        id_jour_ferie SERIAL,
        description VARCHAR(50),
        date_jf DATE NOT NULL,
        PRIMARY KEY (id_jour_ferie)
    );

CREATE TABLE
    Regle_Jour_Ferie (
        id_regle_jour_ferie SERIAL,
        comportement_ INT NOT NULL,
        date_modif TIMESTAMP NOT NULL,
        PRIMARY KEY (id_regle_jour_ferie)
    );

CREATE TABLE
    Etat (
        id_etat SERIAL,
        nom VARCHAR(50) NOT NULL,
        PRIMARY KEY (id_etat)
    );

CREATE TABLE
    Etat_Exemplaire (
        id_etat_exemplaire SERIAL,
        date_modif TIMESTAMP NOT NULL,
        id_exemplaire INT NOT NULL,
        id_etat INT NOT NULL,
        PRIMARY KEY (id_etat_exemplaire),
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_etat) REFERENCES Etat (id_etat)
    );

CREATE TABLE
    Penalite (
        id_penalite SERIAL,
        nb_jour_de_penalite INT NOT NULL,
        PRIMARY KEY (id_penalite)
    );

CREATE TABLE
    Profil (
        id_profil SERIAL,
        nom VARCHAR(50) NOT NULL,
        id_regle INT NOT NULL,
        PRIMARY KEY (id_profil),
        FOREIGN KEY (id_regle) REFERENCES Regle (id_regle)
    );

CREATE TABLE
    Utilisateur (
        id_utilisateur SERIAL,
        username VARCHAR(50) NOT NULL,
        mdp VARCHAR(50) NOT NULL,
        id_role INT NOT NULL,
        PRIMARY KEY (id_utilisateur),
        UNIQUE (username),
        FOREIGN KEY (id_role) REFERENCES Role (id_role)
    );

CREATE TABLE
    Penalite_Profil (
        id_penalite_profil SERIAL,
        date_modif TIMESTAMP NOT NULL,
        id_penalite INT NOT NULL,
        id_profil INT NOT NULL,
        PRIMARY KEY (id_penalite_profil),
        FOREIGN KEY (id_penalite) REFERENCES Penalite (id_penalite),
        FOREIGN KEY (id_profil) REFERENCES Profil (id_profil)
    );

CREATE TABLE Adherent (
    id_adherent SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    date_de_naissance DATE NOT NULL,
    id_utilisateur INT UNIQUE,
    id_profil INT NOT NULL,
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur (id_utilisateur),
    FOREIGN KEY (id_profil) REFERENCES Profil (id_profil)
);

CREATE TABLE Statut_Adherent (
    id_statut_adherent SERIAL PRIMARY KEY,
    id_adherent INT NOT NULL,
    nom VARCHAR(20) NOT NULL, 
    date_debut DATE NOT NULL,
    date_fin DATE, 
    FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent)
);

CREATE TABLE
    Reservation (
        id_reservation SERIAL,
        date_reservation TIMESTAMP NOT NULL,
        date_debut_reservation TIMESTAMP NOT NULL,
        date_fin_reservation TIMESTAMP NOT NULL,
        id_exemplaire INT NOT NULL,
        id_adherent INT NOT NULL,
        PRIMARY KEY (id_reservation),
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent)
    );

CREATE TABLE
    Sanction (
        id_sanction SERIAL,
        date_debut TIMESTAMP NOT NULL,
        date_fin TIMESTAMP NOT NULL,
        id_adherent INT NOT NULL,
        PRIMARY KEY (id_sanction),
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent)
    );

CREATE TABLE
    Statut_Reservation (
        id_statut_reservation SERIAL,
        date_modif TIMESTAMP NOT NULL,
        id_reservation INT NOT NULL,
        id_statut INT NOT NULL,
        PRIMARY KEY (id_statut_reservation),
        FOREIGN KEY (id_reservation) REFERENCES Reservation (id_reservation),
        FOREIGN KEY (id_statut) REFERENCES Statut (id_statut)
    );

CREATE TABLE
    Pret (
        id_pret SERIAL,
        date_debut TIMESTAMP NOT NULL,
        date_fin TIMESTAMP NOT NULL,
        id_exemplaire INT NOT NULL,
        id_adherent INT NOT NULL,
        id_type INT NOT NULL,
        PRIMARY KEY (id_pret),
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent),
        FOREIGN KEY (id_type) REFERENCES Type (id_type)
    );

CREATE TABLE
    Rendu (
        id_rendu SERIAL,
        date_du_rendu TIMESTAMP NOT NULL,
        id_pret INT NOT NULL,
        PRIMARY KEY (id_rendu),
        UNIQUE (id_pret),
        FOREIGN KEY (id_pret) REFERENCES Pret (id_pret)
    );

CREATE TABLE
    Prolongement (
        id_prolongement SERIAL,
        nouveau_date_fin_pret TIMESTAMP NOT NULL,
        date_prolongement TIMESTAMP NOT NULL,
        id_pret INT NOT NULL,
        PRIMARY KEY (id_prolongement),
        FOREIGN KEY (id_pret) REFERENCES Pret (id_pret)
    );

CREATE TABLE
    Type_Exemplaire_Pret (
        id_exemplaire INT,
        id_type INT,
        PRIMARY KEY (id_exemplaire, id_type),
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_type) REFERENCES Type (id_type)
    );

CREATE TABLE
    Abonnement_Adherent (
        id_adherent INT,
        id_abonnement INT,
        date_de_payement TIMESTAMP NOT NULL,
        PRIMARY KEY (id_adherent, id_abonnement),
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent),
        FOREIGN KEY (id_abonnement) REFERENCES Abonnement (id_abonnement)
    );


INSERT INTO Regle (
    nb_jour_duree_pret_max,
    nb_livre_preter_max,
    nb_prolengement_pret_max,
    nb_jour_prolongement_max
) VALUES 
(14, 5, 2, 7),   -- id_regle = 1
(7, 3, 1, 3),    -- id_regle = 2
(21, 10, 3, 10); -- id_regle = 3

INSERT INTO Profil (nom, id_regle) VALUES 
('etudiant', 1),
('professeur', 3),
('autre', 2);

INSERT INTO Adherent (nom, prenom, date_de_naissance, id_utilisateur, id_profil) VALUES 
('Dupont', 'Jean', '1998-03-21', NULL, 1),
('Martin', 'Sophie', '1985-06-12', NULL, 2),
('Durand', 'Luc', '2002-11-05', NULL, 3);

INSERT INTO Penalite (nb_jour_de_penalite) VALUES
(1),
(3),
(7),
(14),
(30);

INSERT INTO Penalite_Profil (date_modif, id_penalite, id_profil) VALUES
('2025-07-01 08:00:00', 2, 1),  
('2025-07-01 08:05:00', 4, 2),  
('2025-07-01 08:10:00', 1, 3);  

SELECT
     a.id_adherent,
     a.nom,
     a.prenom,
     p.id_penalite,
     pe.nb_jour_de_penalite
 FROM
     Adherent a
 JOIN Profil pr ON a.id_profil = pr.id_profil
 JOIN Penalite_Profil p ON pr.id_profil = p.id_profil
 JOIN Penalite pe ON p.id_penalite = pe.id_penalite
 WHERE
     a.id_adherent = 1;