package hwk11;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class CenterOfMasses
{

	static final double EPS = 1e-9;
	static class Point
	{
		public double x, y;
		public Point(double x, double y) { this.x = x; this.y = y; }
		public String toString() { return String.format("(%.03f,%.03f)", x,y); }
	}	
	
	static double angle(Point a, Point b, Point c)
	{
		double d1 = dist(a,b), d2 = dist(b,c);
		if (d1 < EPS || d2 < EPS) return 0;
		return Math.acos( ((a.x-b.x)*(c.x-b.x)+(a.y-b.y)*(c.y-b.y))/(d1*d2) );
	}
	
	static boolean inside(ArrayList<Point> al, Point p)
	{
		int n = al.size();
		if (n == 0) return false;
		double ret = 0;
		for (int i = 0; i < n; ++i)
		{
			int ii = (i+1)%n;	
			if (isCollin(al.get(i), p, al.get(ii))) return true;
			ret += (isLeft(al.get(i),p,al.get(ii))?-1:1) * angle(al.get(i),p,al.get(ii));
		}
		return Math.abs(ret)>EPS;
	}
	
	static double cross(Point a, Point b, Point c)
	{
		double y1 = b.y-a.y, y2 = c.y-b.y;
		double x1 = b.x-a.x, x2 = c.x-b.x;
		return x1*y2-x2*y1;
	}
	
	static double height(Point a, Point b, Point c) { return Math.abs(cross(a,b,c))/dist(a,b); }
	
	static double dist(Point a, Point b) { return Math.hypot(a.x-b.x,a.y-b.y); }
	
	static double area(ArrayList<Point> al)
	{
		int N = al.size();
		if (N <= 2) return 0;
		double ret = 0;
		for (int i = 0; i < N; ++i)
		{
			int ii = (i+1)%N;
			ret += al.get(i).x*al.get(ii).y-al.get(ii).x*al.get(i).y;
		}
		return ret/2;			
	}
	
	static double perimeter(ArrayList<Point> al)
	{
		int N = al.size();
		double ret = 0;
		for (int i = 0; i < N; ++i)
		{
			int ii = (i+1)%N;
			ret += dist(al.get(i),al.get(ii));
		}
		return ret;
	}
	
	static boolean isLeft(Point a, Point b, Point c) { return cross(a,b,c) >= 0; }
	
	static boolean isCollin(Point a, Point b, Point c) { return Math.abs(cross(a,b,c)) < EPS; }
	
	static class PivotComp implements Comparator<Point>
	{
		Point p;
		public PivotComp(Point p) { this.p = p; }
		@Override
		public int compare(Point o1, Point o2)
		{
			double cr = cross(o1,p,o2);
			if (Math.abs(cr) < EPS) 
			{
				double d1 = Math.hypot(o1.y-p.y, o1.x-p.x);
				double d2 = Math.hypot(o2.y-p.y, o2.x-p.x);
				return d1 < d2 ? -1 : d1 > d2 ? 1 : 0;
			}
			return cr >= 0 ? 1 : -1;
		}
	}
	
	static ArrayList<Point> chull(ArrayList<Point> al)
	{
		ArrayList<Point> stack = new ArrayList<Point>();
		if (al.size() <= 3)
		{
			stack.addAll(al);
			return stack;
		}
		int best = 0;
		for (int i = 1; i < al.size(); ++i)
			if (al.get(i).y < al.get(best).y || Math.abs(al.get(i).y-al.get(best).y) < EPS && al.get(i).x < al.get(best).x) best = i;
		Point tmp = al.get(0); al.set(0, al.get(best)); al.set(best, tmp);
		tmp = al.remove(0);
		Collections.sort(al,new PivotComp(tmp));
		al.add(0,tmp);
		stack.add(al.get(0)); stack.add(al.get(1));
		for (int i = 2; i < al.size(); ++i)
		{
			stack.add(al.get(i));
			while (stack.size() > 2)
			{
				int S = stack.size();
				if (isLeft(stack.get(S-3),stack.get(S-2),stack.get(S-1))) break;
				else stack.remove(S-2);
			}
		}
		return stack;
	}
	public static double centroidX(ArrayList<Point> chullp, double area)
	{
		double ans = 0;
		int N = chullp.size();
		for(int i=0; i<N; ++i)
		{
			int ii = (i+1)%N;
			ans += ((chullp.get(i).x+chullp.get(ii).x) * ((chullp.get(i).x*chullp.get(ii).y) - (chullp.get(ii).x*chullp.get(i).y)));
		}
		return ans/(6*area);
	}
	
	public static double centroidY(ArrayList<Point> chullp, double area)
	{
		double ans = 0;
		int N = chullp.size();
		for(int i=0; i<N; ++i)
		{
			int ii = (i+1)%N;
			ans += ((chullp.get(i).y+chullp.get(ii).y) * ((chullp.get(i).x*chullp.get(ii).y) - (chullp.get(ii).x*chullp.get(i).y)));
		}
		return ans/(6*area);
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		double x = 0,y;
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> chullp = new ArrayList<Point>();
		DecimalFormat df = new DecimalFormat("#0.000");
		while((line=in.readLine())!=null)
		{
			if (line.trim().length() == 0) continue;
			StringTokenizer st = new StringTokenizer(line);
			int m = Integer.parseInt(st.nextToken());
			if(m<3) break;
			points.clear();
			chullp.clear();
			int ct =0;
			while(ct<m*2)
			{
				while(!st.hasMoreTokens()) {st = new StringTokenizer(in.readLine());}
				//ypoint
				if(ct%2 != 0)
				{
					y = Double.parseDouble(st.nextToken());
					points.add(new Point(x,y));
				}
				//xpoint
				else
				{
					x = Double.parseDouble(st.nextToken());
				}
				ct++;
			}
			chullp = chull(points);
			sb.append(df.format(centroidX(chullp,area(chullp)))).append(" ").append(df.format(centroidY(chullp,area(chullp)))).append("\n");
		}
		System.out.print(sb);
	}
	

}