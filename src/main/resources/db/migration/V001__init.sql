CREATE TABLE tipo_sanguineo (
    id bigint NOT NULL AUTO_INCREMENT,
    tipo varchar(3) NOT NULL,
    PRIMARY KEY (id)
) DEFAULT CHARSET=UTF8MB4;

CREATE TABLE doador (
    id bigint NOT NULL AUTO_INCREMENT,
    nome varchar(120) NOT NULL,
    cpf varchar(14) NOT NULL,
    rg varchar(12) NOT NULL,
    data_nascimento datetime NOT NULL,
    sexo varchar(9) NOT NULL,
    mae varchar(120) NOT NULL,
    pai varchar(120) NOT NULL,
    email varchar(80) NOT NULL,
    cep varchar(9) NOT NULL,
    endereco varchar(120) NOT NULL,
    numero integer NOT NULL,
    bairro varchar(25) NOT NULL,
    cidade varchar(25) NOT NULL,
    estado varchar(2) NOT NULL,
    telefone_fixo varchar(14) NOT NULL,
    celular varchar(15) NOT NULL,
    altura double precision(2,2) NOT NULL,
    peso integer NOT NULL,
    id_tipo_sanguineo bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_tipo_sanguineo) REFERENCES tipo_sanguineo(id)
) DEFAULT CHARSET=UTF8MB4;