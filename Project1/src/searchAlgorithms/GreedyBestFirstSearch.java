package searchAlgorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GreedyBestFirstSearch extends InformedSearch<Index> {

	public GreedyBestFirstSearch(char[][] maze) throws InstantiationException, IllegalAccessException {
		super(maze, Index.class);
	}

	public List<Index> search() throws InstantiationException, IllegalAccessException {
		Index starting = getStartingIndex(maze);
		List<Index> frontier = new ArrayList<>();
		Index goal = getGoalIndex(maze);
		List<Index> solutionPath = new ArrayList<>();

		// A state in this algorithm is defined as 1. the location (row i, column j)
		
		// Add initial state to the frontier 
		frontier.add(starting);
		
		while (frontier.size() > 0) {
			// Retrieve the state that is closest to goal
			Index expand = getClosest(frontier, goal);
			
			frontier.remove(expand);
			expanded.add(expand);
			Index[] adjNodes = adjList.get(expand);
			
			// Add all possible successor states to the frontier 
			// that are not currently on frontier or already visited
			for (Index i : adjNodes) {
				if (!expanded.contains(i)  && !frontier.contains(i)) {
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
	
	// f(n), function to find next node to expand
	// f(n) = h(n)
	public Index getClosest(List<Index> frontier, Index goal) {
		int lowestManhattanDistance = Integer.MAX_VALUE;
		Index closestIndex = null;
		for (Index i : frontier) {
			int currentManhattanDistance = getManhattanDistance(i, goal);
			if (currentManhattanDistance < lowestManhattanDistance) {
				lowestManhattanDistance = currentManhattanDistance;
				closestIndex = i;
			}
		}
		return closestIndex;
	}
}
