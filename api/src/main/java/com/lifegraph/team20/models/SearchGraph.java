package com.lifegraph.team20.models;

import java.sql.Timestamp;

public class SearchGraph {
	private String name;
	private int user_id;
	private Timestamp updated_at;
	private Timestamp created_at;

//	public searchGraph(String string, int int1, Timestamp timestamp) {
//		// Timestampの型定義ができんで自動生成したからいらんかも
//	}

	public SearchGraph( String name,int user_id,Timestamp updated_at,Timestamp created_at) {
		this.setName(name);
		this.setId(user_id);
		this.setUpdate(updated_at);
		this.setCreate(created_at);
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

	public Timestamp getUpdate() {
		return updated_at;
	}

	public void setUpdate(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Timestamp getCreate() {
		return created_at;
	}

	public void setCreate(Timestamp created_at) {
		this.created_at = created_at;
	}

}