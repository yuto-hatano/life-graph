package com.lifegraph.team20.refapi;


public class Sample {

	private int id;
	private String name;
	private int age;
	private int score;

	public Sample(int id, String name, int age, int score) {
		this.setId(id);
		this.setName(name);
		this.setAge(age);
		this.setScore(score);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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




}
