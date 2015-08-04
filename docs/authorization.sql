-- -----------------------------------------------------
-- Table 'UserProfile'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'UserProfile' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  PRIMARY KEY ('id')  COMMENT '')
;


-- -----------------------------------------------------
-- Table 'ProfileAttribute'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'ProfileAttribute' (
  'attributeName' VARCHAR(255) NOT NULL COMMENT '',
  'profile_id' BIGINT(20) NOT NULL DEFAULT '0' COMMENT '',
  'attributeValue' VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY ('attributeName', 'profile_id')  COMMENT '',
  CONSTRAINT 'FK_PROFILE_ATRIBUTE'
    FOREIGN KEY ('profile_id')
    REFERENCES 'UserProfile' ('id'))
;


-- -----------------------------------------------------
-- Table 'User'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'User' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  'pass' LONGTEXT NULL DEFAULT NULL COMMENT '',
  'salt' VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  'user' VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY ('id')  COMMENT '')
;

CREATE UNIQUE INDEX 'UK_USER_LOGIN' ON 'User' ('user' ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table 'UserSessionAuth'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'UserSessionAuth' (
  'token' VARCHAR(255) NOT NULL COMMENT '',
  'expirationTime' INT(11) NULL DEFAULT NULL COMMENT '',
  'validUntil' DATETIME NULL DEFAULT NULL COMMENT '',
  'user_id' BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY ('token')  COMMENT '',
  CONSTRAINT 'FK_USER_SESSION'
    FOREIGN KEY ('user_id')
    REFERENCES 'User' ('id'))
;



