package searchAlgorithms;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class SearchIOHelper {
	
	public static char[][] generate2DArrayMazeFromInput(String fileName) throws Exception {
		InputStream inputStream = SearchIOHelper.class.getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		String line = "";
		int width = -1;
		ArrayList<String> arrayList = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			
			// make sure every row is same length
			int currentWidth = line.length();
			if (width == -1) {
				width = currentWidth;
			} else if (currentWidth != width) {
				throw new IOException("Not all rows have same length");
			}
			arrayList.add(line);
		}
		
		int height = arrayList.size();
		char[][] maze = new char[height][width];
		for (int i = 0; i < height; i++) {
			String row = arrayList.get(i);
			for (int j = 0; j < width; j++) {
				maze[i][j] = row.charAt((char) j);
			}
		}
		
		return maze;
	}
	
	public static void printMaze(char[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printMazeWithSolution(char[][] maze, List<Index> solutionPath) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				Index ind = new Index(i, j, null);
				if (solutionPath.contains(ind) && maze[i][j] != 'S' && maze[i][j] != 'G') {
					System.out.print('.');
				} else {
					System.out.print(maze[i][j]);	
				}
			}
			System.out.println();
		}
	}
	
	public static void printSearchResults(char[][] maze, List<Index> solutionPath) {
		System.out.println("Unsolved Maze");
		printMaze(maze);
		System.out.println("Solved Maze");
		printMazeWithSolution(maze, solutionPath);
	}
}