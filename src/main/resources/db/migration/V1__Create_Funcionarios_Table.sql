
create TABLE IF NOT EXISTS Funcionarios (
    idFuncionarios INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    sobrenome VARCHAR(255),
    cargo VARCHAR(255),
    PRIMARY KEY (idFuncionarios)
);
