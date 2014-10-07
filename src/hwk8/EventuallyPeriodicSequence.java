package hwk8;

import java.io.*;
import java.util.*;



public class EventuallyPeriodicSequence
{
	
	public static int solve(ArrayList<String> notation, int n, int N)
	{
		Stack<Integer> S = new Stack<Integer>();
		long num1;
		long num2;
		long result;
		for(int i =0; i<notation.size(); i++)
		{
			String op = (notation.get(i));
			if(op.equals("+"))
			{
				num1 = S.pop();
				num2 = S.pop();
				result = ((num1+num2)%N);
				S.push((int)result);
			}
			else if(op.equals("*"))
			{
				num1 = S.pop();
				num2 = S.pop();
				result = (num1*num2)%N;
				S.push((int)result);
			}
			else if(op.equals("%"))
			{
				num1 = S.pop();
				num2 = S.pop();
				result = (num2)%num1;
				S.push((int)result);
			}
			else if(op.equals("x"))
			{
				S.push(n);
			}
			else if(op.equals("N"))
			{
				S.push(N);
			}
			else
			{
				S.push(Integer.parseInt(op));
			}
			
		}
		return S.pop();
	}
	
	public static long seqCount(HashMap<Integer,Boolean> map, ArrayList<String> notation, int n, int N)
	{
		map.put(n, true);
		while(true)
		{
			n = solve(notation, n,N);
			if(map.get(n)!=null) break;
			else map.put(n, true);
		}
		long count=0;
		while(map.get(n))
		{
			map.put(n, false);
			n = solve(notation, n, N);
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String> notation = new ArrayList<String>();
		HashMap<Integer,Boolean> map = new HashMap<Integer, Boolean>();
		while((line=in.readLine())!=null)
		{
			if (line.trim().length() == 0) continue;
			notation.clear();
			map.clear();
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			if(N ==0) break;
			int n = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens())
			{
				String op = st.nextToken();
				notation.add(op);
				if(op.equals("%")) break;
			}
			sb.append(seqCount(map,notation,n,N)).append("\n");
		}
		System.out.print(sb);
	}

}