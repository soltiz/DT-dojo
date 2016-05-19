package com.thalesgroup.services.cte.lis.dojo;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class RealNineCells extends NineCells{

	private Set<Cell> cells;
	public RealNineCells(Set<Cell> cells) {
		if (cells==null) throw new NullPointerException();
		if (cells.size()!=9) throw new InvalidParameterException();
		this.cells=new HashSet<Cell> (cells);
		for(Cell c : cells) {
			c.belongsTo(this);
		}
	}
	public Set<Cell> getCells() {
		return cells;
	}
}
