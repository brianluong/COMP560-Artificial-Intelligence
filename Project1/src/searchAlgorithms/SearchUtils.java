package searchAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchUtils {
	
	public static char[][] generate2DArrayMazeFromInput(String fileName) throws Exception {
		InputStream inputStream = SearchUtils.class.getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		String line = "";
		int width = -1;
		ArrayList<String> arrayList = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			
			// make sure every row is same length
			int currentWidth = line.length();
			if (width == -1) {
				width = currentWidth;
			} else if (currentWidth != width) {
				throw new IOException("Not all rows have same length");
			}
			arrayList.add(line);
		}
		
		int height = arrayList.size();
		char[][] maze = new char[height][width];
		for (int i = 0; i < height; i++) {
			String row = arrayList.get(i);
			for (int j = 0; j < width; j++) {
				maze[i][j] = row.charAt((char) j);
			}
		}
		
		return maze;
	}
	
	
}
