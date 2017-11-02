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

--ALTER SEQUENCE pae.utilisateurs_num_version_seq RESTART WITH 1;
--ALTER SEQUENCE pae.entreprises_num_version_seq RESTART WITH 1;
--ALTER SEQUENCE pae.personnes_contact_num_version_seq RESTART WITH 1;
--ALTER SEQUENCE pae.journees_num_version_seq RESTART WITH 1;
--ALTER SEQUENCE pae.presences_num_version_seq RESTART WITH 1;

INSERT INTO pae.utilisateurs VALUES(default,'dgrolaux','Grolaux','Donatien','d.grolaux@vinci.be',now(),'$2a$12$j7HmzOirZhh47/R0Hr9ULOeRC8h9yxU0O.NUjaggVKl/eCtpNBa4y',true,0,0,now());
INSERT INTO pae.utilisateurs VALUES(default,'blehmannn','Lehmann','Brigitte','b.lehmann@vinci.be',now(),'$2a$12$j7HmzOirZhh47/R0Hr9ULOeRC8h9yxU0O.NUjaggVKl/eCtpNBa4y',true,0,0,now());

INSERT INTO pae.journees VALUES(default,1,'2013-11-13',true,0);
INSERT INTO pae.journees VALUES(default,1,'2014-11-12',true,0);
INSERT INTO pae.journees VALUES(default,2,'2015-10-28',true,0);
INSERT INTO pae.journees VALUES(default,1,'2016-10-26',true,0);

INSERT INTO pae.entreprises VALUES(default,1,'Accenture',now(),null,'Waterloolaan','16',null,'B-1000','Brussels',0);
INSERT INTO pae.entreprises VALUES(default,1,'CodeItBlue',now(),null,'Avenue de l''espérance','40b',null,'1348','Louvain-la-neuve',0);
INSERT INTO pae.entreprises VALUES(default,1,'STERIA',now(),null,'Boulevard du Souverain','36',null,'B-1170','Brussels',0);
INSERT INTO pae.entreprises VALUES(default,2,'Eezee-it AXIS PARC',now(),null,'Rue André Dumont','5',null,'B-1435','Mont-Saint-Guibert',0);
INSERT INTO pae.entreprises VALUES(default,2,'Bewan',now(),null,'Drève Richelle','161 L','b46','B-1410','Waterloo',0);

INSERT INTO pae.personnes_contact VALUES(default,1,'De Troyer','Stéphanie','stéphanie.de.troyer@accenture.com',null,true,0);
INSERT INTO pae.personnes_contact VALUES(default,1,'Van Dyck','Marijke','marijke.vandyck@accenture.com',null,true,0);
INSERT INTO pae.personnes_contact VALUES(default,1,'Marecaux','Aimée','aimee.marecaux@accenture.com',null,true,0);
INSERT INTO pae.personnes_contact VALUES(default,2,'Lepape','Vincent','Vincent.lepape@CodeItBlue.com','0479979505',true,0);
INSERT INTO pae.personnes_contact VALUES(default,3,'Alvarez','Roberto','roberto.alvarez@steria.be',null,true,0);
INSERT INTO pae.personnes_contact VALUES(default,4,'Rigo','Nicolas','nicolas.rigo@eezee-it.com','+32478880255',true,0);
INSERT INTO pae.personnes_contact VALUES(default,5,'BRASSINNE','Virginie','Virginie.BRASSINNE@bewan.be',null,true,0);
INSERT INTO pae.personnes_contact VALUES(default,5,'Croiset','Isabelle','isabelle.croiset@bewan.be',null,true,0);
INSERT INTO pae.personnes_contact VALUES(default,5,'Dedecker','Bénédicte','drh@bewan.be',null,true,0);

INSERT INTO pae.participations VALUES(default,2,1,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,3,1,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,4,1,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,1,2,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,2,2,'refusee',NULL,false,0);
INSERT INTO pae.participations VALUES(default,1,3,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,2,3,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,3,3,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,4,3,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,1,4,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,2,4,'confirmee','facturee',true,0);
INSERT INTO pae.participations VALUES(default,1,5,'confirmee','payee',false,0);
INSERT INTO pae.participations VALUES(default,2,5,'refusee',null,false,0);
INSERT INTO pae.participations VALUES(default,3,5,'refusee',null,false,0);
INSERT INTO pae.participations VALUES(default,4,5,'confirmee','payee',false,0);

INSERT INTO pae.presences VALUES(1,1,0);
INSERT INTO pae.presences VALUES(2,2,0);
INSERT INTO pae.presences VALUES(2,3,0);
INSERT INTO pae.presences VALUES(3,3,0);
INSERT INTO pae.presences VALUES(4,4,0);
INSERT INTO pae.presences VALUES(6,5,0);
INSERT INTO pae.presences VALUES(7,5,0);
INSERT INTO pae.presences VALUES(8,5,0);
INSERT INTO pae.presences VALUES(9,5,0);
INSERT INTO pae.presences VALUES(10,6,0);
INSERT INTO pae.presences VALUES(12,7,0);
INSERT INTO pae.presences VALUES(12,8,0);
INSERT INTO pae.presences VALUES(15,9,0);
