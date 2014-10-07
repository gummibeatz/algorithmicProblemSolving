package hwk12;

import java.io.*;
import java.util.*;

public class ContemplationAlgebra
{
	
	
	  static int i, n, m, MAX_N = 2;
	  static long MOD;

	  static long[][] matMul(long[][] a, long[][] b) {    // O(n^3 ~> 1) as n=2
	    long[][] ans = new long[2][2]; int i, j, k;
	    for (i = 0; i < MAX_N; i++)
	      for (j = 0; j < b[0].length; j++)
	        for (ans[i][j] = k = 0; k < MAX_N; k++) {
	          ans[i][j] += (a[i][k]) * (b[k][j]);
	        }
	    return ans;
	  }

	  static long[][] matPow(long[][] base, int p) {   // O(n^3 log p ~> log p)
	    long[][] ans = new long[MAX_N][MAX_N]; int i, j;
	    for (i = 0; i < MAX_N; i++)
	      for (j = 0; j < MAX_N; j++)
	        ans[i][j] = (i == j ? 1 : 0);            // prepare identity matrix
	    while (p > 0) { // iterative version of Divide & Conquer exponentiation
	      if ((p & 1) == 1)           // check if p is odd (the last bit is on)
	        ans = matMul(ans, base);                              // update ans
	      base = matMul(base, base);                         // square the base
	      p >>= 1;                                             // divide p by 2
	    }
	    return ans;
	  }

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=in.readLine())!=null && line.length()!=0)
		{
			StringTokenizer st = new StringTokenizer(line);
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			if(p==0 && q==0 && !st.hasMoreTokens()) break;
			int n = Integer.parseInt(st.nextToken());
			long[][] ans = new long[MAX_N][MAX_N];
		    ans[0][0] = p;  ans[0][1] = -q;
		    ans[1][0] = 1;  ans[1][1] = 0;
			long[][] X = new long[MAX_N][1];
			X[0][0] = p;
			X[1][0] = 2;
		    ans = matPow(ans,n);
		    X = matMul(ans,X);
		    sb.append(X[1][0]).append("\n");

		}
		System.out.print(sb);
	}

}