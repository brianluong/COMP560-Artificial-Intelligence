package searchAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class AstarCheese extends InformedSearch<CheeseIndex>{

	List<Index> cheeses;
	
	public AstarCheese(char[][] maze) throws InstantiationException, IllegalAccessException {
		super(maze, CheeseIndex.class);
		goal = null; // goal is to eat all cheeses
		cheeses = getCheeseFromMaze(maze);
	}

	public List<CheeseIndex> search() throws InstantiationException, IllegalAccessException {
		CheeseIndex starting = getStartingIndex(maze);
		List<CheeseIndex> frontier = new ArrayList<>();
		List<CheeseIndex> solutionPath = new ArrayList<>();
		
		frontier.add(starting);
		while (frontier.size() > 0) {
			CheeseIndex expand = getClosest(frontier, goal);
			checkLandedOnCheese(expand);
			
			System.out.println("node expanding is " + expand.column + " " + expand.row);
//			for (Index cheeseIndex : expand.cheeses) {
//				System.out.println("Cheeses visited already " + cheeseIndex.row + " " + cheeseIndex.column); 
//			}
//			System.out.println();
			
			if (!cheesesLeft(expand)) {
				for (Index p = expand; p != null; p = p.prev) {
					solutionPath.add(new CheeseIndex(p.row, p.column, null));	
				}
				return solutionPath;
			}
			
			frontier.remove(expand);
			CheeseIndex tempIndex = new CheeseIndex(expand.row, expand.column, null);
			CheeseIndex[] adjNodes = adjList.get(tempIndex);
			
			for (CheeseIndex i : adjNodes) {
				CheeseIndex newIndex = new CheeseIndex(i.row, i.column, null);
				copyCheeseList(expand, newIndex);
				if (!expanded.contains(newIndex) && !frontier.contains(newIndex)) {
					expanded.add(newIndex);
					newIndex.prev = expand;

//					if (getNearest(expand) - getNearest(newIndex) >= 0) {
//						frontier.add(newIndex);
//					}	
					frontier.add(newIndex);
					
				}
				
			}
			
		}
		return solutionPath;
	}

	public int getNearest(CheeseIndex index) {
		int curDistance;
		int minDistance = Integer.MAX_VALUE;
		for (Index cheeseIndex : this.cheeses) {
			if (!(index.cheeses.contains(cheeseIndex))){
				CheeseIndex tempIndex = new CheeseIndex(cheeseIndex.row, cheeseIndex.column, null);
				curDistance = getManhattanDistance(tempIndex, index);
				if (curDistance < minDistance) {
					minDistance = curDistance;
				}
			}
		}
		return minDistance;
	}
	
	// f(n) = g(n) + h(n)
	public CheeseIndex getClosest(List<CheeseIndex> frontier, CheeseIndex goal) {
		CheeseIndex minimumIndex = null;
		int minimumDistance = Integer.MAX_VALUE;
		// going through the frontier nodes
		for (CheeseIndex index : frontier) {
		
//			int distanceFromCheeseToCurrentIndex = (int) (Math.pow(getClosestDistToCheese(index), 1));
			int distanceFromCheeseToCurrentIndex = getClosestDistToCheese(index) + getPathLength(index);
			if (distanceFromCheeseToCurrentIndex < minimumDistance) {
				
				minimumDistance = distanceFromCheeseToCurrentIndex;
				minimumIndex = index;
			}
		}
//		System.out.println("The lowest hueirist c value is " + minimumDistance);
		return minimumIndex;
	}
	
	// sum of distances of all unvisited cheeses
	private int getAggregateManhattanDistance(CheeseIndex origin) {
		int sum = 0;
		for (Index cheeseIndex : this.cheeses) {
			if (!(origin.cheeses.contains(cheeseIndex))) {
				CheeseIndex tempIndex = new CheeseIndex(cheeseIndex.row, cheeseIndex.column, null);
				sum+= Math.pow(getManhattanDistance(origin, tempIndex), 1);
			} else {
				sum += 1;
			}
		}
		return sum;
	}
	
	private int getClosestDistToCheese(CheeseIndex index) {
		int dist = Integer.MAX_VALUE;
		for (Index index2 : this.cheeses) {
			if (!index.cheeses.contains(index2)) {
				CheeseIndex tempIndex = new CheeseIndex(index2.row, index2.column, null);
				int d = getManhattanDistance(tempIndex, index);
				dist = d < dist ? d : dist;
			}
		}
		return dist;
	}
	
//	private int getAggregatePathLengthToCheeses(CheeseIndex origin) throws InstantiationException, IllegalAccessException {
//		int sum = 0;
//		for (CheeseIndex cheeseIndex : this.cheeses) {
//			if (!(origin.cheeses.contains(cheeseIndex))) {
//				BFS bfs = new BFS(maze);
//				sum+= getManhattanDistance(origin, cheeseIndex);
//			}
//			
//		}
//	}
	
	private boolean cheesesLeft(CheeseIndex i) {
		return i.cheeses.size() != this.cheeses.size();
	}
	
	private void copyCheeseList(CheeseIndex fromIndex, CheeseIndex toIndex) {
		for (Index cheeseIndex : fromIndex.cheeses) {
			if (!toIndex.cheeses.contains(cheeseIndex)) {
				toIndex.cheeses.add(cheeseIndex);
			}
		}
	}
	
	private void checkLandedOnCheese(CheeseIndex index)	{
		Index tempIndex = new Index(index.row, index.column, null);
		for (Index cheese : this.cheeses) {
			if (tempIndex.equals(cheese) && !index.cheeses.contains(cheese)) {
				index.cheeses.add(cheese);
				break;
			}
		}
	}
	
	private List<Index> getCheeseFromMaze(char[][] maze) {
		List<Index> cheeses = new ArrayList<>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == '.') {
					cheeses.add(new Index(i, j, null));
				}
			}
		}
		return cheeses;
	}
}