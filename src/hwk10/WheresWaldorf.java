package hwk10;

import java.io.*;
import java.util.*;

public class WheresWaldorf
{
	final static int MAXROW = 50;
	final static int MAXCOL = 50;
	final static int FIRSTLETTER = 0;
	final static char[][] board = new char[MAXROW][MAXCOL];

	public static boolean canHorizRight(int c, int n, String word)
	{return word.length()<=n-c;}
	public static boolean canHorizLeft(int c, String word)
	{return word.length()-1<=c;}
	public static boolean canVertDown(int r, int m, String word)
	{return word.length()<=m-r;}
	public static boolean canVertUp(int r, String word)
	{return word.length()-1<=r;}
	public static boolean canDiagDR(int r, int c, int m, int n, String word)
	{return canHorizRight(c,n,word)&&canVertDown(r,m,word);}
	public static boolean canDiagUR(int r, int c, int m, int n, String word)
	{return canHorizRight(c,n,word)&&canVertUp(r,word);}
	public static boolean canDiagDL(int r, int c, int m, int n, String word)
	{return canHorizLeft(c,word)&&canVertDown(r,m,word);}
	public static boolean canDiagUL(int r, int c, int m, int n, String word)
	{return canHorizLeft(c,word)&&canVertUp(r,word);}
	
	public static boolean searchDiagDR(int r, int c, int m, int n, String word, char[][] board)
	{
		boolean found = false;
		int ct;
		if(canDiagDR(r,c,m,n,word))
		{
			ct =0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r+i][c+i]==word.charAt(i)) ct++; 
			}
			if(ct==word.length()) found = true;
		}
		if(canDiagUL(r,c,m,n,word))
		{
			ct =0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r-i][c-i]==word.charAt(i)) ct++;
			}
			if(ct==word.length()) found = true;
		}
		return found;
	}
	public static boolean searchDiagUR(int r, int c, int m, int n, String word, char[][] board)
	{
		boolean found = false;
		int ct;
		if(canDiagUR(r,c,m,n,word))
		{
			ct=0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r-i][c+i]==word.charAt(i)) ct++;
			}
			if(ct==word.length()) found = true;
		}
		if(canDiagDL(r,c,m,n,word))
		{
			ct =0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r+i][c-i]==word.charAt(i)) ct++;
			}
			if(ct==word.length()) found = true;
		}
		return found;
	}
	
	public static boolean searchHoriz(int r, int c, int m, int n, String word, char[][] board)
	{
		boolean found = false;
		int ct;
		if(canHorizRight(c,n,word))
		{
			ct =0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r][c+i]==word.charAt(i)) ct++;
			}
			if(ct==word.length()) found = true;
		}
		if(canHorizLeft(c,word))
		{
			ct =0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r][c-i]==word.charAt(i)) ct++;
			}
			if(ct == word.length()) found = true;
		}
		return found;
	}
	
	public static boolean searchVert(int r, int c, int m, int n, String word, char[][] board)
	{
		int ct;
		boolean found = false;
		if(canVertDown(r,m,word))
		{
			ct=0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r+i][c]==word.charAt(i)) ct++;
			}
			if(ct == word.length()) found = true;
		}
		if(canVertUp(r,word))
		{
			ct=0;
			for(int i=0; i<word.length(); i++)
			{
				if(board[r-i][c]==word.charAt(i)) ct++;	
			}
			if(ct == word.length()) found = true;
		}
		return found;
	}
	
	public static void search(String word, char[][]board, int m, int n, StringBuilder sb)
	{
		boolean flag = false;
		for(int r=0; r<m; r++)
		{
			for(int c=0; c<n; c++)
			{
				if((board[r][c] == word.charAt(FIRSTLETTER)) &&
					(searchDiagDR(r,c,m,n,word,board)||searchDiagUR(r,c,m,n,word,board)
					||searchHoriz(r,c,m,n,word,board)||searchVert(r,c,m,n,word,board)))
					{
						sb.append(r+1).append(" ").append(c+1).append("\n");
						flag = true;
						break;
					}
			}
			
			if(flag) break;
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		StringTokenizer st = new StringTokenizer(in.readLine());
		int tests = Integer.parseInt(st.nextToken());
		for(int i=0; i<tests; i++)
		{
			in.readLine();
			st = new StringTokenizer(in.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			for(int r=0; r<m; r++)
			{
				st = new StringTokenizer(in.readLine());
				line = st.nextToken().toLowerCase();
				for(int c=0; c<n; c++)
				{
					board[r][c] = line.charAt(c);
				}
			}
			st = new StringTokenizer(in.readLine());
			int k = Integer.parseInt(st.nextToken());
			for(int j=0; j<k; j++)
			{
				st = new StringTokenizer(in.readLine());
				line = st.nextToken().toLowerCase();
				search(line,board,m,n,sb);
			}
			if(i!=tests-1) sb.append("\n");
		}
		System.out.print(sb);

	}

}