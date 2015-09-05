package main;

import java.util.List;

import searchAlgorithms.BFS;
import searchAlgorithms.DFS;
import searchAlgorithms.Index;
import searchAlgorithms.SearchIOHelper;

public class Tester {

	public static void main(String[] args) throws Exception {
		
		String[] mazeFilePaths = {"../mazes/smallMaze.txt", "../mazes/mediumMaze.txt", "../mazes/bigMaze.txt"};
		
		for (String mazeFilePath : mazeFilePaths) {
			char[][] maze = SearchIOHelper.generate2DArrayMazeFromInput(mazeFilePath);
			List<Index> solution = DFS.search(maze);
			SearchIOHelper.printSearchResults(maze, solution);
		}
	}
}