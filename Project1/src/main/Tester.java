package main;

import java.util.List;

import searchAlgorithms.*;

public class Tester {

	public static void main(String[] args) throws Exception {

		// Part 1
		
  		String[] mazeFilePaths = {"../mazes/smallMaze.txt", "../mazes/mediumMaze.txt", "../mazes/bigMaze.txt"};
		
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
			
		// Part 2
		// Testing maze difficulty for A-star and GBFS
		
		char[][] aStarHard = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/aStarHard.txt");
		char[][] gbfsHard = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/GBFSHard.txt");
		//test maze
		char[][] testMaze = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/testMaze.txt");

		System.out.println("TESTING MAZE DIFFICULTY...\n");
		System.out.println("A STAR DIFFICULT");
		Search aStar = new Astar(aStarHard);
		long startAHard = System.currentTimeMillis();
		solution = aStar.search();
		SearchIOHelper.printMazeWithSolution(aStarHard, solution, aStar.getExpandedSet());
		long timeAHard = System.currentTimeMillis() - startAHard;
		System.out.println("Took " + timeAHard / 1000.0 + " secs");

		System.out.println("\nCOMPARING WITH GBFS (NOT AS HARD)");
		Search gbfs_aStarHard = new GreedyBestFirstSearch(aStarHard);
		long startAHard2 = System.currentTimeMillis();
		solution = gbfs_aStarHard.search();
		SearchIOHelper.printMazeWithSolution(aStarHard, solution, gbfs_aStarHard.getExpandedSet());
		long timeAHard2 = System.currentTimeMillis() - startAHard2;
		System.out.println("Took " + timeAHard2 / 1000.0 + " secs");
		
		System.out.println("\nGBFS DIFFICULT");
		Search gbfs = new GreedyBestFirstSearch(gbfsHard);
		long startGBFSHard = System.currentTimeMillis();
		solution = gbfs.search();
		SearchIOHelper.printMazeWithSolution(gbfsHard, solution, gbfs.getExpandedSet());
		long timeGBFSHard = System.currentTimeMillis() - startGBFSHard;
		System.out.println("Took " + timeGBFSHard / 1000.0 + " secs");
		
		System.out.println("\nCOMPARING WITH A STAR (NOT AS HARD)");
		Search aStar_gbfsHard = new Astar(gbfsHard);
		long startGBFSHard2 = System.currentTimeMillis();
		solution = aStar_gbfsHard.search();
		SearchIOHelper.printMazeWithSolution(gbfsHard, solution, aStar_gbfsHard.getExpandedSet());
		long timeGBFSHard2 = System.currentTimeMillis() - startGBFSHard2;
		System.out.println("Took " + timeGBFSHard2 / 1000.0 + " secs");
		
		// test maze
		System.out.println("\nTESTING SPARSE MAZE...");
		System.out.println("A STAR");
		Search test = new Astar(testMaze);
		long startTest = System.currentTimeMillis();
		solution = test.search();
		SearchIOHelper.printMazeWithSolution(testMaze, solution, test.getExpandedSet());
		long timeTest = System.currentTimeMillis() - startTest;
		System.out.println("Took " + timeTest / 1000.0 + " sec\n");
		
		// Part 3
		// Generating cheese solutions
		
		char[][] maze = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/smallCheese.txt");
					
		System.out.println("Small Cheese");
		AstarCheese aStarCheese = new AstarCheese(maze);
		long scStart = System.currentTimeMillis();
		List<CheeseIndex> solutionCheeseIndexs = aStarCheese.search();
		long scTime = System.currentTimeMillis() - scStart;
		List<Index> cheeses = aStarCheese.cheeses;
		SearchIOHelper.printMazeWithSolutionCheeses(maze, solutionCheeseIndexs, aStarCheese.getExpandedSet(), cheeses);
		System.out.println("Took " + scTime / 1000.0 + " sec\n");
					
		System.out.println("Tricky Cheese");
		maze = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/trickyCheese.txt");
		aStarCheese = new AstarCheese(maze);
		long trickyStart = System.currentTimeMillis();
		solutionCheeseIndexs = aStarCheese.search();
		long trickyTime = System.currentTimeMillis() - trickyStart;
		cheeses = aStarCheese.cheeses;
		SearchIOHelper.printMazeWithSolutionCheeses(maze, solutionCheeseIndexs, aStarCheese.getExpandedSet(), cheeses);
		System.out.println("Took " + trickyTime / 1000.0 + " sec\n");
		
		System.out.println("Medium Cheese");
		maze = SearchIOHelper.generate2DArrayMazeFromInput("../mazes/mediumCheese.txt");
		aStarCheese = new AstarCheese(maze);
		trickyStart = System.currentTimeMillis();
		System.out.println("Please wait....");
		solutionCheeseIndexs = aStarCheese.search();
		trickyTime = System.currentTimeMillis() - trickyStart;
		cheeses = aStarCheese.cheeses;
		SearchIOHelper.printingAnimation(maze, solutionCheeseIndexs);
//		SearchIOHelper.printMazeWithSolutionCheeses(maze, solutionCheeseIndexs, aStarCheese.getExpandedSet(), cheeses);
		System.out.println("Took " + trickyTime / 1000.0 + " sec\n");
	}
}