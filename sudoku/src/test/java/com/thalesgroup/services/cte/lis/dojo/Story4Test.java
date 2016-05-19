package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class Story4Test extends SudokuTest{
		
		@Test
		  public void testLastPossibleValueOfCell() {
			
			// When a cell belongs to a row, column and square, that already use all the digits but one,
			// then the last possible value is set in the cell.
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,x,4,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,2,  x,x,x  },
					
					{  7,x,x,  9,1,x,  x,8,x  },
					{  x,x,x,  6,x,3,  x,x,x  },
					{  x,x,x,  x,4,x,  x,x,x  },
					
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,9,  x,x,x  },
					{  x,x,x,  x,x,6,  x,x,x  }
					
			});
			
			result.cell(4, 6).shallContain(5);
			
		  }


	}
