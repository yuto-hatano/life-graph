package com.lifegraph.team20.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChildGraphRepository {

  @Autowired
  //MySQLのデータを持ってくるライブラリ
  private JdbcTemplate jdbcTemplate;
  //子テーブルのレコードを編集する

  // IDを持つ必要がある？？

  public void updateChild(long id, int age, int score, String comment) {

    String sql = "update child_graphs set age = " + age + ", score = " + score + ", comment = '" + comment
        + "'where id  = "
        + id;
    jdbcTemplate.update(sql);
    //    jdbcTemplate.update(
    //        "update child_garphs set age = " + age + ", score = " + score + ", comment = '" + comment + "'where id  = "
    //            + id);

  }

  // 子テーブルにレコードを追加する

  public void addChild(long parentId, int age, int score, String comment) {

    jdbcTemplate
        .update("insert into child_graphs (parent_id, age, score, comment) values (" + parentId + "," + age + ","

            + score + ",'" + comment + "')");

  }

  public Boolean existsByUserIdAndAge(long parentId, int age) {
    final String sql = "select count(( parent_id = " + parentId + " and age = " + age + ") or null) from child_graphs";
    //    final String sql = "select count(*) from child_graphs where parent_id = " + parentId + " age = " + age;
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return 1 <= count;
  }

  //  //ageが重複しているか確認したい
  //  //リクエストできたageが同じユーザーのレコードに既に存在しているか
  //  public static Integer selectChild(int age) {
  //    final String sql = "select count(*) from child_graphs where age = " + age;
  //    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
  //    return count;
  //  }
  //
  //  //子テーブルにレコードを追加する
  //  public static void addChild(long id, int age, int score, String comment) {
  //    final String sql = "insert into child_graphs (parent_id, age, score, comment) values ("
  //        + id + "," + age + "," + score + ")";
  //
  //  }

}
