package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class Story1Test extends SudokuTest{
		
		@Test
		  public void testLastCellOfRow() {
			
			// When in a row, only on 1 cell is not solved, then this cell solution is the last digit
			// not used in the row
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  3,4,5,  1,9,8,  6,x,2  },
					
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  }
					
			});
			
			result.cell(3, 8).shallContain(7);
		  }


	}
