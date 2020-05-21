package com.lifegraph.team20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.service.DeleteService;

@RestController
public class DeleteApiController {

  @Autowired
  private DeleteService service;

  @RequestMapping(value = "/auth/graph-delete", method = RequestMethod.POST)
  public void deleteController(@RequestBody long userId) {

    service.delete(userId);
  }

}
