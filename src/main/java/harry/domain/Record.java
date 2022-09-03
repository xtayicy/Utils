package harry.domain;

import java.util.List;

public class Record {
	List<String> scores;
	String name;
	Integer age;
	public List<String> getScores() {
		return scores;
	}
	public void setScores(List<String> scores) {
		this.scores = scores;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
