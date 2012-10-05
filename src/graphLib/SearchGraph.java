package graphLib;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.lang.Math;

public class SearchGraph {

	public SearchGraphNode root;
	private SearchGraphNode originalRoot;
//	private Set<Coord> goals;
//	private Set<Coord> space;
//	private Coord startPosition;
	public Set<SearchGraphNode> goals;
	public Set<SearchGraphNode> space;
	
	private Set<SearchGraphNode> commonSolution;
	
	
	
	//bench mark parameters
	public float pathCost;
	public int nodesExpanded;
	public int maxDepth;
	public int maxFrontier;
	
	char[][] maze;
	SearchGraphNode[][] nodeMap; //[mapHeight+2][mapWidth+2] to avoid boundary check

	


	public SearchGraph(char[][] input)
	{

		
		
		maze=input;		
		goals = new HashSet<SearchGraphNode>();
		space = new HashSet<SearchGraphNode>();
		
		boolean visitedMap[][] = new boolean[input.length][input[0].length]; 
		
		int mapWidth = input[0].length;
		int mapHeight= input.length;
		
		//increase size to avoid boundary check
		nodeMap = new SearchGraphNode[mapHeight+2][mapWidth+2];
		
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				
//				Coord tempCoord = ;
				SearchGraphNode temp = new SearchGraphNode(new Coord(i,j));
				
				switch (input[i][j]) {
				case 'P':
					root =temp;
					space.add(temp);
					break;
				case ' ':
//					SearchGraphNode newSpace = new SearchGraphNode(new Coord(i,j));
					space.add(temp);
					break;
				case '.':
//					SearchGraphNode newGoal = new SearchGraphNode(new Coord(i,j));
					goals.add(temp);
					break;
				}
				
				nodeMap[i+1][j+1]=temp;
			}
		}
		
		for (int i = 1; i < mapHeight+1; i++) {
			for (int j = 1; j < mapWidth+1; j++) {
				if (space.contains(nodeMap[i][j]) || goals.contains(nodeMap[i][j])) 
				{
					if (space.contains(nodeMap[i-1][j]) || goals.contains(nodeMap[i-1][j]))
					{
						//connect the two nodes
						nodeMap[i][j].connect(nodeMap[i-1][j]);
					}
					
					if (space.contains(nodeMap[i+1][j]) || goals.contains(nodeMap[i+1][j]))
					{
						//connect the two nodes
						nodeMap[i][j].connect(nodeMap[i+1][j]);
					}
					
					if (space.contains(nodeMap[i][j-1])||goals.contains(nodeMap[i][j-1]))
					{
						//connect the two nodes
						nodeMap[i][j].connect(nodeMap[i][j-1]);
					}
					
					if (space.contains(nodeMap[i][j+1])||goals.contains(nodeMap[i][j+1]))
					{
						//connect the two nodes
						nodeMap[i][j].connect(nodeMap[i][j+1]);
					}
				}
				
				
			
			}
		}
		
