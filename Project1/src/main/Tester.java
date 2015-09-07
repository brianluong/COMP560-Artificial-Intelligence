package main;

import java.util.List;

import searchAlgorithms.*;

public class Tester {

	public static void main(String[] args) throws Exception {
		
		String[] mazeFilePaths = {"../mazes/smallMaze.txt", "../mazes/mediumMaze.txt", "../mazes/bigMaze.txt"};
		
		// PART 1
		
		List<Index> solution = null;
		
		for (String mazeFilePath : mazeFilePaths) {
			
			char[][] maze = SearchIOHelper.generate2DArrayMazeFromInput(mazeFilePath);
			
			System.out.println("BFS");
			Search bfs = new BFS(maze);
			solution = bfs.search();
			SearchIOHelper.printMazeWithSolution(maze, solution, bfs.getExpandedSet());

			System.out.println("DFS");
			Search dfs = new DFS(maze);
			solution = dfs.search();
			SearchIOHelper.printMazeWithSolution(maze, solution, dfs.getExpandedSet());

			System.out.println("GREEDY BEST FIRST");
			Search greedyBest = new GreedyBestFirstSearch(maze);
			solution = greedyBest.search();
			SearchIOHelper.printMazeWithSolution(maze, solution, greedyBest.getExpandedSet());
			
			System.out.println("A STAR");
			Search aStar = new Astar(maze);
			solution = aStar.search();
			SearchIOHelper.printMazeWithSolution(maze, solution, aStar.getExpandedSet());
			
			System.out.println();
		}
		
		// PART 2
	}
}