package searchAlgorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Search<T extends Index>{

	public static final char START = 'S';
	public static final char GOAL = 'G';
	public static final char WALL = '%';
	
	protected char[][] maze;
	protected Map<T, T[]> adjList;
	protected Set<T> expanded;
	
	protected Class<T> classOfT;
	
	public Search(char[][] maze, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		this.maze = maze;
		classOfT = clazz;
		adjList = generateAdjacencyList(maze);
		expanded = new HashSet<T>();
	}
	
	public boolean isGoal(Index ind, char[][] maze) {
		if (maze[ind.row][ind.column] == GOAL) {
			return true;
		} else {
			return false;
		}
	}
	
	public T getStartingIndex(char[][] maze) throws InstantiationException, IllegalAccessException {
		T ind = null;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == START) {
					ind = createIndex(i, j);
					return ind;
				}
			}
		}
		return ind;
	}
	
	public T getGoalIndex(char[][] maze) throws InstantiationException, IllegalAccessException {
		T ind = null;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == GOAL) {
					ind = createIndex(i, j);
					return ind;
				}
			}
		}
		return ind;
	}
	
	// g(n), cost to reach this index
	public int getPathLength(T index) {
		int length = 0;
		for (Index p = index; p != null; p = p.prev) {
			length++;
		}
		length--;
		return length;
	}
	
	public Set<T> getExpandedSet() {
		return expanded;
	}
	
	public abstract List<T> search() throws InstantiationException, IllegalAccessException;
	
	public Map<T, T[]> generateAdjacencyList(char[][] maze) throws InstantiationException, IllegalAccessException {
		Map<T, T[]> adjList = new HashMap<T, T[]>();
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == WALL) {
					continue;
				}
				adjList.put(createIndex(i, j), generateAdjacentIndexes(maze, i, j));
			}
		}
		return adjList;
	}
	
	private T[] generateAdjacentIndexes(char[][] maze, int i, int j) throws InstantiationException, IllegalAccessException {
		List<T> arrListAdjInd = new ArrayList<>();
		int length = maze.length - 1;
		int width = maze[0].length - 1;
		if (i == 0) {
			if (j == 0) {
				if (maze[1][0] != WALL) {
					arrListAdjInd.add(createIndex(1,0));
				}
				if (maze[0][1] != WALL) {
					arrListAdjInd.add(createIndex(0,1));
				}		
			} else if (j == width) {
				if (maze[0][width-1] != WALL) {
					arrListAdjInd.add(createIndex(0, width-1));
				}
				if (maze[1][width] != WALL) {
					arrListAdjInd.add(createIndex(1, width));
				}	
			} else {
				if (maze[0][j-1] != WALL) {
					arrListAdjInd.add(createIndex(0, j-1));
				}
				if (maze[0][j+1] != WALL) {
					arrListAdjInd.add(createIndex(0, j+1));
				}
				if (maze[1][j] != WALL) {
					arrListAdjInd.add(createIndex(1, j));
				}
			}
		} else if (i == length) {
			if (j == 0) {
				if (maze[length-1][0] != WALL) {
					arrListAdjInd.add(createIndex(length-1, 0));
				}
				if (maze[length][1] != WALL) {
					arrListAdjInd.add(createIndex(length, 1));
				}
			} else if (j == width) {
				if (maze[length-1][width] != WALL) {
					arrListAdjInd.add(createIndex(length-1, width));
				}
				if (maze[length][width-1] != WALL) {
					arrListAdjInd.add(createIndex(length, width-1));
				}
			} else {
				if (maze[length][j] != WALL) {
					arrListAdjInd.add(createIndex(length, j));
				}
				if (maze[length][j+1] != WALL) {
					arrListAdjInd.add(createIndex(length, j+1));
				}
				if (maze[length][j-1] != WALL) {
					arrListAdjInd.add(createIndex(length, j-1));
				}
			}
		} else if (j == 0) {
			if (maze[i][j+1] != WALL) {
				arrListAdjInd.add(createIndex(i, j+1));
			}
			if (maze[i-1][j] != WALL) {
				arrListAdjInd.add(createIndex(i-1, j));
			}
			if (maze[i+1][j] != WALL) {
				arrListAdjInd.add(createIndex(i+1, j));
			}
		} else if (j == width) {
			if (maze[i][j-1] != WALL) {
				arrListAdjInd.add(createIndex(i, j-1));
			}
			if (maze[i-1][j] != WALL) {
				arrListAdjInd.add(createIndex(i-1, j));
			}
			if (maze[i+1][j] != WALL) {
				arrListAdjInd.add(createIndex(i+1, j));
			}
		} else {
			if (maze[i-1][j] != WALL) {
				arrListAdjInd.add(createIndex(i-1, j));
			}
			if (maze[i+1][j] != WALL) {
				arrListAdjInd.add(createIndex(i+1, j));
			}
			if (maze[i][j-1] != WALL) {
				arrListAdjInd.add(createIndex(i, j-1));
			}
			if (maze[i][j+1] != WALL) {
				arrListAdjInd.add(createIndex(i, j+1));
			}
		}
		
		T[] s = createIndexArray(arrListAdjInd.size());
		for (int k = 0; k < arrListAdjInd.size(); k++) {
			s[k] = arrListAdjInd.get(k);
		}

		return s;
	}
	
	private T createIndex(int i, int j) throws InstantiationException, IllegalAccessException {
		T newIndex = classOfT.newInstance();
		newIndex.row = i;
		newIndex.column = j;
		return newIndex;
	}
	
	@SuppressWarnings("unchecked")
	private T[] createIndexArray(int size) {
		return (T[]) Array.newInstance(classOfT, size);
	}
}