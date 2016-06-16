package com.thalesgroup.services.cte.lis.dojo;

import org.testng.annotations.Test;

public class Story7Test extends SudokuTest {

	@Test
	public void testValueOneSubregionOnly() {

		// When in a line, for a specific value, two SqCols are imossible, then
		// in the last SqColn the value is in the line (and not in the other two lines)
		

		SolvedProblem result = new SolvedProblem(new Integer[][] {

				{ x, x, x,    x, x, x,    x, x, x }, // there cannot be a 6 in column 8 of this line
				{ x, x, x,    x, x, x,    x, 9, x },  // therefore, it is an 8
				{ 1, 2, 3,    7, 5, 9,    x, x, x }, // <-- and the 6 is here in column 8

				{ x, x, x,    x, x, x,    x, 1, x },
				{ x, x, x,    x, x, x,    x, 2, x },
				{ x, x, x,    x, x, x,    x, 3, x },
												      
				{ x, x, x,    x, x, x,    x, 4, x }, 
				{ 2, x, x,    x, x, x,    x, 5, x }, 
				{ x, x, x,    x, x, x,    x, 7, x }

		});
				
		result.cell(3, 8).shallContain(6);
		result.cell(1, 8).shallContain(8);

	}

}
