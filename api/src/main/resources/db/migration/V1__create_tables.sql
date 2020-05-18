CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL UNIQUE,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (
  `email`,
  `username`,
  `password`
) VALUES
  ('taito.kudo@seattleconsulting.co.jp', '工藤', 'team'),
  ('yuto.hatano@seattleconsulting.co.jp', 'はたちゃん', 'team'),
  ('haruka.saito@seattleconsulting.co.jp', 'はるか', 'team');

CREATE TABLE `roles`(
  `id` int NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `user_roles`(
  `user_id` bigint not null,
  `role_id` int not null,
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

INSERT INTO `roles`(
  `name`
)VALUES
('ROLE_USER'),
('ROLE_MODERATOR'),
('ROLE_ADMIN');

CREATE TABLE `parent_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL UNIQUE,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) ,
  UNIQUE KEY (`user_id`),
  FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
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
  FOREIGN KEY(`parent_id`)
  REFERENCES `parent_graphs` (`id`)
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

ALTER TABLE `child_graphs` ADD CONSTRAINT UNIQUE (`parent_id`,`age`);