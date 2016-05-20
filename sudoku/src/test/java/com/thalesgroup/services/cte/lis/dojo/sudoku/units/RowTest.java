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
			row.add(i+1);
		}
		//when
		result = row.searchMissingValue();
//		row.numbersInLine = []
		//then
		Assert.assertEquals(result, Sudoku.SUDOKU_SIZE);
	}
}
