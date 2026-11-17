CREATE TABLE IF NOT EXISTS conta (
    id CHAR(36) PRIMARY KEY,
    centro_id VARCHAR(50) NOT NULL,
    descricao VARCHAR(150) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    valor DECIMAL(12, 2) NOT NULL,
    vencimento DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    INDEX idx_conta_centro_vencimento (centro_id, vencimento)
);
