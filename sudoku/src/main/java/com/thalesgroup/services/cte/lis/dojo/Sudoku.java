package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {

	private Grid grid;
	public Sudoku(Integer[][] problem) {
		this.grid=new Grid(problem);
		grid.lastCellCompletion();
		grid.lastValueCompletion();
	}

	public Integer[][] getSolvedGrid() {
		return grid.getAsTable();
	}


}
