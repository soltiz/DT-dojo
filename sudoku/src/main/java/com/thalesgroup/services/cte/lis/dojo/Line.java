package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Line extends NineCells {
	Map<Integer,Cell> cells;
	public Line(Integer[] values) {
		cells=new HashMap<Integer,Cell>();
		for (int c=0;c<9;c++) {
			Cell cell=new Cell(values[c]);
			cells.put(c,cell);
			cell.belongsTo(this);
		}
	}
	public Integer[] getAsTable() {
		Integer[] line=new Integer[9];
		for (int c=0;c<9;c++) {
			Cell v=cells.get(c);
			
			line[c]=v.getIntValue();
		}
		return line;
	}
	public Cell getCell(int column) {
		return cells.get(column);
	}
	public Set<Cell> getCells() {
		return new HashSet<Cell>(cells.values());
	}
}
