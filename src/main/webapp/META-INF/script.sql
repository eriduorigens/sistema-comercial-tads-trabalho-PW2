-- Script Postgress

-- Database: sistema-comercial

-- DROP DATABASE "sistema-comercial";

CREATE DATABASE sistema-comercial


CREATE TABLE produto(
	id SERIAL PRIMARY KEY NOT NULL,
	nome VARCHAR(80),
	marca VARCHAR(80),
	codigo_barra VARCHAR(30),
	unidade_medida VARCHAR(2),
	categoria_id INT
)

CREATE TABLE categoria(
	id SERIAL PRIMARY KEY NOT NULL,
	nome VARCHAR(80)
)

ALTER TABLE produto
ADD CONSTRAINT categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria(id);


INSERT INTO categoria VALUES	(DEFAULT,'aquático'),
                	(DEFAULT,' terrestre'),
                	(DEFAULT, 'aéreo');


INSERT INTO produto VALUES	(DEFAULT,'navio',' aaaa','123456','3',1),
                 	(DEFAULT,'aviao', '‘cccc','123985',9,3),
                 	(DEFAULT,'carro','bbbb','123456','3',2),
                 	(DEFAULT,'moto',' bbbb','123456','6',2),
                 	(DEFAULT,'submarino',' aaaa','12586','3',1),
                 	(DEFAULT,'helicóptero',' cccc','123456','3',3);



