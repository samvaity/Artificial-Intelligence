package baysian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enumeration {
	
	public double enumerate(Node node, HashMap evidence,BayesianNetwork bn)
	{
		double probability = 0.0;
		boolean allParentsfound = true;
		if(node.number_of_parents == 0){
			return node.probability;
		}
		
		List<Node> parents = node.parents;
		List<Node> summationNodes = new ArrayList<Node>();
		
		for(int i=0;i<parents.size();i++)
		{
			
			Node parent = parents.get(i);
			String parentName = parent.name;
			if(!evidence.containsKey(parentName)){
				allParentsfound=false;
			}
		}
		
		if(allParentsfound){
			//System.out.println("inside found");
			return calcProbFromTable(node, evidence);
			
		}
		
		
		return probability;
	}
	
	public double calcProbFromTable(Node node,HashMap evidence)
	{
		double probability=0.0;
		for(Map.Entry<String, Double> me : node.conditionalProbability.entrySet()){
			
			String key = me.getKey();
			// A=t
			//System.out.println("key="+key);
			String[] keyTruthValuePair = key.split(",");
			
			for(int k=0; k<keyTruthValuePair.length;k++){
				//System.out.println("inside k for loop: "+k);
				String[] keyTruthValue = keyTruthValuePair[k].split("=");
				//System.out.println("k="+k+" key= "+keyTruthValue[0]+" value="+keyTruthValue[1]);
				if(evidence.containsKey(keyTruthValue[0])){
					//System.out.println("inside evidence if");
					String value = (String) evidence.get(keyTruthValue[0]);	
					if(value.equalsIgnoreCase(keyTruthValue[1]) ){
						probability = me.getValue();
						break;
					}
				}
			}
			
		}
		
		return probability;
	}
	
}
