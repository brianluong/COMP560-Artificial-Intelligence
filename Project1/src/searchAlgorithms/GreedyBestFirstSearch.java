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

	public List<Index> search() {
		Index starting = SearchUtils.getStartingIndex(maze);
		List<Index> frontier = new ArrayList<>();
		Index goal = SearchUtils.getGoalIndex(maze);
		List<Index> solutionPath = new ArrayList<>();
		
		frontier.add(starting);
		while (frontier.size() > 0) {
			Index expand = getClosest(frontier, goal);
			frontier.remove(expand);
			expanded.add(expand);
			Index[] adjNodes = adjList.get(expand);
			
			for (Index i : adjNodes) {
				if (!expanded.contains(i)  && !frontier.contains(i)) {
					i.prev = expand;
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
