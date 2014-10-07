package hwk2;

import java.util.*;
import java.io.*;
import java.math.*;

public class MaximumSubSequence {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<BigInteger> AL = new ArrayList<BigInteger>();
		BigInteger max =new BigInteger("-999999");
		final BigInteger end = new BigInteger("-999999");
		String line;
		while(((line = in.readLine())!=null))
		{
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens())
			{
				BigInteger num = new BigInteger(st.nextToken());
				if(!num.equals(end))
				{
					AL.add(num);
				}
				else
				{
					BigInteger current = new BigInteger("1");
					for(int i=0; i<AL.size();i++)
					{
						for(int j = i; j < AL.size();j++)
						{
							current = current.multiply(AL.get(j));
							if(current.compareTo(max) == 1)
							{
								max = current;
							}
						}
						current = BigInteger.ONE;
					}
					sb.append(max.toString());
					sb.append('\n');
					max = BigInteger.valueOf(-999999);
					AL.clear();
				}
			}
		}
		System.out.print(sb);
	}

}