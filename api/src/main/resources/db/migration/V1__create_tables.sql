CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mail_address` varchar(255) NOT NULL UNIQUE,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `authority` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (
  `mail_address`,
  `name`,
  `password`,
  `authority`
) VALUES
  ('taito.kudo@seattleconsulting.co.jp', '工藤', 'team', 1),
  ('yuto.hatano@seattleconsulting.co.jp', 'はたちゃん', 'team', 1),
  ('haruka.saito@seattleconsulting.co.jp', 'はるか', 'team', 1);


CREATE TABLE `parent_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL UNIQUE,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) ,
  UNIQUE KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `parent_graphs` (
  `user_id`,
  `created_at`,
  `updated_at`
) VALUES
  (3, cast( now() as date), cast( now() as datetime )),
  (1, cast( now() as date), cast( now() as datetime )),
  (2, cast( now() as date), cast( now() as datetime ));


CREATE TABLE `child_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL,
  `age` int NOT NULL,
  `score` int NOT NULL,
  `comment` varchar(4255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`parent_id`,`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `child_graphs` (
  `parent_id`,
  `age`,
  `score`,
  `comment`
) VALUES
  (3, 10, 100, 'aaaaaaa'),
  (3, 15, 50, 'bbbbbbb'),
  (2, 14, -20, 'ccccccc'),
  (2, 22, -90, 'ccccccc');