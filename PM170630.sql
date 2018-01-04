/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.5.58 : Database - pm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pm`;

/*Table structure for table `pm_pro_user_rel_tab` */

DROP TABLE IF EXISTS `pm_pro_user_rel_tab`;

CREATE TABLE `pm_pro_user_rel_tab` (
  `id` varchar(100) NOT NULL COMMENT '编号',
  `projectid` varchar(100) NOT NULL COMMENT '项目id',
  `userid` varchar(100) NOT NULL COMMENT '人员id',
  `role` varchar(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目人员关系表';

/*Data for the table `pm_pro_user_rel_tab` */

insert  into `pm_pro_user_rel_tab`(`id`,`projectid`,`userid`,`role`) values 
('0e00a4d4ede04969ad571b4da9ce1eb0','905dd5297f274a68b366ef635b6bff5b','dc647ef98d8d491ba2be8fdbbb7079e2','1'),
('339a42be00204cff870ed4c923aa2067','905dd5297f274a68b366ef635b6bff5b','991edda8e8f64ed1bf0ab0574733652e','0'),
('62db6cfa2d274dc6a036dabbf46f356a','905dd5297f274a68b366ef635b6bff5b','e65cc68180024a0cbf468ebac34ed08a','2'),
('c6c1d5ac51e947519c0f9248952cd56d','905dd5297f274a68b366ef635b6bff5b','d64f6b0117924657a290d0f2589166d4','0');

/*Table structure for table `pm_project_tab` */

DROP TABLE IF EXISTS `pm_project_tab`;

CREATE TABLE `pm_project_tab` (
  `id` varchar(100) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '项目名',
  `description` varchar(1000) DEFAULT NULL COMMENT '项目描述',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目表';

/*Data for the table `pm_project_tab` */

insert  into `pm_project_tab`(`id`,`name`,`description`,`createtime`) values 
('905dd5297f274a68b366ef635b6bff5b','网银','淘宝付款','2018-01-02 15:04:55');

/*Table structure for table `pm_task_tab` */

DROP TABLE IF EXISTS `pm_task_tab`;

CREATE TABLE `pm_task_tab` (
  `id` varchar(100) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '任务名称',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `bug` varchar(2000) DEFAULT NULL COMMENT 'bug描述',
  `jhstarttime` datetime DEFAULT NULL,
  `jhendtime` datetime DEFAULT NULL,
  `sjstarttime` datetime DEFAULT NULL,
  `sjendtime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `userid` varchar(100) NOT NULL COMMENT '执行人',
  `testerid` varchar(100) DEFAULT NULL COMMENT '测试人',
  `projectid` varchar(100) NOT NULL COMMENT '所属项目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

/*Data for the table `pm_task_tab` */

