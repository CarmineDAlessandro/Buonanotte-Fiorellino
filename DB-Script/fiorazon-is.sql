
drop database IF EXISTS  fiorazon;
create database fiorazon;
use fiorazon;


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


CREATE TABLE amministratore (
	eMail varchar(30) not null,
	username varchar(30) not null,
	password varchar(30) not null,
	constraint chiavePrimaria1 PRIMARY KEY (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE prodotto (
	idProdotto int not null AUTO_INCREMENT,
	urlImmagine varchar(50) ,
	nome varchar(30) not null,
	quantita int(15),
	descrizione varchar(300),
	prezzo double(15,2),
	constraint chiavePrimaria3 	PRIMARY KEY (idProdotto)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE ordine (
	id int not null AUTO_INCREMENT,
	iban varchar (16),
	utenteOrdine varchar(30) not null,
	prezzoTotale double(15,2) ,
	stato ENUM('Da Spedire','Spedito','Arrivato')not null,
	constraint chiavePrimaria4 PRIMARY KEY (id),
	constraint chiaveEsterna1 foreign key (utenteOrdine) references utente(username) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE prodottiOrdine (
	idOrdine int not null,
	idProdottoOrdine int not null,
	quantitàProdottoOrdine int,
	prezzo int,
    constraint chiavePrimaria5 primary key (idOrdine, idProdottoOrdine),
	constraint chiaveEsterna3 foreign key (idOrdine) references ordine(id) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint chiaveEsterna4 foreign key (idProdottoOrdine) references prodotto(idProdotto) ON DELETE CASCADE ON UPDATE CASCADE 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE carrello (
	numeroCarrello int not null AUTO_INCREMENT,
    usernameCarrello varchar(30) not null,
    constraint chiavePrimaria6 primary key (numeroCarrello),
    constraint chiaveEsterna5 foreign key (usernameCarrello) references utente(username) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE prodottiCarrello (
	numeroCarrello int not null,
    idProdottoCarrello int not null,
	quantitàCarrello int,
    constraint chiavePrimaria7 primary key (numeroCarrello, idProdottoCarrello),
	constraint chiaveEsterna7 foreign key (numeroCarrello) references carrello(numeroCarrello) ON DELETE CASCADE ON UPDATE CASCADE,
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
(1,'./Immagini/margerita.jpg','margerita','140','la margerita  ......',2.3),
(2,'./Immagini/geranio.jpg','geranio','140','la geranio  ......',4.1),
(3,'./Immagini/viola.jpg','viola','140','la viola  ......',1.0),
(4,'./Immagini/gelsomino.jpg','gelsomino','140','la gelsomino  ......',3.5),
(5,'./Immagini/gardenia.jpg','gardenia','140','la cardenia ......',6.0),
(6,'./Immagini/fiordaliso.jpg','fiordaliso','140','la fiordaliso  ......',0.9),
(7,'./Immagini/ciclamino.jpg','ciclamino','140','la ciclamino  ......',0.50),
(8,'./Immagini/melo.jpg','melo','140','la melo  ......',9.30),
(9,'./Immagini/tulipano.jpg','tulipano','140','la tulipano  ......',0.55),
(10,'./Immagini/boccaLeone.jpg','bocca di leone','140','la bocca di leone  ......',0.20),
(11,'./Immagini/crisantemo.jpg','crisantemo','140','la crisantemo  ......',0.9),
(12,'./Immagini/lilium.jpg','lilium','140','la lilium  ......',5.5),
(13,'./Immagini/rosa.jpg','rosa','140','la rosa  ......',4.4);

insert into ordine (utenteOrdine,prezzoTotale,stato)
values
('roccomiele1',34.5,'Da Spedire'),
('alessandrazullo1',50.1,'Spedito'),
('pamelaAnderson1',20.44,'Arrivato'),
('eleonoradaurea1',5.0,'Da Spedire'),
('carmelosottile',26.7,'Spedito'),
('carmelosottile',77.7,'Da Spedire');

insert into prodottiOrdine (idOrdine,idProdottoOrdine,quantitàProdottoOrdine,prezzo)
values
(1,10,4,10),
(1,1,30,10),
(2,13,51,10),
(2,12,66,10),
(3,9,24,10),
(3,5,12,10),
(4,8,8,10),
(4,2,71,10),
(5,4,20,5),
(5,5,20,5),
(6,8,18,5),
(6,7,18,5);


insert into carrello(usernameCarrello)
values
('carmelosottile'),
('roccomiele1');

insert into prodottiCarrello(numeroCarrello,idProdottoCarrello,quantitàCarrello)
values
(1,1,4),
(1,2,7),
(2,2,2),
(2,3,6),
(1,3,2),
(2,4,3),
(1,7,4),
(2,8,4),
(1,9,4),
(2,10,4);


