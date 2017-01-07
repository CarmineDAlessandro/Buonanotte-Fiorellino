
drop database IF EXISTS  fiorazonIs;
create database fiorazonIs;
use fiorazonIs;

DROP TABLE IF EXISTS utente;
CREATE TABLE utente (
	nome char(30),
	cognome char(30),
	eMail varchar(30) not null,
	codiceFiscale varchar(16) not null,
	dataNascita date not null,
	cittaNascita varchar(40),
	cittaResidenza varchar(40),
	Provincia varchar(2),
	via	 varchar(30),
	numeroCivico int(4),
	cap varchar(5),
	username varchar(30) not null,
	password varchar(30) not null,
	constraint chiavePrimaria1 PRIMARY KEY (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS amministratore;
CREATE TABLE amministratore (
	eMail varchar(30) not null,
	username varchar(30) not null,
	password varchar(30) not null,
	constraint chiavePrimaria1 PRIMARY KEY (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS prodotto;
CREATE TABLE prodotto (
	idProdotto int not null AUTO_INCREMENT,
	urlImmagine varchar(50) ,
	nome varchar(30) not null,
	quantita int(15),
	descrizione varchar(300),
	prezzo double(15,2),
	constraint chiavePrimaria3 	PRIMARY KEY (idProdotto)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ordine;
CREATE TABLE ordine (
	id int not null AUTO_INCREMENT,
	iban varchar (16),
	utenteOrdine varchar(30) not null,
	prezzoTotale double(15,2) ,
	stato ENUM('Da Spedire','Spedito','Arrivato')not null,
	constraint chiavePrimaria4 PRIMARY KEY (id),
	constraint chiaveEsterna1 foreign key (utenteOrdine) references utente(username) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS prodottiOrdine;
CREATE TABLE prodottiOrdine (
	numeroLista int not null AUTO_INCREMENT,
	idOrdine int not null,
	idProdottoLista int not null,
	numeroProdotto int(10),
	constraint chiavePrimaria5 PRIMARY KEY (numeroLista),
	constraint chiaveEsterna3 foreign key (idOrdine) references ordine(id) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint chiaveEsterna4 foreign key (idProdottoLista) references prodotto(idProdotto) ON DELETE CASCADE ON UPDATE CASCADE 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS prodottiCarrello;
CREATE TABLE prodottiCarrello (
	numeroSequenza int not null AUTO_INCREMENT,
	utenteCarrello varchar(30) not null,
	numeroProdotto int(10),
	quantitaCarrello int,
	constraint chiavePrimaria8 PRIMARY KEY (numeroSequenza),
	constraint chiaveEsterna7 foreign key (utenteCarrello) references utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint chiaveEsterna8 foreign key (idProdottoCarrello) references prodotto(idProdotto) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into utente (nome,cognome,eMail,codiceFiscale,dataNascita,cittaNascita,cittaResidenza,via,numeroCivico,Provincia,cap,username,password)
values
('carmelo','sottile','carmelosottile@libero.it','crmlstt993re138h','1995-01-15','roma','salerno','via libertas',24,'sa','82034','carmelosottile','pinko'),
('rocco','miele','roccoc@libero.it','rocrtfr993re138h','1922-07-10','trieste','udine','via roma',81,'sa','82034','roccomiele1','pinko'),
('alessandra','zullo','alex@libero.it','lkjhstt993re138h','1931-08-21','roma','salerno','via libertas',24,'sa','82034','alessandrazullo1','pinko'),
('pamela','anderson','carmelosottile@libero.it','crmlstt993re138h','1995-01-15','roma','salerno','via libertas',24,'sa','82034','pamelaAnderson1','pinko'),
('eleonora','daurea','ele@libero.it','elldrtt112re138f','1980-04-19','napoli','casandrino','via vittoria',43,'sa','82022','eleonoradaurea1','pinko');

insert into amministratore (eMail,username,password)
values
('siani@outlook.it','pinko','pippo');

insert into prodotto (idProdotto,urlImmagine,nome,quantita,descrizione,prezzo)
values
(1,'./Immagini/margerita.jpg','margerita','40','la margerita  ......',2.3),
(2,'./Immagini/geranio.jpg','geranio','40','la geranio  ......',4.1),
(3,'./Immagini/viola.jpg','viola','40','la viola  ......',1.0),
(4,'./Immagini/gelsomino.jpg','gelsomino','40','la gelsomino  ......',3.5),
(5,'./Immagini/gardenia.jpg','gardenia','40','la cardenia ......',6.0),
(6,'./Immagini/fiordaliso.jpg','fiordaliso','40','la fiordaliso  ......',0.9),
(7,'./Immagini/ciclamino.jpg','ciclamino','40','la ciclamino  ......',0.50),
(8,'./Immagini/melo.jpg','melo','40','la melo  ......',9.30),
(9,'./Immagini/tulipano.jpg','tulipano','40','la tulipano  ......',0.55),
(10,'./Immagini/boccaLeone.jpg','bocca di leone','40','la bocca di leone  ......',0.20),
(11,'./Immagini/crisantemo.jpg','crisantemo','40','la crisantemo  ......',0.9),
(12,'./Immagini/lilium.jpg','lilium','40','la lilium  ......',5.5),
(13,'./Immagini/rosa.jpg','rosa','40','la rosa  ......',4.4);

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
(1,10,4),
(1,1,30),
(2,13,51),
(2,12,66),
(3,9,24),
(3,5,12),
(4,8,8),
(4,2,71),
(5,4,20),
(5,4,20),
(6,8,18),
(6,7,18);

insert into carrello(utenteCarrello)
values
('carmelosottile'),
('roccomiele1'),
('alessandrazullo1'),
('pamelaAnderson1'),
('eleonoradaurea1');

insert into prodottiCarrello(numeroCarrello,idProdottoCarrello,numeroProdotto)
values
(1,1,4),
(1,2,7),
(2,3,2),
(2,4,6),
(3,5,2),
(3,6,3),
(4,7,11),
(4,8,16),
(5,9,12),
(5,10,15);
