package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
/*read text file line by line
text should be in the following syntax:
oval/rect xPos yPos width height (space)-------the line must end with a space
other shapes? that's a lot of workload
*/
public class TxtToMap {
	String str = "";
	int substring;
	int[] numbers = new int[4];
	public static Stack<Obstacle> obs = new Stack<Obstacle>();
	public TxtToMap(String location) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(location));
		while((str = br.readLine())!=null){
			if(str.substring(0,4).equals("oval")){
				str = str.substring(5);
				getNumber(str, 0);
				obs.push(new Obstacle("oval", numbers[0], numbers[1], numbers[2], numbers[3]));
			}
			if(str.substring(0,4).equals("rect")){
				str = str.substring(5);
				getNumber(str, 0);
				obs.push(new Obstacle("rect", numbers[0], numbers[1], numbers[2], numbers[3]));
			}
		}
	}
	public void getNumber(String in, int index){
		numbers[index] = Integer.parseInt(in.substring(0, in.indexOf(" ")));
		index += 1;
		if(index<4){
			getNumber(in.substring(in.indexOf(" ")+1), index);
		}
	}
}
