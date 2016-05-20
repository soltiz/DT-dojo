package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class RealGridsTest extends SudokuTest{
		
		@Test
		  public void RealGrid1() {
			
				//intermediate level, from http://www.le-sudoku.fr/sudoku-en-ligne
			
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,x,8,  x,x,x  },
					{  x,x,5,  9,x,6,  x,2,x  },
					{  2,x,1,  x,4,x,  x,x,x  },
					
					{  5,x,x,  2,x,7,  x,3,x  },  
					{  8,x,x,  x,5,x,  x,x,6  },       
					{  x,7,x,  6,x,3,  x,x,2  }, 
					
					{  x,x,x,  x,6,x,  9,x,3  },
					{  x,9,x,  3,x,1,  6,x,x  },
					{  x,x,x,  5,x,x,  x,x,x  }
					
			});
			
			result.shallBeSolved();
			
		  }

		
		@Test
		  public void RealGrid2() {
				
			
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  3,x,x,  x,x,x,  x,x,1  },
					{  x,x,x,  x,x,x,  9,4,x  },
					{  4,x,x,  9,x,3,  x,7,x  },
					
					{  x,4,2,  x,6,x,  x,x,7  },  
					{  6,x,x,  x,9,x,  x,1,x  },       
					{  x,8,1,  x,3,x,  x,x,5  }, 
					
					{  1,x,x,  4,x,7,  x,3,x  },
					{  x,x,x,  x,x,x,  7,5,x  },
					{  5,x,x,  x,x,x,  x,x,8  }
					
			});
			
			result.shallBeSolved();
			
		  }
		  
		
		@Test
		  public void RealGrid3() {
				
				//difficult level, from http://www.le-sudoku.fr/sudoku-en-ligne
			
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,5,x,  1,2,x  },
					{  5,x,x,  x,x,x,  x,7,8  },
					{  x,7,8,  x,x,x,  5,x,9  },
					
					{  1,x,x,  x,3,x,  x,x,x },  
					{  x,x,x,  2,x,9,  x,x,3  },       
					{  x,x,5,  x,8,x,  x,x,x  }, 
					
					{  7,3,x,  5,x,x,  6,x,x  },
					{  x,x,4,  x,x,x,  2,x,x  },
					{  6,x,1,  x,x,2,  x,3,x  }
					
			});
			result.tryRandom();
			result.shallBeSolved();
			
		  }
		  
		@Test
		  public void EmptyGrid() {
				
				//difficult level, from http://www.le-sudoku.fr/sudoku-en-ligne
			
			
			SolvedProblem result = new SolvedProblem(new Integer[][]{
					
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					
					{  x,x,x,  x,x,x,  x,x,x  },  
					{  x,x,x,  x,x,x,  x,x,x  },       
					{  x,x,x,  x,x,x,  x,x,x  }, 
					
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  },
					{  x,x,x,  x,x,x,  x,x,x  }
					
			});
			
//			result.shallBeSolved();
			
		  }
		  
		  
	}
