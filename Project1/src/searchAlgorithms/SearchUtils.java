package searchAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUtils {
	
	public static final char START = 'S';
	public static final char GOAL = 'G';
	public static final char WALL = '%';
			
	public static boolean isGoal(Index ind, char[][] maze) {
		if (maze[ind.row][ind.column] == GOAL) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Map<Index, Index[]> generateAdjacencyList(char[][] maze) {
		Map<Index, Index[]> adjList = new HashMap<Index, Index[]>();
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == WALL) {
					continue;
				} 
				adjList.put(new Index(i,j, null), generateAdjacentIndexes(maze, i, j));
			}
		}
		return adjList;
	}
	
	private static Index[] generateAdjacentIndexes(char[][] maze, int i, int j) {
		List<Index> arrListAdjInd = new ArrayList<Index>();
		int length = maze.length - 1;
		int width = maze[0].length - 1;
		if (i == 0) {
			if (j == 0) {
				if (maze[1][0] != WALL) {
					arrListAdjInd.add(new Index(1, 0, null));
				}
				if (maze[0][1] != WALL) {
					arrListAdjInd.add(new Index(0, 1, null));
				}		
			} else if (j == width) {
				if (maze[0][width-1] != WALL) {
					arrListAdjInd.add(new Index(0, width-1, null));
				}
				if (maze[1][width] != WALL) {
					arrListAdjInd.add(new Index(1, width, null));
				}	
			} else {
				if (maze[0][j-1] != WALL) {
					arrListAdjInd.add(new Index(0, j-1, null));
				}
				if (maze[0][j+1] != WALL) {
					arrListAdjInd.add(new Index(0, j+1, null));
				}
				if (maze[1][j] != WALL) {
					arrListAdjInd.add(new Index(1, j, null));
				}
			}
		} else if (i == length) {
			if (j == 0) {
				if (maze[length-1][0] != WALL) {
					arrListAdjInd.add(new Index(length-1, 0, null));
				}
				if (maze[length][1] != WALL) {
					arrListAdjInd.add(new Index(length, 1, null));
				}
			} else if (j == width) {
				if (maze[length-1][width] != WALL) {
					arrListAdjInd.add(new Index(length-1, width, null));
				}
				if (maze[length][width-1] != WALL) {
					arrListAdjInd.add(new Index(length, width-1, null));
				}
			} else {
				if (maze[length][j] != WALL) {
					arrListAdjInd.add(new Index(length, j, null));
				}
				if (maze[length][j+1] != WALL) {
					arrListAdjInd.add(new Index(length, j+1, null));
				}
				if (maze[length][j-1] != WALL) {
					arrListAdjInd.add(new Index(length, j-1, null));
				}
			}
		} else if (j == 0) {
			if (maze[i][j+1] != WALL) {
				arrListAdjInd.add(new Index(i, j+1, null));
			}
			if (maze[i-1][j] != WALL) {
				arrListAdjInd.add(new Index(i-1, j, null));
			}
			if (maze[i+1][j] != WALL) {
				arrListAdjInd.add(new Index(i+1, j, null));
			}
		} else if (j == width) {
			if (maze[i][j-1] != WALL) {
				arrListAdjInd.add(new Index(i, j-1, null));
			}
			if (maze[i-1][j] != WALL) {
				arrListAdjInd.add(new Index(i-1, j, null));
			}
			if (maze[i+1][j] != WALL) {
				arrListAdjInd.add(new Index(i+1, j, null));
			}
		} else {
			if (maze[i-1][j] != WALL) {
				arrListAdjInd.add(new Index(i-1, j, null));
			}
			if (maze[i+1][j] != WALL) {
				arrListAdjInd.add(new Index(i+1, j, null));
			}
			if (maze[i][j-1] != WALL) {
				arrListAdjInd.add(new Index(i, j-1, null));
			}
			if (maze[i][j+1] != WALL) {
				arrListAdjInd.add(new Index(i, j+1, null));
			}
		}
		
		Index[] s = new Index[arrListAdjInd.size()];
		for (int k = 0; k < arrListAdjInd.size(); k++) {
			s[k] = arrListAdjInd.get(k);
		}

		return s;
	}
	
	public static Index getStartingIndex(char[][] maze) {
		Index ind = null;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == START) {
					ind = new Index(i, j, null);
				}
			}
		}
		return ind;
	}
	
	public static CheeseIndex getStartingCheeseIndex(char[][] maze) {
		CheeseIndex ind = null;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == START) {
					ind = new CheeseIndex(i, j, null);
				}
			}
		}
		return ind;
	}
	
	public static Index getGoalIndex(char[][] maze) {
		Index ind = null;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == GOAL) {
					ind = new Index(i, j, null);
				}
			}
		}
		return ind;
	}
	
	public static List<CheeseIndex> getCheeseFromMaze(char[][] maze) {
		List<CheeseIndex> cheeses = new ArrayList<>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == '.') {
					cheeses.add(new CheeseIndex(i, j, null));
				}
			}
		}
		return cheeses;
	}
}