package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {

	private Grid grid;
	public Sudoku(Integer[][] problem) {
		this.grid=new Grid(problem);
			}
	
	public void solve(){
		Integer remainingValuesToEliminate=remainingValuesToEliminate();
		Integer previousRemaining=10000;
		while ((remainingValuesToEliminate!=0) && (remainingValuesToEliminate<previousRemaining)) {
			previousRemaining=remainingValuesToEliminate;
		
			//grid.lastCellCompletion();
			grid.lastValueCompletion();
			grid.twoGroupsValuesPairLock();
			//grid.NGroupsValuesLock();
			remainingValuesToEliminate=remainingValuesToEliminate();
			System.out.println(String.format("%d values yet to be eliminated before problem is solved.",remainingValuesToEliminate));
		}
	}

	public Integer[][] getAsTable() {
		return grid.getAsTable();
	}
	
	public Integer remainingValuesToEliminate() {
		return grid.remainingNbOfValuesToEliminate();
	}

	public int remainingCellsToSolve() {
		return grid.remainingCellsToSolve();
	}


}
