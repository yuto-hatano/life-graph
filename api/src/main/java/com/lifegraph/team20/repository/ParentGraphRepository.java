package com.lifegraph.team20.repository;
//

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.lifegraph.team20.models.ParentGraph;

@Repository
public class ParentGraphRepository {

  @Autowired
  //MySQLのデータを持ってくるライブラリ
  private JdbcTemplate jdbcTemplate;

  //user_idが存在するかを確認したい
  //リクエストを送った人のuser_idのレコードをDBから取得する

  /**
   * 親グラフをユーザーIDを元に検索する　※ユーザーIDユニーク
   * @param UserId ユーザーID
   * @return 親グラフ
   */
  // user_idがあるかどうかの処理
  public Optional<ParentGraph> findByUserId(long userId) {
    final String sql = "select * from parent_graphs where user_id = " + userId;
    RowMapper<ParentGraph> mapper = new BeanPropertyRowMapper<ParentGraph>(ParentGraph.class);

    ArrayList<ParentGraph> parentLifeGraphs = (ArrayList<ParentGraph>) jdbcTemplate.query(sql, mapper);
    //    List<Parent> parents = jdbcTemplate.queryForList(sql, Parent.class);
    // paentsに中身がある(user_idが存在する)とき、CollectionUtils.isEmpty(parents)はfalse
    // 空の時はtrue
    // true → Optional.empty() :
    // false → Optional.of(parents.get(0))：parentsにあるインデックス番号が0のものを取得。レコードは一つしか無いから。

    return CollectionUtils.isEmpty(parentLifeGraphs) ? Optional.empty() : Optional.of(parentLifeGraphs.get(0));
  }

  public Boolean existsByUserId(long userId) {
    final String sql = "select count(*) from parent_graphs where user_id = " + userId;
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return 1 <= count;
  }

  // 親グラフにidがなかった時にレコードを追加するインサートを流す処理
  public long insert(long userId) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("parent_graphs").usingGeneratedKeyColumns("id");
    Map<String, Object> parameters = new HashMap<>();

    Date date = new Date();
    parameters.put("user_id", userId);
    parameters.put("created_at", date);
    parameters.put("updated_at", date);
    // execute insert
    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    // convert Number to Int using ((Number) key).intValue()
    return key.longValue();
  }

  public void update(long userId) {
    String sql = "update parent_graphs set updated_at = now() where user_id = " + userId;
    jdbcTemplate.update(sql);
  }

}
