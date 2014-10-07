package hwk1;

import java.io.*;
import java.util.*;

public class ICanGuessTheDataStructure {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		PriorityQueue<Integer> pqueue = new PriorityQueue<Integer>(10, Collections.reverseOrder());
		int num;
		int stackb, queueb, pqueueb;
		while((line=in.readLine())!=null)
		{
			stackb = queueb = pqueueb=1;
			StringTokenizer st1 = new StringTokenizer(line);		
			int ops = Integer.parseInt(st1.nextToken());
			for(int i=0; i < ops; i++)
			{
				StringTokenizer st2 = new StringTokenizer(in.readLine());
				int action = Integer.parseInt(st2.nextToken());
				if(action == 1)
				{
					num = Integer.parseInt(st2.nextToken());
					stack.push(num);
					queue.add(num);
					pqueue.add(num);
				}
				else if((action ==2) && stack.isEmpty())
				{
					stackb = queueb = pqueueb =0;
				}
				else
				{
					num = Integer.parseInt(st2.nextToken());
					if(stack.pop() != num)
					{
						stackb=0;
					}
					if(queue.pop() != num)
					{
						queueb=0;
					}
					if(pqueue.poll() != num)
					{
						pqueueb=0;
					}
				}
			}
			if((queueb|stackb|pqueueb)==0)
			{
				sb.append("impossible\n");
			}
			else if((queueb&(~(pqueueb|stackb)))==1)
			{
				sb.append("queue\n");
			}
			else if((pqueueb&(~(queueb|stackb)))==1)
			{
				sb.append("priority queue\n");
			}
			else if((stackb&(~(pqueueb|queueb)))==1)
			{
				sb.append("stack\n");
			}
			else if(((stackb&pqueueb)|(pqueueb&queueb)|(stackb&queueb))==1)
			{
				sb.append("not sure\n");
			}
			stack.clear();
			queue.clear();
			pqueue.clear();
		}	
		System.out.print(sb);
	}
}

