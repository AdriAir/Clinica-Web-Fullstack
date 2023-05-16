-- -----------------------------------------------------
-- DATABASE 'ClinicaDentista'
-- -----------------------------------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS ClinicaDentista;
CREATE DATABASE ClinicaDentista;
USE ClinicaDentista;

-- -----------------------------------------------------
-- Table 'ClinicaDentista'.'Clinic'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'ClinicaDentista'.'Clinic' (
  'cif' VARCHAR(15) NOT NULL,
  'name' VARCHAR(45) NOT NULL,
  'address' VARCHAR(45) NOT NULL,
  'phoneNumber' VARCHAR(45) NOT NULL,
  'email' VARCHAR(45) NOT NULL,
  UNIQUE INDEX 'cif_UNIQUE' ('cif' ASC) VISIBLE,
  PRIMARY KEY ('cif'))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'ClinicaDentista'.'Patient'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'ClinicaDentista'.'Patient' (
  'dni' VARCHAR(9) NOT NULL,
  'name' VARCHAR(45) NOT NULL,
  'surname' VARCHAR(45) NOT NULL,
  'phoneNumber' VARCHAR(45) NOT NULL,
  'email' VARCHAR(45) NOT NULL,
  'bornDate' DATE NOT NULL,
  'clinic' VARCHAR(15) NOT NULL,
  PRIMARY KEY ('dni'),
  UNIQUE INDEX 'dni_UNIQUE' ('dni' ASC) VISIBLE,
  INDEX 'clinic_idx' ('clinic' ASC) VISIBLE,
  CONSTRAINT 'clinic'
    FOREIGN KEY ('clinic')
    REFERENCES 'ClinicaDentista'.'Clinic' ('cif')
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'ClinicaDentista'.'Treatment'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'ClinicaDentista'.'Treatment' (
  'code' VARCHAR(45) NOT NULL,
  'description' VARCHAR(100) NOT NULL,
  'date' DATE NOT NULL,
  'price' DECIMAL(10,2) NOT NULL,
  'isPaid' BOOLEAN NOT NULL,
  'patient' VARCHAR(9) NOT NULL,
  PRIMARY KEY ('code'),
  UNIQUE INDEX 'code_UNIQUE' ('code' ASC) VISIBLE,
  INDEX 'patient_idx' ('patient' ASC) VISIBLE,
  CONSTRAINT 'patient'
    FOREIGN KEY ('patient')
    REFERENCES 'ClinicaDentista'.'Patient' ('dni')
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- USER 'principal'@'%'
-- -----------------------------------------------------
CREATE USER 'principal'@'%' IDENTIFIED BY '1234';

GRANT SELECT, DELETE, INSERT, UPDATE ON ClinicaDentista.* TO 'principal'@'%';

FLUSH PRIVILEGES;
