package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
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


}
