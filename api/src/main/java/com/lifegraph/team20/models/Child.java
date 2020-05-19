package com.lifegraph.team20.models;


public class Child {

	private long id;
	private long parentId;
	private int age;
	private int score;
	private String comment;

	public Child(long id, long parentId, int age, int score, String comment) {
		this.setId(id);
		this.setParentId(parentId);
		this.setAge(age);
		this.setScore(score);
		this.setComment(comment);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



}
