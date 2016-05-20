package com.thalesgroup.services.cte.lis.dojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Sudoku {

	public static final int SUDOKU_SIZE = 9;
	List<Row> rowsList = new ArrayList<Row>();

	public Sudoku(Integer[][] problem) {
		for(int i = 0; i < SUDOKU_SIZE; i ++){
			Row row = new Row();
			for(int j = 0; j < SUDOKU_SIZE; j ++){
				row.numbersInLine.add(problem[i][j]);
			}
			rowsList.add(row);
		}
		
	}
	
	public void solve(){
		
		for (Row row : rowsList) {
			try{
				row.searchMissingValue();
				//trouverCaseVide
				//trouverCasevVide.valeur = row.searchMissingValue(); 
			}catch(Exception e){
				System.out.println("Not implemented");
			}
		}

	
	}

	public Integer[][] getAsTable() {

		Integer[][] table = new Integer[SUDOKU_SIZE][SUDOKU_SIZE];
		for(int i=0; i<SUDOKU_SIZE ; i++){
			for(int j=0; j<SUDOKU_SIZE; j++){
				table[i][j] = rowsList.get(i).getNumberInLineAtPosition(j);
			}
		}
		return table;
		//return grid.getAsTable();
	}
	


	public int remainingCellsToSolve() {
		return -1;
	//S	return grid.remainingCellsToSolve();
	}
	

}
