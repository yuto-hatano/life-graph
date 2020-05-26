package com.lifegraph.team20.models;

import lombok.Data;

@Data
public class ParentGraph {

  private long id;
  private long userId;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
