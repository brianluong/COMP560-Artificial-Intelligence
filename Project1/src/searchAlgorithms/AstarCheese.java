package searchAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class AstarCheese extends InformedSearch<CheeseIndex>{

	List<CheeseIndex> cheeses;
	public AstarCheese(char[][] maze) throws InstantiationException, IllegalAccessException {
		super(maze, CheeseIndex.class);
		goal = null; // goal is to eat all cheeses
		cheeses = getCheeseFromMaze(maze);
	}

	public List<CheeseIndex> search() throws InstantiationException, IllegalAccessException {
		CheeseIndex starting = getStartingIndex(maze);
		List<CheeseIndex> frontier = new ArrayList<>();
		List<CheeseIndex> solutionPath = new ArrayList<>();
		
		frontier.add(starting);
		while (frontier.size() > 0) {
			CheeseIndex expand = getClosest(frontier, goal);
			frontier.remove(expand);
			expanded.add(expand);
			CheeseIndex[] adjNodes = adjList.get(expand);
			
			for (CheeseIndex i : adjNodes) {
				if (expanded.contains(i)) {
					i = new CheeseIndex(i.row, i.column, null);
					expanded.add(i);
				} else {
					checkLandedOnCheese(i);
				}
				
				copyCheeseList(expand, i);
				
				i.prev = expand;
				if (!cheesesLeft(i)) { // change method to find the goal 
					for (Index p = i; p != null; p = p.prev) {
						solutionPath.add(new CheeseIndex(p.row, p.column, null));	
					}
					return solutionPath;
				} else {
					frontier.add(i);
				}	
			}
		}
		return solutionPath;
	}

	// f(n) = g(n) + h(n)
	public CheeseIndex getClosest(List<CheeseIndex> frontier, CheeseIndex goal) {
		CheeseIndex minimumIndex = null;
		int minimumDistance = Integer.MAX_VALUE;
		// going through the frontier nodes
		for (CheeseIndex index : frontier) {
			
			// going through the Global list of cheeses to find the list of cheeses NOT eaten yet
			for (CheeseIndex cheeseIndex : this.cheeses) {
				if (!(index.cheeses.contains(cheeseIndex))) {
					int distanceFromCheeseToCurrentIndex = getManhattanDistance(cheeseIndex, index) + getPathLength(index);
					if (distanceFromCheeseToCurrentIndex < minimumDistance) {
						minimumDistance = distanceFromCheeseToCurrentIndex;
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
	
	private List<CheeseIndex> getCheeseFromMaze(char[][] maze) {
		List<CheeseIndex> cheeses = new ArrayList<>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == '.') {
					cheeses.add(new CheeseIndex(i, j, null));
				}
			}
		}
		return cheeses;
	}
}