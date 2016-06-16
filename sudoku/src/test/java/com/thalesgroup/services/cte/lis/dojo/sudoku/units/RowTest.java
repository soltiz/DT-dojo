package com.thalesgroup.services.cte.lis.dojo.sudoku.units;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.thalesgroup.services.cte.lis.dojo.Row;
import com.thalesgroup.services.cte.lis.dojo.Sudoku;

public class RowTest {

	@Test
	public void testValeurManquante() {
		Row row = new Row();
		int result = 0;
		// given
		for(int i = 0; i < Sudoku.SUDOKU_SIZE - 1; i ++){
			row.setCellIfEmpty(i, i+1);
		}
		//when
		result = row.searchMissingValue();
		
		//then
		Assert.assertEquals(result, Sudoku.SUDOKU_SIZE);
	}
	
	
	@Test
	public void findEmptyCase(){
		Row row = new Row();
		// given
		for(int i = 0; i < Sudoku.SUDOKU_SIZE - 1; i ++){
			row.setCellIfEmpty(i, i+1);
		}
		//when
		int indexCaseEmpty = row.findEmptyCase();
		
		//then
		Assert.assertEquals(indexCaseEmpty, Sudoku.SUDOKU_SIZE - 1);
	}
	
	@Test
	public void testSetCellIfEmpty(){
		Row row = new Row();
		//when
		row.setCellIfEmpty(1,6);
		
		//then
		Assert.assertEquals(row.getNumberInLineAtPosition(1), new Integer(6));
	}
	
	@Test(expectedExceptions=RuntimeException.class)
	public void testSetCellIfNotEmpty(){
		Row row = new Row();
		//when
		row.setCellIfEmpty(1,6);
		row.setCellIfEmpty(1,7);
		
		//then
		
	}
}
