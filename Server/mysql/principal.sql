-- -----------------------------------------------------
-- USER `principal`@`%`
-- -----------------------------------------------------
CREATE USER IF NOT EXISTS 'principal'@'%' IDENTIFIED BY '1234';

GRANT SELECT, DELETE, INSERT, UPDATE ON ClinicaDentista.* TO 'principal'@'%';

FLUSH PRIVILEGES;