package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grid {
	Map<Integer,Line> lines;
	Map <Integer,NineCells> columns;
	Set <NineCells> squares;
	Set <NineCells> allNineCells;
	
	
	public Grid(Integer[][] values) {
		lines=new HashMap<Integer,Line>();
		columns=new HashMap<Integer,NineCells>();
		squares=new HashSet<NineCells>();
		
		for (int l=0;l<9;l++) {
			Line line=new Line(values[l]);
			lines.put(l,line);
		}
		for (int c=0;c<9;c++) {
			Set<Cell> colCells=new HashSet<Cell>();
			for (int l=0; l<9; l++) {
				colCells.add(lines.get(l).getCell(c));
			}
			columns.put(c, new RealNineCells(colCells)); 
		}
		for (int sqrow=0;sqrow<3;sqrow++) { 
			for (int sqcol=0;sqcol<3;sqcol++) {
				Set<Cell> squareCells=new HashSet<Cell>();
				for (int row=0;row<3;row++) {
					for (int col=0;col<3;col++) {
						squareCells.add(lines.get(row+3*sqrow).getCell(col+3*sqcol));
					}
				}
				squares.add(new RealNineCells(squareCells));
			}
		}
		allNineCells=new HashSet<NineCells>();
		allNineCells.addAll(lines.values());
		allNineCells.addAll(columns.values());
		allNineCells.addAll(squares);
		
	}
	public Integer[][] getAsTable() {
		Integer[][] grid=new Integer[9][9];
		for (int l=0;l<9;l++) {
			Line line=lines.get(l);
			for (int c=0;c<9;c++) {
				grid[l]=line.getAsTable();
			}
		}
		return grid;
		
	}
	public Cell getCell(int row, int column) {
		return lines.get(row).getCell(column);
	}

	public void lastCellCompletion() {	
		for (NineCells nineCells:allNineCells) {
			nineCells.lastCellCompletion();
		}
		
	}
	public void lastValueCompletion() {
		for (Line l:lines.values()) {
			for (Cell c:l.getCells()) {
				c.lastValueCompletion();
			}
		}
		// TODO Auto-generated method stub
		
	}
}
