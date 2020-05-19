package com.lifegraph.team20.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.payload.request.LifeGraphData;
import com.lifegraph.team20.service.LifeGraphsService;

@RestController
public class LifeGraphsController {

  @Autowired
  private LifeGraphsService service;

  @RequestMapping(value = "/auth/life_graphs", method = RequestMethod.POST)
  public ResponseEntity<Void> postController(@Valid @RequestBody LifeGraphData data) throws URISyntaxException {

    // call service
    service.resiter(data);
//
//    long id = data.getUserId();
//    int ageInt = data.getAge();
//    int scoreInt = data.getScore();
//    String comment = data.getComment();
//
//    // 存在チェック
//    ExistenceCheckService.exists(id, ageInt);

    return ResponseEntity.created(new URI("/life_graphs/")).build();
  }

}