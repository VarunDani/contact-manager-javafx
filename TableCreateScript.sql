CREATE TABLE `company`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for Person Table ',
  `first_name` VARCHAR(200) NOT NULL COMMENT 'First Name for Person',
  `middle_initial` CHAR(1) NULL COMMENT 'Non Mandatory Middle Initial',
  `last_name` VARCHAR(200) NOT NULL COMMENT 'Last Name of Person',
  `birth_date` DATETIME NOT NULL,
  `sex` CHAR(1) NOT NULL,
  PRIMARY KEY (`person_id`),
  INDEX `ind_first_name` (`first_name` ASC)  COMMENT 'Index on First name of Person, because in application like contact manager most usually used attribute is first_name')
COMMENT = 'Person Table for All Attributes of Individual Person	';
USE `company`;


CREATE TABLE `company`.`contact_info` (
  `contact_info_id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NULL,
  `contact_date` DATETIME NOT NULL,
  `address_line_1` VARCHAR(100) NOT NULL,
  `address_line_2` VARCHAR(100) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `zipcode` VARCHAR(45) NULL,
  PRIMARY KEY (`contact_info_id`),
  INDEX `Contact Person ID_idx` (`person_id` ASC),
  CONSTRAINT `Contact Person ID`
    FOREIGN KEY (`person_id`)
    REFERENCES `company`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Contact Information for perticular person';



CREATE TABLE `company`.`phone_number_info` (
  `idphone_number_info_id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `phone_number` INT NOT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`idphone_number_info_id`),
  INDEX `phone Number` (`phone_number` ASC),
  INDEX `Person_ID_idx` (`person_id` ASC),
  CONSTRAINT `Person_ID`
    FOREIGN KEY (`person_id`)
    REFERENCES `company`.`person` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
	
CREATE TABLE `company`.`email_id_info` (
  `email_id_info_key` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `email_id` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`email_id_info_key`),
  INDEX `Person_ID_idx` (`person_id` ASC),
  INDEX `Email_ID` (`email_id` ASC),
  CONSTRAINT `Person_ID_fk`
    FOREIGN KEY (`person_id`)
    REFERENCES `company`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
