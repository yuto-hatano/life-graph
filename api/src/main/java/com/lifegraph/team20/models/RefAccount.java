package com.lifegraph.team20.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefAccount {

  @Id
  @GeneratedValue
  private int id;
  @NotBlank
  private String username;
  @NotBlank
  private String name;

  //  public int getId() {
  //    return id;
  //  }
  //
  //  public int setId() {
  //    return id;
  //  }
  //
  //  public String getUsername() {
  //    return username;
  //  }
  //
  //  public String setUsername() {
  //    return username;
  //  }
  //
  //  public String getName() {
  //    return name;
  //  }
  //
  //  public String setName() {
  //    return name;
  //  }
  //
  //  public RefAccount(int id, String username, String name) {
  //    this.id = id;
  //    this.username = username;
  //    this.name = name;
  //  }
}