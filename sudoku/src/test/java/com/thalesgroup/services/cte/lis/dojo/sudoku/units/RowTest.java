package com.thalesgroup.services.cte.lis.dojo.sudoku.units;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.thalesgroup.services.cte.lis.dojo.RowOrColumn;
import com.thalesgroup.services.cte.lis.dojo.Sudoku;

public class RowTest {

	@Test
	public void testValeurManquante() {
		RowOrColumn row = new RowOrColumn();
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
		RowOrColumn row = new RowOrColumn();
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
		RowOrColumn row = new RowOrColumn();
		//when
		row.setCellIfEmpty(1,6);
		
		//then
		Assert.assertEquals(row.getNumberInLineAtPosition(1), new Integer(6));
	}
	
	@Test(expectedExceptions=RuntimeException.class)
	public void testSetCellIfNotEmpty(){
		RowOrColumn row = new RowOrColumn();
		//when
		row.setCellIfEmpty(1,6);
		row.setCellIfEmpty(1,7);
		
		//then
		
	}
	
	@Test
	public void testArrayInversion() {
		//given
		Integer[][] input = new Integer[][]{
				{  1,2 },
				{  4,5}};
		Integer[][] expected = new Integer[][]{
				{  1,4 },
				{  2,5}};
		
		//when
		Integer[][] output = Sudoku.getInvertedArray(input);
	
	//then 
	Assert.assertEquals(output, expected);
}
}