//		for (int i = 1; i < mapHeight+1; i++) {
//			for (int j = 1; j < mapWidth+1; j++) {
//				System.out.format("%d ", nodeMap[i][j].neighbors.size() );
//			
//			}
//			System.out.println();
//		}
		
		originalRoot=root;
		
	}
	
	
	
	public void DFS()
	{
		System.out.println("DFS:");
		
		pathCost=0;
		nodesExpanded=0;
		maxDepth=0;
		maxFrontier=0;
		
		DFS_helper(root,0);
		
		
		System.out.format("pathCost:%.2f nodesExpanded:%d maxDepth:%d maxFrontier:-\n",pathCost,nodesExpanded,maxDepth);
		//maxFrontier;
	}
	
	
	/**
	 * @param curr
	 * @return true if current node is the path to goal
	 */
	public boolean DFS_helper(SearchGraphNode curr, int currDepth) {
		
		if (currDepth>maxDepth)
		{
			maxDepth=currDepth;
		}
		
		nodesExpanded++;
		
		if (curr.isVisited) {
			return false;
		}
		
		curr.isVisited = true;
//		System.out.format("y:%d x:%d\n",curr.coord.row,curr.coord.col);
		if (goals.contains(curr)) {
			pathCost=currDepth;
//			System.out.println("found it");
//			curr.onPath=true;
			return true;
		} else {
			for (SearchGraphNode node : curr.neighbors) {
				if (DFS_helper(node, currDepth+1)) {
					curr.onPath = true;
					return true;
//					System.out.println("this is happening");
				}
			}
		}
		
		return false;
	}
	
	public void printDFSPath(int ... num)
	{
		int mapWidth = maze[0].length;
		int mapHeight= maze.length;
		
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				if (goals.contains(nodeMap[i+1][j+1]))
				{
					if (num.length==0)
					{
						System.out.print(".");
					}
					else
					{
						System.out.print(num[0]%10);
					}
				}
				else if (nodeMap[i+1][j+1]==root)
				{
					
					if (num.length==0)
					{
						System.out.print("P");
					}
					else
					{
						System.out.print((num[0]-1)%10);
					}
				}
				else if (nodeMap[i+1][j+1].onPath)
				{
					System.out.print(",");
				}
				else
				{
					if (maze[i][j] == 'P') {
						System.out.print(" ");
					} else {
						System.out.print(maze[i][j]);
					}
				}
			}
			System.out.println();
		}
	}
	
	public void Cleanup()
	{
		for (int i = 1; i < nodeMap.length-1; i++) {
			for (int j = 1; j < nodeMap[0].length-1; j++) {
				nodeMap[i][j].cleanUp();
				
			}
		}
	}
	
	public void BFS(boolean ... swt)
	{		
		pathCost=0;
		nodesExpanded=0;
		maxDepth=0;
		maxFrontier=0;
	
		System.out.println("BFS:");
		BFS_helper(root);

		
		Set<SearchGraphNode> solution = new HashSet<SearchGraphNode>();
//		
		if (goals.size()==1)
		{
			
			Iterator<SearchGraphNode> it = goals.iterator();
			SearchGraphNode temp = it.next();
			
//			System.out.format("y:%d x:%d\n",temp.coord.row,temp.coord.col);
			
//			SearchGraphNode a = temp.caller;
			
//			System.out.format("y:%d x:%d\n",a.coord.row,a.coord.col);
			
			while (temp.caller!=null && temp!=null)
			{
				solution.add(temp.caller);
				temp=temp.caller;
				pathCost+=1.0;
			}
		}
		else
		{
			System.out.println("multi goal, need to see what's up");
		}
		
//		System.out.format("solution size: %d\n",solution.size());
		System.out.format("pathCost:%.2f nodesExpanded:%d maxDepth:%d maxFrontier:%d\n",pathCost,nodesExpanded,(int) pathCost,maxFrontier);
		
		commonSolution = solution;
		
//		if (swt.length == 1) {
//			if (swt[0] == true) {
//				printBFSPath(solution);
//			}
//		}
//		else
//		{
//			printBFSPath(solution);
//		}
	}
	

	
	/**
	 * @param root
	 * @return true if current node is the path to goal
	 */
	public boolean BFS_helper(SearchGraphNode root) {
		Queue<SearchGraphNode> searchList = new LinkedList<SearchGraphNode>();
		Queue<SearchGraphNode> BFScaller = new LinkedList<SearchGraphNode>();
		Queue<Float> costList = new LinkedList<Float>();
		
		searchList.add(root);
		costList.add( (float) 0);
		BFScaller.add(null);
		
		while (!searchList.isEmpty())
		{	
			nodesExpanded++;
			if (searchList.size()>maxFrontier)
			{
				maxFrontier=searchList.size();
			}
			
			SearchGraphNode curr = searchList.poll();
			SearchGraphNode caller = BFScaller.poll();
			float newCost=costList.poll();
			
			if (goals.contains(curr))
			{
//				System.out.println("BFS found it");
				
				if (newCost<curr.cost)
				{
					curr.cost=newCost;
					curr.caller=caller;
				}
				
				//finish the queue with no more children added
				while (!searchList.isEmpty()) {
					SearchGraphNode lastCurr = searchList.poll();
					SearchGraphNode lastCaller=BFScaller.poll();
					float lastNewCost = costList.poll();
					
					if ( lastCurr==curr && lastNewCost<curr.cost)
					{
						System.out.println("another path to goal");
						curr.cost=lastNewCost;
						curr.caller=lastCaller;
					}
				}
				
				break;
			}
			
			if (newCost<curr.cost)
			{
				curr.cost=newCost;
				curr.caller=caller;
				//in the single goal case, if path reaches the goal
				//we just need to finish the remaining queue
				
				
				for (SearchGraphNode e: curr.neighbors)
				{
					searchList.add(e);
					BFScaller.add(curr);
					costList.add((float) (newCost+1.0));
				}
			}
			

			

			
		}
		
		return false;
	}
	
	public void printBFSPath(int ... num)
	{
		
		int mapWidth = maze[0].length;
		int mapHeight= maze.length;
		
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				if (goals.contains(nodeMap[i+1][j+1]))
				{
					if (num.length==0)
					{
						System.out.print(".");
					}
					else
					{
						System.out.print(num[0]%10);
					}
				}
				else if (nodeMap[i+1][j+1]==root)
				{
					if (num.length==0)
					{
						System.out.print("P");
					}
					else
					{
						System.out.print((num[0]-1)%10);
					}
				}
				else if (commonSolution.contains(nodeMap[i+1][j+1]))
				{
					System.out.print(",");
				}
				else
				{
					if (maze[i][j] == 'P') {
						System.out.print(" ");
					} else {
						System.out.print(maze[i][j]);
					}
				}
			}
			System.out.println();
		}
		
		commonSolution.clear();
	}
	
	/**the cost in the function is the heuristic function value
	 * @throws Exception
	 */
	public void GreedyBestFirstSearch() throws Exception
	{
		
		pathCost=0;
		nodesExpanded=0;
		maxDepth=0;//should be path cost
		maxFrontier=0;
		
		System.out.println("GreedyBestFirstSearch:");
		Comparator<SearchGraphNode> comparator = new SearchGraphNodeComparator();
        PriorityQueue<SearchGraphNode> queue =  new PriorityQueue<SearchGraphNode>(10, comparator);
        
        root.cost=costFunction(root);
        root.caller=null;
        queue.add(root);
        

        while(!queue.isEmpty())
        {
        	
        	if (queue.size()>maxFrontier)
			{
				maxFrontier=queue.size();
			}
        	nodesExpanded++;
        	
        	
        	SearchGraphNode curr = queue.poll();
        	float currCost = curr.cost;
//        	System.out.format("the current cost is: %.2f\n", currCost);
        	
        	//if this is the goal, finish the rest of the queue
        	if (goals.contains(curr) )
        	{
//        		System.out.println("found it!");
        		while(!queue.isEmpty())
        		{
                	SearchGraphNode newCurr = queue.poll();
                	float newCurrCost = newCurr.cost;
                	
                	if (newCurrCost<currCost)
                	{
                		curr.cost=newCurrCost;
                		curr.caller=newCurr;
                	}
        		}
        		
        		break;
        	}

        	if (goals.size()!=1)
        	{
        		throw new Exception("# of goals is not 1");
        	}
        	
        	
			for (SearchGraphNode e : curr.neighbors) {
				
				if (e.cost > (float) (costFunction(e))) {
					e.cost = (float) (costFunction(e));
					queue.add(e);
					e.caller=curr;
				}

			}
        }
        
        Set<SearchGraphNode> solution = new HashSet<SearchGraphNode>();
        
		for (SearchGraphNode e : goals) {
			SearchGraphNode temp = e;
			while(temp!=null)
			{
				temp = temp.caller;
				solution.add(temp);
				pathCost+=1.0;
			}

		}
		System.out.format("pathCost:%.2f nodesExpanded:%d maxDepth:%d maxFrontier:%d\n",pathCost,nodesExpanded,(int) pathCost,maxFrontier);
		
		commonSolution = solution;
		
		
//		printGreedyBestFirstSearch(solution);
		
	}
	
	public void printGreedyBestFirstSearch(int ... num)
	{
		
		int mapWidth = maze[0].length;
		int mapHeight= maze.length;
		
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				if (goals.contains(nodeMap[i+1][j+1]))
				{
					if (num.length==0)
					{
						System.out.print(".");
					}
					else
					{
						System.out.print(num[0]%10);
					}
				}
				else if (nodeMap[i+1][j+1]==root)
				{
					if (num.length==0)
					{
						System.out.print("P");
					}
					else
					{
						System.out.print((num[0]-1)%10);
					}
				}
				else if (commonSolution.contains(nodeMap[i+1][j+1]))
				{
					System.out.print(",");
				}
				else
				{
					if (maze[i][j] == 'P') {
						System.out.print(" ");
					} else {
						System.out.print(maze[i][j]);
					}
				}
			}
			System.out.println();
		}
		
		commonSolution.clear();
	}
	

	
	private class SearchGraphNodeComparator implements Comparator<SearchGraphNode>
	{
	    @Override
	    public int compare(SearchGraphNode x, SearchGraphNode y)
	    {
//	    	System.out.println("comparator invoked");
	        // Assume neither string is null. Real code should
	        // probably be more robust
	        if (x.cost < y.cost)
	        {
	            return -1;
	        }
	        if (x.cost > y.cost)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}
	
	
	public void AStar() throws Exception
	{
		
		pathCost=0;
		nodesExpanded=0;
		maxDepth=0;//should be path cost
		maxFrontier=0;
		
		System.out.println("Astar:");
		Comparator<SearchGraphNode> comparator = new SearchGraphNodeComparator();
        PriorityQueue<SearchGraphNode> queue =  new PriorityQueue<SearchGraphNode>(10, comparator);
//        PriorityQueue<SearchGraphNode> callerQ =  new PriorityQueue<SearchGraphNode>(10, comparator);
        
        root.cost=0;
        root.caller=null;
        queue.add(root);
        

        while(!queue.isEmpty())
        {
			nodesExpanded++;
			if (queue.size() > maxFrontier) {
				maxFrontier = queue.size();
			}
        	
        	
        	SearchGraphNode curr = queue.poll();
        	float currCost = curr.cost;
//        	System.out.format("the current cost is: %.2f\n", currCost);
        	
        	//if this is the goal, finish the rest of the queue
        	if (goals.contains(curr) )
        	{
//        		System.out.println("found it!");
        		while(!queue.isEmpty())
        		{
                	SearchGraphNode newCurr = queue.poll();
                	float newCurrCost = newCurr.cost;
                	
                	if (newCurrCost<currCost)
                	{
                		curr.cost=newCurrCost;
                		curr.caller=newCurr;
                	}
        		}
        		
        		break;
        	}

        	if (goals.size()!=1)
        	{
        		throw new Exception(
    					"# of goals is not 1");
        	}
        	
        	
			for (SearchGraphNode e : curr.neighbors) {
				
	        	if (goals.size()!=1)
	        	{
	        		throw new Exception("# of goals is not 1");
	        	}
	        	
				if (e.cost > (float) (currCost + 1.0 + costFunction(e))) {
					e.cost = (float) (currCost + 1.0 + costFunction(e));
					queue.add(e);
					e.caller=curr;
				}

			}
        }
        
        Set<SearchGraphNode> solution = new HashSet<SearchGraphNode>();
        
		for (SearchGraphNode e : goals) {
			SearchGraphNode temp = e;
			while(temp!=null)
			{
				temp = temp.caller;
				solution.add(temp);
				pathCost+=1.0;
			}

		}
		System.out.format("pathCost:%.2f nodesExpanded:%d maxDepth:%d maxFrontier:%d\n",pathCost,nodesExpanded,(int) pathCost,maxFrontier);
//		printAStarSearch(solution);
	
		commonSolution=solution;

//        while (queue.size() != 0)
//        {
//            System.out.println(queue.remove());
//        }
		
	}
	
	private float costFunction(SearchGraphNode x)
	{
		Iterator<SearchGraphNode> it = goals.iterator();
		SearchGraphNode g = it.next();
		
		return (float)(Math.abs(x.coord.row-g.coord.row) + Math.abs(x.coord.col-g.coord.col));
		
		
	}
	
	public void printAStarSearch(int ... num)
	{
		
		int mapWidth = maze[0].length;
		int mapHeight= maze.length;
		
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				if (goals.contains(nodeMap[i+1][j+1]))
				{
					if (num.length==0)
					{
						System.out.print(".");
					}
					else
					{
						System.out.print(num[0]%10);
					}
				}
				else if (nodeMap[i+1][j+1]==root)
				{
					if (num.length==0)
					{
						System.out.print("P");
					}
					else
					{
						System.out.print((num[0]-1)%10);
					}
				}
				else if (commonSolution.contains(nodeMap[i+1][j+1]))
				{
					System.out.print(",");
				}
				else
				{
					if (maze[i][j] == 'P') {
						System.out.print(" ");
					} else {
						System.out.print(maze[i][j]);
					}
				}
			}
			System.out.println();
		}
		
		commonSolution.clear();
	}
	
	public void UniformCostSearch(float powerSign) throws Exception
	{
		
		pathCost=0;
		nodesExpanded=0;
		maxDepth=0;
		maxFrontier=0;
		
		System.out.println("UniformCostSearch:");
		Comparator<SearchGraphNode> comparator = new SearchGraphNodeComparator();
        PriorityQueue<SearchGraphNode> queue =  new PriorityQueue<SearchGraphNode>(10, comparator);
//        PriorityQueue<SearchGraphNode> callerQ =  new PriorityQueue<SearchGraphNode>(10, comparator);
        
        root.cost=0;
        root.caller=null;
        queue.add(root);
        

        while(!queue.isEmpty())
        {
        	nodesExpanded++;
			if (queue.size() > maxFrontier) {
				maxFrontier = queue.size();
			}
			
			
        	SearchGraphNode curr = queue.poll();
        	float currCost = curr.cost;
//        	System.out.format("the current cost is: %.2f\n", currCost);
        	
        	//if this is the goal, finish the rest of the queue
        	if (goals.contains(curr) )
        	{
//        		System.out.println("found it!");
        		while(!queue.isEmpty())
        		{
                	SearchGraphNode newCurr = queue.poll();
                	float newCurrCost = newCurr.cost;
                	
                	if (newCurrCost<currCost)
                	{
                		curr.cost=newCurrCost;
                		curr.caller=newCurr;
                	}
        		}
        		
        		pathCost = curr.cost;
        		break;
        	}

        	if (goals.size()!=1)
        	{
        		throw new Exception(
    					"# of goals is not 1");
        	}
        	
        	
			for (SearchGraphNode e : curr.neighbors) {
				
				if (e.cost > (float) (currCost + Math.pow(2, powerSign*e.coord.col))) {
					e.cost = (float) (currCost + Math.pow(2, powerSign*e.coord.col));
					queue.add(e);
					e.caller=curr;
				}

			}
        }
        
        Set<SearchGraphNode> solution = new HashSet<SearchGraphNode>();
        
		for (SearchGraphNode e : goals) {
			SearchGraphNode temp = e;
			while(temp!=null)
			{
				temp = temp.caller;
				solution.add(temp);
				maxDepth++;
			}

		}
		System.out.format("pathCost:%.6f nodesExpanded:%d maxDepth:%d maxFrontier:%d\n",pathCost,nodesExpanded,maxDepth,maxFrontier);
		printUniformCostSearch(solution);
	

//        while (queue.size() != 0)
//        {
//            System.out.println(queue.remove());
//        }
		
	}
	
	public void printUniformCostSearch(Set<SearchGraphNode> solution)
	{
		
		int mapWidth = maze[0].length;
		int mapHeight= maze.length;
		
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				if (nodeMap[i+1][j+1]==root)
				{
					System.out.print("P");
				}
				else if (solution.contains(nodeMap[i+1][j+1]))
				{
					System.out.print(",");
				}
				else
				{
					System.out.print(maze[i][j]);
				}
			}
			System.out.println();
		}
	}



	public void BFS_Cleanup() {
		// TODO Auto-generated method stub
		
	}



	public void resetRoot() {
		// TODO Auto-generated method stub
		root=originalRoot;
	}

}
