package com.lifegraph.team20.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.models.ParentGraph;

@Repository
public class DeleteRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // parent_idを取り出す
  // 親テーブルのidを取り出す
  public ParentGraph parentId(long userId) {
    String sql = "select * from parent_graphs where user_id =" + userId;
    RowMapper<ParentGraph> mapper = new BeanPropertyRowMapper<ParentGraph>(ParentGraph.class);
    ArrayList<ParentGraph> parentLifeGraphs = (ArrayList<ParentGraph>) jdbcTemplate.query(sql, mapper);

    return parentLifeGraphs.get(0);
  }

  // 子テーブルのデータを全て消す
  public void childDelete(long parentId) {
    String sql = "delete from child_graphs where parent_id = " + parentId;
    jdbcTemplate.update(sql);
  }

  // 親テーブルのデータを消す
  public void parentDelete(long userId) {
    String sql = "delete from parent_graphs where user_id = " + userId;
    jdbcTemplate.update(sql);
  }

  //子テーブルのレコード削除
  public void clear(long id) {
    final String sql = "delete from child_graphs where id = " + id;
    jdbcTemplate.update(sql);
  }

}
