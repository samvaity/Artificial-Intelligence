
import java.util.ArrayList;
import java.util.HashMap;

public class IDS {

	private boolean destFound;

	private int depth;
	private int maxDepth;

	public void iterativeDeepening(HashMap<String, ArrayList> hm, HashMap visited, String source, String destination,
			String path) {

		int count = 0;

		visited.put(source, true); // Mark source as visited

		String start = source;
		boolean result;
		while (maxDepth <= 1000) // At max search for these many levels. kind of
									// infinity
		{
			result = recursive_dls(hm, visited, start, destination, path);
			maxDepth++;

			if (destFound)
				break;

		}

		if (destFound) {
			System.out.println("Destination found at depth " + (maxDepth - 1));
		} else
			System.out.println("Destination could not be found");

	}

	public boolean recursive_dls(HashMap<String, ArrayList> hm, HashMap visited, String source, String destination,
			String path) {
		boolean res = true;
		ArrayList neighbour = (ArrayList) hm.get(source);
		SearchTechniques st = new SearchTechniques();

		if (source.equals(destination)) {
			visited.put(source, true);

			path = path + "";

			destFound = true;
			st.updatePath(path, hm, destination);
			return true;
		}

		if (depth < maxDepth) {
			visited.put(source, true);
			for (int k = 0; k < neighbour.size(); k++) {

				if (!(Boolean) visited.get(destination)) {
					CityNode temp = (CityNode) neighbour.get(k);
					String tempCname = temp.getCname();

					if (tempCname.equals(destination)) {
						visited.put(tempCname, true);
						// System.out.println("inside destination found");
						path = path + "," + tempCname;
						// System.out.println(" intermediate cost :"+cost);
						destFound = true;
						st.updatePath(path, hm, destination);
						break;
					}

					if (!(Boolean) visited.get(temp.cname)) {
						depth++;
						// System.out.println("depth in before
						// recursion"+depth);
						recursive_dls(hm, visited, temp.cname, destination, path + "," + tempCname);
						depth--;
						visited.put(temp.cname, false);
					}
				}
			} // end of for loop
		} else {
			res = false;
			return res;
		}
		return res;
	} // end of method

	public boolean isDestFound() {
		return destFound;
	}

	public void setDestFound(boolean destFound) {
		this.destFound = destFound;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
