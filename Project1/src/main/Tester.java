package main;

import java.util.List;


import searchAlgorithms.*;

public class Tester {

	public static void main(String[] args) throws Exception {
		
//		String[] mazeFilePaths = {"../mazes/smallMaze.txt", "../mazes/mediumMaze.txt", "../mazes/bigMaze.txt"};
//		String[] mazeCheeseFilePaths = {"../mazes/smallCheese.txt", "../mazes/mediumCheese.txt", "../mazes/bigCheese.txt", "../mazes/trickyCheese.txt"};
		
		// PART 1
		
		List<Index> solution = null;
		
//		for (String mazeFilePath : mazeFilePaths) {
//			
//			char[][] maze = SearchIOHelper.generate2DArrayMazeFromInput(mazeFilePath);
//			
//			System.out.println("BFS");
//			Search bfs = new BFS(maze);
//			solution = bfs.search();
//			SearchIOHelper.printMazeWithSolution(maze, solution, bfs.getExpandedSet());
//
//			System.out.println("DFS");
//			Search dfs = new DFS(maze);
//			solution = dfs.search();
//			SearchIOHelper.printMazeWithSolution(maze, solution, dfs.getExpandedSet());
//
//			System.out.println("GREEDY BEST FIRST");
//			Search greedyBest = new GreedyBestFirstSearch(maze);
//			solution = greedyBest.search();
//			SearchIOHelper.printMazeWithSolution(maze, solution, greedyBest.getExpandedSet());
//			
//			System.out.println("A STAR");
//			Search aStar = new Astar(maze);
//			solution = aStar.search();
//			SearchIOHelper.printMazeWithSolution(maze, solution, aStar.getExpandedSet());
//			
//			System.out.println();
//		}
		
		// PART 2
		//Ben's part
		
		// PART 3
		
//		for (String mazeCheeseFilePath : mazeCheeseFilePaths) {
			
//			char[][] maze = SearchIOHelper.generate2DArrayMazeFromInput(mazeCheeseFilePath);
			char[][] maze = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/smallCheese.txt");
		
			System.out.println("Small Cheese");
			AstarCheese aStarCheese = new AstarCheese(maze);
			aStarCheese.search();
			
			System.out.println("Medium Cheese");
			
			System.out.println("Big Cheese");
			
			System.out.println("Tricky Cheese");
			
//		}

		//Stephen's part: Testing maze difficulty for A-star and GBFS
		
//		char[][] aStarHard = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/aStarHard.txt");
		//char[][] GBFS = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/GBFSHard.txt");
		
//		System.out.println("TESTING MAZE DIFFICULTY...\n");
//		System.out.println("A STAR DIFFICULT");
//		Search aStar = new Astar(aStarHard);
//		solution = aStar.search();
//		SearchIOHelper.printMazeWithSolution(aStarHard, solution, aStar.getExpandedSet());
//		
//		System.out.println("COMPARING WITH GBFS");
//		Search gbfs_aStarHard = new GreedyBestFirstSearch(aStarHard);
//		solution = gbfs_aStarHard.search();
//		SearchIOHelper.printMazeWithSolution(aStarHard, solution, gbfs_aStarHard.getExpandedSet());
		
	}
}