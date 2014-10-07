package hwk1;

import java.io.*;
import java.util.*;

public class EasyProblemFromRujiaLiu {

	/**
	 * @param args
	 * @throws IOException 
	 */
	static List<Integer> update(List<Integer> list, int val)
	{
		list.add(val);
		return list;
	}
	
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		Map<String,ArrayList<Integer>> hash = new HashMap<String,ArrayList<Integer>>();
		while(((line=in.readLine())!= null))
		{
			StringTokenizer param = new StringTokenizer(line);
			int arrsize = Integer.parseInt(param.nextToken());
			int cases = Integer.parseInt(param.nextToken());
			StringTokenizer array = new StringTokenizer(in.readLine());
			for(int i=1; i<=arrsize; i++)
			{
				String key = array.nextToken();
				ArrayList<Integer> AL = hash.get(key);
				if(AL == null)
				{
					AL = new ArrayList<Integer>();
					hash.put(key,AL);
				}
				AL.add(i);
			}
	
			for(int j=0; j<cases; j++)
			{
				StringTokenizer st = new StringTokenizer(in.readLine());
				int occurence = Integer.parseInt(st.nextToken());
				String key = st.nextToken();
				if(hash.containsKey(key))
				{
					if((occurence <= hash.get(key).size()))
					{
						sb.append(hash.get(key).get(occurence-1));
					}
					else
					{
						sb.append('0');
					}
					sb.append('\n');
				}
				else
				{
					sb.append("0\n");
				}
			}
			hash.clear();
		}
		System.out.print(sb);
	}
}