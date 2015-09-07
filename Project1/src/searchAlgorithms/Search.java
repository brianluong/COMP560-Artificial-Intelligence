package searchAlgorithms;

import java.util.List;
import java.util.Map;

public abstract class Search{

	protected char[][] maze;
	protected Map<Index, Index[]> adjList;
	
	public Search(char[][] maze) {
		this.maze = maze;
		adjList = SearchUtils.generateAdjacencyList(maze);
	}
	
	public abstract List<Index> search();
}