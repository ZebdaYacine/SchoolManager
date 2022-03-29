create table student(
id BIGINT primary key auto_increment,
firstName varchar(20),
lastName varchar(20),
phone varchar(10)
);

create table teacher(
id BIGINT primary key auto_increment,
firstName varchar(20),
lastName varchar(20),
phone varchar(10)
);

CREATE TABLE level (
id BIGINT primary key auto_increment,       /*CIAM LYCEE COLEGE */
nameLevel varchar(10) 
);

CREATE TABLE room (
id BIGINT primary key auto_increment,       
roomName varchar(4) 
);

CREATE TABLE type (
id BIGINT primary key auto_increment,    /*8 seances ou bien 4 */
nameType varchar(20) 
);

CREATE TABLE module(
id BIGINT primary key auto_increment,
nameModule varchar(10)  
);

CREATE TABLE Offer(
id BIGINT primary key auto_increment,
offerName varchar(50),
idModule BIGINT not null,
nameModule varchar(10),
idType BIGINT not null,
nameType varchar(20),
idLevel BIGINT not null,
nameLevel varchar(10), 
FOREIGN KEY (idLevel) REFERENCES level(id),
FOREIGN KEY (idType) REFERENCES type(id),
FOREIGN KEY (idModule) REFERENCES module(id),
price float 
);

CREATE TABLE seance(
id BIGINT primary key auto_increment,
idOffer BIGINT not null,
idStudent BIGINT not null,
idTeacher BIGINT not null,
idRoom BIGINT ,
presenceTeacher boolean ,
presenceStudent boolean ,
FOREIGN KEY (idOffer) REFERENCES Offer(id),
FOREIGN KEY (idStudent) REFERENCES student(id),
FOREIGN KEY (idTeacher) REFERENCES teacher(id),
FOREIGN KEY (idRoom) REFERENCES room(id),
day date not null
);

 