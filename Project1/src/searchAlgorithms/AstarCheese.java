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
			checkLandedOnCheese(expand);
			
			if (!cheesesLeft(expand)) { // change method to find the goal 
				for (Index p = expand; p != null; p = p.prev) {
					solutionPath.add(new CheeseIndex(p.row, p.column, null));	
				}
				return solutionPath;
			}
			
			frontier.remove(expand);
			CheeseIndex[] adjNodes = adjList.get(expand);
			
			for (CheeseIndex i : adjNodes) {
				CheeseIndex newIndex = new CheeseIndex(i.row, i.column, null);
				expanded.add(newIndex);
				copyCheeseList(expand, newIndex);
				newIndex.prev = expand;
				frontier.add(newIndex);
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
					int distanceFromCheeseToCurrentIndex = getManhattanDistance(cheeseIndex, index) + getPathLength(index) - (index.cheeses.size() * 3);
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
		return i.cheeses.size() != this.cheeses.size();
	}
	
	private void copyCheeseList(CheeseIndex fromIndex, CheeseIndex toIndex) {
		for (CheeseIndex cheeseIndex : fromIndex.cheeses) {
			if (!toIndex.cheeses.contains(cheeseIndex)) {
				toIndex.cheeses.add(cheeseIndex);
				break;
			}
		}
	}
	
	private void checkLandedOnCheese(CheeseIndex index)	{
		for (CheeseIndex cheese : this.cheeses) {
			if (index.equals(cheese) && !index.cheeses.contains(cheese)) {
				index.cheeses.add(cheese);
				break;
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