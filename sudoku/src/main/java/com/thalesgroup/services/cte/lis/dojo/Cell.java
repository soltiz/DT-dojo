package com.thalesgroup.services.cte.lis.dojo;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Cell {
	private Integer value;
	private Set<NineCells> knownContainingNineCells;
	private Set<Integer> remainingPossibleValues;
	private int row;
	private int col;
	public Cell(int row, int col, Integer value) {
		this.row=row;
		this.col=col;
		this.value=new Integer(value);
		knownContainingNineCells=new HashSet<NineCells>();
		remainingPossibleValues=new HashSet<Integer>();
		if (hasValue()) {
			remainingPossibleValues.add(value);
		} else {
			remainingPossibleValues=getAllValues();
		}
	}
	
	public void removeImpossibleValue(Integer impossibleValue) {
		if (remainingPossibleValues.remove(impossibleValue)) {
			//System.out.println(String.format("Removed '%d' as impossible for cell '%s'. Remaining possible values : '%s'.",impossibleValue,this,remainingPossibleValuesAsString()));
	
			if (remainingPossibleValues.size()==1) {
				System.out.println("Only one possible value remaining for cell "+this);
				setValue(remainingPossibleValues.iterator().next());
			} else if (remainingPossibleValues.size()==0) {
				throw new InvalidParameterException(String.format("No more possible value for cell '%s' while removing last value '%d')  !!!",this,impossibleValue));
			}
		}
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
		if (hasValue()) {
			if (value!=i) {
				String error=String.format("Tried to set value '%d' for cell '%s' but it already has value '%d'.",i,this,value);
				throw new InvalidParameterException(error);
			}
		} else {
			if (! remainingPossibleValues.contains(i)) {
				String error=String.format("Tried to set value '%d' for cell '%s' but remaining possible values are only : %s",i,this,remainingPossibleValuesAsString());
				throw new InvalidParameterException(error);
			}
			for (NineCells group:knownContainingNineCells) {
				for (Cell c:group.getCells()) {
					if ((c!=this) && (c.getValue()==i)) {
						String error=String.format("Tried to set value '%d' for cell '%s' but cell '%s' already has it !",i,this,c);
						throw new InvalidParameterException(error);
						
					}
				}
			}
			System.out.println(String.format("Setting value '%d' in cell '%s'.",i,this));
			this.value=i;
		}
	}
	
	public String toString() {
		return String.format("(row=%d,col=%d)",row+1,col+1);
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
	
	private void dumpContainingGroups() {
		System.out.println();
		System.out.println(String.format("Cell '%s' is contained in : ", this));
		
		for (NineCells group:knownContainingNineCells) {
			System.out.println(String.format(" - %s", group.getName()));
		}
		System.out.println();

	}
	
	public void lastValueCompletion() {
		boolean debug=false;
		//if ((row==3) && (col==1)) {
		//	dumpContainingGroups();
		//	debug=true;
		//}
		if (hasValue()) {
			return;
		}
		for (NineCells containingGroup:knownContainingNineCells) {
			if (debug) {
				containingGroup.dumpState();
			}
			for (Cell c:containingGroup.getCells()){
				if ((c.hasValue()) && (c!=this)) {
					removeImpossibleValue(c.getValue());
				}
			}
		}
	}

	public Set<Integer> getRemainingPossibleValues() {
		return new HashSet<Integer>(remainingPossibleValues);
	}

	public void removeImpossibleValues(Set<Integer> impossibleValues) {
			for (Integer i:impossibleValues) {
				removeImpossibleValue(i);
			}
	}

	public String remainingPossibleValuesAsString() {
		String result="";
		for (Iterator<Integer> iterator = getRemainingPossibleValues().iterator()
				; iterator.hasNext();) {
			result+=String.valueOf(iterator.next());
			if (iterator.hasNext()) {
				result+=",";
			}
		}
		return result;
	}

	public int getZeroBasedRow() {
		return row;
	}

	public int getZeroBasedCol() {
		return col;
	}
}
