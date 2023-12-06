package harry.domain;

public class Result {
	private String msg;
	
	public Result() {
		this("");
	}
	
	public Result(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
