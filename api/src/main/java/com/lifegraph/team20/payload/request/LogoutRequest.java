package com.lifegraph.team20.payload.request;

import lombok.Data;

@Data
public class LogoutRequest {
  private String token;

  public LogoutRequest(String accesstoken) {
    this.token = accesstoken;
  }

}