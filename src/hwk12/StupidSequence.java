package hwk12;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class StupidSequence {
	
	final static int setSize = 1500; //change to 1500 later
	final static int MOD = 1001;

	static BigInteger[] xPow(BigInteger x)
	{
		BigInteger[] xpow = new BigInteger[7];
		xpow[0] = new BigInteger("1");
		for(int i=1; i<7; i++)
		{
			xpow[i] = xpow[i-1].multiply(x);
		}
		return xpow;
	}
	
	static boolean check(long[] solutions, long[] a)
	{
		BigInteger[] xpow = new BigInteger[7];
		xpow[0] = BigInteger.valueOf(1);
		for(int i=0; i<setSize; i++)
		{
			xpow = xPow(BigInteger.valueOf(i+1));
			if(solutions[i]!=(a[0]
					+xpow[1].longValue()*a[1]
					+xpow[2].longValue()*a[2]
					+xpow[3].longValue()*a[3]
					+xpow[4].longValue()*a[4]
					+xpow[5].longValue()*a[5]
					+xpow[6].longValue()*a[6]))
			{
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		String line;
		long[] solutions = new long[setSize];
		while((line=in.readLine())!=null && !line.equals("yay"))
		{
			if(line.trim().length()==0) continue;
			
			StringTokenizer st = new StringTokenizer(line);
			int sets = Integer.parseInt(st.nextToken());
			for(int i=0; i<sets; i++)
			{
				if(first) {first = false;}
				else {in.readLine();}
				for(int j=0; j<setSize; j++)
				{
					st = new StringTokenizer(in.readLine());
					solutions[j] = Long.decode(st.nextToken());
				}
				BigInteger[] xpow = xPow(BigInteger.valueOf(MOD));
				long[] a = new long[7];
				//since a is bounded by 1000, mod f(1001) by 1001
				a[0] = solutions[1000]%MOD;
				if(a[0]<0){sb.append("This is a smart sequence!\n"); break;}
				a[1] = ((solutions[1000]-a[0])/xpow[1].longValue())%MOD;
				if(a[1]<0){sb.append("This is a smart sequence!\n"); break;}
				long a1x = a[1]*xpow[1].longValue();
				a[2] = ((solutions[1000]- a[0] - a1x)
						/(xpow[2]).longValue())%MOD;
				if(a[2]<0){sb.append("This is a smart sequence!\n"); break;}
				long a2x = a[2]*xpow[2].longValue();
				a[3] = ((solutions[1000]- a[0] - a1x - a2x)
						/(xpow[3]).longValue())%MOD;
				if(a[3]<0){sb.append("This is a smart sequence!\n"); break;}
				long a3x = a[3]*xpow[3].longValue();
				a[4] = ((solutions[1000]- a[0] - a1x - a2x - a3x)
						/(xpow[4]).longValue())%MOD;
				if(a[4]<0){sb.append("This is a smart sequence!\n"); break;}
				long a4x = a[4]*xpow[4].longValue();
				a[5] = ((solutions[1000]- a[0] - a1x - a2x - a3x - a4x)
						/(xpow[5]).longValue())%MOD;
				if(a[5]<0){sb.append("This is a smart sequence!\n"); break;}
				long a5x = a[5]*xpow[5].longValue();
				a[6] = ((solutions[1000]- a[0] - a1x - a2x - a3x - a4x - a5x)
						/(xpow[6]).longValue())%MOD;
				if(a[6]<0){sb.append("This is a smart sequence!\n"); break;}
				if(check(solutions,a)){
					sb.append(a[0]).append(" ").append(a[1]).append(" ").append(a[2]).append(" ")
					   .append(a[3]).append(" ").append(a[4]).append(" ").append(a[5]).append(" ")
					   .append(a[6]).append("\n");
				}
				else {sb.append("This is a smart sequence!\n");}
			}	
		}
		System.out.print(sb);
		
	}

}