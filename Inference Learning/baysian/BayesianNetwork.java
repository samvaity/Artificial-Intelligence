package baysian;
import java.util.*;
public class BayesianNetwork {

	HashMap<String,Node> mapNode;
	
	BayesianNetwork(){
		Node B = new Node();
		B.name = "B";
		B.number_of_parents=0;
		B.probability = 0.001;
		
		Node E = new Node();
		E.name = "E";
		E.number_of_parents=0;
		E.probability=0.002;
		
		Node A = new Node();
		A.name = "A";
		A.number_of_parents=2;
		List<Node> par = new ArrayList<Node>();
		par.add(B);
		par.add(E);
		A.parents=par;
		
		HashMap<String,Double> parent_A = new HashMap<String,Double>();
		parent_A.put("B=t,E=t", 0.95);
		parent_A.put("B=t,E=f", 0.94);
		parent_A.put("B=f,E=t", 0.29);
		parent_A.put("B=t,E=t", 0.001);
		
		A.conditionalProbability = parent_A;
		
		Node J = new Node();
		J.name = "J";
		J.number_of_parents = 1;
		par = new ArrayList<Node>();
		par.add(A);
		J.parents = par;
		
		HashMap<String,Double> parent_J = new HashMap<String,Double>();
		parent_J.put("A=t", 0.90);
		parent_J.put("A=f", 0.05);
		
		J.conditionalProbability = parent_J;
		
		Node M = new Node();
		M.name= "M";
		M.number_of_parents = 1;
		par = new ArrayList<Node>();
		par.add(A);
		
		HashMap<String,Double> parent_M = new HashMap<String,Double>();
		parent_M.put("A=t", 0.70);
		parent_M.put("A=f", 0.01);
		M.conditionalProbability = parent_M;
		M.parents = par;
		
		mapNode = new HashMap<String,Node>();
		
		mapNode.put(B.name, B);
		mapNode.put(A.name, A);
		mapNode.put(E.name, E);
		mapNode.put(M.name, M);
		mapNode.put(J.name, J);
		
	}
	
	
	
}
