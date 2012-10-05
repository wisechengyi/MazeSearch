package graphLib;

import java.util.HashSet;
import java.util.Set;

public class Coord {
	
//	public boolean isVisited;
	
	public int row;
	public int col;

	public Coord(int input_row, int input_col) {
		row=input_row;
		col=input_col;
//		isVisited = false;
	}
	
	public Coord(Coord coord) {
		row=coord.row;
		col=coord.col;
//		isVisited = false;
	}
	
	public boolean equals(Object O)
	{
		System.out.println("coord equal invoked");
		
		
		if (!(O instanceof Coord))
		{
			return false;
		}
		
		
		Coord otherCoord = (Coord) O;
		
		if (otherCoord.row==row && otherCoord.col ==col )
		{
			return true;
		}
		
		return false;
	}
	
	public Coord[] getNeighborCoords()
	{
		Coord[] neighbors = new Coord[4];
		neighbors[0]= new Coord(row,col-1);
		neighbors[1]= new Coord(row,col+1);
		neighbors[2]= new Coord(row-1,col);
		neighbors[3]= new Coord(row+1,col);

		return neighbors;
	}
	

}
