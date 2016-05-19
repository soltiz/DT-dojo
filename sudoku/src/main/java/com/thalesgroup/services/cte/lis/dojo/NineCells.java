package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Set;

public abstract class NineCells {
	
	public abstract Set<Cell> getCells();
	
	public void lastCellCompletion() {
		HashSet<Cell> emptyCells = new HashSet<Cell>();
		Set<Integer> remainingValues = getAllValues();
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
	private Set<Integer> getAllValues() {
		HashSet<Integer> result = new HashSet<Integer>();
		for (int i=1;i<10;i++) {
			result.add(new Integer(i));
		}
		return result;
	}

}
