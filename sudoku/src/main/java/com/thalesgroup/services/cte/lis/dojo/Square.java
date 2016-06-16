package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashSet;
import java.util.Set;

public class Square extends RealNineCells {

	private int sqrow;
	private int sqcol;
	private Set<NineCells> columns;
	private Set<NineCells> rows;
	
	 public Square(Set<Cell> cells, Set<NineCells> rows, Set<NineCells>columns , int sqrow, int sqcol) {
		super(cells,String.format("Square (sqrow=%d, sqcol=%d)",sqrow,sqcol));
		this.sqrow=sqrow;
		this.sqcol=sqcol;
		this.rows=rows;
		this.columns=columns;
	}

	public void InterdictedSubregions() {
//		Set<NineCells> regionsSet=new HashSet<NineCells>(columns);
//		for (NineCells region:)
//		// TODO Auto-generated method stub
//		
	}
}

