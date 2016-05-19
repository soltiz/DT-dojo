package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class Story2Test extends SudokuTest{
		
		@Test
		  public void testLastCellOfRow() {
			
			// When in a column, only on 1 cell is not solved, then this cell solution is the last digit
			// not used in the row
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,x,4,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,2,  x,x,x  },
					
					{  x,x,x,  x,x,5,  x,x,x  },
					{  x,x,x,  x,x,3,  x,x,x  },
					{  x,x,x,  x,x,7,  x,x,x  },
					
					{  x,x,x,  x,x,1,  x,x,x  },
					{  x,x,x,  x,x,9,  x,x,x  },
					{  x,x,x,  x,x,6,  x,x,x  }
					
			});
			
			result.cell(2, 6).shallContain(8);
			
		  }


	}
