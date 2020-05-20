package com.lifegraph.team20.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.LifeGraphData;
import com.lifegraph.team20.service.ExistenceCheckService;

@RestController

public class LifeGraphsController {

  @RequestMapping(value = "/auth/life_graphs", method = RequestMethod.POST)

  public ResponseEntity<String> postController(@Valid @RequestBody LifeGraphData data) {

    long id = data.getUserId();
    int ageInt = data.getAge();
    int scoreInt = data.getScore();
    String comment = data.getComment();

    //存在チェック
    ExistenceCheckService.exists(id, ageInt);

    return ResponseEntity.ok("OK");
  }

}