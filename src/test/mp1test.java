package test;

import java.io.IOException;
import java.util.Set;

import graphLib.SearchGraph;
import graphLib.SearchGraphNode;
import graphLib.mazeIO;

import org.junit.Test;

//import static org.junit.Assert.*;

public class mp1test {
	// mazeIO reader;
	// String[] smallmaze;
	// char[][] maze;
	// SearchGraph graph;
	// @Before
	// public void setup() {
	// reader = new mazeIO();
	// smallmaze = reader.readLines("data/smallMaze.lay");
	// maze = reader.convertStringto2DArray(smallmaze);
	//
	// graph = new SearchGraph(maze);
	// }
	@Test
	public void DFSSmall() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/smallMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.DFS();
		graph.printDFSPath();
	}

	@Test
	public void DFSMedium() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/mediumMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.DFS();
		graph.printDFSPath();
	}

	@Test
	public void DFSBig() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/bigMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.DFS();
		graph.printDFSPath();
	}

	@Test
	public void DFSOpen() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/openMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.DFS();
		graph.printDFSPath();
	}

	@Test
	public void BFSSmall() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/smallMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.BFS();
		graph.printBFSPath();
		// graph.printDFSPath();
	}

	@Test
	public void BFSmedium() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/mediumMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.BFS();
		graph.printBFSPath();
		// graph.printDFSPath();
	}

	@Test
	public void BFSbig() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/bigMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.BFS();
		graph.printBFSPath();
		// graph.printDFSPath();
	}

	@Test
	public void BFSopen() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/openMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.BFS();
		graph.printBFSPath();
		// graph.printDFSPath();
	}
	
	@Test
	public void GreedyBestSearchSmall() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/smallMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.GreedyBestFirstSearch();
		graph.printGreedyBestFirstSearch();
		// graph.printDFSPath();
	}
	
	@Test
	public void GreedyBestSearchMedium() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/mediumMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.GreedyBestFirstSearch();
		graph.printGreedyBestFirstSearch();
		// graph.printDFSPath();
	}
	
	@Test
	public void GreedyBestSearchBig() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/bigMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.GreedyBestFirstSearch();
		graph.printGreedyBestFirstSearch();
		// graph.printDFSPath();
	}
	
	@Test
	public void GreedyBestSearchOpen() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/openMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.GreedyBestFirstSearch();
		graph.printGreedyBestFirstSearch();
		// graph.printDFSPath();
	}

	@Test
	public void AStarSmall() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/smallMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.AStar();
		graph.printAStarSearch();
		// graph.printDFSPath();
	}

	@Test
	public void AStarMedium() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/mediumMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.AStar();
		graph.printAStarSearch();
		// graph.printDFSPath();
	}

	@Test
	public void AStarBig() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/bigMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.AStar();
		graph.printAStarSearch();
		// graph.printDFSPath();
	}

	@Test
	public void AStarOpen() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/openMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.AStar();
		graph.printAStarSearch();
		// graph.printDFSPath();
	}

	@Test
	public void UniformCostSearchMediumPositive() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/mediumMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.UniformCostSearch((float) 1.0);
		// graph.printDFSPath();
	}

	@Test
	public void UniformCostSearchMediumNegative() throws Exception {
		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/mediumMaze.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		graph.UniformCostSearch((float) -1.0);
		// graph.printDFSPath();
	}
	


}
