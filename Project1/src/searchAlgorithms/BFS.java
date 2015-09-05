package searchAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFS {
	// Breadth First Search 
	// FIFO queue
	// look in four directions. if not a % sign, then add to frontier
	// when node added to queue, check if it's goal
	
	public static List<Index> search(char[][] maze) {
		Index starting = SearchUtils.getStartingIndex(maze);
		Map<Index, Index[]> map = SearchUtils.generateAdjacencyList(maze);
		Queue<Index> frontier = new LinkedList<>();
		Set<Index> alreadyVisited = new HashSet<>();
		List<Index> solutionPath = new ArrayList<>();
		
		frontier.add(starting);
		while (frontier.size() > 0) {
			Index expand = frontier.poll();
			alreadyVisited.add(expand);
			Index[] adjNodes = map.get(expand);
			
			for (Index i : adjNodes) {
				if (!alreadyVisited.contains(i)) {
					i.prev = expand;
					if (SearchUtils.isGoal(i, maze)) {
						for (Index p = i; p != null; p = p.prev) {
							solutionPath.add(new Index(p.row, p.column, null));	
						}
					} else {
						frontier.add(i);
					}	
				}
			}
		}
		return solutionPath;
	}
}