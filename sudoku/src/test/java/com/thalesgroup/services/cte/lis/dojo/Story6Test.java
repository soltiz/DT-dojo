package com.thalesgroup.services.cte.lis.dojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

public class Story6Test extends SudokuTest {

	@Test
	public void testGroupsOfNWithNPossibleValues() {

		// When in a 9-group, N cells (N<8) have only the same N possible values
		// (and no other digits)
		// Then these N values are not possible for other cells of the 9-group

		SolvedProblem result = new SolvedProblem(new Integer[][] {

				{ x, x, x, x, x, 4, x, x, x }, { x, x, x, x, x, 1, x, x, 6 }, { x, x, 3, x, x, 2, x, x, x },

				{ x, x, x, 9, 1, x, x, 8, x }, // <-- here, column 3 and 6 have
												// only 5,6 and 7
				{ 4, x, x, x, x, 3, x, x, x }, // And column 2 of same line has
												// only 7, 6, 5 and 3
				{ x, x, 2, x, 4, 8, x, x, x }, // ===> Therefore 3 is the only
												// choice remaining there

				{ x, x, 8, x, x, x, x, x, 3 }, { x, x, x, x, x, 9, x, x, x }, { x, x, x, x, x, x, x, x, x }

		});

		//result.cell(4, 2).shallContain(3);

	}

	@Test
		void testThis() {
			
			List<Integer> source=new ArrayList<Integer>();
			source.add(3);
			source.add(7);
			source.add(11);
			source.add(17);
			
			
			// We will work on indexes, and translate at the end
			
			// First step : generate combination of size 1
			List<List<Integer>> result=new ArrayList<List<Integer>>();
			for (Integer i=0; i<4; i++) {
				List<Integer> combination=new ArrayList<Integer>();
				combination.add(i);
				result.add(combination);
			}
			// Then at each step, generate combination of size +1, by choosing elements at the right of the last chosen one.
			for (int sz=0; sz<2; sz++) {
				List<List<Integer>> previousStep = result;
				result = new ArrayList<List<Integer>>();
				for (List<Integer> previousList:previousStep) {
					Integer last = previousList.get(previousList.size()-1);
					for (int j=last+1;j<4;j++) {
						ArrayList<Integer> newList = new ArrayList<Integer>(previousList);
						newList.add(j);
						result.add(newList);
					}

				}
			}
			
			
			for (List<Integer> list:result) {
				 for (Integer i:list) {
					 System.out.print(String.valueOf(source.get(i))+"  " );
				 }
				System.out.println();
			}
		}

}
