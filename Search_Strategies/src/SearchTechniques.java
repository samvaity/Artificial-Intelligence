
//package AI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class SearchTechniques {
	static boolean cityExists = false;

	public static void main(String args[]) throws FileNotFoundException {
		ArrayList<CityNode> lt = new ArrayList<CityNode>();
		SearchTechniques st = new SearchTechniques();
		File file = new File(st.getClass().getResource("/resources/input.txt").getFile());

		Scanner scanner = new Scanner(file);

		HashMap<String, ArrayList> hm = new HashMap<String, ArrayList>();

		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			String[] strArray = str.split(",");
			String from = strArray[0];
			String to = strArray[1];
			int cost = Integer.parseInt(strArray[2].trim());

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

	/*	System.out.println("Graph can be represented as: ");
		for (Map.Entry<String, ArrayList> m : hm.entrySet()) {
			String from = m.getKey();
			ArrayList<CityNode> temp = m.getValue();

			for (int i = 0; i < temp.size(); i++) {
				System.out.print(from + "--");
				CityNode t1 = temp.get(i);
				System.out.print(t1.cname + "  " + t1.getCost());
				System.out.println();
			}

		}*/

		/* make all nodes as false */
		initAllNodes(hm);

	}

	private static boolean checkIfExists(HashMap<String, ArrayList> hm, String city) {
		cityExists = false;
		if (hm.containsKey(city)) {

			return cityExists = true;
		}
		return cityExists;
	}

	/* Below 3 methods are for Searching techniques */
	public static void initAllNodes(HashMap<String, ArrayList> h) {

		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		char ch = 'Y';

		do {

			for (Map.Entry<String, ArrayList> m : h.entrySet()) {
				String key = m.getKey();
				visited.put(key, false);
			}

			Scanner reader = new Scanner(System.in);

			System.out.println("Enter city 1 :");
			String from = reader.nextLine();
			if (!(checkIfExists(h, from))) {
				System.out.println("Invalid city!!\n Do you want to continue/n Y/N");
				ch = reader.next().charAt(0);
				if (ch == 'Y' || ch == 'y') {
					initAllNodes(h);
				} else {
					return;
				}
			}

			System.out.println("Enter city 2 :");
			String to = reader.nextLine();
			if (!(checkIfExists(h, to))) {
				System.out.println("Please enter a valid city!!");
				System.out.println("Invalid city!!\n Do you want to continue/n Y/N");
				ch = reader.next().charAt(0);
				if (ch == 'Y' || ch == 'y') {
					initAllNodes(h);
				} else {
					return;
				}
			}

			System.out.println("Enter search method number:\n 1. DFS \n 2. BFS \n 3. Iterative Deepening");
			int searchMethod = reader.nextInt();

			switch (searchMethod) {
			case 1:
				DFS dfs = new DFS();
				dfs.dfsSearch(h, visited, from, to, from);
				if (!dfs.isDestFound())
					System.out.println("Path does not exist using dfs");
				break;

			case 2:
				BFS bfs = new BFS();
				bfs.bfsSearch(h, from, to);
				if (!bfs.isDestFound())
					System.out.println("Path does not exist using bfs");
				break;

			case 3:
				IDS ids = new IDS();
				ids.iterativeDeepening(h, visited, from, to, from);
				if (!ids.isDestFound())
					System.out.println("Path does not exist using ids");
				break;

			default:
				System.out.println("Incorrect option");

			}

			System.out.println("Do you want to continue? Y/N ");
			ch = reader.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
	}

	public void updatePath(String path, HashMap hshmap, String destination) {
		String[] pathToDest;
		pathToDest = path.split(",");
		int cost = 0;

		System.out.println("Path towards destination is ");
		for (int k = 0; k < pathToDest.length - 1; k++) {
			System.out.print(pathToDest[k] + " ---> ");
			ArrayList tempList = (ArrayList) hshmap.get(pathToDest[k]);

			Iterator t = tempList.iterator();

			while (t.hasNext()) {
				CityNode cn = (CityNode) t.next();
				String cityName = cn.getCname();

				if (pathToDest[k + 1].equals(cityName)) {
					// System.out.print(pathToDest[k]+ " ---> ");
					cost += cn.getCost();
					break;
				}
			}

		}
		System.out.println(destination);
		System.out.println("Cost - " + cost);
	} // end of update path

}
