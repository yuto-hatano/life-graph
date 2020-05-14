package com.lifegraph.team20.refapi;

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

@RestController
public class RefApiSampleController {


	@RequestMapping(value = "/ref", method = RequestMethod.GET)
	 public List<Map<String, Object>> ref() {
		List<Sample> samples = selectSamples();
		List<Map<String, Object>> sampleList = uploadSample(samples);
		return sampleList;
	}
	@Autowired
	//MySQLのデータを持ってくるライブラリ
	private JdbcTemplate jdbcTemplate;

	public List<Sample> selectSamples() {
		final String sql = "select * from sample";

		return jdbcTemplate.query(sql, new RowMapper<Sample>() {
			// Sampleの中にそれぞれのデータを入れている　
			//その後にRowMapper<Sample>に返却される
			public Sample mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Sample(rs.getInt("id"), rs.getString("name"),
			            rs.getInt("age"), rs.getInt("score"));
			}
		});
	}

	public List<Map<String, Object>> uploadSample(List<Sample> samples) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	    Map<String, Object> map;
	    Map<String, List<Sample>> sampleMap = samples.stream()
	        .collect(Collectors.groupingBy(Sample::getName));
	    for (Map.Entry<String, List<Sample>> entry : sampleMap.entrySet()) {
	      map = new HashMap<>();
	      map.put("name", entry.getValue());
//	      map.put("name", entry.getKey());
//	      map.put("data", entry.getValue());

	      dataList.add(map);
	    }
		return dataList;
	}

}
