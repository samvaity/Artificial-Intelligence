//package AI;

import java.util.ArrayList;
import java.util.HashMap;

public class DFS {

	boolean destFound;
	
	DFS()
	{
		destFound = false;
	}
	
	public boolean isDestFound()
	{
		return destFound;
	}
	
	public void dfsSearch(HashMap h, HashMap<String,Boolean> visited,String source, String destination, String path)
	{
		//int cost=0;
		SearchTechniques st = new SearchTechniques();
		
		//System.out.println(source);
			if(h.containsKey(source) && destination != null )
			{	
				visited.put(source, true);
				ArrayList neighbour = (ArrayList) h.get(source);
				
				for(int k=0;k<neighbour.size(); k++)
				{
					if( !(Boolean)visited.get(destination)) 
					{
						CityNode temp = (CityNode) neighbour.get(k);
						String tempCname = temp.getCname();
						
						if(tempCname.equals(destination))
						{
							   visited.put(tempCname, true);
							  
							   path=path+","+tempCname;
							  
							   destFound = true;
							   st.updatePath(path,h,destination);
								break;
						}
							
						if( !(Boolean)visited.get(temp.cname))
						{
							dfsSearch(h, visited, temp.cname ,destination,path+" "+tempCname);
							
						}
						
					}	// end of check for destination visit
				}
					
			}
		//System.out.println("before dfs exit");
	}
	
	
	
}
