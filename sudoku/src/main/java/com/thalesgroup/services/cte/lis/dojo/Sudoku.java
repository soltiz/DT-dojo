package com.thalesgroup.services.cte.lis.dojo;

public class Sudoku {

	private Grid grid;
	public Sudoku(Integer[][] problem) {
		this.grid=new Grid(problem);
		grid.lastCellCompletion();
	}

	public Integer[][] getSolvedGrid() {
		return grid.getAsTable();
	}


}
