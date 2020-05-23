package com.lifegraph.team20.models;

import lombok.Data;

@Data
public class ChildGraph {

  private long id;
  private long parentId;
  private int age;
  private int score;
  private String comment;

}
