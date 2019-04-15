


CREATE DATABASE "sistema-comercial_trabalho";

CREATE TABLE produto(

    id SERIAL PRIMARY KEY NOT NULL,    
    codigo_de_barra VARCHAR(32)    NOT NULL,
    nome VARCHAR(80) NOT NULL,
    marca  VARCHAR(40)   NOT NULL,
    unidade_de_medida  VARCHAR(40)  NOT NULL,    
    categoria_id INT
) 
CREATE TABLE categoria(
    id SERIAL PRIMARY KEY NOT NULL,    
    nome VARCHAR(80) NOT NULL
     
) 
ALTER TABLE produto ADD CONSTRAINT categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria (id);