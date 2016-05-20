package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Line extends NineCells {
	Map<Integer,Cell> cells;
	private int row;
	public Line(int row, Integer[] values) {
		this.name="Line "+String.valueOf(row+1);
		this.row=row;
		cells=new HashMap<Integer,Cell>();
		for (int c=0;c<9;c++) {
			Cell cell=new Cell(row,c,values[c]);
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
	public void display() {
		System.out.println("+---+---+---+  +---+---+---+  +---+---+---+");
		for (Integer c=0;c<9; c++) {
			Cell cell=cells.get(c);
			String cellValue=" ";
			if (cell.hasValue()) {
				cellValue=String.valueOf(cell.getIntValue());
			}
			System.out.print(String.format("| %s ",cellValue));
			if ((c%3)==2) {
				System.out.print("|  ");
			}
		}
		System.out.println();
		if ((row%3)==2) {
			System.out.println("+---+---+---+  +---+---+---+  +---+---+---+");
		}
	}
}
