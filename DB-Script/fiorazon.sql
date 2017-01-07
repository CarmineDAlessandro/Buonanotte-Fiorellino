
drop database IF EXISTS  fiorazon;
create database fiorazon;
use fiorazon;

DROP TABLE IF EXISTS utente;
CREATE TABLE utente (
	ruolo ENUM('utente','amministratore') not null,
	nome char(30),
	cognome char(30),
	eMail varchar(30) not null,
	codiceFiscale varchar(16) not null,
	dataNascita varchar(16) not null,
	cittaNascita varchar(40),
	cittaResidenza varchar(40),
	via	 varchar(30),
	numeroCivico int(4),
	cap int(11),
	username varchar(30) not null,
	password varchar(30) not null,
	constraint chiavePrimaria1 PRIMARY KEY (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS prodotto;
CREATE TABLE prodotto (
	idProdotto varchar(30) not null,
	urlImmagine varchar(50) ,
	nome varchar(30) not null,
	quantita int(15),
	descrizione varchar(30),
	prezzo double(15,2),
	constraint chiavePrimaria3 	PRIMARY KEY (idProdotto)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ordine;
CREATE TABLE ordine (
	utenteOrdine varchar(30) not null,
	prezzoTotale double(15,2) ,
	stato ENUM('Da Spedire','Spedito','Arrivato')not null,
	id int(30) not null AUTO_INCREMENT,
	constraint chiavePrimaria4 PRIMARY KEY (id),
	constraint chiaveEsterna1 foreign key (utenteOrdine) references utente(username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS prodottiOrdine;
CREATE TABLE prodottiOrdine (
	numeroLista int not null AUTO_INCREMENT,
	idOrdine int(30) not null,
	idProdottoLista varchar(30) not null,
	numeroProdotto int(10),
	constraint chiavePrimaria5 PRIMARY KEY (numeroLista),
	constraint chiaveEsterna3 foreign key (idOrdine) references ordine(id),
	constraint chiaveEsterna4 foreign key (idProdottoLista) references prodotto(idProdotto)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS carrello;
CREATE TABLE carrello (
	utenteCarrello varchar(30) not null,
	idCarrello int(30) not null AUTO_INCREMENT,
	constraint chiavePrimaria7 PRIMARY KEY (idCarrello),
	constraint chiaveEsterna6 foreign key (utenteCarrello) references utente(username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS prodottiCarrello;
CREATE TABLE prodottiCarrello (
	numeroSequenza int not null AUTO_INCREMENT,
	numeroCarrello int(30) not null,
	idProdottoCarrello varchar(30) not null,
	numeroProdotto int(10),
	constraint chiavePrimaria8 PRIMARY KEY (numeroSequenza),
	constraint chiaveEsterna7 foreign key (numeroCarrello) references carrello(idCarrello),
	constraint chiaveEsterna8 foreign key (idProdottoCarrello) references prodotto(idProdotto)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into utente (ruolo,nome,cognome,eMail,codiceFiscale,dataNascita,cittaNascita,cittaResidenza,via,numeroCivico,cap,username,password)
values
('utente','carmelo','sottile','carmelosottile@libero.it','crmlstt993re138h','1995-01-15','roma','salerno','via libertas',24,82034,'carmelosottile','pinko'),
('utente','rocco','miele','roccoc@libero.it','rocrtfr993re138h','1922-07-10','trieste','udine','via roma',81,82034,'roccomiele1','pinko'),
('utente','alessandra','zullo','alex@libero.it','lkjhstt993re138h','1931-08-21','roma','salerno','via libertas',24,82034,'alessandrazullo1','pinko'),
('utente','pamela','anderson','carmelosottile@libero.it','crmlstt993re138h','1995-01-15','roma','salerno','via libertas',24,82034,'pamelaAnderson1','pinko'),
('utente','eleonora','daurea','ele@libero.it','elldrtt112re138f','1980-04-19','napoli','casandrino','via vittoria',43,82022,'eleonoradaurea1','pinko'),
('amministratore','giuseppe','siani','siani@outlook.it','gppdrtt112re138f','1995-08-21','salerno','roccapiemonte','via pace',84,82082,'pinko','pippo');


insert into prodotto (idProdotto,urlImmagine,nome,quantita,descrizione,prezzo)
values
('f01','./Immagini/','margerita','40','la margerita  ......',2.3),
('f02','./Immagini/','geranio','40','la geranio  ......',4.1),
('f03','./Immagini/','viola','40','la viola  ......',1.0),
('f04','./Immagini/','gelsomino','40','la gelsomino  ......',3.5),
('f05','./Immagini/','cardenia','40','la cardenia ......',6.0),
('f06','./Immagini/','fiordaliso','40','la fiordaliso  ......',0.9),
('f07','./Immagini/','ciclamino','40','la ciclamino  ......',0.50),
('f08','./Immagini/','melo','40','la melo  ......',9.30),
('f09','./Immagini/','tulipano','40','la tulipano  ......',0.55),
('f10','./Immagini/','bocca di leone','40','la bocca di leone  ......',0.20),
('f11','./Immagini/','crisantemo','40','la crisantemo  ......',0.9),
('f12','./Immagini/','lilium','40','la lilium  ......',5.5),
('f13','./Immagini/','rosa','40','la rosa  ......',4.4);

insert into ordine (utenteOrdine,prezzoTotale,stato)
values
('roccomiele1',34.5,'Da Spedire'),
('alessandrazullo1',50.1,'Spedito'),
('pamelaAnderson1',20.44,'Arrivato'),
('eleonoradaurea1',5.0,'Da Spedire'),
('carmelosottile',26.7,'Spedito'),
('carmelosottile',77.7,'Da Spedire');

insert into prodottiOrdine (idOrdine,idProdottoLista,numeroProdotto)
values
(1,'f10',4),
(1,'f01',30),
(2,'f13',51),
(2,'f12',66),
(3,'f09',24),
(3,'f05',12),
(4,'f08',8),
(4,'f02',71),
(5,'f04',20),
(5,'f04',20),
(6,'f08',18),
(6,'f07',18);

insert into carrello(utenteCarrello)
values
('carmelosottile'),
('roccomiele1'),
('alessandrazullo1'),
('pamelaAnderson1'),
('eleonoradaurea1');

insert into prodottiCarrello(numeroCarrello,idProdottoCarrello,numeroProdotto)
values
(1,'f01',4),
(1,'f02',7),
(2,'f03',2),
(2,'f04',6),
(3,'f05',2),
(3,'f06',3),
(4,'f07',11),
(4,'f08',16),
(5,'f09',12),
(5,'f10',15);
