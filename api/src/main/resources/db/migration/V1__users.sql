CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mail_adress` varchar(255) NOT NULL UNIQUE,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `autherity` int NOT NULL DEFAULT=1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_adress` (`mail_adress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;