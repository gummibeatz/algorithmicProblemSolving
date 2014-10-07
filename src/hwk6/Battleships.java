package hwk6;

import java.io.*;
import java.util.*;

public class Battleships
{

	final static int VISITED = -2;
	final static int X = 1;
	final static int AT = 2;
	final static int UDLR = 4;
	final static int N = 6500;
	
	//return  0 if there is no ship. anything else means its a ship
	public static int isShip(ArrayList<ArrayList<Integer>>ships, int row, int col)
	{
		int flag = 0;
		if((ships.get(row).get(col) == X || ships.get(row).get(col) == AT))
		{
			
			if(ships.get(row).get(col) == X)
			{
				flag ++;
			}
			ships.get(row).set(col, VISITED);
			
			if( col == ships.size()-1)
			{
				if(row!=ships.size()-1)
				{
					flag += isShip(ships,row+1,col);
				}
			}
			else if(row == ships.size()-1)
			{
				if(col != ships.size()-1)
				{
					flag += isShip(ships,row,col+1);
				}
			}
			else{flag += isShip(ships,row+1, col) + isShip(ships,row, col+1);}
		}
		return flag;
	}

	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int cases = Integer.parseInt(st.nextToken());
		for(int i = 0; i <cases; i++)
		{
			st = new StringTokenizer(in.readLine());
			int graph_size =Integer.parseInt(st.nextToken());
			int count = 0;
			ArrayList<ArrayList<Integer>> ships = new ArrayList<ArrayList<Integer>>();
			for(int row =0; row <graph_size; row++)
			{
				st = new StringTokenizer(in.readLine());
				String r = st.nextToken();
				ArrayList<Integer> newRow = new ArrayList<Integer>();
				for(int col =0; col < graph_size; col++)	
				{
					char elem = r.charAt(col);
					if(elem == 'x') newRow.add(X);
					else if(elem == '@') newRow.add(AT);
					else  newRow.add(VISITED);
				}
				ships.add(newRow);
			}
			for(int row =0; row < graph_size; row++)
			{
				for(int col =0; col < graph_size; col++)
				{
					if(ships.get(row).get(col) != VISITED)
					{
						if (isShip(ships,row,col)!= 0) {count++;}
					}
				}
			}
			sb.append("Case ").append(i+1).append(": ").append(count).append("\n");
		}
		System.out.print(sb);
	}

}