package com.lifegraph.team20.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.Child;
import com.lifegraph.team20.models.ChildGraph;
import com.lifegraph.team20.models.Parent;
import com.lifegraph.team20.models.ParentGraph;

@RestController
public class RefApiController {

<<<<<<< HEAD
  //  @GetMapping(value = "/ref/{parentId}")
  @GetMapping(value = "/auth/ref/{id}")
  public ResponseEntity<ChildGraph> Child(
      //      @RequestBody Child data) {
      @PathVariable("id") Integer id) {
    //	Optional:その値がnullかもしれないことを表現するクラス
    //			URLが叩かれたときにこれが動く
    //    long parentId = data.getParentId();
    ChildGraph Child = selectChilds(id);
=======
  @GetMapping(value = "/auth/ref/{userId}")
  public ResponseEntity<List<Child>> Child(
      @PathVariable("userId") long userId) {
    //	Optional:その値がnullかもしれないことを表現するクラス
    //			URLが叩かれたときにこれが動く

    //userIdを元に、親テーブルのレコードを持ってくる
    ParentGraph parent = parentId(userId);
    long parentId = parent.getId();

    List<Child> Child = selectChilds(parentId);
>>>>>>> develop
    //			l.29のselectSearchGraphを呼び出す
    return ResponseEntity.ok(Child);
  }

  @Autowired
  //MySQLのデータを持ってくるライブラリ
  private JdbcTemplate jdbcTemplate;

<<<<<<< HEAD
  public ChildGraph selectChilds(long ID) {
    final String sql = "select child_graphs.id,parent_id,age,score,comment from child_graphs inner join parent_graphs on child_graphs.parent_id = parent_graphs.id\n"
        +
        "inner join users on parent_graphs.user_id = users.id where users.id= :id;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", ID);
    ChildGraph result = (ChildGraph) jdbcTemplate.queryForList(sql, param, new RowMapper<ChildGraph>() {
      public ChildGraph mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChildGraph(rs.getInt("id"), rs.getInt("parent_id"), rs.getInt("age"), rs.getInt("score"),
            rs.getString("name"));
=======
  //userIdを元に、親テーブルのレコードを持ってくる処理
  public ParentGraph parentId(long userId) {
    String sql = "select * from parent_graphs where user_id =" + userId;
    RowMapper<ParentGraph> mapper = new BeanPropertyRowMapper<ParentGraph>(ParentGraph.class);
    ArrayList<ParentGraph> parentLifeGraphs = (ArrayList<ParentGraph>) jdbcTemplate.query(sql, mapper);

    return parentLifeGraphs.get(0);
  }

  public List<Child> selectChilds(long ID) {
    final String sql = "select * from child_graphs where parent_id=" + ID + "";

    return jdbcTemplate.query(sql, new RowMapper<Child>() {
      // Sampleの中にそれぞれのデータを入れている　
      //その後にRowMapper<Sample>に返却される
      public Child mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Child(rs.getInt("id"), rs.getInt("parent_id"),
            rs.getInt("age"), rs.getInt("score"), rs.getString("comment"));
>>>>>>> develop
      }
    });
    return result;
  }
<<<<<<< HEAD

  //    return jdbcTemplate.query(sql, new RowMapper<Child>() {
  //      // Sampleの中にそれぞれのデータを入れている　
  //      //その後にRowMapper<Sample>に返却される
  //      public Child mapRow(ResultSet rs, int rowNum) throws SQLException {
  //        return new Child(rs.getInt("id"), rs.getInt("parent_id"),
  //            rs.getInt("age"), rs.getInt("score"), rs.getString("comment"));
  //      }
  //    });

  //親グラフのテーブルを丸々持ってくる
  //Parentの中にデータを入れて、RowMapper<Parent>に返却
  //	public List<User> selectUsers(int ID){
  //		final String sql = "select username from users where id="+ID+"";
  //		return jdbcTemplate.query(sql, new RowMapper<Parent>() {
  //			public Parent mapRow(ResultSet rs, int rowNum) throws SQLException{
  //				return new Parent(rs.getInt("id"));
  //			}
  //		});
  //	}
  //親グラフのテーブルを丸々持ってくる
  //Parentの中にデータを入れて、RowMapper<Parent>に返却
  //	public List<Parent> selectParents(int ID){
  //		final String sql = "select id from parent_graphs where id="+ID+"";
  //		return jdbcTemplate.query(sql, new RowMapper<Parent>() {
  //			public Parent mapRow(ResultSet rs, int rowNum) throws SQLException{
  //				return new Parent(rs.getInt("id"));
  //			}
  //		});
  //	}
=======
>>>>>>> develop

  public List<Map<String, Object>> uploadGraph(List<Parent> graphs, List<Child> data) {
    List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
    Map<String, Object> map;
    for (Parent graph : graphs) {
      map = new HashMap<>();
      map.put("id", graph.getId());
      map.put("id", data.stream()
          .filter(

              s -> s.getParentId() == graph.getId())
          .collect(Collectors.toList()));
      dataList.add(map);
    }
    return dataList;
  }

}
