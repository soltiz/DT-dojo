package com.thalesgroup.services.cte.lis.dojo;

public class Cell {
	private Integer value;
	public Cell(Integer value) {
		this.value=new Integer(value);
	}
	
	public Integer getValue() {
		return this.value;
	}

	public int getIntValue() {
		if (value==null) {
			return 0;
		}
		return value;
	}

	public void setValue(int i) {
		this.value=i;
	}

	public boolean hasValue() {
		return ((value!=null) && (value!=0));
	}
}
