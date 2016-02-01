import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

public class BFS {
	
	 boolean destFound;
	
	BFS()
	{
		destFound = false;
	}
	
	public boolean isDestFound()
	{
		return destFound;
	}
	public  void bfsSearch(HashMap<String, ArrayList> hm, String from, String to) {

		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();

		int pathCost = 0;

		/* For cost and path computation */
		HashMap<String, Integer> pathMap = new HashMap<String, Integer>();
		int i = 1;
		Iterator tempIt = hm.entrySet().iterator();

		while (tempIt.hasNext()) {
			Map.Entry city = (Map.Entry) tempIt.next();

			pathMap.put((String) city.getKey(), i);
			i++;

		}

		int n = pathMap.size() + 1;
		int pathCostArray[][] = new int[n][2];

		/*Marking visited as true*/
		for (Map.Entry<String, ArrayList> m : hm.entrySet()) {
			String key = m.getKey();
			visited.put(key, false);
		}

		Queue<String> bfsQueue = new LinkedList<String>();
		bfsQueue.add(from);

		while (!bfsQueue.isEmpty() && destFound != true) {
			String current = bfsQueue.peek();
			int parentId = pathMap.get(current);
			ArrayList neighbour = (ArrayList) hm.get(bfsQueue.peek());
			visited.put(bfsQueue.peek(), true);

			if (bfsQueue.peek().equals(to)) {
				System.out.println("Goal:" + bfsQueue.peek());
				System.out.println("Path Cost" + 0);
				break;
			}
			bfsQueue.remove(bfsQueue.peek());

			for (int k = 0; k < neighbour.size(); k++) {

				CityNode temp = (CityNode) neighbour.get(k);
				String tempCname = temp.getCname();
				pathCost = temp.getCost();
				int childId = pathMap.get(tempCname);
				if (!(Boolean) visited.get(tempCname)) {
					updatePathCostArray(pathCostArray, childId, pathCost, parentId);


					if (tempCname.equals(to)) {
						destFound= true;
						findCostPath(pathCostArray, to, pathMap, from);
						break;
					} else {
						bfsQueue.add(tempCname);
						visited.put(tempCname, true);
					}
				}

			}
		}

	}
	

	
	/* To print and calculate final path */
	private static void findCostPath(int[][] pathCostArray, String to, HashMap<String, Integer> pathMap, String from) {
		 StringBuilder finalPath = new StringBuilder(from);
		System.out.println("BFS Path towards destination is  : ");
		int sourceId = pathMap.get(from);
		int destId = pathMap.get(to);
		int finalCost = pathCostArray[destId][1];
		//String intermediateNode = null;
		int intermediateId = pathCostArray[destId][0];

		if (intermediateId == sourceId) {
			finalCost = pathCostArray[destId][1];
			for (Entry<String, Integer> e : pathMap.entrySet()) {
				// intermediateNode = e.getKey();
				if (intermediateId == e.getValue()) {
					//String finalPath = "" + e.getKey(); // can print this
				}
			}
		}

		while (intermediateId != sourceId) {

			for (Entry<String, Integer> e : pathMap.entrySet()) {
				// intermediateNode = e.getKey();
				if (intermediateId == e.getValue()) {
					finalPath.append(" ---> " + e.getKey());
				}

			}
			finalCost = finalCost + pathCostArray[intermediateId][1];
			intermediateId = pathCostArray[intermediateId][0];
		}
		finalPath.append(" ---> "+to);
		System.out.println(finalPath);
		System.out.println("Final Cost : " + finalCost);

	}

	/* To add the edges in the graph */
	private static void updatePathCostArray(int[][] pathCostArray, int childId, int pathCost, int parentId) {

		pathCostArray[childId][0] = parentId;
		pathCostArray[childId][1] = pathCost;

	}


}
