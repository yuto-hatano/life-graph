package com.lifegraph.team20.models;

import java.sql.Timestamp;

public class SearchGraph {
	private String username;
	private int user_id;
	private Timestamp updated_at;
	private Timestamp created_at;

//	public searchGraph(String string, int int1, Timestamp timestamp) {
//		// Timestampの型定義ができんで自動生成したからいらんかも
//	}

	public SearchGraph( String username,int user_id,Timestamp updated_at,Timestamp created_at) {
		this.setName(username);
		this.setId(user_id);
		this.setUpdateAt(updated_at);
		this.setCreateAt(created_at);
	}

	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public int getId() {
		return user_id;
	}

	public void setId(int Id) {
		this.user_id = Id;
	}

	public Timestamp getUpdateAt() {
		return updated_at;
	}

	public void setUpdateAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Timestamp getCreateAt() {
		return created_at;
	}

	public void setCreateAt(Timestamp created_at) {
		this.created_at = created_at;
	}

}