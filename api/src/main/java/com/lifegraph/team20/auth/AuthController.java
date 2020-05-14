package com.lifegraph.team20.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//  RestControllerではViewに遷移せず、メソッドの戻り値がそのままレスポンスコンテンツになる。
@RestController
@RequestMapping("/auth")
public class AuthController {

  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public ResponseEntity<String> login() {
    return ResponseEntity.ok("OK");
  }
}
