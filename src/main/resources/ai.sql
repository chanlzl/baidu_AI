/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.26-log : Database - ai
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ai` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ai`;

/*Table structure for table `config_item` */

DROP TABLE IF EXISTS `config_item`;

CREATE TABLE `config_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) DEFAULT NULL,
  `item_value` varchar(100) DEFAULT NULL,
  `item_desc` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `config_item` */

insert  into `config_item`(`id`,`item_name`,`item_value`,`item_desc`,`create_time`,`update_time`) values (1,'client_id','mnQDV2hMayyMdMAk9YAXmGFE','API Key','2020-01-04 11:45:09',NULL),(2,'client_secret','2XZmzZvuZUov7fWoGcWHkW6MzWeuNGtC','Secret Key','2020-01-04 11:45:12',NULL),(3,'access_token','24.4f2d0ebd357393b5505839deb6423a4f.2592000.1580729356.282335-18173611',NULL,NULL,NULL),(12,'group_id','test','group_id',NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(200) DEFAULT NULL,
  `access_token` varchar(200) DEFAULT NULL,
  `left` double DEFAULT NULL,
  `top` double DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `rotation` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
