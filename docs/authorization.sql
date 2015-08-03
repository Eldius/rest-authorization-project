-- -----------------------------------------------------
-- Table 'User'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'User' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'User id',
  'pass' LONGTEXT NULL DEFAULT NULL COMMENT 'Hashed password',
  'salt' VARCHAR(255) NULL DEFAULT NULL COMMENT 'Salt used to hash the password',
  'user' VARCHAR(255) NULL DEFAULT NULL COMMENT 'Username',
  PRIMARY KEY ('id')  COMMENT '')
;

CREATE UNIQUE INDEX 'UK_USER_LOGIN' ON 'User' ('user' ASC)  COMMENT 'User login unique key';


-- -----------------------------------------------------
-- Table 'UserProfile'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'UserProfile' (
  'id' BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Profile identification. Must have the same value of User identification',
  PRIMARY KEY ('id')  COMMENT '')
;


-- -----------------------------------------------------
-- Table 'UserSessionAuth'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'UserSessionAuth' (
  'token' VARCHAR(255) NOT NULL COMMENT 'Authorization identification - used as an identification token',
  'expirationTime' INT(11) NULL DEFAULT NULL COMMENT 'The kind of authorization - 0->short term, 1->long term',
  'validUntil' DATETIME NULL DEFAULT NULL COMMENT 'Session valid until this time',
  'user_id' BIGINT(20) NULL DEFAULT NULL COMMENT 'User who uses this token',
  PRIMARY KEY ('token')  COMMENT '',
  CONSTRAINT 'FK_USER_SESSION'
    FOREIGN KEY ('user_id')
    REFERENCES 'User' ('id'))
;


