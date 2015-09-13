package searchAlgorithms;

import java.util.List;

public abstract class InformedSearch<T extends Index> extends Search<T> {

	protected T goal;
	
	public InformedSearch(char[][] maze, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		super(maze, clazz);
		goal = getGoalIndex(maze);
	}
	
	// function to find next node to expand, f(n)
	public abstract T getClosest(List<T> frontier, T goal);
	
	// heuristic function h(n)
	public int getManhattanDistance(T current, T goal) {
		return Math.abs(goal.column - current.column) + Math.abs(goal.row - current.row);
	}
}