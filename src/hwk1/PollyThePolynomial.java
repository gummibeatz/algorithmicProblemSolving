package hwk1;

import java.io.*;
import java.util.*;


public class PollyThePolynomial {
	//Polly the polynomial
	/**
	 * @param args
	 */
	static int polyEval(ArrayList<Integer> al, int num)
	{
		int ans=0;
		for(int coef : al)
		{
			ans = ans*num+coef;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<Integer> al = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		while((line = in.readLine()) != null)
		{
			StringTokenizer st1 = new StringTokenizer(line);
			while(st1.hasMoreTokens())
			{
				al.add(Integer.parseInt(st1.nextToken()));
			}					
			StringTokenizer st2 = new StringTokenizer(in.readLine());
			while(st2.hasMoreTokens())
			{
				sb.append(polyEval(al,Integer.parseInt(st2.nextToken())));
				sb.append(' ');
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append('\n');
			al.clear();
		}
		System.out.print(sb);
	}

}