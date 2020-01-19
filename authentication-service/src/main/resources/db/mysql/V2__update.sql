
-- ----------------------------
-- Table structure for student
-- ----------------------------
CREATE TABLE student (
  ID varchar(40) NOT NULL,
  NAME varchar(16) DEFAULT NULL COMMENT '姓名',
  STU_NO varchar(32) DEFAULT NULL COMMENT '学号',
  BIRTH timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出生日期',
  SCORE float(5,2) DEFAULT NULL COMMENT '分数',
  REMARK mediumtext COMMENT '评价',
  CLASSES_ID varchar(40) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (ID)
) ;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO student VALUES ('10', '10', '10', '1990-01-10 00:00:00', '10.00', '10', '03');
INSERT INTO student VALUES ('11', '11', '11', '1990-01-11 00:00:00', '11.00', '11', '04');
INSERT INTO student VALUES ('12', '12', '12', '1990-01-12 00:00:00', '12.00', '12', '05');
INSERT INTO student VALUES ('13', '13', '13', '1990-01-13 00:00:00', '13.00', '13', '06');
INSERT INTO student VALUES ('14', '14', '14', '1990-01-14 00:00:00', '14.00', '14', '00');
INSERT INTO student VALUES ('15', '15', '15', '1990-01-15 00:00:00', '15.00', '15', '01');
INSERT INTO student VALUES ('16', '16', '16', '1990-01-16 00:00:00', '16.00', '16', '02');
INSERT INTO student VALUES ('17', '17', '17', '1990-01-17 00:00:00', '17.00', '17', '03');
INSERT INTO student VALUES ('18', '18', '18', '1990-01-18 00:00:00', '18.00', '18', '04');
INSERT INTO student VALUES ('19', '19', '19', '1990-01-19 00:00:00', '19.00', '19', '05');
INSERT INTO student VALUES ('20', '20', '20', '1990-01-20 00:00:00', '20.00', '20', '06');
INSERT INTO student VALUES ('21', '21', '21', '1990-01-21 00:00:00', '21.00', '21', '00');
INSERT INTO student VALUES ('22', '22', '22', '1990-01-22 00:00:00', '22.00', '22', '01');
INSERT INTO student VALUES ('23', '23', '23', '1990-01-23 00:00:00', '23.00', '23', '02');
INSERT INTO student VALUES ('3', '3', '3', '1990-01-03 00:00:00', '3.00', '3', '03');
INSERT INTO student VALUES ('3a9b985d6b514e09a5555bb61d1f810d', 'aa', 'aa', null, null, null, null);
INSERT INTO student VALUES ('4', '4--', '4', '1990-01-04 00:00:00', '4.00', '4', '04');
INSERT INTO student VALUES ('41687c37e4dd4a85b079befcaa965bd0', '李四', 'zhangsan', '2018-12-09 02:59:54', '33.00', '努力学习', '01');
INSERT INTO student VALUES ('491befbeb0d845dd9cb97800003b3fde', '王五', 'zhangsan', '2018-12-09 03:04:46', '33.00', '努力学习', '01');
INSERT INTO student VALUES ('5', '5', '5', '1990-01-05 00:00:00', '5.00', '5', '05');
INSERT INTO student VALUES ('55', '55', '55', '1990-01-15 00:00:00', '55.00', '55', '55');
INSERT INTO student VALUES ('6', '6', '6', '1990-01-06 00:00:00', '6.00', '6', '06');
INSERT INTO student VALUES ('62cef88db87f4bb6b30eceac1e6afaa8', 'string', 'string', '2018-12-08 19:43:59', '0.00', 'string', 'string');
INSERT INTO student VALUES ('7', '7', '7', '1990-01-07 00:00:00', '7.00', '7', '00');
INSERT INTO student VALUES ('8', '8', '8', '1990-01-08 00:00:00', '8.00', '8', '01');
INSERT INTO student VALUES ('9429a5692c5d41e6b66582d60262df68', '李四', 'zhangsan', '2018-12-09 02:38:22', '33.00', '努力学习', '01');
INSERT INTO student VALUES ('9c7584a43e964478b7ffc373f0980334', '李四', 'zhangsan', '2018-12-09 02:39:38', '33.00', '努力学习', '01');
INSERT INTO student VALUES ('a16c7d2d331042f7bef5a632b85ee762', '张三', 'zhangsan', null, null, null, null);
INSERT INTO student VALUES ('a99f478f2bbc43cc8d599f8a7d0bf2c4', '张三', 'zhangsan', null, null, null, null);
