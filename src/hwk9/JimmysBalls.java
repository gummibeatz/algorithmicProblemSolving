package hwk9;

import java.util.*;
import java.io.*;

public class JimmysBalls
{

	public static int twoBall(int totalballs, int redBall)
	{
		int minBlueBall = redBall+1;
		int maxGreenBall = totalballs - minBlueBall;
		return (int)Math.ceil((maxGreenBall-minBlueBall)/2.0);
	}
	public static long threeBall(int totalballs)
	{
		long count = 0;
		for(int redBall =1; redBall*3+3<=totalballs; redBall++)
		{
			count+=twoBall(totalballs-redBall,redBall);
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		String line;
		int count =0;
		while((line = in.readLine())!=null)
		{
			if (line.trim().length() == 0) continue;
			count++;
			StringTokenizer st = new StringTokenizer(line);
			int balls = Integer.parseInt(st.nextToken());
			if(balls ==0) break;
			sb.append("Case ").append(count).append(": ");
			sb.append(threeBall(balls)).append("\n");
		}
		System.out.print(sb);
	}

}