package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {

	private Grid grid;
	public Sudoku(Integer[][] problem) {
		this.grid=new Grid(problem);
			}
	
	public void solve(){
		grid.lastCellCompletion();
		grid.lastValueCompletion();
		grid.twoGroupsValuesPairLock();

	}

	public Integer[][] getAsTable() {
		return grid.getAsTable();
	}


}
