package com.lifegraph.team20.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.lifegraph.team20.models.ChildGraph;

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

  //idが存在しているかどうか
  public Optional<ChildGraph> findById(long id) {
    final String sql = "select * from child_graphs where id = " + id;
    RowMapper<ChildGraph> mapper = new BeanPropertyRowMapper<ChildGraph>(ChildGraph.class);
    ArrayList<ChildGraph> childLifeGraphs = (ArrayList<ChildGraph>) jdbcTemplate.query(sql, mapper);
    return CollectionUtils.isEmpty(childLifeGraphs) ? Optional.empty() : Optional.of(childLifeGraphs.get(0));
  }

  public Boolean existsByUserIdAndAge(long parentId, int age) {
    final String sql = "select count(( parent_id = " + parentId + " and age = " + age + ") or null) from child_graphs";
    //    final String sql = "select count(*) from child_graphs where parent_id = " + parentId + " age = " + age;
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return 1 <= count;
  }

  // parentIdとageが一致するレコードを持ってくる
  public ChildGraph refRecord(long parentId, int age) {
    String sql = "select * from child_graphs where parent_id = " + parentId + " and age = " + age;
    RowMapper<ChildGraph> mapper = new BeanPropertyRowMapper<ChildGraph>(ChildGraph.class);
    ArrayList<ChildGraph> childLifeGraphs = (ArrayList<ChildGraph>) jdbcTemplate.query(sql, mapper);
    return childLifeGraphs.get(0);
  }

}
