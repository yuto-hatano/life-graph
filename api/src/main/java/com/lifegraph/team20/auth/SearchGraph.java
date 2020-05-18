package com.lifegraph.team20.auth;

import java.sql.Timestamp;

public class SearchGraph {
	private String name;
	private int user_id;
	private Timestamp updated_at;

//	public searchGraph(String string, int int1, Timestamp timestamp) {
//		// Timestampの型定義ができんで自動生成したからいらんかも
//	}

	public SearchGraph( String name, int user_id ,Timestamp updated_at) {
		this.setName(name);
		this.setId(user_id);
		this.setAt(updated_at);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return user_id;
	}

	public void setId(int Id) {
		this.user_id = Id;
	}

	public Timestamp getAt() {
		return updated_at;
	}

	public void setAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}