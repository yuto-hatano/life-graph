INSERT INTO `parent_graphs` (
  `user_id`,
  `created_at`,
  `updated_at`
) VALUES
  (3, cast( now() as date), cast( now() as datetime )),
  (1, cast( now() as date), cast( now() as datetime )),
  (2, cast( now() as date), cast( now() as datetime ));