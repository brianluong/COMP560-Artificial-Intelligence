package searchAlgorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Astar extends InformedSearch {

	public Astar(char[][] maze) {
		super(maze);
	}

	public List<Index> search() {
		Index starting = SearchUtils.getStartingIndex(maze);
		List<Index> frontier = new ArrayList<>();
		List<Index> solutionPath = new ArrayList<>();
		
		frontier.add(starting);
		while (frontier.size() > 0) {
			Index expand = getClosest(frontier, goal);
			frontier.remove(expand);
			expanded.add(expand);
			Index[] adjNodes = adjList.get(expand);
			
			for (Index i : adjNodes) {
				if (!expanded.contains(i)) {
					i.prev = expand;
					if (SearchUtils.isGoal(i, maze)) {
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
