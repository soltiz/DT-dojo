package com.thalesgroup.services.cte.lis.dojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class NineCells {

	public abstract Set<Cell> getCells();

	public void lastCellCompletion() {
		HashSet<Cell> emptyCells = new HashSet<Cell>();
		Set<Integer> remainingValues = Cell.getAllValues();
		for (Cell cell : getCells()) {
			if (cell.hasValue()) {
				remainingValues.remove(cell.getValue());
			} else {
				emptyCells.add(cell);
			}
		}
		if (emptyCells.size() == 1) {
			emptyCells.iterator().next().setValue(remainingValues.iterator().next());
		}
	}

	public void twoGroupsValuesPairLock() {
		Set<Cell> remainingCellsForFirstChoice = new HashSet<Cell>(getCells());
		while (remainingCellsForFirstChoice.size() > 0) {
			Iterator<Cell> iterator = remainingCellsForFirstChoice.iterator();
			Cell firstChoice = iterator.next();
			Set<Integer> firstCellPossibilities = firstChoice.getRemainingPossibleValues();
			if (firstCellPossibilities.size() == 2) {
				while (iterator.hasNext()) {
					Cell secondChoice = iterator.next();
					Set<Integer> secondCellPossibilities = secondChoice.getRemainingPossibleValues();
					if ((secondCellPossibilities.size() == 2)
							&& (firstCellPossibilities.containsAll(secondCellPossibilities))) {
						System.out.println(
								String.format("Cells '%s' and '%s' have only two remaining posssibilities : '%s'.",
										firstChoice, secondChoice, firstChoice.remainingPossibleValuesAsString()));

						for (Cell otherCell : getCells()) {
							if ((otherCell != firstChoice) && (otherCell != secondChoice)) {
								otherCell.removeImpossibleValues(firstCellPossibilities);
							}
						}
					}
				}
			}
			remainingCellsForFirstChoice.remove(firstChoice);
		}

	}

	private Set<Cell> getUnsolvedCells() {
		HashSet<Cell> result = new HashSet<Cell>();
		for (Cell c : getCells()) {
			if (!c.hasValue()) {
				result.add(c);
			}
		}
		return result;
	}

	private Set<List<Cell>> getUnsolvedCellsCombinations(int size) {

		List<Cell> source = new ArrayList<Cell>(getUnsolvedCells());
		Set<List<Integer>> resultIndexes = new HashSet<List<Integer>>();
		Set<List<Cell>> result = new HashSet<List<Cell>>();

		if (size > source.size()) {
			return result;
		}

		if (size == source.size()) {
			result.add(source);
			return result;
		}

		// We will work on indexes, and translate at the end

		// First step : generate combination of size 1
		for (Integer i = 0; i < source.size(); i++) {
			List<Integer> combination = new ArrayList<Integer>();
			combination.add(i);
			resultIndexes.add(combination);
		}
		// Then at each step, generate combination of size +1, by choosing
		// elements at the right of the last chosen one.
		for (int sz = 0; sz < size; sz++) {
			Set<List<Integer>> previousStep = resultIndexes;
			resultIndexes = new HashSet<List<Integer>>();
			for (List<Integer> previousList : previousStep) {
				Integer last = previousList.get(previousList.size() - 1);
				for (int j = last + 1; j < source.size(); j++) {
					ArrayList<Integer> newList = new ArrayList<Integer>(previousList);
					newList.add(j);
					resultIndexes.add(newList);
				}

			}
		}
		// Finally we compute result with these indexes combination
		for (List<Integer> indexCombination : resultIndexes) {
			ArrayList<Cell> combination = new ArrayList<Cell>();
			for (Integer index : indexCombination) {
				combination.add(source.get(index));
			}
			result.add(combination);
		}
		return result;
	}
	
	public void NGroupsValuesLock() {
		for (int sz=2;sz<9;sz++) {
			Set<List<Cell>> unsolvedCombinations = getUnsolvedCellsCombinations(sz);
			for (List<Cell> cellCombination:unsolvedCombinations) {
				Iterator<Cell> cellIterator = cellCombination.iterator();
				Cell firstCell=cellIterator.next();
				Set<Integer> firtCellRemainingPossibilities = firstCell.getRemainingPossibleValues();
				if (firtCellRemainingPossibilities.size()==sz) {
					boolean everythingMatches = true;
					while((everythingMatches) && (cellIterator.hasNext())) {
						Cell otherCell=cellIterator.next();
						Set<Integer> otherCellPossibilities = otherCell.getRemainingPossibleValues();
						if ((otherCellPossibilities.size()!=sz) || (! otherCellPossibilities.containsAll(firtCellRemainingPossibilities))) {
							everythingMatches=false;
						}
					}
					if (everythingMatches) {
							
						for (Cell cell : getUnsolvedCells()) {
							if (!cellCombination.contains(cell)) {
								cell.removeImpossibleValues(firtCellRemainingPossibilities);
							}
						}
					}
				}
			}
			
		}

	}
	
}
