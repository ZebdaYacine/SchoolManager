use SchoolManager;

create table user(
id BIGINT primary key auto_increment,
userName varchar(10),
password varchar(20),
role varchar(10)
);

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

use SchoolManager;
alter table student
drop column phone,
add column phone1 varchar(10),
add column phone2 varchar(10);

alter table teacher
add column workePlace varchar(50);

alter table room
add column nbrchair int;


ALTER TABLE level RENAME COLUMN nameLevel TO name;
ALTER TABLE module RENAME COLUMN nameModule TO name;
ALTER TABLE type RENAME COLUMN nameType TO name;

ALTER TABLE seance
DROP COLUMN presenceStudent;

create table follow(
id BIGINT primary key auto_increment,
idStudent BIGINT not null,
idSeance BIGINT not null,
presenceStudent boolean ,
FOREIGN KEY (idStudent) REFERENCES student(id),
FOREIGN KEY (idSeance) REFERENCES seance(id)
);

create table section(
id BIGINT primary key auto_increment,
name varchar(50) 
);

alter table student
add idSection bigint not null ;

delete from student where id>0;
alter table student
add foreign key (idSection) references section(id);

alter table seance 
drop constraint seance_ibfk_2;

alter table seance 
drop column idStudent;

alter table follow
add column status boolean;

ALTER TABLE room 
CHANGE roomName name VARCHAR(25);

create table groupe (
id BIGINT primary key auto_increment,
name varchar(50) unique,
idOffer BIGINT not null,
FOREIGN KEY (idOffer) REFERENCES Offer(id) on delete cascade on update cascade ,
nbrPlace int 
);

create table belongs(
id BIGINT primary key auto_increment,
idGroupe BIGINT not null,
idStudnet BIGINT not null,
FOREIGN KEY (idGroupe) REFERENCES groupe(id)  on delete cascade on update cascade ,
FOREIGN KEY (idStudnet) REFERENCES student(id)  on delete cascade on update cascade 
);

alter table follow
add idBelongs bigint not null ,
add foreign key (idBelongs) references belongs(id);

  
alter table follow
DROP FOREIGN KEY follow_ibfk_3;

alter table follow
drop column idBelongs;

alter table seance
add idBelongs bigint not null ,
add foreign key (idBelongs) references belongs(id) on delete cascade on update cascade;

alter table seance 
drop foreign key seance_ibfk_5;

alter table seance 
drop column idBelongs;

alter table seance
add idGroupe bigint not null ,
add foreign key (idGroupe) references groupe(id) on delete cascade on update cascade;

alter table follow 
add CONSTRAINT uni_idStudent_idSeance UNIQUE (idStudent,idSeance);


