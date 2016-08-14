package baysian;
import java.util.*;

public class Node {

	int  number_of_parents;
	double probability;
	String name;
	HashMap<String,Double> conditionalProbability;
	List<Node> parents;

	
}
