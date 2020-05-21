package com.lifegraph.team20.models;

public class ParentGraph {

  private long id;
  private long userId;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
  //
  //  public Parent(long id, long userId, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt) {
  //    this.setId(id);
  //    this.setUserId(userId);
  //    this.setCreatedAt(createdAt);
  //    this.setUpdatedAt(updatedAt);
  //  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public java.sql.Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(java.sql.Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

}
