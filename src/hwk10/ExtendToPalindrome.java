package hwk10;

import java.io.*;
import java.util.StringTokenizer;


public class ExtendToPalindrome 
{
	public static int[] buildKMPTable(String pattern)
	{
		int[] table = new int[pattern.length()+1];
		for (int i = 2; i < table.length; ++i)
		{
			int j = table[i-1];
			while (true) 
			{
				if (pattern.charAt(j) == pattern.charAt(i-1)) { table[i] = j+1; break;}
				else if (j == 0) break;
				else j = table[j];
			} 
		}
		return table;
	}
	/** Returns the final state when simulating the DFA built using pattern on the string text */
	public static int simulate(int[] table, String text, String pattern)
	{
		int state = 0;
		for (int i = 0; i < text.length(); ++i)
		{
			while (true)
			{
				if (text.charAt(i) == pattern.charAt(state)) { state++; break; }
				else if (state == 0) break;
				state = table[state];
			} 
			if (state == table.length -1) break;
		}
		return state;
	}
	
	public static String getPalindrome(String word)
	{
		String pattern = new StringBuilder(word).reverse().toString();
		String text = word;
		int[]table = buildKMPTable(pattern);
		int matchLength = simulate(table, text, pattern);
		int lengthToAdd = word.length() - matchLength;
		String strToAdd = new StringBuilder(word.substring(0,lengthToAdd)).reverse().toString();
		String palindrome = word.concat(strToAdd);
		return palindrome;
	}

		
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=in.readLine())!=null)
		{
			StringTokenizer st = new StringTokenizer(line);
			String word = st.nextToken();
			sb.append(getPalindrome(word)).append("\n");
		}
		System.out.print(sb);
		

	}

}