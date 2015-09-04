package main;

import java.util.List;

import searchAlgorithms.BFS;
import searchAlgorithms.Index;
import searchAlgorithms.SearchUtils;

public class Tester {

	public static void main(String[] args) throws Exception {
		
		String[] mazeFilePaths = {"../mazes/smallMaze.txt", "../mazes/mediumMaze.txt", "../mazes/bigMaze.txt"};
		
		for (String mazeFilePath : mazeFilePaths) {
			char[][] maze = SearchUtils.generate2DArrayMazeFromInput(mazeFilePath);
			List<Index> solution = BFS.search(maze);
			SearchUtils.printSearchResults(maze, solution);
		}
	}

}
