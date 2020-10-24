-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jn_servicos_ambientais` DEFAULT CHARACTER SET utf8 ;
USE `jn_servicos_ambientais` ;

-- -----------------------------------------------------
-- Table `jn_servicos_ambientais`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jn_servicos_ambientais`.`Cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nome_cliente` VARCHAR(45) NOT NULL,
  `cpf` BIGINT(11) NULL,
  `endereco` VARCHAR(45) NULL,
  `telefone` BIGINT(15) NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE INDEX `id_cliente_UNIQUE` (`id_cliente` ASC),
  UNIQUE INDEX `nome_cliente_UNIQUE` (`nome_cliente` ASC),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jn_servicos_ambientais`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jn_servicos_ambientais`.`tipo` (
  `id_tipo` INT NOT NULL AUTO_INCREMENT,
  `nome_tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_tipo`),
  UNIQUE INDEX `id_tipo_UNIQUE` (`id_tipo` ASC),
  UNIQUE INDEX `nome_tipo_UNIQUE` (`nome_tipo` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jn_servicos_ambientais`.`Servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jn_servicos_ambientais`.`Servico` (
  `id_servico` INT NOT NULL AUTO_INCREMENT,
  `data_inicio` DATE NULL,
  `data_fim` DATE NULL,
  `valor` DOUBLE NULL,
  `Cliente_id_cliente` INT NOT NULL,
  `tipo_id_tipo` INT NOT NULL,
  PRIMARY KEY (`id_servico`, `Cliente_id_cliente`, `tipo_id_tipo`),
  INDEX `fk_Servico_Cliente_idx` (`Cliente_id_cliente` ASC),
  INDEX `fk_Servico_tipo1_idx` (`tipo_id_tipo` ASC),
  CONSTRAINT `fk_Servico_Cliente`
    FOREIGN KEY (`Cliente_id_cliente`)
    REFERENCES `jn_servicos_ambientais`.`Cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Servico_tipo1`
    FOREIGN KEY (`tipo_id_tipo`)
    REFERENCES `jn_servicos_ambientais`.`tipo` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;