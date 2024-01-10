CREATE TABLE pdi (
    pdi_id INT PRIMARY KEY NOT NULL auto_increment,
    funcionario_id INT NOT NULL,
    avaliacao VARCHAR(500) NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);