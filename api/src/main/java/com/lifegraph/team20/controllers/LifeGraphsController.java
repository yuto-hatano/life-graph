package com.lifegraph.team20.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.ChildGraph;
import com.lifegraph.team20.models.LifeGraphData;
import com.lifegraph.team20.models.SearchGraph;
import com.lifegraph.team20.service.LifeGraphsService;

@RestController
public class LifeGraphsController {

  @Autowired
  private LifeGraphsService service;

  //-----ここから登録編集API-------
  @PostMapping(value = "/life-graphs")
  //  @RequestMapping(value = "/life_graphs", method = RequestMethod.POST)
  public ResponseEntity<Void> register(@Valid @RequestBody LifeGraphData data) throws URISyntaxException {
    // call service
    service.resiter(data);

    return ResponseEntity.created(new URI("/life-graphs/" + data.getUserId())).build();
  }

  //  子テーブルの1レコードのみを消去
  @DeleteMapping(value = "/life_graphs")
  public ResponseEntity<Void> clear(@RequestBody LifeGraphData data) {
    service.clear(data);
    return ResponseEntity.noContent().build();
  }

  //-----ここから削除API(全データ削除)-----
  @DeleteMapping(value = "/life-graphs/{id}")
  public ResponseEntity<Void> deleteGraphs(@PathVariable("id") long userId) {

    service.delete(userId);
    return ResponseEntity.noContent().build();
  }

  //-----ここからレコード参照API-----
  @PostMapping(value = "/ref-record")
  public ResponseEntity<ChildGraph> refRecode(@RequestBody LifeGraphData data) {
    ChildGraph record = service.refRecode(data);
    return ResponseEntity.ok(record);
  }

  @GetMapping(value = "/life-graphs")
  //  ResponseEntity<String> doPost(@RequestBody UploadForm body){
  public ResponseEntity<List<SearchGraph>> SearchGraphs(@RequestParam("likeName") Optional<String> likeName,
      @RequestParam("startDate") Optional<String> startDate,
      @RequestParam("finishDate") Optional<String> finishDate) {
    //    Optional:その値がnullかもしれないことを表現するクラス
    //        URLが叩かれたときにこれが動く
    List<SearchGraph> SearchGraphs = SelectSearchGraph(likeName, startDate, finishDate);
    //        l.84のselectSearchGraphを呼び出す
    return ResponseEntity.ok(SearchGraphs);
  }

  //  }
  @Autowired
  //MySQLのデータを持ってくるライブラリ
  private JdbcTemplate jdbcTemplate;

  private List<SearchGraph> SelectSearchGraph(Optional<String> likeName, Optional<String> startDate,
      Optional<String> finishDate) {
    //    String sql="";
    //    ここにif文をいれる 名前検索が入ったらと日時検索が入ったら
    String sql = "select username,user_id,updated_at,created_at from users inner join parent_graphs on users.id "
        + "= parent_graphs.user_id ";
    if (likeName.isPresent()) {
      sql += " where username like '%" + likeName.get() + "%'";
    } else if (startDate.isPresent() && finishDate.isPresent()) {
      sql += "WHERE `updated_at` BETWEEN '" + startDate.get() + "' AND '" + finishDate.get() + "'";
    }
    //        sqlに"select ~"という文字列をいれる
    return jdbcTemplate.query(sql, new RowMapper<SearchGraph>() {

      //      quelyの操作
      //      RowMapper:JdbcTemplate.queryの処理を実行した際に、DBから取得した結果とJavaのオブジェクトとを紐づける
      //      SearchGraph.javaの中にそれぞれのデータを入れている　
      //      その後にRowMapper<SearchGraph>に返却される
      public SearchGraph mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SearchGraph(rs.getString("username"), rs.getInt("user_id"),
            rs.getTimestamp("updated_at"), rs.getTimestamp("created_at"));
        //          取得したidをl.33のSearchGraphに返す
        //          さらにそのSearchGraphをl.24のselectSearchGraphに返す。
      }
    });
  }
}
