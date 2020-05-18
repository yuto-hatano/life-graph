package com.lifegraph.team20.payload.response;

import lombok.Data;

@Data
public class AccountResponse {
  private int id;

  public AccountResponse(int id) {
    this.id = id;
  }
}