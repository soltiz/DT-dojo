package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {

	private Grid grid;
	public Sudoku(Integer[][] problem) {
			}
	
	public void solve(){
	
	}

	public Integer[][] getAsTable() {
		return grid.getAsTable();
	}
	


	public int remainingCellsToSolve() {
		return grid.remainingCellsToSolve();
	}


}
