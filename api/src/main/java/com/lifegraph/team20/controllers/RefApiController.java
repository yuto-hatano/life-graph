package com.lifegraph.team20.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.Child;
import com.lifegraph.team20.models.Parent;

@RestController
public class RefApiController {


//	private static final int ParentId = 2;
	private static final int ID = 3;

	@RequestMapping(value = "/auth/ref", method = RequestMethod.GET)
	 public List<Map<String, Object>> ref() {
		List<Parent> graphs = selectParents();
		List<Child> data = selectChilds();

		List<Map<String, Object>> graphList = uploadGraph(graphs, data);
		return graphList;
	}
	@Autowired
	//MySQLのデータを持ってくるライブラリ
	private JdbcTemplate jdbcTemplate;

	public List<Child> selectChilds() {
		final String sql = "select * from `child_graphs` ";

		return jdbcTemplate.query(sql, new RowMapper<Child>() {
			// Sampleの中にそれぞれのデータを入れている　
			//その後にRowMapper<Sample>に返却される
			public Child mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Child(rs.getLong("id"), rs.getLong("parent_id"),
			            rs.getInt("age"), rs.getInt("score"), rs.getString("comment"));
			}
		});
	}
	//親グラフのテーブルを丸々持ってくる
	//Parentの中にデータを入れて、RowMapper<Parent>に返却
	public List<Parent> selectParents(){
		final String sql = "select id from parent_graphs where id="+ID+"";
		return jdbcTemplate.query(sql, new RowMapper<Parent>() {
			public Parent mapRow(ResultSet rs, int rowNum) throws SQLException{
				return new Parent(rs.getLong("id"));
			}
		});
	}


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
