DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `mobile` varchar(40) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Customer_customerId_uindex` (`id`),
  UNIQUE KEY `Customer_cid_uindex` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `team_member` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'billt101','Parth Narang','parthnarang2201@gmail.com','9811677643','2019-01-02 13:01:10','2019-01-02 13:02:10');
/*!40000 ALTER TABLE `team_member` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mid` varchar(100) DEFAULT NULL,
  `merchant_name` varchar(100) DEFAULT NULL,
  `merchant_address` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `merchant_logo` varchar(300) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `contact_list` varchar(40) DEFAULT NULL,
  `gst_no` varchar(40) DEFAULT NULL,
  `tin_no` varchar(40) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Merchant_id_uindex` (`id`),
  UNIQUE KEY `Merchant_mid_uindex` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;

INSERT INTO `merchant` VALUES (1,'mid1001','Burger King','Great India Palace Noida','https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/512px-Burger_King_Logo.svg.png','burgerking@gmailc.com','9811677643','9811666444','2019-01-02 13:01:10','2019-01-02 13:02:10');
INSERT INTO `merchant` VALUES (2,'mid1002','H&M Store','DLF Mall of india','https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/512px-Burger_King_Logo.svg.png','h&m@gmailc.com','9811677643','9811666444','2019-01-02 13:01:10','2019-01-02 13:02:10');
INSERT INTO `merchant` VALUES (3,'mid1003','Starbucks','DLF Mall of india','https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Starbucks_Corporation_Logo_2011.svg/1200px-Starbucks_Corporation_Logo_2011.svg.png','h&m@gmailc.com','9811677643','9811666444','2019-01-02 13:01:10','2019-01-02 13:02:10');
INSERT INTO `merchant` VALUES (4,'mid1004','Big Bazaar','DLF Mall of india','https://www.bigbazaar.com/assets/images/logo-bigbazaar-fb.jpg','h&m@gmailc.com','9811677643','9811666444','2019-01-02 13:01:10','2019-01-02 13:02:10');
INSERT INTO `merchant` VALUES (5,'mid1005','Pizza Hut','DLF Mall of india','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToizJ2VPvQjlICjowUFyEgDkA_Wi9_pe7agf3uIlvi-U5zn6dQ','h&m@gmailc.com','9811677643','9811666444','2019-01-02 13:01:10','2019-01-02 13:02:10');
INSERT INTO `merchant` VALUES (6,'mid1006','NIKE','DLF Mall of india','https://images-na.ssl-images-amazon.com/images/I/61Q-S6t2yNL._SX425_.jpg','h&m@gmailc.com','9811677643','9811666444','2019-01-02 13:01:10','2019-01-02 13:02:10');






