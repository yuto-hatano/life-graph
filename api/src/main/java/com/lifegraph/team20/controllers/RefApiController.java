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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.Child;
import com.lifegraph.team20.models.Parent;

@RestController
public class RefApiController {

	@GetMapping(value = "/ref/{parent_id}")
	public ResponseEntity<List<Child>> Child(
			@PathVariable("parent_id") int ID){
//	Optional:その値がnullかもしれないことを表現するクラス
//			URLが叩かれたときにこれが動く
		List<Child> Child = selectChilds(ID);
//			l.29のselectSearchGraphを呼び出す
		return ResponseEntity.ok(Child);
	}
//	private static final int ParentId = 2;
//	@RequestMapping(value = "/auth/ref", method = RequestMethod.GET)
//	 public List<Map<String, Object>> ref() {
//		List<Parent> graphs = selectParents();
//		List<Child> data = selectChilds();
//
//		List<Map<String, Object>> graphList = uploadGraph(graphs, data);
//		return graphList;
//	}
	@Autowired
	//MySQLのデータを持ってくるライブラリ
	private JdbcTemplate jdbcTemplate;

	public List<Child> selectChilds(int ID) {
		final String sql = "select * from child_graphs where parent_id="+ID+"";

		return jdbcTemplate.query(sql, new RowMapper<Child>() {
			// Sampleの中にそれぞれのデータを入れている　
			//その後にRowMapper<Sample>に返却される
			public Child mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Child(rs.getInt("id"), rs.getInt("parent_id"),
			            rs.getInt("age"), rs.getInt("score"), rs.getString("comment"));
			}
		});
	}
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


	public List<Map<String, Object>> uploadGraph(List<Parent> graphs, List<Child> data) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	    Map<String, Object> map;
	    for (Parent graph: graphs) {
	    	map = new HashMap<>();
	    	map.put("id", graph.getId());
	    	map.put("id", data.stream()
	    			.filter(s -> s.getParentId()==graph.getId())
	    			.collect(Collectors.toList()));
	    	dataList.add(map);
	    }
		return dataList;
	}

}
