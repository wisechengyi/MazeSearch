package test;

import graphLib.SearchGraph;
import graphLib.SearchGraphNode;
import graphLib.mazeIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class multiGoalTest {
	
//	Queue<Object> permutatedResults;
	

	static int fact(int n) {

		// Base Case:
		// If n <= 1 then n! = 1.
		if (n <= 1) {
			return 1;
		}
		// Recursive Case:
		// If n > 1 then n! = n * (n-1)!
		else {
			return n * fact(n - 1);
		}
	}

	//
	public SearchGraphNode[][] permute(Object[] allGoals)
	{
		
		int setLength = allGoals.length;
		
//		SearchGraphNode[] ret = new SearchGraphNode[setLength];
		
		SearchGraphNode[][] seq = new SearchGraphNode[fact(setLength)][];
		
		
		System.out.println(seq.length);
		
		
		
		int[] index = new int[setLength];
		
		for (int i=0;i<setLength;i++)
		{
			index[i]=i;
		}
	
		
		int k=0;
		for (int i=0; i<fact(setLength -1 );i++)
		{
			
			
			int temp1 = index[0];
			index[0]=index[setLength-1];
			index[setLength-1]=temp1;
			
			
			
			SearchGraphNode[] currSeq1 = new SearchGraphNode[setLength];
			for (int l=0;l<setLength;l++)
			{
				currSeq1[l]=(SearchGraphNode) allGoals[index[l]];
//				System.out.print(currSeq[l].hashCode());
			}
			
			seq[k]=currSeq1;
			
			k++;
			
			for (int j=0; j< (setLength-1); j++)
			{
				int temp = index[j];
				index[j]=index[j+1];
				index[j+1]=temp;
				
				
				
				SearchGraphNode[] currSeq2 = new SearchGraphNode[setLength];
				for (int l=0;l<setLength;l++)
				{
					currSeq2[l]=(SearchGraphNode) allGoals[index[l]];
//					System.out.print(currSeq[l].hashCode());
				}
				
				seq[k]=currSeq2;
				
				k++;
			}
			
			
		}
		System.out.println(k);
		return seq;
	}

	
	public void permuteOnCount(Object[] allGoals, int count)
	{
		int i = count % allGoals.length;
		
		Object temp = allGoals[i];
		
		if ( i == allGoals.length -1)
		{
			allGoals[i]=allGoals[0];
			allGoals[0]=temp;
		}
		else
		{
			
			allGoals[i]=allGoals[i+1];
			allGoals[i+1]=temp;
		}
		

	}
	@Test
	public void MultiGoalDFS() throws IOException {
		System.out.println("Multi-Goal DFS\n"+
				"*******************************************************");

		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/tinySearch.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		Object[] allGoals = graph.goals.toArray();

		int k = 0;

		int total = 2;
		// int total = fact(allGoals.length);

		float multiCost = 0;

		while (k < total) {

			//first iteration
			SearchGraphNode end = (SearchGraphNode) allGoals[0];

			Set<SearchGraphNode> rmList = new HashSet<SearchGraphNode>();

			graph.goals.clear();
			graph.goals.add(end);

			graph.goals.removeAll(rmList);

			// System.out.format("goal size: %d\n",graph.goals.size());

			graph.DFS();
			multiCost += graph.pathCost;
			graph.printDFSPath(1);
			graph.Cleanup();

			for (int i = 0; i < allGoals.length - 1; i++) {

				graph.root = (SearchGraphNode) allGoals[i];
				graph.goals.clear();
				graph.goals.add((SearchGraphNode) allGoals[i + 1]);

				graph.DFS();
				multiCost += graph.pathCost;
				graph.printDFSPath(i + 2);
				graph.Cleanup();

			}

			System.out.format("COST:%4.2f\n\n\n\n", multiCost);
			graph.resetRoot();
			permuteOnCount(allGoals, k);
			k++;

		}

	}
	
	@Test
	public void MultiGoalBFS() throws IOException {
		System.out.println("Multi-Goal BFS\n" +
				"*******************************************************");

		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/tinySearch.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		Object[] allGoals = graph.goals.toArray();

		int k = 0;

		int total = 2; // need to modify total to be the factorial of the sequence size
		// int total = fact(allGoals.length);

		float bestCost = Float.MAX_VALUE;
		List<SearchGraphNode> bestSeq = new ArrayList<SearchGraphNode>();

		while (k < total) {
			
			float currMultiCost = 0;

			//first iteration
			SearchGraphNode end = (SearchGraphNode) allGoals[0];

			Set<SearchGraphNode> rmList = new HashSet<SearchGraphNode>();

			graph.goals.clear();
			graph.goals.add(end);

			graph.goals.removeAll(rmList);

			// System.out.format("goal size: %d\n",graph.goals.size());

			graph.BFS();
			currMultiCost += graph.pathCost;
			graph.printBFSPath(1);
			graph.Cleanup();

			for (int i = 0; i < allGoals.length - 1; i++) {

				graph.root = (SearchGraphNode) allGoals[i];
				graph.goals.clear();
				graph.goals.add((SearchGraphNode) allGoals[i + 1]);

				graph.BFS();
				currMultiCost += graph.pathCost;
				graph.printBFSPath(i + 2);
				graph.Cleanup();

			}
			
			if (currMultiCost < bestCost)
			{
				bestCost=currMultiCost;
				bestSeq.clear();
				for (Object e : allGoals )
				{
					bestSeq.add((SearchGraphNode) e) ;
				}
			}
			
			System.out.format("COST:%4.2f\n\n\n\n", currMultiCost);

			graph.resetRoot();
			permuteOnCount(allGoals, k);
			k++;

		}
		
		

	}
	
	@Test
	public void MultiGoalGreedyBest() throws Exception {
		System.out.println("Multi-Goal Greedy Best First Search\n" +
				"*******************************************************");

		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/tinySearch.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		Object[] allGoals = graph.goals.toArray();

		int k = 0;

		int total = 2; // need to modify total to be the factorial of the sequence size
		// int total = fact(allGoals.length);

		float bestCost = Float.MAX_VALUE;
		List<SearchGraphNode> bestSeq = new ArrayList<SearchGraphNode>();

		while (k < total) {
			
			float currMultiCost = 0;

			//first iteration
			SearchGraphNode end = (SearchGraphNode) allGoals[0];

			Set<SearchGraphNode> rmList = new HashSet<SearchGraphNode>();

			graph.goals.clear();
			graph.goals.add(end);

			graph.goals.removeAll(rmList);

			// System.out.format("goal size: %d\n",graph.goals.size());

			graph.GreedyBestFirstSearch();
			currMultiCost += graph.pathCost;
			graph.printGreedyBestFirstSearch(1);
			graph.Cleanup();

			for (int i = 0; i < allGoals.length - 1; i++) {

				graph.root = (SearchGraphNode) allGoals[i];
				graph.goals.clear();
				graph.goals.add((SearchGraphNode) allGoals[i + 1]);

				graph.GreedyBestFirstSearch();
				currMultiCost += graph.pathCost;
				graph.printGreedyBestFirstSearch(i + 2);
				graph.Cleanup();

			}
			
			if (currMultiCost < bestCost)
			{
				bestCost=currMultiCost;
				bestSeq.clear();
				for (Object e : allGoals )
				{
					bestSeq.add((SearchGraphNode) e) ;
				}
			}
			
			System.out.format("COST:%4.2f\n\n\n\n", currMultiCost);

			graph.resetRoot();
			permuteOnCount(allGoals, k);
			k++;

		}
		
		

	}
	
	@Test
	public void MultiGoalAStar() throws Exception {
		System.out.println("Multi-Goal AStar\n" +
				"*******************************************************");

		mazeIO reader = new mazeIO();
		String[] smallmaze = reader.readLines("data/tinySearch.lay");
		char[][] maze = reader.convertStringto2DArray(smallmaze);

		SearchGraph graph = new SearchGraph(maze);

		Object[] allGoals = graph.goals.toArray();

		int k = 0;

		int total = 2; // need to modify total to be the factorial of the sequence size
		// int total = fact(allGoals.length);

		float bestCost = Float.MAX_VALUE;
		List<SearchGraphNode> bestSeq = new ArrayList<SearchGraphNode>();

		while (k < total) {
			
			float currMultiCost = 0;

			//first iteration
			SearchGraphNode end = (SearchGraphNode) allGoals[0];

			Set<SearchGraphNode> rmList = new HashSet<SearchGraphNode>();

			graph.goals.clear();
			graph.goals.add(end);

			graph.goals.removeAll(rmList);

			// System.out.format("goal size: %d\n",graph.goals.size());

			graph.AStar();
			currMultiCost += graph.pathCost;
			graph.printAStarSearch(1);
			graph.Cleanup();

			for (int i = 0; i < allGoals.length - 1; i++) {

				graph.root = (SearchGraphNode) allGoals[i];
				graph.goals.clear();
				graph.goals.add((SearchGraphNode) allGoals[i + 1]);

				graph.AStar();
				currMultiCost += graph.pathCost;
				graph.printAStarSearch(i + 2);
				graph.Cleanup();

			}
			
			if (currMultiCost < bestCost)
			{
				bestCost=currMultiCost;
				bestSeq.clear();
				for (Object e : allGoals )
				{
					bestSeq.add((SearchGraphNode) e) ;
				}
			}
			
			System.out.format("COST:%4.2f\n\n\n\n", currMultiCost);

			graph.resetRoot();
			permuteOnCount(allGoals, k);
			k++;

		}
		
		

	}
}
