package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class NineCells {
	
	public abstract Set<Cell> getCells();
	
	public void lastCellCompletion() {
		HashSet<Cell> emptyCells = new HashSet<Cell>();
		Set<Integer> remainingValues = Cell.getAllValues();
		for (Cell cell:getCells()) {
			if (cell.hasValue()) {
				remainingValues.remove(cell.getValue());
			} else {
				emptyCells.add(cell);
			}
		}
		if (emptyCells.size()==1) {
			emptyCells.iterator().next().setValue(remainingValues.iterator().next());
		}
	}
	
	public void twoGroupsValuesPairLock() {
		Set<Cell> remainingCellsForFirstChoice=new HashSet<Cell> (getCells());
		while (remainingCellsForFirstChoice.size()>0) {
			Iterator<Cell> iterator = remainingCellsForFirstChoice.iterator();
			Cell firstChoice =iterator.next();
			Set<Integer> firstCellPossibilities = firstChoice.getRemainingPossibleValues();
			if (firstCellPossibilities.size()==2) {
				while (iterator.hasNext()) {
					Cell secondChoice=iterator.next();
					Set<Integer> secondCellPossibilities = secondChoice.getRemainingPossibleValues();
					if ((secondCellPossibilities.size()==2) && (firstCellPossibilities.containsAll(secondCellPossibilities))) {
						System.out.println(String.format(
								"Cells '%s' and '%s' have only two remaining posssibilities : '%s'.",
								firstChoice,
								secondChoice,
								firstChoice.remainingPossibleValuesAsString()));
								
						
						for (Cell otherCell:getCells()) {
							if ((otherCell!=firstChoice) && (otherCell!=secondChoice)) {
								otherCell.removeImpossibleValues(firstCellPossibilities);
							}
						}
					}
				}
			}
			remainingCellsForFirstChoice.remove(firstChoice);
		}
		
	}


}
