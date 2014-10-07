package hwk11;

import java.io.*;
import java.util.*;
	
	public class CollidingTraffic
	{
		private static final double EPS  		= 6.123233995736766E-16;
		private static final int    XPOS 		= 0;
		private static final int 	YPOS 		= 1;
		private static final int	HSPEED		= 2;
		private static final int 	VSPEED 		= 3;
		private static final int	MAXBOATS	= 1000;
		private static final int 	BOATFIELDS	= 4;
		private static final int 	LAKESIZE 	= 1000;
		
		public static double sqr(double d){return d*d;}
		
		public static double quadraticRoots(double a, double b, double c)
		{
			double radical = sqr(b) - 4*a*c;
			if(radical<0) return Double.POSITIVE_INFINITY;
			double root1 = (-1*b + Math.sqrt(radical))/(2*a);
			double root2 = (-1*b - Math.sqrt(radical))/(2*a);
			double min = Math.min(root1, root2);
			double max = Math.max(root1, root2);
	//		System.out.print(min); System.out.print(" "); System.out.println(max);
	//		System.out.println(a); System.out.println(b); System.out.println(c);
	//		System.out.println();
			if(min>0)   return min;
			if(max>0)  return max;
			return Double.POSITIVE_INFINITY;
		}
		
		public static double hSpeed(double theta, double speed) 
		{
			return Math.abs(Math.sin(Math.toRadians(theta)))>EPS ? speed*Math.sin(Math.toRadians(theta)):0;
		}
		
		public static double vSpeed(double theta, double speed) 
		{
			return Math.abs(Math.cos(Math.toRadians(theta)))>EPS ? speed*Math.cos(Math.toRadians(theta)):0;
		}
		
		public static boolean isInBounds(double t, double[][] boats, int i, int j)
		{
			double XPos1 = boats[i][XPOS] + boats[i][HSPEED]*t;
	 		double YPos1 = boats[i][YPOS] + boats[i][VSPEED]*t;
	 		double XPos2 = boats[j][XPOS] + boats[i][HSPEED]*t;
	 		double YPos2 = boats[j][YPOS] + boats[i][VSPEED]*t;
	 		if(Math.abs(XPos1)<LAKESIZE && Math.abs(XPos2)<LAKESIZE && Math.abs(YPos1)<LAKESIZE && Math.abs(YPos2)<LAKESIZE)
	 		{
	 			return true;
	 		}
	 		return false;
		}
		
		public static double solve(double[][] boats, double r, int n)
		{
			
			double minT = Double.POSITIVE_INFINITY;
			for(int i=0; i<n; i++)
			{
				for(int j=i+1; j<n; j++)
				{
					//check if initial points collide
					double dsqr = sqr(boats[i][XPOS]-boats[j][XPOS])
								+ sqr(boats[i][YPOS]-boats[j][YPOS]);
					if(sqr(r)>dsqr) {return 0;}
					
					//set up for quadratic eqn
					double a = sqr(boats[i][HSPEED]) + sqr(boats[j][HSPEED]) - 2*boats[i][HSPEED]*boats[j][HSPEED]
							 + sqr(boats[i][VSPEED]) + sqr(boats[j][VSPEED]) - 2*boats[i][VSPEED]*boats[j][VSPEED];
					double b = 2*(boats[j][XPOS]*boats[j][HSPEED] - boats[j][XPOS]*boats[i][HSPEED] - boats[i][XPOS]*boats[j][HSPEED] + boats[i][XPOS]*boats[i][HSPEED])
							 + 2*(boats[j][YPOS]*boats[j][VSPEED] - boats[j][YPOS]*boats[i][VSPEED] - boats[i][YPOS]*boats[j][VSPEED] + boats[i][YPOS]*boats[i][VSPEED]);				
					double c = sqr(boats[i][XPOS]) + sqr(boats[j][XPOS]) - 2*boats[i][XPOS]*boats[j][XPOS]
							 + sqr(boats[i][YPOS]) + sqr(boats[j][YPOS]) - 2*boats[i][YPOS]*boats[j][YPOS]
							 - sqr(r);
					double t = quadraticRoots(a,b,c);
					
	//				System.out.print(i); System.out.print(" -----------"); System.out.println(j); 
	//				System.out.println(a);
	//				System.out.println(b);
	//				System.out.println(c);
	//				System.out.println();
	//				System.out.print(boats[i][HSPEED]);System.out.print(" ");System.out.println(boats[i][VSPEED]);
	//				System.out.print(boats[j][HSPEED]);System.out.print(" ");System.out.println(boats[j][VSPEED]);
	//				System.out.println();
	//				System.out.print(boats[i][XPOS]); System.out.print(" "); System.out.println(boats[i][YPOS]);
	//				System.out.print(boats[j][XPOS]); System.out.print(" "); System.out.println(boats[j][YPOS]);
	//				System.out.println(t);
					
	//				check if t is out of bounds;
					if(t<minT) {minT = t;}
				}			
			}
			return minT;
		}
		
		public static void main(String[] args) throws IOException
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			double[][] boats = new double[MAXBOATS][BOATFIELDS]; 
			StringTokenizer st = new StringTokenizer(in.readLine());
			int c = Integer.parseInt(st.nextToken());
			for(int i=0; i<c; i++)
			{
				st = new StringTokenizer(in.readLine());
				int n = Integer.parseInt(st.nextToken());
				double r = Double.parseDouble(st.nextToken());
				for(int j=0; j<n; j++)
				{
					st = new StringTokenizer(in.readLine());
					boats[j][XPOS] = Double.parseDouble(st.nextToken());
					boats[j][YPOS] = Double.parseDouble(st.nextToken());
					double theta = Double.parseDouble(st.nextToken());
					double speed = Double.parseDouble(st.nextToken());
					boats[j][HSPEED] = hSpeed(theta,speed);
					boats[j][VSPEED] = vSpeed(theta,speed);
	
				}
				double ans = solve(boats,r,n);
				if(Double.isInfinite(ans)) sb.append("No collision.");
				else sb.append((int)Math.round(ans));
				sb.append("\n");
			}
			System.out.print(sb);
		}
	
	}