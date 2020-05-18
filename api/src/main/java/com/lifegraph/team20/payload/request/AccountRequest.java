package com.lifegraph.team20.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountRequest {
  @NotBlank
  private String username;

  @NotBlank
  private String password;

}