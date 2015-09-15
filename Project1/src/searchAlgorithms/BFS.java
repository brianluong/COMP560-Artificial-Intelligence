package searchAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFS extends Search<Index>{
	
	public BFS(char[][] maze) throws InstantiationException, IllegalAccessException {
		super(maze, Index.class);
	}
	
	public List<Index> search() throws InstantiationException, IllegalAccessException {
		Queue<Index> frontier = new LinkedList<>();
		List<Index> solutionPath = new ArrayList<>();
		
		Index starting = getStartingIndex(maze);
	
		// A state in this algorithm is defined as 1. the location (row i, column j)
		
		// Add initial state to the frontier 
		frontier.add(starting);
	
		while (frontier.size() > 0) {
			// Removes the state on the shallowest level
			Index expand = frontier.poll();
			
			expanded.add(expand);
			Index[] adjNodes = adjList.get(expand);
//			System.out.println("" + expand.row + ", " + expand.column);
			
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
}