insert  into `pm_task_tab`(`id`,`name`,`description`,`bug`,`jhstarttime`,`jhendtime`,`sjstarttime`,`sjendtime`,`state`,`userid`,`testerid`,`projectid`) values 
('1289a7be82d842e8b6e372af0b355f77','前台写完','','我日','2017-12-28 14:05:32','2017-12-29 14:05:35','2017-12-28 15:17:35','2017-12-31 23:50:24',4,'91a0ee0e31d642c686ea4b89e3c4b7bf','91a0ee0e31d642c686ea4b89e3c4b7bf','15da2d555cfc42119359db51827292d9'),
('18d2954d068a4affa1514b62fe4d51cb','前端网页','使用css3设计页面',NULL,'2018-01-03 08:55:42','2018-01-04 08:55:46',NULL,NULL,0,'d64f6b0117924657a290d0f2589166d4',NULL,'905dd5297f274a68b366ef635b6bff5b'),
('3394ce683ac14f0186a588faea139383','的','阿斯蒂as',NULL,'2018-01-01 23:01:40','2018-01-04 23:01:40',NULL,NULL,0,'fb62e72d0ecf42258a1d197c0f40c3ce',NULL,'15da2d555cfc42119359db51827292d9'),
('5bac3d2f443046cdb0e29451200b97cf','我是你大爷','','撒地方的阿斯蒂芬','2017-12-30 18:42:40','2017-12-30 18:42:43',NULL,NULL,2,'1c75c77ede874c25a3f85ad3c01ab7f1','5044b2ab151d4527a17b37a4651102fe','15da2d555cfc42119359db51827292d9'),
('6a54497914ef47899a81dbc32b5fb5e8','标题做完','把标题做完',NULL,'2018-01-02 15:09:50','2018-01-12 15:09:52','2018-01-02 15:10:10',NULL,1,'e65cc68180024a0cbf468ebac34ed08a',NULL,'905dd5297f274a68b366ef635b6bff5b'),
('6ec925e0195c4c5b9dfa060ac1b45250','我们都是','修改你的','我日了哦 哦','2017-12-29 09:38:12','2017-12-29 09:38:18',NULL,'2018-01-02 15:00:04',1,'5044b2ab151d4527a17b37a4651102fe','91a0ee0e31d642c686ea4b89e3c4b7bf','15da2d555cfc42119359db51827292d9'),
('80b7daf650864700af8d62c00acdec00','网络计算器','前台第一部分',NULL,'2018-01-02 23:00:24','2018-01-04 23:00:29',NULL,NULL,0,'fb62e72d0ecf42258a1d197c0f40c3ce',NULL,'15da2d555cfc42119359db51827292d9'),
('8848a08caf2742e881f1b0678dc93ceb','后端数据','后端数据设计',NULL,'2018-01-03 08:57:32','2018-01-04 08:57:35',NULL,NULL,0,'e65cc68180024a0cbf468ebac34ed08a',NULL,'905dd5297f274a68b366ef635b6bff5b'),
('a41b70e4af7b487dafb986db628a2d14','你好','','阿斯蒂','2017-12-31 16:09:45','2018-01-03 16:09:50',NULL,NULL,2,'1c75c77ede874c25a3f85ad3c01ab7f1','91a0ee0e31d642c686ea4b89e3c4b7bf','15da2d555cfc42119359db51827292d9'),
('ae53253fbcbb4f4f9406af81b32ccef1','后台写完','','我是bug','2017-12-28 14:06:04','2017-12-29 14:06:06',NULL,'2017-12-31 23:50:27',1,'1c75c77ede874c25a3f85ad3c01ab7f1','91a0ee0e31d642c686ea4b89e3c4b7bf','15da2d555cfc42119359db51827292d9'),
('c99e57a83b0c455ab1699cb5d43c61b7','第三方','','我们都是大爷的的的的 的','2018-01-02 11:51:25','2018-01-11 11:51:27','2018-01-02 11:51:51',NULL,2,'d78b8e55d46043019eb2dcf8bb53866f','d78b8e55d46043019eb2dcf8bb53866f','15da2d555cfc42119359db51827292d9'),
('d081eb28bdb044578f14559769d9d8ab','都是','',NULL,'2018-01-02 11:51:33','2018-01-19 11:51:38',NULL,NULL,0,'1c75c77ede874c25a3f85ad3c01ab7f1',NULL,'15da2d555cfc42119359db51827292d9');

/*Table structure for table `pm_user_tab` */

DROP TABLE IF EXISTS `pm_user_tab`;

CREATE TABLE `pm_user_tab` (
  `id` varchar(100) NOT NULL COMMENT '编号',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `name` varchar(100) NOT NULL COMMENT '真实姓名',
  `pwd` varchar(100) NOT NULL COMMENT '密码',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `pinyin` varchar(100) DEFAULT NULL COMMENT '拼音',
  `jianpin` varchar(100) DEFAULT NULL COMMENT '简拼',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `pm_user_tab` */

insert  into `pm_user_tab`(`id`,`username`,`name`,`pwd`,`memo`,`pinyin`,`jianpin`) values 
('991edda8e8f64ed1bf0ab0574733652e','2','王刚','123456','工程师','wanggang','wg'),
('d64f6b0117924657a290d0f2589166d4','1','王大治','123456','王大治程序员','wangdazhi','wdz'),
('dc647ef98d8d491ba2be8fdbbb7079e2','4','胡一刀','123456','刀客','huyidao','hyd'),
('e65cc68180024a0cbf468ebac34ed08a','3','小刘','123456','经理','xiaoliu','xl');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
