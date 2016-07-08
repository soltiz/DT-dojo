package com.thalesgroup.services.cte.lis.dojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Grid {
	Map<Integer, Line> lines;
	Map<Integer, NineCells> columns;
	Set<Square> squares;
	Set<NineCells> allNineCells;
	NineCells[][] squareGrid;

	public Grid(Integer[][] values) {
		lines = new HashMap<Integer, Line>();
		columns = new HashMap<Integer, NineCells>();
		squares = new HashSet<Square>();
		squareGrid=new Square[3][3];

		for (int l = 0; l < 9; l++) {
			Line line = new Line(l, values[l]);
			lines.put(l, line);
		}
		for (int c = 0; c < 9; c++) {
			Set<Cell> colCells = new HashSet<Cell>();
			for (int l = 0; l < 9; l++) {
				colCells.add(lines.get(l).getCell(c));
			}
			columns.put(c, new RealNineCells(colCells,String.format("Column %d", c+1)));
		}
		//columns.get(1).dumpState();
		for (int sqrow = 0; sqrow < 3; sqrow++) {
			for (int sqcol = 0; sqcol < 3; sqcol++) {
				Set<Cell> squareCells = new HashSet<Cell>();
				Set<NineCells> squareRows=new HashSet<NineCells>();
				Set<NineCells> squareCols=new HashSet<NineCells>();
				for (int row = 0; row < 3; row++) {
					for (int col = 0; col < 3; col++) {
						squareCells.add(lines.get(row + 3 * sqrow).getCell(col + 3 * sqcol));
					}
					squareRows.add(lines.get(3*sqrow+row));
					for (int col = 0; col < 3; col++) {
						squareCols.add(columns.get(col+3*sqcol));
					}
				}
				
				Square square=new Square(squareCells,squareRows, squareCols, sqrow, sqcol);
				squares.add(square);
				squareGrid[sqrow][sqcol]=square;
			}
		}
		allNineCells = new HashSet<NineCells>();
		allNineCells.addAll(lines.values());
		allNineCells.addAll(columns.values());
		allNineCells.addAll(squares);

	}

	public Integer[][] getAsTable() {
		Integer[][] grid = new Integer[9][9];
		for (int l = 0; l < 9; l++) {
			Line line = lines.get(l);
			for (int c = 0; c < 9; c++) {
				grid[l] = line.getAsTable();
			}
		}
		return grid;

	}

	public Cell getCell(int row, int column) {
		return lines.get(row).getCell(column);
	}

	public void lastCellCompletion() {
		for (NineCells nineCells : allNineCells) {
			nineCells.lastCellCompletion();
		}

	}

	public void lastValueCompletion() {
		System.out.println("Applying last value completion...");
		for (Cell c : getCells()) {
			c.lastValueCompletion();
		}
	}

	public void twoGroupsValuesPairLock() {
		for (NineCells nineCells : allNineCells) {
			nineCells.twoGroupsValuesPairLock();
		}

	}

	private Set<Cell> getCells() {
		HashSet<Cell> cells = new HashSet<Cell>();
		for (NineCells square : squares) {
			cells.addAll(square.getCells());
		}
		return cells;
	}

	public Integer remainingNbOfValuesToEliminate() {
		Integer count = 0;
		for (Cell c : getCells()) {
			count += c.getRemainingPossibleValues().size() - 1;
		}
		return count;
	}

	public Set<Cell> getUnsovedCells() {
		HashSet<Cell> result = new HashSet<Cell>();
		for (Cell c : getCells()) {
			if (!c.hasValue()) {
				result.add(c);
			}
		}
		return result;
	}
	
	
	public int remainingCellsToSolve() {
		Integer count = 0;
		for (Cell c : getCells()) {
			if (!c.hasValue()) {
				count++;
			}
		}
		return count;
	}

	public void NGroupsValuesLock() {
		System.out.println("Applying NGroupsValuesLock...");
		for (NineCells nineCells : allNineCells) {
			nineCells.NGroupsValuesLock();
		}
	}

	public void display() {
		System.out.println();
		for (int r=0;r<9;r++) {
			Line l=lines.get(r);
			l.display();
			if ((r%3)==2) {
				System.out.println();
			}
		}
	}

	public void tryRandom() {
		for (Cell c:getUnsovedCells()) {
			for (Integer v : c.getRemainingPossibleValues()) {
				Integer[][] stateSnapshot = this.getAsTable();
				stateSnapshot[c.getZeroBasedRow()][c.getZeroBasedCol()]=v;
				try {
					Grid alternateGrid=new Grid(stateSnapshot);
					if (alternateGrid.solve()) {
						System.out.println("Solved by random trial.");
						c.setValue(v);
					} 
				}catch (Exception e) {
					c.removeImpossibleValue(v);
				}
				
			}
		}
		// TODO Auto-generated method stub
		
	}
	
	public boolean isSolved() {
		return (remainingNbOfValuesToEliminate()==0);
	}

	public boolean solve() {
		Integer remainingValuesToEliminate=remainingNbOfValuesToEliminate();
		Integer previousRemaining=10000;
		while ((remainingValuesToEliminate!=0) && (remainingValuesToEliminate<previousRemaining)) {
			previousRemaining=remainingValuesToEliminate;
		
			//grid.lastCellCompletion();
			lastValueCompletion();
			//grid.twoGroupsValuesPairLock();
			NGroupsValuesLock();
			InterdictedSubregions();
			
			remainingValuesToEliminate=remainingNbOfValuesToEliminate();
			display();
			
			getSquare(0,2).dumpState();
			
			//Cell c=getCell(0, 7);
			//System.out.println(String.format("Cell %s has remaining values : %s",c,c.remainingPossibleValuesAsString()));
			System.out.println(String.format("%d values yet to be eliminated before problem is solved.",remainingValuesToEliminate));
		}	
		return isSolved();
	}

	public static Set<Integer> allSqNums() {
		Set<Integer> possibleIndexes=new HashSet<Integer>();
		possibleIndexes.add(0);
		possibleIndexes.add(1);
		possibleIndexes.add(2);
		return possibleIndexes;
	}
	
	private void InterdictedSubregions() {
		for (Integer sqRow:allSqNums()) {
			for (Integer rowNum:allSqNums()) {
				Line line = lines.get(3*sqRow+rowNum);
				for (Integer value:Cell.getAllValues()) {
					Set<Integer> possibleSqCols = allSqNums();
					for (Integer sqCol:allSqNums()) {
						for (Integer colNum:allSqNums()) {
							if (line.getCell(3*sqCol+colNum).getRemainingPossibleValues().contains(value)) {
								possibleSqCols.remove(sqCol);
							}
						}
					}
					if (possibleSqCols.size()==1) {
						for (Integer otherLine:allSqNums()) {
							if (otherLine!=rowNum) {
								for (Integer colNum:allSqNums()) {							
									getCell(sqRow*3+otherLine, possibleSqCols.iterator().next()*3+colNum).removeImpossibleValue(value);
								}
							}
						}
					}
				}
			}
		}
	}

	private  NineCells getSquare(int i, int j) {
		return squareGrid[i][j];
	}

	
}
