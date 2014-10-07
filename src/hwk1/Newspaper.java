package hwk1;

import java.io.*;
import java.util.*;


public class Newspaper {
	/**
	 * Newspaper
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in,"ISO-8859-1"));
		int cases = Integer.parseInt(new StringTokenizer(in.readLine()).nextToken());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<cases; i++)
		{
			int[] ascii = new int[256];
			int money = 0;
			int num2trans = Integer.parseInt(new StringTokenizer(in.readLine()).nextToken());
			for(int j =0; j<num2trans;j++)
			{	
				StringTokenizer translate = new StringTokenizer(in.readLine());
				char idx = translate.nextToken().charAt(0);
				ascii[idx] = Integer.parseInt(translate.nextToken());
			}
			int prgphlines = Integer.parseInt(new StringTokenizer(in.readLine()).nextToken());
			for(int k=0; k<prgphlines; k++)
			{
				StringTokenizer st = new StringTokenizer(in.readLine());
				while(st.hasMoreTokens())
				{
					String word = st.nextToken();
					for(int q =0; q<word.length(); q++)
					{
						if(ascii[word.charAt(q)]!=0)
						{
							money += ascii[word.charAt(q)];
						}
					}
				}
			}
			//could change to float then append. Decided this was faster. not sure?
			if((money/100)>0)
			{
				sb = sb.append(money);
				sb.insert(sb.length()-2, '.');
				sb = sb.append("$\n");
			}
			else if((money/10)>0)
			{
				sb = sb.append("0.");
				sb = sb.append(money);
				sb = sb.append("$\n");
			}
			else
			{
				sb = sb.append("0.0");
				sb = sb.append(money);
				sb = sb.append("$\n");
			}
		}
		System.out.print(sb);
		
	}

}