CREATE TABLE categoria(
    idCategoria BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    descricao varchar(100)
);
INSERT INTO categoria(descricao) VALUES('Humanas');
INSERT INTO categoria(descricao) VALUES('Exatas');
INSERT INTO categoria(descricao) VALUES('Entretenimento');
INSERT INTO categoria(descricao) VALUES('Ciências');

CREATE TABLE usuario (
    rg varchar(10) UNIQUE,
    cpf varchar(14) UNIQUE,
    idUsuario BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    nome varchar(120)
);
INSERT INTO usuario(rg,cpf,nome) VALUES('999999999','44444444444','André Ramos Neves');

CREATE TABLE publicacao(
    idPublicacao BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    idCategoria BIGINT NOT NULL,
    FOREIGN KEY(idCategoria) REFERENCES categoria(idCategoria),
    datapublicacao date
);

CREATE TABLE livro(
    idLivro BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    idPublicacao BIGINT,
    isbn INT,
    titulo varchar(120),
    ano int,
    autor varchar(120),
    valor DECIMAL(9,3),
    FOREIGN KEY(idPublicacao) REFERENCES publicacao(idPublicacao)
);

CREATE TABLE revista(
    idRevista BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    idPublicacao BIGINT,
    datapublicacao date,
    editora varchar(100),
    valor DECIMAL(9,3),
    FOREIGN KEY(idPublicacao) REFERENCES publicacao(idPublicacao),
    autor varchar(40)
);
INSERT INTO revista(autor,datapublicacao,editora,valor) VALUES('Abril','2015-09-01','Veja',2.30);



CREATE TABLE emprestimo(
    idEmprestimo BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    idUsuario BIGINT NOT NULL,
    FOREIGN KEY(idUsuario) REFERENCES usuario(idUsuario),    
    dataRetirada DATE,
    dataEntrada DATE,
    prazoDias INT,
    valor DECIMAL(9,3)
);

CREATE TABLE itemEmprestimo(
    idItem BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    idPublicacao BIGINT NOT NULL,
    nome VARCHAR(120),
    FOREIGN KEY(idPublicacao) REFERENCES publicacao(idPublicacao),
    idEmprestimo BIGINT NOT NULL,
    FOREIGN KEY(idEmprestimo) REFERENCES emprestimo(idEmprestimo)
);


CREATE TABLE recibo(
    idDocto BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1),
    idEmprestimo BIGINT NOT NULL,
    FOREIGN KEY(idEmprestimo) REFERENCES emprestimo(idEmprestimo),
    dataRecibo DATE
)
INSERT INTO livro(autor,idPublicacao,isbn,titulo,ano,valor) VALUES('Lev Psakhis',1,321312,'Advanced Chess Tactics',2009,5.00);
