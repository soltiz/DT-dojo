package com.thalesgroup.services.cte.lis.dojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RowOrColumn {
	
	private List<Integer> numbersInLine = new ArrayList<Integer>();

	
	public RowOrColumn(){
		for (int i = 0; i< Sudoku.SUDOKU_SIZE; i++) {
					
			this.numbersInLine.add(0);
		}
	}
	
	public RowOrColumn(Integer[] problem){
		this();
		for(int positionColonne = 0; positionColonne < Sudoku.SUDOKU_SIZE; positionColonne ++){
			this.numbersInLine.set(positionColonne, problem[positionColonne]);
		}
	}
	
	public Integer getNumberInLineAtPosition(int j) {
		return this.numbersInLine.get(j);
	}

	public int searchMissingValue() {
		Set<Integer> possibleValues = new HashSet<Integer>();

		for (int i = 0; i< Sudoku.SUDOKU_SIZE; i++) {
			possibleValues.add(i+1);	
		}
		possibleValues.removeAll(numbersInLine);
		
		if(possibleValues.size() > 1){
			throw new RuntimeException("pb more than one result"); 
		}else if(possibleValues.isEmpty()){
			throw new RuntimeException("pb result is empty"); 
		}
		
		return possibleValues.iterator().next();
	}

	public int findEmptyCase() {
		int posEmptyCase=0;
		for(Integer number : numbersInLine){
			if(number == 0){
				break;
			}
			posEmptyCase ++;	
		}
		return posEmptyCase;
	}

	public void setCellIfEmpty(int indexInline, int value) {
		
		if(false == Sudoku.getAvailableValues().contains(value)){
			throw new RuntimeException("value is impossible à la position " + indexInline + "avec la valeur " + value); 
		}
		
		if(this.getNumberInLineAtPosition(indexInline) != 0){
			throw new RuntimeException("pb cell is not empty"); 
		}
				
		this.numbersInLine.set(indexInline, value);
	}

}
