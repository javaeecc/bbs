/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.5.5-10.0.10-MariaDB : Database - bbs
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bbs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bbs`;

/*Table structure for table `jbbs_bankuai` */

DROP TABLE IF EXISTS `jbbs_bankuai`;

CREATE TABLE `jbbs_bankuai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `descs` varchar(2000) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `orderby` int(11) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `fatiegroup` varchar(2000) DEFAULT NULL,
  `huifugroup` varchar(2000) DEFAULT NULL,
  `findgroup` varchar(2000) DEFAULT NULL,
  `yuming` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_bankuai` */

insert  into `jbbs_bankuai`(`id`,`name`,`img`,`type`,`descs`,`parentid`,`orderby`,`createtime`,`createuserid`,`fatiegroup`,`huifugroup`,`findgroup`,`yuming`) values (1,'论坛','bankuai.jpg',NULL,'论坛',0,1,'2017-08-16 15:07:12',1,'','','','luntan'),(2,'javaee','bankuai.jpg',NULL,'javaee',1,1,'2017-08-16 15:07:33',1,'','','','javaee');

/*Table structure for table `jbbs_group` */

DROP TABLE IF EXISTS `jbbs_group`;

CREATE TABLE `jbbs_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `isdel` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_group` */

insert  into `jbbs_group`(`id`,`groupname`,`createtime`,`createuserid`,`state`,`isdel`) values (1,'超级管理员','2017-05-09',1,'1','1'),(2,'版主组','2017-05-09',1,'1','1'),(3,'普通会员','2017-05-09',1,'1','1');

/*Table structure for table `jbbs_group_user` */

DROP TABLE IF EXISTS `jbbs_group_user`;

CREATE TABLE `jbbs_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_group_user` */

insert  into `jbbs_group_user`(`id`,`userid`,`groupid`) values (1,1,1);

/*Table structure for table `jbbs_huifu` */

DROP TABLE IF EXISTS `jbbs_huifu`;

CREATE TABLE `jbbs_huifu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tieziid` int(11) DEFAULT NULL,
  `huifuid` int(11) DEFAULT NULL,
  `contenthtml` longtext,
  `contenttxt` longtext,
  `createtime` varchar(255) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `lastupdate` varchar(2000) DEFAULT NULL,
  `isdel` varchar(2) DEFAULT NULL,
  `orderby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `jbbs_huifu_tieziid_index` (`tieziid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_huifu` */

/*Table structure for table `jbbs_info` */

DROP TABLE IF EXISTS `jbbs_info`;

CREATE TABLE `jbbs_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indextitle` varchar(2000) DEFAULT NULL,
  `name` varchar(2000) DEFAULT NULL,
  `keywords` varchar(2000) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `logoimg` varchar(2000) DEFAULT NULL,
  `foothtml` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_info` */

insert  into `jbbs_info`(`id`,`indextitle`,`name`,`keywords`,`description`,`logoimg`,`foothtml`) values (1,'国内免费开源javaee论坛源码系统','javaee论坛','javaee,开源论坛,免费论坛,javaee论坛,java论坛','国内唯一一款免费开源javaee论坛源码系统，采用springMVC+mybatis框架开发。','image/20170731/2017073110232016919656.PNG','');

/*Table structure for table `jbbs_inner_link` */

DROP TABLE IF EXISTS `jbbs_inner_link`;

CREATE TABLE `jbbs_inner_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `innerlink` varchar(1000) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `isuse` varchar(1) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_inner_link` */

/*Table structure for table `jbbs_jifen` */

DROP TABLE IF EXISTS `jbbs_jifen`;

CREATE TABLE `jbbs_jifen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tieziid` int(11) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `fenshu` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `operateuserid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_jifen` */

/*Table structure for table `jbbs_jifen_group` */

DROP TABLE IF EXISTS `jbbs_jifen_group`;

CREATE TABLE `jbbs_jifen_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `jifen` int(11) DEFAULT NULL,
  `classname` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `descs` varchar(2000) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_jifen_group` */

insert  into `jbbs_jifen_group`(`id`,`name`,`jifen`,`classname`,`createtime`,`descs`,`createuserid`) values (1,'VIP1',0,'VIP1',NULL,NULL,NULL),(2,'VIP2',100,'VIP2',NULL,NULL,NULL),(3,'VIP3',200,'VIP3',NULL,NULL,NULL),(4,'VIP4',300,'VIP4',NULL,NULL,NULL),(5,'VIP5',400,'VIP5',NULL,NULL,NULL),(6,'VIP6',500,'VIP6',NULL,NULL,NULL),(7,'VIP7',600,'VIP7',NULL,NULL,NULL),(8,'VIP8',700,'VIP8',NULL,NULL,NULL),(9,'VIP9',800,'VIP9',NULL,NULL,NULL),(10,'VIP10',900,'VIP10',NULL,NULL,NULL),(11,'VIP11',1000,'VIP11',NULL,NULL,NULL),(12,'VIP12',1100,'VIP12',NULL,NULL,NULL),(13,'VIP13',1200,'VIP13',NULL,NULL,NULL),(14,'VIP14',1300,'VIP14',NULL,NULL,NULL),(15,'VIP15',1400,'VIP15',NULL,NULL,NULL);

/*Table structure for table `jbbs_link` */

DROP TABLE IF EXISTS `jbbs_link`;

CREATE TABLE `jbbs_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL,
  `link` varchar(2000) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `orderby` int(11) DEFAULT NULL,
  `bankuaiid` int(11) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `isshow` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_link` */

/*Table structure for table `jbbs_message` */

DROP TABLE IF EXISTS `jbbs_message`;

CREATE TABLE `jbbs_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` longtext,
  `content` longtext,
  `createtime` varchar(255) DEFAULT NULL,
  `useridsend` int(11) DEFAULT NULL,
  `useridaccept` int(11) DEFAULT NULL,
  `messagetype` varchar(1) DEFAULT NULL,
  `isread` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_message` */

/*Table structure for table `jbbs_pic` */

DROP TABLE IF EXISTS `jbbs_pic`;

CREATE TABLE `jbbs_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(1000) DEFAULT NULL,
  `tieziid` int(11) DEFAULT NULL,
  `bankuaiid` int(11) DEFAULT NULL,
  `pictype` varchar(2) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `isdel` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_pic` */

/*Table structure for table `jbbs_stop_ip` */

DROP TABLE IF EXISTS `jbbs_stop_ip`;

CREATE TABLE `jbbs_stop_ip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `startip` varchar(255) DEFAULT NULL,
  `endip` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `isuse` varchar(1) DEFAULT NULL,
  `remarks` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_stop_ip` */

/*Table structure for table `jbbs_tiezi` */

DROP TABLE IF EXISTS `jbbs_tiezi`;

CREATE TABLE `jbbs_tiezi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `bankuai_id` int(11) DEFAULT NULL,
  `zhutiid` varchar(255) DEFAULT NULL,
  `lastupdate` varchar(2000) DEFAULT NULL,
  `jinghua` varchar(255) DEFAULT NULL,
  `zhiding` varchar(255) DEFAULT NULL,
  `findcount` int(11) DEFAULT '0',
  `huifuid` int(11) DEFAULT NULL,
  `isuse` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `jbbs_tiezi_bankuai_id_index` (`bankuai_id`),
  KEY `jbbs_tiezi_createuserid_index` (`createuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_tiezi` */

/*Table structure for table `jbbs_tiezi_big` */

DROP TABLE IF EXISTS `jbbs_tiezi_big`;

CREATE TABLE `jbbs_tiezi_big` (
  `id` int(11) NOT NULL,
  `html` longtext,
  `text` longtext,
  `createtime` varchar(255) DEFAULT NULL,
  `orderby` int(11) DEFAULT NULL,
  KEY `jbbs_tiezi_big_id_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_tiezi_big` */

/*Table structure for table `jbbs_user` */

DROP TABLE IF EXISTS `jbbs_user`;

CREATE TABLE `jbbs_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `qianming` varchar(2000) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `lastlogintime` varchar(255) DEFAULT NULL,
  `zhuceip` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `flag` varchar(2) DEFAULT NULL,
  `jifen` int(11) DEFAULT NULL,
  `onlinetime` int(11) DEFAULT '0',
  `cookieid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_user` */

insert  into `jbbs_user`(`id`,`name`,`loginname`,`pwd`,`img`,`email`,`birthday`,`sex`,`mobile`,`qianming`,`createtime`,`lastlogintime`,`zhuceip`,`loginip`,`flag`,`jifen`,`onlinetime`,`cookieid`) values (1,'超级管理员','javaee','4QrcOUm6Wau+VuBX8g+IPg==','image/20170816/2017081615082239675566.JPG','javaeecc@qq.com','2017-05-12','0','13911111111','签名','2017-05-06 22:25:35','2017-08-16 15:06:37','737a87b275c7477b8cebaa90b3f1d08a','192.168.1.147','1',7,240,'40814129c79f485e9b99ed763da3869a');

/*Table structure for table `jbbs_user_liuyan` */

DROP TABLE IF EXISTS `jbbs_user_liuyan`;

CREATE TABLE `jbbs_user_liuyan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenthtml` longtext,
  `liuyanuserid` int(11) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `createtime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_user_liuyan` */

/*Table structure for table `jbbs_zhuti` */

DROP TABLE IF EXISTS `jbbs_zhuti`;

CREATE TABLE `jbbs_zhuti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `bankuai_id` int(11) DEFAULT NULL,
  `orderby` int(11) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `jbbs_zhuti` */

insert  into `jbbs_zhuti`(`id`,`name`,`bankuai_id`,`orderby`,`createtime`) values (1,'javaee',2,1,'2017-08-16 15:07:51');


DROP VIEW IF EXISTS `jbbs_jifen_group_user`;
CREATE  VIEW `jbbs_jifen_group_user` AS select (select `tt2`.`id` from `jbbs_jifen_group` `tt2` where (`tt2`.`jifen` <= `t1`.`jifen`) order by `tt2`.`jifen` desc limit 0,1) AS `jifen_groupid`,`t1`.`id` AS `userid` from `jbbs_user` `t1` ;
