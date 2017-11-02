--SELECT sum(numbackends) FROM pg_stat_database where datname='dbrachid_asli';
--select pg_terminate_backend(pid) from pg_stat_activity where datname='dbrachid_asli';
--SELECT pg_terminate_backend(pg_stat_activity.procpid) FROM pg_stat_get_activity(NULL::integer) WHERE datid=(SELECT oid from pg_database where datname = 'dbrachid_asli');

DROP SCHEMA pae CASCADE ;
--DROP USER rachid_asli ;
--CREATE USER rachid_asli SUPERUSER ENCRYPTED PASSWORD 'Mimi_210';
CREATE SCHEMA pae;

CREATE TABLE pae.utilisateurs(
  id_utilisateur SERIAL CONSTRAINT id_utilisateur_utilisateurs PRIMARY KEY,
  pseudo VARCHAR(25),
  nom VARCHAR(100),
  prenom VARCHAR(100),
  email VARCHAR(100),
  date_inscription TIMESTAMP,
  mdp VARCHAR(1000),
  responsable BOOLEAN,
  num_version INTEGER,
  nb_tentatives_connexions INTEGER,
  date_derniere_tentative TIMESTAMP
);

CREATE TABLE pae.entreprises(
  id_entreprise SERIAL CONSTRAINT id_entreprise_entreprises PRIMARY KEY,
  id_createur INTEGER CONSTRAINT id_createur_entreprises REFERENCES pae.utilisateurs(id_utilisateur),
  nom_entreprise VARCHAR(150),
  date_creation TIMESTAMP,
  date_derniere_participation_paye DATE,
  rue VARCHAR(150),
  numero VARCHAR(10),
  boite VARCHAR(10),
  code_postal VARCHAR(10),
  commune VARCHAR(75),
  num_version INTEGER
);

CREATE TABLE pae.personnes_contact(
  id_personne SERIAL CONSTRAINT id_personne_personne_contact PRIMARY KEY,
  id_entreprise INTEGER CONSTRAINT id_entreprise_personne_contact REFERENCES pae.entreprises(id_entreprise),
  nom VARCHAR(100),
  prenom VARCHAR(100),
  email VARCHAR(100),
  telephone VARCHAR(15),
  actif BOOLEAN,
  num_version INTEGER
);

CREATE TABLE pae.journees(
  id_journee SERIAL CONSTRAINT id_journee_journees PRIMARY KEY,
  id_createur INTEGER CONSTRAINT id_createur_journees REFERENCES pae.utilisateurs(id_utilisateur),
  date_je DATE,
  cloturee BOOLEAN,
  num_version INTEGER
);

CREATE TABLE pae.participations(
  id_participation SERIAL CONSTRAINT id_participation_participations PRIMARY KEY,
  id_journee INTEGER CONSTRAINT id_journee_participations REFERENCES pae.journees(id_journee),
  id_entreprise INTEGER CONSTRAINT id_entreprise_participations REFERENCES pae.entreprises(id_entreprise),
  etat VARCHAR(15),
  evolution VARCHAR(15),
  annulee BOOLEAN,
  num_version INTEGER
);

CREATE TABLE pae.presences(
  id_participation INTEGER CONSTRAINT id_participation_presences REFERENCES pae.participations(id_participation),
  id_personne INTEGER CONSTRAINT id_personne_presences REFERENCES pae.personnes_contact(id_personne),
  num_version INTEGER,
  CONSTRAINT pk_presences PRIMARY KEY (id_personne, id_participation)
);

ALTER SEQUENCE pae.utilisateurs_id_utilisateur_seq RESTART WITH 1;
ALTER SEQUENCE pae.entreprises_id_entreprise_seq RESTART WITH 1;
ALTER SEQUENCE pae.personnes_contact_id_personne_seq RESTART WITH 1;
ALTER SEQUENCE pae.journees_id_journee_seq RESTART WITH 1;
ALTER SEQUENCE pae.participations_id_participation_seq RESTART WITH 1;
