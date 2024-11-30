CREATE DATABASE GestionPharmacie;

USE GestionPharmacie;

CREATE TABLE Adresse (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rue VARCHAR(255),
    codePostal VARCHAR(10),
    ville VARCHAR(50)
);

CREATE TABLE Utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    motDePasse VARCHAR(255),
    role ENUM('manager', 'utilisateur'),
    adresse_id INT,
    telephone VARCHAR(20),
    FOREIGN KEY (adresse_id) REFERENCES Adresse(id)
);

CREATE TABLE Mutuelle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    tauxRemboursement DECIMAL(5,2),
    adresse_id INT,
    departement VARCHAR(50),
    FOREIGN KEY (adresse_id) REFERENCES Adresse(id)
);

CREATE TABLE Medecin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroAgrement VARCHAR(50) UNIQUE,
    utilisateur_id INT,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    securiteSociale VARCHAR(15) UNIQUE,
    dateNaissance DATE,
    utilisateur_id INT,
    mutuelle_id INT,
    medecinTraitantId INT,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id),
    FOREIGN KEY (mutuelle_id) REFERENCES Mutuelle(id),
    FOREIGN KEY (medecinTraitantId) REFERENCES Medecin(id)
);


CREATE TABLE Specialiste (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroAgrement VARCHAR(50) UNIQUE,
    specialite ENUM('cardiologie', 'dermatologie', 'neurologie'),
    utilisateur_id INT,
    telephone VARCHAR(20),
    email VARCHAR(100),
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Medicament (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    categorie ENUM('antibiotique', 'antalgique', 'antiviral'),
    prix DECIMAL(10,2),
    dateMiseEnService DATE,
    quantite INT
);

CREATE TABLE Ordonnance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    medecin_id INT NULL,
    specialiste_id INT NULL,
    client_id INT,
    FOREIGN KEY (medecin_id) REFERENCES Medecin(id),
    FOREIGN KEY (specialiste_id) REFERENCES Specialiste(id),
    FOREIGN KEY (client_id) REFERENCES Client(id),
    CONSTRAINT chk_Ordonnance_Medecin_Specialiste CHECK (
        (medecin_id IS NOT NULL AND specialiste_id IS NULL) OR
        (medecin_id IS NULL AND specialiste_id IS NOT NULL)
    )
);

CREATE TABLE OrdonnanceMedicament (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ordonnance_id INT,
    medicament_id INT,
    quantitePrescrite INT,
    prixTotal DECIMAL(10,2),
    FOREIGN KEY (ordonnance_id) REFERENCES Ordonnance(id),
    FOREIGN KEY (medicament_id) REFERENCES Medicament(id)
);

CREATE TABLE RelationMedecinClient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    medecin_id INT,
    typeRelation ENUM('traitant', 'specialiste'),
    dateDebutRelation DATE,
    dateFinRelation DATE,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (medecin_id) REFERENCES Medecin(id)
);

CREATE TABLE AffiliationMutuelle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    mutuelle_id INT,
    dateAffiliation DATE,
    dateFinAffiliation DATE,
    tauxRemboursementPersonnalise DECIMAL(5,2),
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (mutuelle_id) REFERENCES Mutuelle(id)
);

CREATE TABLE Achat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateAchat DATE,
    client_id INT,
    medicament_id INT,
    quantiteAchat INT,
    montantTotal DECIMAL(10,2),
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (medicament_id) REFERENCES Medicament(id)
);


