package searchAlgorithms;

import java.util.List;

public abstract class InformedSearch extends Search{

	protected Index goal;
	
	public InformedSearch(char[][] maze) {
		super(maze);
		goal = SearchUtils.getGoalIndex(maze);
	}
	
	// function to find next node to expand, f(n)
	public abstract Index getClosest(List<Index> frontier, Index goal);
	
	// heuristic function h(n)
	public int getManhattanDistance(Index current, Index goal) {
		return Math.abs(goal.column - current.column) + Math.abs(goal.row - current.column);
	}

	// function to find path lengths g(n)
	public int getPathLength(Index index) {
		int length = 0;
		for (Index p = index; p != null; p = p.prev) {
			length++;
		}
		return length;
	}
}