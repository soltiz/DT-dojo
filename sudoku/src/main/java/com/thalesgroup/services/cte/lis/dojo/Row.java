package com.thalesgroup.services.cte.lis.dojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Row {
	private Set<Integer> sudokuValues; 
	
	public List<Integer> numbersInLine = new ArrayList<Integer>();

	
	public Row(){
		this.sudokuValues = new HashSet<Integer>();
		for (int i = 0; i< Sudoku.SUDOKU_SIZE; i++) {
			this.sudokuValues.add(i+1);	
		}
	}
	
	public Integer getNumberInLineAtPosition(int j) {
		return this.numbersInLine.get(j);
	}

	public void add(int i) {
		if(false == sudokuValues.contains(i)){
			throw new RuntimeException("value is impossible"); 
		}
		
		this.numbersInLine.add(i);


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

}
