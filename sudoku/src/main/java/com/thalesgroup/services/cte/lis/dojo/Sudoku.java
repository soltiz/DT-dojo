package com.thalesgroup.services.cte.lis.dojo;

public class Sudoku {

	private Grid grid;
	public Sudoku(Integer[][] problem) {
		this.grid=new Grid(problem);
			}
	
	public void solve(){
		grid.solve();
	}
	
	public void tryRandom(){
		grid.tryRandom();
	}
	public void display() {
		grid.display();
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

	public Cell getCell(int oneBasedRow, int oneBasedCol) {
		return grid.getCell(oneBasedCol-1, oneBasedCol-1);
	}


}
