package com.lifegraph.team20.payload.response;

import lombok.Data;

@Data
public class LogoutResponse {
  String token;

  public LogoutResponse(String accesstoken) {
    this.token = accesstoken;
  }
}