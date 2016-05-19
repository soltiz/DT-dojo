package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Set;

public class Cell {
	private Integer value;
	private Set<NineCells> knownContainingNineCells;
	public Cell(Integer value) {
		this.value=new Integer(value);
		knownContainingNineCells=new HashSet<NineCells>();
	}
	
	/**
	 * 
	 * Notify the Cell that it also belongs to the provided NineCells group
	 * The cell will add this to its already known container NineCells 
	 * 
	 * @param containingNineCells
	 */
	public void belongsTo(NineCells containingNineCells) {
		this.knownContainingNineCells.add(containingNineCells);
	}
	
	public Integer getValue() {
		return this.value;
	}

	public int getIntValue() {
		if (value==null) {
			return 0;
		}
		return value;
	}

	public void setValue(int i) {
		this.value=i;
	}

	public boolean hasValue() {
		return ((value!=null) && (value!=0));
	}
	public static Set<Integer> getAllValues() {
		HashSet<Integer> result = new HashSet<Integer>();
		for (int i=1;i<10;i++) {
			result.add(new Integer(i));
		}
		return result;
	}
	
	public void lastValueCompletion() {
		if (hasValue()) {
			return;
		}
		Set<Integer> remainingValues = getAllValues();
		for (NineCells containingGroup:knownContainingNineCells) {
			for (Cell c:containingGroup.getCells()){
				remainingValues.remove(c.getValue());
			}
		}
		
		if (remainingValues.size()==1) {
			setValue(remainingValues.iterator().next());
		}
	}
}
