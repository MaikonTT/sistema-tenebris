CREATE TABLE cliente(
	id			SERIAL PRIMARY KEY,	
	nivel		VARCHAR,
	nome		VARCHAR,
	cpf			VARCHAR,
	telefone	VARCHAR,
	email		VARCHAR,
	login		VARCHAR UNIQUE,	
	senha		VARCHAR
);
CREATE TABLE funcionario(
	id			SERIAL PRIMARY KEY,	
	nivel		VARCHAR,
	nome		text,
	cpf			text UNIQUE,
	telefone	varchar,
	endereco	varchar,	
	login		VARCHAR UNIQUE,	
	senha		VARCHAR
);
CREATE TABLE gerente(
	id			SERIAL PRIMARY KEY,
	nivel		VARCHAR,
	login		VARCHAR UNIQUE,	
	senha		VARCHAR
);
CREATE TABLE relatorio(
	id		SERIAL PRIMARY KEY,
	data_compra	date,
	valor		numeric(10,2)
);
CREATE TABLE ingresso(
	id			SERIAL PRIMARY KEY,
	codigo		varchar UNIQUE,
	data		date,
	valor		numeric(10,2),
	estado		text,
	id_relatorio int references relatorio(id)
);
CREATE TABLE cupom(
	id			SERIAL PRIMARY KEY,
	descri		varchar,
	codigo		varchar UNIQUE,
	desconto	numeric(10,2),
	estado		text
);

INSERT into gerente (nivel, login, senha) VALUES ('1','admin','admin');

SELECT * FROM gerente;

SELECT * FROM cliente;