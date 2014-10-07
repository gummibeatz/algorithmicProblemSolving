package hwk11;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class SunnyMountains
{
	static final double EPS = 1e-9;
	
//	public static class Pair<X,Y> implements Comparable<Pair<X,Y>>
//	{
//		private int X;
//		private int Y;
//
//		public Pair(int X, int Y)
//		{
//			this.X = X;
//			this.Y = Y;
//		}
//		
//	   public void setX(int X) {this.X = X;}
//	   public void setY(int Y) {this.Y = Y;}
//	   public int getX() {return X;}
//	   public int getY() {return Y;}	
//	   
//	   public int compareTo(Pair<X,Y> p)
//	   {
//		   if(getX() == getX()) return 0;
//		   if(getX()>p.getX()) return 1;
//		   else return -1;
//	   }
//	}
	
	static class Point implements Comparable<Point>
	{
		public double x, y;
		public Point(double x, double y) { this.x = x; this.y = y; }
		public String toString() { return String.format("(%.03f,%.03f)", x,y); }
	    public int compareTo(Point p)
	    {
		   if(this.x == p.x) return 0;
		   if(this.x> p.x) return 1;
		   else return -1;
	    }
	}	
	/** Computes the unsigned angle abc */
	static double angle(Point a, Point b, Point c)
	{
		double d1 = dist(a,b), d2 = dist(b,c);
		if (d1 < EPS || d2 < EPS) return 0;
		return Math.acos( ((a.x-b.x)*(c.x-b.x)+(a.y-b.y)*(c.y-b.y))/(d1*d2) );
	}
	static double dist(Point a, Point b) { return Math.hypot(a.x-b.x,a.y-b.y); }
	
	public static double solve(ArrayList<Point> coord)
	{
		double length =0;
		double highest = 0;
		Point prevP = coord.get(coord.size()-1);
		for(int i=coord.size()-2; i>-1; i--)
		{
			Point currentP = coord.get(i);
			if(coord.get(i).y>highest)
			{
				length +=calcLength(currentP,prevP, highest);
				highest = coord.get(i).y;
			}
			prevP = currentP;
		}
		return length;
	}
	
	public static double calcLength(Point currentP, Point prevP, double highest)
	{
		Point c = new Point(currentP.x,0);
		double theta = angle(c,currentP,prevP);
		double yLen = currentP.y - highest;
		return yLen/Math.cos(theta);
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		Integer C = Integer.parseInt(st.nextToken());
		NumberFormat formatter = new DecimalFormat("#0.00");
		ArrayList<Point> coord = new ArrayList<Point>();
		for(int i=0; i<C; i++)
		{
			coord.clear();
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			for (int j =0; j<N; j++)
			{
				st = new StringTokenizer(in.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				Point p = new Point(X,Y);
				coord.add(p);
			}
			Collections.sort(coord);
			double ans = solve(coord);
			sb.append(formatter.format(ans)).append("\n");
		}
		System.out.print(sb);

	}

}