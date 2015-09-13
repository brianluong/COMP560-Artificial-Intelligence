package searchAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class AstarCheese extends InformedSearch{

	List<CheeseIndex> cheeses;
	public AstarCheese(char[][] maze) {
		super(maze);
		goal = null; // goal is to eat all cheeses
		cheeses = SearchUtils.getCheeseFromMaze(maze);
	}

	public List<Index> search() {
		CheeseIndex starting = SearchUtils.getStartingCheeseIndex(maze);
		List<CheeseIndex> frontier = new ArrayList<>();
		List<Index> solutionPath = new ArrayList<>();
		
		frontier.add(starting);
		while (frontier.size() > 0) {
			CheeseIndex expand = getClosestCheese(frontier);
			frontier.remove(expand);
			expanded.add(expand);
			Index[] adjNodes = adjList.get(expand);
			
			for (Index i : adjNodes) {
				if (expanded.contains(i)) {
					i = new CheeseIndex(i.row, i.column, null);
					expanded.add(i);
				} else {
					checkLandedOnCheese((CheeseIndex) i);
				}
				
				copyCheeseList(expand, (CheeseIndex) i);
				
				i.prev = expand;
				if (!cheesesLeft((CheeseIndex) i)) { // change method to find the goal 
					for (Index p = i; p != null; p = p.prev) {
						solutionPath.add(new Index(p.row, p.column, null));	
					}
					return solutionPath;
				} else {
					frontier.add((CheeseIndex) i);
				}	
			}
		}
		return solutionPath;
	}

	// f(n) = g(n) + h(n)
	
	public CheeseIndex getClosestCheese(List<CheeseIndex> frontier) {
		CheeseIndex minimumIndex = null;
		int minimumDistance = Integer.MAX_VALUE;
		// going through the frontier nodes
		for (CheeseIndex index : frontier) {
			
			// going through the Global list of cheeses to find the list of cheeses NOT eaten yet
			for (CheeseIndex cheeseIndex : this.cheeses) {
				if (!(index.cheeses.contains(cheeseIndex))) {
					int distanceFromCheeseToCurrentIndex = getManhattanDistance(cheeseIndex, index) + getPathLength(index);
					if (distanceFromCheeseToCurrentIndex < minimumDistance) {
						minimumIndex = index;
					}
				}
			}
		}
		return minimumIndex;
	}
	
	private boolean cheesesLeft(CheeseIndex i) {
		for (CheeseIndex cheese: this.cheeses) {
			boolean match = false;
			for (CheeseIndex cheese2 : i.cheeses) {
				if (cheese.equals(cheese2)) {
					match = true;
					break;
				}
			}
			if (match) {
				return false;
			}
		}
		return true;
	}
	
	private void copyCheeseList(CheeseIndex fromIndex, CheeseIndex toIndex) {
		toIndex.cheeses = fromIndex.cheeses;
	}
	
	private void checkLandedOnCheese(CheeseIndex index)	{
		for (CheeseIndex cheese : this.cheeses) {
			if (index.equals(cheese)) {
				index.cheeses.add(cheese);
			}
		}
	}

	@Override
	public Index getClosest(List<Index> frontier, Index goal) {
		// TODO Auto-generated method stub
		return null;
	}
}