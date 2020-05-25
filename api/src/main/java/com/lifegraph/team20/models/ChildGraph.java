package com.lifegraph.team20.models;

import lombok.Data;

@Data

public class ChildGraph {

  private long id;
  private long parentId;
  private int age;
  private int score;
  private String comment;

  public ChildGraph(long id, long parentId, int age, int score, String comment) {
    this.id = id;
    this.parentId = parentId;
    this.age = age;
    this.score = score;
    this.comment = comment;
  }
}
