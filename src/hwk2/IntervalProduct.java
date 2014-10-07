package hwk2;

import java.io.*;
import java.util.*;

public class IntervalProduct {

	/**
	 * @param args
	 */
	//Assumes indices are 1-based
	static public class Fenwickcheckzero 
	{
		public int[] table;
		public Fenwickcheckzero(int maxN)
		{
			this.table = new int[maxN+1];
		}
		public int sumQuery(int k)
		{
			int ret = 0;
			while (k > 0)
			{
				ret += table[k];
				k &= k-1;
			}
			return ret;
		}
		public void adjust(int i, int adj)
		{
			while (i < table.length)
			{
				table[i] += adj;
				i += (i & (-i));
			}
		}
		public int sumQuery(int a, int b) { return sumQuery(b) - sumQuery(a-1); }
		public int getValue(int i) { return sumQuery(i,i); }
		//Assumes entries of list are non-negative (i.e., cumulative sums are increasing)
		//Returns first index whose cumulative sum is >= k
		//Returns -1 if all are less
		public int findFirst(int k)
		{
			int L = 1, R = table.length-1;
			while (R-L > 1)
			{
				int M = (R+L)/2;
				int val = sumQuery(M);
				if (val < k) L = M+1;
				else R = M;
			}
			int LVal = sumQuery(L);
			if (LVal >= k) return L;
			return R==L || sumQuery(R) < k ? -1 : R;
		}
	}
	
	static public class Fenwickmult 
	{
		public int[] table;
		public Fenwickmult(int maxN)
		{
			this.table = new int[maxN+1];
			Arrays.fill(table,1);
		}
		public int multQuery(int k)
		{
			int ret = 1;
			while (k > 0)
			{
				ret *= table[k];
				k &= k-1;
			}
			return ret;
		}
		public void adjust(int i, int adj)
		{
			while (i < table.length)
			{
				table[i] *= adj;
				i += (i & (-i));
			}
		}
		
		public int multQuery(int a, int b)
		{
			return multQuery(b) / multQuery(a-1); 	
		}
		
		public int getValue(int i) { return multQuery(i,i); }
		public int findFirst(int k)
		{
			int L = 1, R = table.length-1;
			while (R-L > 1)
			{
				int M = (R+L)/2;
				int val = multQuery(M);
				if (val < k) L = M+1;
				else R = M;
			}
			int LVal = multQuery(L);
			if (LVal >= k) return L;
			return R==L || multQuery(R) < k ? -1 : R;
		}
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while(((line = in.readLine())!= null))
		{
			StringTokenizer st = new StringTokenizer(line);
			int tablesize = Integer.parseInt(st.nextToken());
			int numcmds = Integer.parseInt(st.nextToken());
			StringTokenizer table_vals = new StringTokenizer(in.readLine());
			Fenwickmult fen = new Fenwickmult(tablesize);
			Fenwickcheckzero zero = new Fenwickcheckzero(tablesize);
			//if there is a zero, will put a 1 in that spot of the array
			for(int i=1; i<tablesize+1; i++ )
			{
				int num = Integer.parseInt(table_vals.nextToken());
				if(num ==0)
				{
					zero.adjust(i,1);
					fen.adjust(i,1);
				}
				else
				{
					if(num>0)
					{
						fen.adjust(i,1);
					}
					else
					{
						fen.adjust(i,-1);
					}
				}
			}
			for (int j =0; j<numcmds; j++)
			{
				StringTokenizer commands = new StringTokenizer(in.readLine());
				String cmd = commands.nextToken();
				if(cmd.equals("C"))
				{
					int I = Integer.parseInt(commands.nextToken());
					int V = Integer.parseInt(commands.nextToken());

					if(V==0)
					{
						zero.adjust(I,1);
					}
					else
					{
						if(zero.getValue(I)==1)
						{
							zero.adjust(I,-1);
						}
						if(V>0)
						{
							if(fen.getValue(I)<0)
							{
							fen.adjust(I, -1);
							}
						}
						else
						{
							if(fen.getValue(I)>0)
							{
								fen.adjust(I,-1);
							}
						}
					}
				}
				else
				{
					int begin = Integer.parseInt(commands.nextToken());
					int end  = Integer.parseInt(commands.nextToken());	
					if(zero.sumQuery(begin, end)>0)
					{
						sb.append('0');					
					}
					else
					{
						int val = fen.multQuery(begin, end);
						if(val > 0)
						{
							sb.append('+');
						}
						else
						{
							sb.append('-');
						}
					}
				}
			}
			sb.append('\n');
		}//end while
		System.out.print(sb);
	}
}