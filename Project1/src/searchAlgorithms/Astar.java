package searchAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Astar extends InformedSearch<Index> {

	public Astar(char[][] maze) throws InstantiationException, IllegalAccessException {
		super(maze, Index.class);
	}

	public List<Index> search() throws InstantiationException, IllegalAccessException {
		Index starting = getStartingIndex(maze);
		List<Index> frontier = new ArrayList<>();
		List<Index> solutionPath = new ArrayList<>();
		
		// A state in this algorithm is defined as 1. the location (row i, column j)

		// Add initial state to the frontier 
		frontier.add(starting);
		
		while (frontier.size() > 0) {
			
			// Removes the state that is has the lowest value of the evaluation function f(n)
			Index expand = getClosest(frontier, goal);
			//System.out.println("Expanded is " + expand.row  + " " + expand.column);
			
			frontier.remove(expand);
			expanded.add(expand);
			Index[] adjNodes = adjList.get(expand);
			
			// Add all possible successor states to the frontier 
			// that are not currently on frontier or already visited
			for (Index i : adjNodes) {
				if (!expanded.contains(i) && !frontier.contains(i)) { 
					i.prev = expand;
					
					// Check if reached goal state. If not, add on the frontier
					if (isGoal(i, maze)) {
						for (Index p = i; p != null; p = p.prev) {
							solutionPath.add(new Index(p.row, p.column, null));	
						}
						return solutionPath;
					} else {
						frontier.add(i);
					}	
				}
			}
		}
		return solutionPath;
	}
	
	// f(n) = g(n) + h(n)
	public Index getClosest(List<Index> frontier, Index goal) {
		int cheapest = Integer.MAX_VALUE;
		Index closestIndex = null;
		for (Index i : frontier) {
			int currentCheapest = getManhattanDistance(i, goal) + getPathLength(i);
			if (currentCheapest < cheapest) {
				cheapest = currentCheapest;
				closestIndex = i;
			}
		}
		return closestIndex;
	}
}