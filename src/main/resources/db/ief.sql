create database if not exists ief;
use ief;
create table if not exists user_info (
  id              BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username        VARCHAR(16)
  COMMENT '姓名',
  password        VARCHAR(16)
  COMMENT '密码',
  sex             TINYINT     DEFAULT '-1'
  COMMENT '性别 1女,2南',
  birthday        DATE COMMENT '生日',
  birthdayType             TINYINT     DEFAULT '0'
  COMMENT '日期格式,0-阳历,1-农历',
  hometown VARCHAR(128) COMMENT '家乡',
  locate VARCHAR(128) COMMENT '现居地',
  career          VARCHAR(32) COMMENT '职业',
  phone           VARCHAR(16) COMMENT '电话号码',
  school          VARCHAR(128) COMMENT '毕业院校',
  deviceInfo     VARCHAR(64) COMMENT '设备信息',
  signature       VARCHAR(128) COMMENT '个性签名',
  ownedNum    BIGINT NOT NULL default 0
  COMMENT '想要看这本书的人的数量',
  userHeadImg      VARCHAR(128) COMMENT '图片地址',
  createdTime    TIMESTAMP DEFAULT current_timestamp
) engine=innodb auto_increment=1 default charset=utf8;

create table if not exists concerned_persons (
  id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId      BIGINT NOT NULL,
  keyUserId   BIGINT NOT NULL ,
  createdTime TIMESTAMP DEFAULT current_timestamp,
  KEY `keyUserId` (`keyUserId`) USING BTREE
) engine=innodb auto_increment=1 default charset=utf8;

create table if not exists books_wanted (
  id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  bookId      BIGINT NOT NULL,
  keyUserId   BIGINT NOT NULL,
  createdTime TIMESTAMP DEFAULT current_timestamp,
  KEY `keyUserId` (`keyUserId`) USING BTREE
) engine=innodb auto_increment=1 default charset=utf8;

create table if not exists books_owned (
  id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  bookId      BIGINT NOT NULL,
  keyUserId   BIGINT NOT NULL,
  createdTime TIMESTAMP DEFAULT current_timestamp,
  KEY `keyUserId` (`keyUserId`) USING BTREE
) engine=innodb auto_increment=1 default charset=utf8;


create table if not exists known_persons (
  id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId      BIGINT  NOT NULL,
  keyUserId   BIGINT  NOT NULL,
  createdTime TIMESTAMP DEFAULT current_timestamp,
  KEY `keyUserId` (`keyUserId`) USING BTREE
) engine=innodb auto_increment=1 default charset=utf8;

create table if not exists upload_books (
  id           BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  bookName    VARCHAR(64) NOT NULL
  COMMENT '书籍名称',
  bookId      BIGINT         
  COMMENT '书籍id',
  userId      BIGINT         NOT NULL
  COMMENT '用户id',
  onLoan      TINYINT(1)                 DEFAULT '0'
  COMMENT '是否借出，1：借出 0:未借出' ,
  comment      VARCHAR(128) COMMENT '评价',
  createdTime TIMESTAMP                       DEFAULT current_timestamp,
  wantedNum    BIGINT NOT NULL default 0
  COMMENT '想要看这本书的人的数量',
  bookCoverImg      VARCHAR(128) COMMENT '图片地址',
  category      TINYINT         NOT NULL
  COMMENT '分类',
  KEY `userId` (`userId`) USING BTREE,
  KEY `bookId` (`bookId`) USING BTREE
) engine=innodb auto_increment=1 default charset=utf8;


create table if not exists books (
  id           BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  bookName    VARCHAR(64) NOT NULL
  COMMENT '书名',
  categoryId  INT         NOT NULL
  COMMENT '类别id',
  createdTime TIMESTAMP                        DEFAULT current_timestamp
) engine=innodb auto_increment=1 default charset=utf8;

create table if not exists book_user_wanted (
  id           BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId      BIGINT NOT NULL,
  createdTime TIMESTAMP DEFAULT current_timestamp,
  KEY `userId` (`userId`) USING BTREE
) engine=innodb auto_increment=1 default charset=utf8;

create table if not exists category (
  id            BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  categoryName VARCHAR(24) COMMENT '类别名',
  createdTime  TIMESTAMP DEFAULT current_timestamp
) engine=innodb auto_increment=1 default charset=utf8;


create table if not exists photo (
  id           BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  address      VARCHAR(64) COMMENT '地址',
  createdTime TIMESTAMP DEFAULT current_timestamp
) engine=innodb auto_increment=1 default charset=utf8;

insert into user_info
(username, sex, birthday, hometown, locate, career, phone, school,deviceInfo,signature,userHeadImg,createdTime)
values
('小明', 1, sysDate(), 'china', '新郑', '程序员', '159xxxxxxxx', '北信科','hello sunshine', 'signature','/Users/zhangdongsheng/osgit/ief/src/main/webapp/res/img/book/img.jpg', sysDate());

insert into user_info
(username, sex, birthday, hometown, locate, career, phone, school,deviceInfo,signature,userHeadImg,createdTime)
values
('小王', 2, sysDate(), 'china', '新郑', '程序员', '159xxxxxxxx', '北信科','hello sunshine', 'signature','/Users/zhangdongsheng/osgit/ief/src/main/webapp/res/img/book/img.jpg', sysDate());

insert into category(id, categoryName, createdTime) values (null, '小说', sysDate());
insert into category(id, categoryName, createdTime) values (null, '科普', sysDate());

insert into books(id,bookName,categoryId,createdTime) values(null, '平凡的世界', '1', sysDate());
insert into books(id,bookName,categoryId,createdTime) values(null, '钢铁是怎样炼成的', '1', sysDate());

insert into upload_books (id,bookName,bookId,userId,onLoan,comment,wantedNum,bookCoverImg,createdTime,category) values (null, '平凡的世界', '1', '1', 0,  '奋斗励志书籍', 2,'res/img/book/img.jpg', sysDate(),1);
insert into upload_books (id,bookName,bookId,userId,onLoan,comment,wantedNum,bookCoverImg,createdTime,category) values (null, '钢铁是怎样炼成的', '2', '1', 0, '铁血',1, 'res/img/book/img.jpg', sysDate(),1);
insert into upload_books (id,bookName,bookId,userId,onLoan,comment,wantedNum,bookCoverImg,createdTime,category) values (null, '平凡的世界', '1', '2', 0,  '奋斗励志书籍', 2, 'res/img/book/img.jpg', sysDate(),2);

insert into books_wanted(id, bookId, createdTime) values (null, '1', sysDate());
