package hwk10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class PowerStrings 
{
	final static int FIRSTIDX = 0;
	
	public static boolean validLength(String line, int subStrLength)
	{return (line.length()%subStrLength)==0;}
	
	public static boolean isPowerString(String line, String subStr, int power)
	{
		for(int i=0; i<power; i++)
		{
			if(!line.substring(i*subStr.length(),(i+1)*subStr.length()).equals(subStr)) return false;
		}
		return true;
	}
	public static int solve(String line)
	{
		for(int subStrLength=1; subStrLength<=line.length(); subStrLength++)
		{
			if(validLength(line,subStrLength))
			{
//				System.out.println(subStrLength);
				int power = line.length()/subStrLength;
				String subStr = line.substring(FIRSTIDX,subStrLength);
//				System.out.println(subStr);
				if(isPowerString(line,subStr,power)) return power;
			}
		}
		System.out.println();
		return -1;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=in.readLine())!=null && !line.equals("."))
		{
			StringTokenizer st = new StringTokenizer(line);
			line = st.nextToken();
			sb.append(solve(line)).append("\n");
		}
		System.out.print(sb);
		
	}

}