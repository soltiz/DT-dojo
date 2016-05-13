package com.thalesgroup.services.cte.lis.dojo;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class SudokuTest {
	public static final Integer x=0;

	private Integer[][] test(Integer[][] src) {
		Integer[][] result={{1,2}};
		result[0][0]=3;
		return result;
	}
	
	
	/**
	 * 	 * 
	 * @param gridA a 9x9 int[][] 
	 * @param gridA a 9x9 int[][] 
	 *  
	 */
	private void DisplayGrid(Integer[][] gridA, Integer[][] gridB) {
		System.out.println("                 BEFORE RESOLUTION                                       AFTER RESOLUTION");
		System.out.println("                 -----------------                                       ----------------");
		System.out.println();
		for (int l=0;l<9;l++) {
			System.out.println("+---+---+---+  +---+---+---+  +---+---+---+                    +---+---+---+  +---+---+---+  +---+---+---+");
			for (int c=0;c<9;c++) {
				int n=gridA[l][c];
				if (n>0) {
					System.out.print(String.format("| %d ",n)); }
				else { System.out.print("|   ");}
				if (c%3==2) { System.out.print("|  ");}
			}
			System.out.print("                  ");
			for (int c=0;c<9;c++) {
				int n=gridB[l][c];
				if (n>0) {
					System.out.print(String.format("| %d ",n)); }
				else { System.out.print("|   ");}
				if (c%3==2) { System.out.print("|  ");}
			}
			
			
			
			System.out.println();
			if (l%3==2) { System.out.println("+---+---+---+  +---+---+---+  +---+---+---+                    +---+---+---+  +---+---+---+  +---+---+---+");System.out.println();
			 }
		}
	}
	
	@Test
	  public void f() {
		Integer [][] source = {
				
				{  x,x,x,  x,x,x,  x,x,x  },
				{  x,x,x,  x,x,x,  x,x,x  },
				{  3,4,5,  1,9,8,  6,x,2  },
				
				{  x,x,x,  x,x,x,  x,x,x  },
				{  x,x,x,  x,x,x,  x,x,x  },
				{  x,x,x,  x,x,x,  x,x,x  },
				
				{  x,x,x,  x,x,x,  x,x,x  },
				{  x,x,x,  x,x,x,  x,x,x  },
				{  x,x,x,  x,x,x,  x,x,x  }
				
		};
		
		DisplayGrid(source,source);
		//result=test(source);
		//assert.result[3][9];
		
						
		assertEquals(true,false);
	  }

	
}
