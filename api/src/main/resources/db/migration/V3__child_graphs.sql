CREATE TABLE `child_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL UNIQUE,
  `age` int NOT NULL,
  `score` int NOT NULL,
  `comment` varchar(4255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `parent_id`,`age` (`parent_id`,`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;