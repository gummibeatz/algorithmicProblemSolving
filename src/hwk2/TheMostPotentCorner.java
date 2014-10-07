package hwk2;

import java.io.*;
import java.util.*;

public class TheMostPotentCorner {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		Map<Integer, Integer> potency = new HashMap<Integer, Integer>();
		while(((line=in.readLine())!=null))
		{
			map.clear();
			StringTokenizer st = new StringTokenizer(line);
			int numbits = Integer.parseInt(st.nextToken());
			int MASK = (int)Math.pow(2, numbits);
			for(int i =0 ; i<MASK; i++ )
			{
				StringTokenizer weights = new StringTokenizer(in.readLine());
				map.put(i,Integer.parseInt(weights.nextToken()));
			}
			//System.out.println(MASK);
			Integer max = 0;
			for(int i = 0; i<MASK; i++ )
			{
				int val = 0;
				int bits =i;
				for(int j = 0; j<numbits; j++)
				{
					bits = (bits^(1<<j));
					val += map.get(bits);
					bits = (bits^(1<<j));
				}
				potency.put(i, val);
			}
			for(int i =0; i <MASK; i++)
			{
				int bits =i;
				for(int j =0;j <numbits; j++)
				{
					
					bits = (bits^(1<<j));
					if((potency.get(bits)+potency.get(i))>max)
					{
						max = potency.get(bits)+potency.get(i);
					}
					bits = bits^(1<<j);
				}
			}
			sb.append(max.toString());
			sb.append('\n');
		}
		System.out.print(sb);
	}

}