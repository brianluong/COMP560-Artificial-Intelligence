package searchAlgorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Search{

	protected char[][] maze;
	protected Map<Index, Index[]> adjList;
	protected Set<Index> expanded;
	
	public Search(char[][] maze) {
		this.maze = maze;
		adjList = SearchUtils.generateAdjacencyList(maze);
		expanded = new HashSet<Index>();
	}
	
	// g(n), cost to reach this index
	public int getPathLength(Index index) {
		int length = 0;
		for (Index p = index; p != null; p = p.prev) {
			length++;
		}
		return length;
	}
	
	public Set<Index> getExpandedSet() {
		return expanded;
	}
	
	public abstract List<Index> search();
}