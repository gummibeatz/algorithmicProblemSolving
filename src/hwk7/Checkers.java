package hwk7;

import java.io.*;
import java.util.*;

public class Checkers 
{

	final static int FREE = 0;
	final static int BLACK = 1;
	final static int WHITE = 2;
	final static int MAXSIZE = 105;
	final static int MOD = 1000007;
	final static int[][] memo = new int[MAXSIZE][MAXSIZE]; 
	
	static int count(int row, int col, int[][] board)
	{
		if(col<0 || col>= board.length || row<0)   		{return 0;}
		else if(board[row][col]==BLACK)					{return 0;}
		else if(row==0)									{return 1;}
		else if(memo[row][col]!=0) 						{return memo[row][col];}
		else
		{
			//cases
			//go left diag
			if(col>0)
			{
			if(board[row-1][col-1]==BLACK){memo[row][col]+= count(row-2,col-2,board);}
			else if(board[row-1][col-1]==FREE){memo[row][col]+= count(row-1,col-1,board);}
			}
			//go right diag
			if(col<board.length-1)
			{
				if(board[row-1][col+1]==BLACK){memo[row][col]+= count(row-2,col+2,board);}
				else if(board[row-1][col+1] == FREE){memo[row][col]+=count(row-1,col+1,board);}
			}
						
			return memo[row][col]%MOD;
		
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int tests = Integer.parseInt(st.nextToken());
		for(int i =0; i<tests; i++)
		{
			for(int[] row: memo)
			{
				Arrays.fill(row, 0);
			}
			int white_row =0;
			int white_col =0;
			st = new StringTokenizer(in.readLine());
			int size = Integer.parseInt(st.nextToken());
			int[][] board = new int[size][size];
			for(int j=0; j<size; j++)
			{
				st = new StringTokenizer(in.readLine());
				String line = st.nextToken();
				for(int k=0; k<size; k++)
				{
					char space = line.charAt(k);
					if(space == '.'){board[j][k] = FREE;}
					else if(space == 'B'){board[j][k] = BLACK;}
					else if(space == 'W')
					{
						white_row  = j;
						white_col = k;
					}
				}
			}
			sb.append("Case ").append(i+1).append(": ");
			sb.append(count(white_row,white_col,board)).append("\n");
		}		
		System.out.print(sb);
	}

}