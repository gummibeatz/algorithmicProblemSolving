package hwk2;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class OpenSource {

	static class ValThenKey<K extends Comparable<String>, V extends Comparable<Integer>> implements Comparator<Map.Entry<K,V>>
	{
		public int compare(Map.Entry<K,V> m , Map.Entry<K, V> n)
		{
			int valComp = n.getValue().compareTo((Integer) m.getValue());
			if(valComp!=0)
			{
				return valComp;
			}
			else
			{
				return m.getKey().compareTo((String) n.getKey());
			}
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		Map<String, HashSet<String>> projects = new HashMap<String, HashSet<String>>();
		Map<String,Integer> groupsize = new HashMap<String,Integer>();
		Set<String> blacklist = new HashSet<String>();
		String projname = null;
		while(true)
		{
			groupsize.clear();
			blacklist.clear();
			projects.clear();
			while(true)
			{
				line = in.readLine();
				String check;
				StringTokenizer s = new StringTokenizer(line);
				if((check=s.nextToken()).equals("1")||check.equals("0"))
				{
					break;
				}
				else
				{
					
					if(Character.isUpperCase(line.charAt(0)))
					{
						projname = line;
						groupsize.put(projname, 0);
					}
					else
					{
						HashSet<String> set = projects.get(projname);
						String student = check;
						if(set==null && !blacklist.contains(student))
						{
							set = new HashSet<String>();
							projects.put(projname, set );
						}
						for (Map.Entry<String, HashSet<String>> hash : projects.entrySet())
				        {
							if((hash.getKey()!= projname)&& (hash.getValue().contains(student)))
							{
								blacklist.add(student);
								String dup = hash.getKey();
								set = projects.get(dup);
								projects.put(dup, set);
								set.remove(student);
								set = projects.get(projname);
								projects.put(projname, set);
							}
				        }
						if(!blacklist.contains(student))
						{
							set.add(student);
						}
					}
				}	
			}
			if(line.equals("0"))
			{
				break;
			}
			for (Map.Entry<String, Integer> hash : groupsize.entrySet())
			{
				String key = hash.getKey(); 
				Integer setsize = 0;
				if(projects.containsKey(key))
				{
					setsize = projects.get(key).size();
				}
				groupsize.put(key, setsize);
			}
			List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(groupsize.entrySet());
			Collections.sort(list, new ValThenKey<String, Integer>());
			for (Entry<String, Integer> hash: list)
			{
				String key = hash.getKey(); 
				Integer value = hash.getValue(); 
				sb.append(key);
				sb.append(' ');
				sb.append(value);
				sb.append('\n');
			}

		}
		System.out.print(sb);
	}		
}



	