CREATE TABLE containers (
    id          UUID            NOT NULL PRIMARY KEY,
    numero      VARCHAR(11)     NOT NULL UNIQUE,
    tipo        VARCHAR(100)    NOT NULL,
    tamanho     VARCHAR(4)      NOT NULL  
);

CREATE TABLE cargas (
    id              INTEGER         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    containers_id   UUID            NOT NULL REFERENCES containers(id),
    tipo            INTEGER         NOT NULL,
    descricao       VARCHAR(100)    NOT NULL,
    condicao        INTEGER         NOT NULL
);