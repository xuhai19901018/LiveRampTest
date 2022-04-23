package io.github.xuhai19901018.abs;

public enum ProcessStatus {

	NotStarted("尚未开始"),
	
	Handling("进行中"),
	
	Failed("失败"),
	
	Succeed("成功");

	private String message;

	ProcessStatus(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
	
}
