package com.thalesgroup.services.cte.lis.dojo;

import org.testng.annotations.Test;

public class Story7Test extends SudokuTest {

	@Test
	public void testValueOneSubregionOnly() {

		// When in a SqRow(resp SqCol), for a specific value, two rows are impossible because of independant SqCols,
		// then it is in the third row of the thirsdsqCol

		SolvedProblem result = new SolvedProblem(new Integer[][] {

				{ x, x, x,    x, x, x,    x, x, x }, 
				{ x, 5, 8,    1, 3, 8,    x, x, x }, 
				{ x, 6, 9,    5, x, 7,    x, x, x },

				{ x, x, x,    x, x, x,    x, x, x },
				{ x, x, x,    x, x, x,    x, x, x },
				{ x, x, x,    x, x, x,    x, x, x },
												      
				{ x, x, x,    x, x, x,    x, x, x }, 
				{ 2, x, x,    x, x, x,    x, x, x }, 
				{ x, x, x,    x, x, x,    x, x, x }

		});
				
		result.cell(3, 5).shallContain(2);

	}

}
