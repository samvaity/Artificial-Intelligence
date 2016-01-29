
//package practice;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

class CityNode {
	String cname;
	int cost;

	CityNode() {
		cname = null;
		cost = 0;
	}

	public void setName(String name) {
		cname = name;
	}

	public void setCost(int c) {
		cost = c;
	}

	public int getCost() {
		return cost;
	}

	public String getCname() {
		return cname;
	}
}

public class DFS {

	static boolean destFound;
	static String[] pathToDest;

	@SuppressWarnings({ "resource", "rawtypes" })
	public static void main(String args[]) throws FileNotFoundException {
		ArrayList<CityNode> lt = new ArrayList<CityNode>();

		DFS dfs = new DFS();

		File file = new File(dfs.getClass().getResource("/resources/input.txt").getFile());
		System.out.println(file.getName());

		Scanner scanner = new Scanner(file);

		HashMap<String, ArrayList> hm = new HashMap<String, ArrayList>();

		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			String[] strArray = str.split(" ");
			String from = strArray[0];
			String to = strArray[1];
			int cost = Integer.parseInt(strArray[2]);

			if (!hm.containsKey(from)) {
				CityNode cn = new CityNode();
				cn.cname = to;
				cn.cost = cost;
				ArrayList<CityNode> CityList = new ArrayList<CityNode>();
				CityList.add(cn);
				// System.out.println(CityList.get(0).cname);
				hm.put(from, CityList);
			} else {
				ArrayList<CityNode> CityList = hm.get(from);
				if (null == CityList) // check for removal of this line.
				{
					CityList = new ArrayList<CityNode>();
				}
				CityNode cn = new CityNode();
				cn.cname = to;
				cn.cost = cost;
				CityList.add(cn);
				hm.put(from, CityList);

			}

			if (!hm.containsKey(to)) {
				// System.out.println("inside if");
				CityNode cn = new CityNode();
				cn.cname = from;
				cn.cost = cost;
				ArrayList<CityNode> CityList = new ArrayList<CityNode>();
				CityList.add(cn);
				hm.put(to, CityList);
			} else {

				ArrayList<CityNode> CityList = hm.get(to);
				if (null != CityList) {
					CityNode cn = new CityNode();
					cn.cname = from;
					cn.cost = cost;
					CityList.add(cn);
					hm.put(to, CityList);
				}
			}

		}

		System.out.println("Graph can be represented as: ");
		for (Map.Entry<String, ArrayList> m : hm.entrySet()) {
			String from = m.getKey();
			ArrayList<CityNode> temp = m.getValue();

			for (int i = 0; i < temp.size(); i++) {
				System.out.print(from + "--");
				CityNode t1 = temp.get(i);
				System.out.print(t1.cname + "  " + t1.cost);
				System.out.println();
			}

		}

		/*
		 * // call dfsSearch String from = "Arad"; String to = "Sibiu";
		 * dfsSearch(hm, from, to);
		 */

		Scanner reader = new Scanner(System.in);

		System.out.println("Enter city 1 :");
		String from = reader.nextLine();

		System.out.println("Enter city 2 :");
		String to = reader.nextLine();

		System.out.println("Enter search method number:\n 1. BFS \n 2. DFS \n 3. Iterative Deepening");
		int searchMethod = reader.nextInt();

		switch (searchMethod) {
		case 1:
			bfsSearch(hm,from,to);
		case 2:
			dfsSearch(hm, from, to);
		case 3:
			iterativeDeepeningSearch();
		}

	}

	private static void iterativeDeepeningSearch() {
		// TODO Auto-generated method stub

	}

	private static void bfsSearch(HashMap<String, ArrayList> hm, String from, String to) {
		// TODO Auto-generated method stub

	}

	public static void dfsSearch(HashMap<String, ArrayList> h, String source, String destination) {

		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();

		for (Map.Entry<String, ArrayList> m : h.entrySet()) {
			String key = m.getKey();
			visited.put(key, false);
		}

		/* Remove the following line */
		for (Map.Entry<String, Boolean> m : visited.entrySet()) {
			System.out.println(m.getKey() + "/" + m.getValue());
		}

		explore(h, visited, source, destination, source);

		/* Remove the following line */
		for (Map.Entry<String, Boolean> m : visited.entrySet()) {
			System.out.println(m.getKey() + "/" + m.getValue());
		}
	}

	public static int explore(HashMap h, HashMap<String, Boolean> visited, String source, String destination,
			String path) {
		int cost = 0;

		if (h.containsKey(source)) {
			visited.put(source, true);
			ArrayList neighbour = (ArrayList) h.get(source);

			for (int k = 0; k < neighbour.size(); k++) {
				// if(h.get(neighbour(k)))
				if (!(Boolean) visited.get(destination)) {
					CityNode temp = (CityNode) neighbour.get(k);
					String tempCname = temp.getCname();

					if (tempCname.equals(destination)) {
						System.out.println("inside if for break");
						visited.put(tempCname, true);
						cost += temp.getCost();
						path = path + " " + tempCname;
						// System.out.println(" intermediate cost :"+cost);
						destFound = true;
						updatePath(path, h);
						break;
					}
					System.out.println("just after break");
					if (!(Boolean) visited.get(temp.cname)) {
						System.out.println(temp.cname + " " + destination);
						cost = cost + temp.cost;
						System.out.println(" intermediate cost :" + cost);
						System.out.println(path + " " + tempCname);
						int t = explore(h, visited, temp.cname, destination, path + " " + tempCname);
						cost = cost + t;
					} else {
						// cost = cost-temp.cost;
						// System.out.println("cost-temp.cost");
						// System.out.println();

					}

				} // end of check for destination visit
			}

		}
		System.out.println("path: " + path);
		System.out.println("cost = " + cost);
		return cost;

	}

	public static void updatePath(String path, HashMap hshmap) {
		pathToDest = path.split(" ");
		int cost = 0;

		System.out.println("Path towards destination is ");
		for (int k = 0; k < pathToDest.length - 1; k++) {
			System.out.print(pathToDest[k] + "-->");
			ArrayList tempList = (ArrayList) hshmap.get(pathToDest[k]);

			Iterator t = tempList.iterator();

			while (t.hasNext()) {
				CityNode cn = (CityNode) t.next();
				String cityName = cn.getCname();
				if (pathToDest[k + 1].equals(cityName))
					cost += cn.getCost();
			}

		}

		System.out.println("Final cost " + cost);

	}

}
