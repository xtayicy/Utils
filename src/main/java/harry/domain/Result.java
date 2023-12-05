package harry.domain;

public class Result {
	public static final String ERROR_MESSAGE_DATABASE = "No database selected";
	
	private String msg;
	
	public Result() {
		this("execute success!");
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
