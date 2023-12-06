package harry.domain.test;

public class Parameter {
	private String code;
	private String key1;
	private String key2;
	private String workflow;
	private String keepResultName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getWorkflow() {
		return workflow;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public String getKeepResultName() {
		return keepResultName;
	}
	public void setKeepResultName(String keepResultName) {
		this.keepResultName = keepResultName;
	}
	@Override
	public String toString() {
		return "Parameter [code=" + code + ", key1=" + key1 + ", key2=" + key2 + ", workflow=" + workflow
				+ ", keepResultName=" + keepResultName + "]";
	}
}
