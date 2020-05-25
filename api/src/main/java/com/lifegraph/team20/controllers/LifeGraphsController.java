package com.lifegraph.team20.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.ChildGraph;
import com.lifegraph.team20.models.LifeGraphData;
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
}
