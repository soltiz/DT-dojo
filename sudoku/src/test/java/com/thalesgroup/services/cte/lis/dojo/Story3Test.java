package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class Story3Test extends SudokuTest{
		
		@Test
		  public void testLastCellOfRow() {
			
			// When in a square, only on 1 cell is not solved, then this cell solution is the last digit
			// not used in the column
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,x,4,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,2,  x,x,x  },
					
					{  x,x,x,  9,1,5,  x,x,x  },
					{  x,x,x,  6,8,3,  x,x,x  },
					{  x,x,x,  x,4,7,  x,x,x  },
					
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,9,  x,x,x  },
					{  x,x,x,  x,x,6,  x,x,x  }
					
			});
			
			result.cell(6, 4).shallContain(2);
			
		  }


	}
