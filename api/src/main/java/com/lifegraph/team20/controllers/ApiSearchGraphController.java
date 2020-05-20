package com.lifegraph.team20.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.SearchGraph;

@RestController
public class ApiSearchGraphController {

//	private static final String like_name = "";
//	private static final String start_date = "2020-05-01";
//	private static final String finish_date = "2020-06-01";

//	メソッドや処理を関連づけるアノテーション
	@GetMapping(value = "/auth/search")
//	ResponseEntity<String> doPost(@RequestBody UploadForm body){
		public  List<SearchGraph>SearchGraphs(@RequestParam("LikeName") Optional<String>like_name,
				@RequestParam("StartDate") Optional<Timestamp>start_date,
				@RequestParam("FinishDate") Optional<Timestamp>finish_date){
//		Optional:その値がnullかもしれないことを表現するクラス
//				URLが叩かれたときにこれが動く
			List<SearchGraph> SearchGraphs = SelectSearchGraph(like_name,start_date,finish_date);
//				l.29のselectSearchGraphを呼び出す
			return SearchGraphs;
		}

//	}
  @Autowired
  //MySQLのデータを持ってくるライブラリ
  private JdbcTemplate jdbcTemplate;
	private List<SearchGraph> SelectSearchGraph(Optional<String> like_name,Optional<Timestamp> start_date,Optional<Timestamp> finish_date){
		String sql="";
//		ここにif文をいれる 名前検索が入ったらと日時検索が入ったら
		if(like_name != null) {
		final String sql1 = "select name,user_id,updated_at,created_at from users inner join parent_graphs on users.id "
				+ "= parent_graphs.user_id  where name like '%"+like_name+"%'";
		sql=sql1;
		}
		else if(start_date != null && finish_date != null) {
		final String sql2 =" select name,user_id,updated_at,created_at from users inner join parent_graphs on users.id "
				+ "= parent_graphs.user_id WHERE `updated_at` BETWEEN  '"
				+ start_date+"' AND '"+finish_date+"'";
		sql=sql2;
		}
//				sqlに"select ~"という文字列をいれる
		return jdbcTemplate.query(sql, new RowMapper<SearchGraph>() {

//			quelyの操作
//			RowMapper:JdbcTemplate.queryの処理を実行した際に、DBから取得した結果とJavaのオブジェクトとを紐づける
//			SearchGraph.javaの中にそれぞれのデータを入れている　
//			その後にRowMapper<SearchGraph>に返却される
				public SearchGraph mapRow(ResultSet rs, int rowNum) throws SQLException{
					return new SearchGraph(rs.getString("name"),rs.getInt("user_id"),
							rs.getTimestamp("updated_at"),rs.getTimestamp("created_at"));
//					取得したidをl.33のSearchGraphに返す
//					さらにそのSearchGraphをl.24のselectSearchGraphに返す。
				}
		});
	}

  //	public List<Map<String, Object>> uploadSearchGraph(List<SearchGraph> SearchGraphs) {
  //		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
  //	    Map<String, Object> map;
  ////	    for (SearchGraph graph: SearchGraphs) {
  ////	    	繰り返し行う
  //	    map = new HashMap<>();
  //	    map.put("name", SearchGraphs.stream());
  //    	map.put("user_id", SearchGraphs.stream());
  //    	map.put("update_at", SearchGraphs.stream());
  ////	    		DBのid
  ////    			.filter(s -> s.getParentId()==graph.getId())
  ////    			.collect(Collectors.toList()));
  ////	      map.put("name", entry.getKey());
  ////	      map.put("data", entry.getValue());
  //
  //	      dataList.add(map);
  ////	      mapの中身からdatalistにいれる
  ////	    }
  //		return dataList;
  //	}

}
