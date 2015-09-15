package searchAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class AstarCheese extends InformedSearch<CheeseIndex>{

	public List<Index> cheeses;
	
	public AstarCheese(char[][] maze) throws InstantiationException, IllegalAccessException {
		super(maze, CheeseIndex.class);
		goal = null; // goal is to eat all cheeses
		cheeses = getCheeseFromMaze(maze);
	}

	public List<CheeseIndex> search() throws InstantiationException, IllegalAccessException {
		CheeseIndex starting = getStartingIndex(maze);
		List<CheeseIndex> frontier = new ArrayList<>();
		List<CheeseIndex> solutionPath = new ArrayList<>();

		// A state in this algorithm is defined as 1. the location (row i, column j), and 2. the exact cheeses visited
		
		// Add initial state to the frontier 
		frontier.add(starting);
		
		while (frontier.size() > 0) {
			
			// Removes the state that is has the lowest value of the evaluation function f(n)
			CheeseIndex expand = getClosest(frontier, goal);
			
			// Check if expanded state reached a cheese
			checkLandedOnCheese(expand);
			
			// Check if current state has eaten all cheese
			if (!cheesesLeft(expand)) {
				for (Index p = expand; p != null; p = p.prev) {
					solutionPath.add(new CheeseIndex(p.row, p.column, null));	
				}
				return solutionPath;
			}
			
			frontier.remove(expand);
			expanded.add(expand);
			
			CheeseIndex[] adjNodes = getAdjacent(expand);
			
			for (CheeseIndex i : adjNodes) {
				// Updating these states with the cheeses already visited on that path 
				CheeseIndex newCheeseIndex = copyCheeseList(expand, i);
				
				// Add all possible successor states to the frontier 
				// that are not currently on frontier or already visited
				if (!expanded.contains(newCheeseIndex) && !frontier.contains(newCheeseIndex)) {
					newCheeseIndex.prev = expand;
					frontier.add(newCheeseIndex);
				}
			}
		}
		return solutionPath;
	}

	private CheeseIndex[] getAdjacent(CheeseIndex expand) {
		CheeseIndex tempIndex = new CheeseIndex(expand.row, expand.column, null);
		CheeseIndex[] adjNodes = adjList.get(tempIndex);
		return adjNodes;
	}

	// f(n) = g(n) + h(n)
	public CheeseIndex getClosest(List<CheeseIndex> frontier, CheeseIndex goal) {
		CheeseIndex minimumIndex = null;
		int minimumDistance = Integer.MAX_VALUE;
		// going through the frontier nodes
		for (CheeseIndex index : frontier) {
		
			// g(n) + h(n)
			int distanceFromCheeseToCurrentIndex = getAggregateManhattanDistance(index) + getPathLength(index);
			if (distanceFromCheeseToCurrentIndex < minimumDistance) {
				minimumDistance = distanceFromCheeseToCurrentIndex;
				minimumIndex = index;
			}
		}
		return minimumIndex;
	}
	
	// Sum of all Manhattan Distances from a location to unvisited cheese
	private int getAggregateManhattanDistance(CheeseIndex origin) {
		int sum = 0;
		for (Index cheeseIndex : this.cheeses) {
			if (!(origin.cheeses.contains(cheeseIndex))) {
				CheeseIndex tempIndex = new CheeseIndex(cheeseIndex.row, cheeseIndex.column, null);
				sum+= Math.pow(getManhattanDistance(origin, tempIndex), 1);
			}
		}
		return sum;
	}
	
	// Check if any cheeses left
	private boolean cheesesLeft(CheeseIndex i) {
		return i.cheeses.size() != this.cheeses.size();
	}
	
	// Update a state with the cheeses that predecessor state had 
	private CheeseIndex copyCheeseList(CheeseIndex fromIndex, CheeseIndex toIndex) {
		CheeseIndex tempIndex = new CheeseIndex(toIndex.row, toIndex.column, null);
		for (Index cheeseIndex : fromIndex.cheeses) {
			if (!tempIndex.cheeses.contains(cheeseIndex)) {
				tempIndex.cheeses.add(cheeseIndex);
			}
		}
		return tempIndex;
	}
	
	// Check if current state arrived at a cheese
	private void checkLandedOnCheese(CheeseIndex index)	{
		Index tempIndex = new Index(index.row, index.column, null);
		for (Index cheese : this.cheeses) {
			if (tempIndex.equals(cheese) && !index.cheeses.contains(cheese)) {
				index.cheeses.add(cheese);
				break;
			}
		}
	}
	
	// Extract cheese from maze
	private List<Index> getCheeseFromMaze(char[][] maze) {
		List<Index> cheeses = new ArrayList<>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == '.') {
					cheeses.add(new Index(i, j, null));
				}
			}
		}
		return cheeses;
	}
}