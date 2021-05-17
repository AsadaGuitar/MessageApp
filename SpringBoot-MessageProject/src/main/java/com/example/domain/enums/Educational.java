package com.example.domain.enums;

public enum Educational {
	Junior("中学校",12,16),
	High("高校",15,19),
	College("大学",18,65),
	Society("社会人",15,65);
	
	private final String viewName;
	private final int start;
	private final int end;
	
	Educational(String viewName, int start, int end) {
		this.viewName = viewName;
		this.start = start;
		this.end = end;
	}
	
	public String getViewName() {
		return viewName;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
}