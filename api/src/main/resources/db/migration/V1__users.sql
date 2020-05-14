CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL UNIQUE,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `authority` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;