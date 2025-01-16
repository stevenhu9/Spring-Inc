CREATE SCHEMA `airforce`;
CREATE TABLE `airforce`.`commander` (
  `commander_id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(32) NOT NULL,
  `rank` VARCHAR(32) NULL,
  `years_of_service` INT NULL,
  `specialization` VARCHAR(16) NULL,
  `active_duty` TINYINT NULL,
  PRIMARY KEY (`commander_id`),
  UNIQUE INDEX `commander_id_UNIQUE` (`commander_id` ASC) VISIBLE);
CREATE TABLE `airforce`.`pilot` (
  `pilot_id` INT NOT NULL AUTO_INCREMENT,
  `rank` VARCHAR(32) NULL,
  `flight_hours_logged` INT NULL,
  `license` VARCHAR(32) NOT NULL,
  `full_name` VARCHAR(32) NOT NULL,
  `aircraft` VARCHAR(32) NULL,
  PRIMARY KEY (`pilot_id`),
  UNIQUE INDEX `license_UNIQUE` (`license` ASC) VISIBLE,
  UNIQUE INDEX `pilot_id_UNIQUE` (`pilot_id` ASC) VISIBLE);
CREATE TABLE `airforce`.`squadron` (
  `squadron_id` INT NOT NULL AUTO_INCREMENT,
  `squadron_name` VARCHAR(32) NOT NULL,
  `base` VARCHAR(32) NOT NULL,
  `date_formed` DATETIME NULL,
  `mission` VARCHAR(32) NULL,
  `capacity` INT NULL,
  `status` VARCHAR(32) NULL,
  PRIMARY KEY (`squadron_id`),
  UNIQUE INDEX `squadron_name_UNIQUE` (`squadron_name` ASC) VISIBLE);
  ALTER TABLE `airforce`.`pilot` 
ADD COLUMN `commander_id` INT NULL AFTER `aircraft`,
ADD COLUMN `squadron_id` INT NULL AFTER `commander_id`,
ADD INDEX `pilot_squadron_id_idx` (`squadron_id` ASC) VISIBLE,
ADD INDEX `pilot_commander_id_idx` (`commander_id` ASC) VISIBLE;
;
ALTER TABLE `airforce`.`pilot` 
ADD CONSTRAINT `pilot_squadron_id`
  FOREIGN KEY (`squadron_id`)
  REFERENCES `airforce`.`squadron` (`squadron_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `pilot_commander_id`
  FOREIGN KEY (`commander_id`)
  REFERENCES `airforce`.`commander` (`commander_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
ALTER TABLE `airforce`.`squadron` 
ADD COLUMN `commander_id` INT NOT NULL AFTER `status`,
ADD INDEX `squadron_commander_id_idx` (`commander_id` ASC) VISIBLE;
;
ALTER TABLE `airforce`.`squadron` 
ADD CONSTRAINT `squadron_commander_id`
  FOREIGN KEY (`commander_id`)
  REFERENCES `airforce`.`commander` (`commander_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
