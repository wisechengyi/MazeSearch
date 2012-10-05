package graphLib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.lang.Float;


public class SearchGraphNode {
	
//	public Set<SearchGraphNode> parents;
	
	public Coord coord;
	
//	public Set<SearchGraphNode> children;
	
	public Set<SearchGraphNode> neighbors;
	public SearchGraphNode caller;
	public Queue<SearchGraphNode> GreedyCallers;
	public boolean isVisited;
	public boolean onPath;
	
	public float cost;
	public SearchGraphNode(Coord input) {
//		children = new HashSet<SearchGraphNode>();
//		parents = new HashSet<SearchGraphNode>();
		neighbors= new HashSet<SearchGraphNode>();
//		Queue<SearchGraphNode> BFSCaller = new LinkedList<SearchGraphNode>();
//		Queue<SearchGraphNode> GreedyCallers
		isVisited=false;
		onPath=false;
		caller=null;
		cost=Float.MAX_VALUE;
		coord = new Coord(input);
  }
	
	public void cleanUp()
	{
		isVisited=false;
		onPath=false;
		caller=null;
		cost=Float.MAX_VALUE;
	}
	
//	public SearchGraphNode(Coord inputCoord,  SearchGraphNode parent)
//	{
//
//		children = new HashSet<SearchGraphNode>();
//		parents = new HashSet<SearchGraphNode>();
//		neighbors= new HashSet<SearchGraphNode>();
//		isVisited=false;
//		
//		coord = new Coord(inputCoord);
//		if (parent!=null)
//		{
//			parents.add(parent);
//		}
//		
//		
//		
//	}
	
	public void connect(SearchGraphNode otherNode)
	{
		if (!neighbors.contains(otherNode))
		{
			neighbors.add(otherNode);
		}
		
		if(!otherNode.neighbors.contains(this))
		{
			otherNode.neighbors.add(this);
		}
		
	}
	
	public boolean equals(Object O)
	{
		System.out.println("SearchGraphNode equal invoked");
		
		
		if (!(O instanceof SearchGraphNode))
		{
			return false;
		}
		
		
		SearchGraphNode otherNode = (SearchGraphNode) O;
		
		if (coord.equals(otherNode.coord) )
		{
			return true;
		}
		
		return false;
	}
//	public void expand(Set<Coord> space, Set<Coord> goals, Coord curr, boolean[][] visitedMap)
//	{
//		coord=curr;
////		coord.isVisited=true;
//		
////		Coord neighbors[4] = 
//		
//		
//		
//	}


}
