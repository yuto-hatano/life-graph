package com.lifegraph.team20.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.models.RefAccount;

@Repository
public class AccountRepository {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  public RefAccount sellectAccount(Integer id) {
    // 三つのテーブルくっつけてる。ユーザ名と権限名を取得するため
    final String sql = "select users.id, username, name from users inner join user_roles on users.id = user_roles.user_id\n"
        +
        "inner join roles on roles.id = user_roles.role_id where users.id = :id;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
    RefAccount result = jdbcTemplate.queryForObject(sql, param, new RowMapper<RefAccount>() {
      public RefAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RefAccount(rs.getInt("id"), rs.getString("username"), rs.getString("name"));
      }
    });
    return result;
  }
}