package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class SudokuTest {
	public static final Integer x=0;
	
	/**
	 * 	 * 
	 * @param gridA a 9x9 int[][] 
	 * @param gridA a 9x9 int[][] 
	 *  
	 */
	private void DisplayGrid(Integer[][] gridA, Integer[][] gridB) {
		System.out.println("                 BEFORE RESOLUTION                                       AFTER RESOLUTION");
		System.out.println("                 -----------------                                       ----------------");
		System.out.println();
		for (int l=0;l<9;l++) {
			System.out.println("+---+---+---+  +---+---+---+  +---+---+---+                    +---+---+---+  +---+---+---+  +---+---+---+");
			for (int c=0;c<9;c++) {
				int n=gridA[l][c];
				if (n>0) {
					System.out.print(String.format("| %d ",n)); }
				else { System.out.print("|   ");}
				if (c%3==2) { System.out.print("|  ");}
			}
			System.out.print("                  ");
			for (int c=0;c<9;c++) {
				int n=gridB[l][c];
				if (n>0) {
					System.out.print(String.format("| %d ",n)); }
				else { System.out.print("|   ");}
				if (c%3==2) { System.out.print("|  ");}
			}
			
			
			
			System.out.println();
			if (l%3==2) { System.out.println("+---+---+---+  +---+---+---+  +---+---+---+                    +---+---+---+  +---+---+---+  +---+---+---+");System.out.println();
			 }
		}
	}
	
	protected class SolvedProblem {
		private Integer [][] result;
		private Sudoku sudoku;
		SolvedProblem(Integer[][] problem) {
			sudoku=new Sudoku(problem);
			
			try {
				sudoku.solve();
			} catch (RuntimeException e) {
				System.out.println("EXCEPTION CAUGHT !!! "+e.toString());
				
				throw e;
			} finally {
				result=sudoku.getAsTable();
				DisplayGrid(problem, result);
			}
		}
		
		public void shallBeSolved(){
			if (sudoku.remainingCellsToSolve()!=0) {
				fail(String.format("Could not solve the grid ! %d cells still remaining to sove.",sudoku.remainingCellsToSolve()));
			}
		}
		
		class CellChecker {
			private Integer value;
			private int rowNum;
			private int columnNum;
			CellChecker(Integer value,int rowNum,int columnNum) {
				this.value=value;
				this.rowNum=rowNum;
				this.columnNum=columnNum;
			}
			public void shallContain(Integer expectedValue){
				if (value!=expectedValue) {
					String error=String.format("Expected value '%d' in cell (row=%d,column=%d) but obtained value '%d' instead.",expectedValue,rowNum,columnNum,value);
					System.err.println(error);
					fail(error);
				}
			}
		}
		/**
		 * 
		 * Retrieves cell by row,column, in a 1-based way (i.e. first cell is 1,1)
		 * 
		 * @param row
		 * @param column
		 * @return
		 */
		public CellChecker cell(int row, int column) {
			return new CellChecker(result[row-1][column-1],row,column);
		}
	}
	
	
	
}
