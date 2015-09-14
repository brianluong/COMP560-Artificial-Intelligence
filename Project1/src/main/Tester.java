package main;

import java.util.List;

import searchAlgorithms.*;

public class Tester {

	public static void main(String[] args) throws Exception {
		
		String[] mazeFilePaths = {"../mazes/smallMaze.txt", "../mazes/mediumMaze.txt", "../mazes/bigMaze.txt"};
		
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
		
		char[][] maze = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/smallCheese.txt");
		
		Search aStar = new AstarCheese(maze);
		List<CheeseIndex> solutionCheese = aStar.search();
		SearchIOHelper.printMazeWithSolution2(maze, solutionCheese, aStar.getExpandedSet());
//		
//		maze = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/trickyCheese.txt");
//		
//		aStarCheese = new AstarCheese(maze);
//		solutionCheese = aStarCheese.search();
//		SearchIOHelper.printMazeWithSolution2(maze, solutionCheese, aStarCheese.getExpandedSet());
//		
				
		// PART 3
		//Stephen's part: Testing maze difficulty for A-star and GBFS
//		
//		char[][] aStarHard = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/aStarHard.txt");
//		char[][] gbfsHard = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/GBFSHard.txt");
//		
//		System.out.println("TESTING MAZE DIFFICULTY...\n");
//		System.out.println("A STAR DIFFICULT");
//		Search aStar = new Astar(aStarHard);
//		solution = aStar.search();
//		SearchIOHelper.printMazeWithSolution(aStarHard, solution, aStar.getExpandedSet());
//		
//		System.out.println("\nCOMPARING WITH GBFS (NOT AS HARD)");
//		Search gbfs_aStarHard = new GreedyBestFirstSearch(aStarHard);
//		solution = gbfs_aStarHard.search();
//		SearchIOHelper.printMazeWithSolution(aStarHard, solution, gbfs_aStarHard.getExpandedSet());
//		
//		System.out.println("\nGBFS DIFFICULT");
//		Search gbfs = new GreedyBestFirstSearch(gbfsHard);
//		solution = gbfs.search();
//		SearchIOHelper.printMazeWithSolution(gbfsHard, solution, gbfs.getExpandedSet());
//		
//		System.out.println("\nCOMPARING WITH A STAR (NOT AS HARD)");
//		Search aStar_gbfsHard = new Astar(gbfsHard);
//		solution = aStar_gbfsHard.search();
//		SearchIOHelper.printMazeWithSolution(gbfsHard, solution, aStar_gbfsHard.getExpandedSet());
	}
}