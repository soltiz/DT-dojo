package com.thalesgroup.services.cte.lis.dojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sudoku {

	public static final int SUDOKU_SIZE = 9;
	List<Row> rowsList = new ArrayList<Row>();

	public Sudoku(Integer[][] problem) {
		for(int positionLigne = 0; positionLigne < SUDOKU_SIZE; positionLigne ++){
			Row row = new Row(problem[positionLigne]);
			
			rowsList.add(row);
		}
		
	}
	
	public void solve(){
		
		for (Row row : rowsList) {
			try{
				int value = row.searchMissingValue();
				int index= row.findEmptyCase();				
				row.setCellIfEmpty(index, value);
				
			}catch(Exception e){
				System.out.println("Not implemented");
			}
		}

	
	}

	public Integer[][] getAsTable() {

		Integer[][] table = new Integer[SUDOKU_SIZE][SUDOKU_SIZE];
		for(int positionLigne=0; positionLigne<SUDOKU_SIZE ; positionLigne++){
			for(int positionColonne=0; positionColonne<SUDOKU_SIZE; positionColonne++){
				table[positionLigne][positionColonne] = rowsList.get(positionLigne).getNumberInLineAtPosition(positionColonne);
			}
		}
		return table;
	}
	


	public int remainingCellsToSolve() {
		return -1;
	}
	
	public static Set<Integer> getAvailableValues(){
		Set<Integer> sudokuValues = new HashSet<Integer>();
		for (int i = 0; i< Sudoku.SUDOKU_SIZE; i++) {
			sudokuValues.add(i+1);
		}
		
		return sudokuValues;
	}

}
