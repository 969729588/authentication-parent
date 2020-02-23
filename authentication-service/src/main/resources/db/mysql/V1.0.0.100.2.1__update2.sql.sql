CREATE TABLE person (
  ID varchar(50) NOT NULL,
  FIRST_NAME varchar(20) DEFAULT NULL,
  LAST_NAME varchar(20) DEFAULT NULL,
  BIRTH timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  SCORE float DEFAULT NULL,
  REMARK mediumtext,
  PRIMARY KEY (ID)
) ;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO person VALUES ('1', 'zhang', 'san', null, null, null);
INSERT INTO person VALUES ('2', 'li', 'si', null, null, null);
INSERT INTO person VALUES ('3', 'wang', 'wu', null, null, null);