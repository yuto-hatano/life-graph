package com.lifegraph.team20.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchGraph {
  private String username;
  private int user_id;
  private LocalDateTime updated_at;
  private LocalDateTime created_at;

}