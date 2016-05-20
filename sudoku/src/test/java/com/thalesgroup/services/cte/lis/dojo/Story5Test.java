package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class Story5Test extends SudokuTest{
		
		@Test
		  public void testGroupsOfTwoWithTwoPossibleValues() {
			
			// When in a 9-group, two cells have only the same 2 possible values (and no other digits)
			// Then these 2 values are not possible for other cells of the 9-group
			// therefore if an other cell of the 9-group had only these 2 possible values plus a third one
			// then the third is the only possible choic.
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,x,4,  x,x,x  },
					{  x,4,x,  x,x,1,  x,x,6  },
					{  x,x,3,  x,x,2,  x,x,x  },
					
					{  x,x,x,  9,1,x,  x,8,x  }, // <-- here, column 3 and 6 have only 7 and 5 
					{  x,6,x,  x,x,3,  x,x,x  }, //    And column 2 of same line has only 7, 5 and 3      
					{  x,x,2,  x,4,8,  x,x,x  }, //        ===> Therefore 3 is the only choice remaining there
					
					{  x,x,8,  x,x,x,  x,x,3  },
					{  x,x,4,  x,x,9,  x,x,x  },
					{  x,x,x,  x,x,6,  x,x,x  }
					
			});
			
			result.cell(4, 2).shallContain(3);
			
		  }


	}
