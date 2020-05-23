package com.lifegraph.team20.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  @PostMapping(value = "/auth/life_graphs")
  //  @RequestMapping(value = "/auth/life_graphs", method = RequestMethod.POST)
  public ResponseEntity<Void> register(@Valid @RequestBody LifeGraphData data) throws URISyntaxException {
    // call service
    service.resiter(data);

    return ResponseEntity.created(new URI("/life_graphs/" + data.getUserId())).build();
  }

  @DeleteMapping(value = "/auth/life_graphs")
  public void clear(@RequestBody LifeGraphData data) {
    service.clear(data);
  }

  //-----ここから削除API(全データ削除)-----
  @DeleteMapping(value = "/auth/life-graphs")
  public void deleteGraphs(@RequestBody LifeGraphData data) {

    service.delete(data);
  }

  //-----ここからレコード参照API-----
  @GetMapping(value = "/auth/ref-record")
  public ChildGraph refRecode(@RequestBody LifeGraphData data) {
    ChildGraph record = service.refRecode(data);
    return record;
  }
}