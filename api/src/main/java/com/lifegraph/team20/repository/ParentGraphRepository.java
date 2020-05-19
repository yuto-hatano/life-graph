package com.lifegraph.team20.repository;
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParentGraphRepository {

  @Autowired
  //MySQLのデータを持ってくるライブラリ
  private static JdbcTemplate jdbcTemplate;

  //user_idが存在するかを確認したい
  //リクエストを送った人のuser_idのレコードをDBから取得する

  public static Integer selectParents(long id) {
    final String sql = "select count(*) from parent_graphs where user_id = " + id;
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return count;
  }

}
