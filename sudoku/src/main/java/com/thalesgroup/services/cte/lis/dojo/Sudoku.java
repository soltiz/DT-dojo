package com.thalesgroup.services.cte.lis.dojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sudoku {

	public static final int SUDOKU_SIZE = 9;
	List<RowOrColumn> rowsList = new ArrayList<RowOrColumn>();
	List<RowOrColumn> columnsList = new ArrayList<RowOrColumn>();

	public Sudoku(Integer[][] problem) {
		for(int positionLigne = 0; positionLigne < SUDOKU_SIZE; positionLigne ++){
			RowOrColumn row = new RowOrColumn(problem[positionLigne]);
			rowsList.add(row);
		}
		
		for(int positionColumn = 0; positionColumn < SUDOKU_SIZE; positionColumn ++){
			RowOrColumn column = new RowOrColumn(getInvertedArray(problem)[positionColumn]);
			columnsList.add(column);
		}
		
	}
	
	public static Integer[][] getInvertedArray(Integer[][] input){
		int dimAlength = input.length;
		Integer[][] output = new Integer[dimAlength][dimAlength];
		System.out.println("transforming... "+dimAlength+", "+dimAlength);
		for(int i=0; i<dimAlength; i++){
			output[i] = new Integer[dimAlength];
			for(int j=0; j<dimAlength; j++){
				output[i][j] = input[j][i];
			}
		}
		return output;
	}
	
	public void solve(){
		
		for (RowOrColumn row : rowsList) {
			try{
				int value = row.searchMissingValue();
				int index= row.findEmptyCase();				
				row.setCellIfEmpty(index, value);
				
			}catch(Exception e){
				System.out.println("Not implemented");
			}
		}
		
		for (RowOrColumn column : columnsList) {
			try{
				int value = column.searchMissingValue();
				int index= column.findEmptyCase();				
				column.setCellIfEmpty(index, value);
				
			}catch(Exception e){
				System.out.println("Not implemented");
			}
		}

	
	}

	public Integer[][] getAsTable() {

		Integer[][] table = new Integer[SUDOKU_SIZE][SUDOKU_SIZE];
		for(int positionLigne=0; positionLigne<SUDOKU_SIZE ; positionLigne++){
			for(int positionColonne=0; positionColonne<SUDOKU_SIZE; positionColonne++){
				table[positionLigne][positionColonne] = columnsList.get(positionLigne).getNumberInLineAtPosition(positionColonne);
			}
		}
		return getInvertedArray(table);
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
