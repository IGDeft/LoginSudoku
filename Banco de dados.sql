CREATE DATABASE bancoteste;
use bancoteste;

 CREATE table usuario (
	id_usuario int AUTO_INCREMENT PRIMARY KEY,
    nome_usuario varchar (45),
    senha_usuario varchar (45)
    );
    
    insert into usuario (nome_usuario, senha_usuario) values ('admin', '123');
    
    select * from usuario;
    
    
    
