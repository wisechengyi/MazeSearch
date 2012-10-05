package graphLib;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class mazeIO {

	
	
    /**
     * @param filename
     * @return
     * @throws IOException
     * http://stackoverflow.com/questions/285712/java-reading-a-file-into-an-array
     */
    public String[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }
    
    /**
     * @param array of strings
     * @return the 2D array of characters
     */
    public char[][] convertStringto2DArray(String[] input)
    {
    	int y = input.length;
    	int x = input[0].length();
    	
//    	System.out.println("x:" +x +" y:" + y);
    	
    	char[][] maze = new char[y][x];
    	
    	for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				maze[i][j]=input[i].charAt(j);
			}
		}
    	
    	return maze;
    }
}
