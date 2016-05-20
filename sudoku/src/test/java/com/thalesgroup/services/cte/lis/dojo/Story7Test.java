package com.thalesgroup.services.cte.lis.dojo;

import org.testng.annotations.Test;

public class Story7Test extends SudokuTest {

	@Test
	public void testValueOneSubregionOnly() {

		// When in a SqRow(resp SqCol), for a specific value, two rows are impossible because of independant SqCols,
		// then it is in the third row of the thirsdsqCol

		SolvedProblem result = new SolvedProblem(new Integer[][] {

				{ x, x, x,    1, 3, x,    x, x, x }, 
				{ x, 5, 8,    4, 7, x,    x, x, x }, 
				{ x, 6, 9,    x, x, x,    x, x, x },

				{ x, x, x,    x, x, x,    x, x, x },
				{ x, x, x,    x, x, x,    x, x, x },
				{ x, x, x,    x, x, x,    x, x, 2 },
												      
				{ x, x, x,    x, x, x,    2, x, x }, 
				{ 2, x, x,    x, x, x,    x, x, x }, 
				{ x, x, x,    x, x, 2,    x, x, x }

		});
				
		result.cell(2, 8).shallContain(2);

	}

}
