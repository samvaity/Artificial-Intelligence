package baysian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RejectionSampling {

	public double rejectionSampling(int sampleSize,HashMap<String,String> evidence, String querynode)
	{
		//int[][] sample = getPriorSample(sampleSize);
		List<List<Integer>> sampleList = getRejectionSample(sampleSize, evidence);
		//int evidenceSamples=0;
		int querySamples=0;
		//System.out.println("sampleList: size= "+sampleList.size());
		/*System.out.println("Printing sample");
		for(int i=0; i<sampleList.size();i++)
		{
			System.out.println(sampleList.get(i));
		}*/
		
		
		HashMap<String,Integer> mapSample = new HashMap<String,Integer>();
		
		mapSample.put("B", 0);
		mapSample.put("E", 1);
		mapSample.put("A", 2);
		mapSample.put("J", 3);
		mapSample.put("M", 4);
		
		for(int i=0;i<sampleList.size();i++)
		{
			//int num_evidences_satisfied=0;
			/*for(Map.Entry<String, String> me : evidence.entrySet())
			{
				String key = me.getKey();
				String truthvalue = me.getValue();
				int truthvalueInt;
				
				if(truthvalue.equalsIgnoreCase("T")){
					truthvalueInt = 1;
				}else{
					truthvalueInt = 0;
				}
				int index = mapSample.get(key);	
				
				if(sample[i][index]== truthvalueInt){
					num_evidences_satisfied++;
				}
				
			}*/
			
			
				
				//Logic for query nodes
				int queryIndex = mapSample.get(querynode);
				if(sampleList.get(i).get(queryIndex) == 1){
					querySamples++;
				}
		}
		
		double probability=0;
		if(sampleList.size()>0){
			probability= (double)querySamples/sampleList.size();
		}
		return probability;
	}
	
	
	
	public List<List<Integer>> getRejectionSample(int sampleSize,HashMap<String,String> evidence){
		
		HashMap<String,Integer> mapSample = new HashMap<String,Integer>();
	
		mapSample.put("B", 0);
		mapSample.put("E", 1);
		mapSample.put("A", 2);
		mapSample.put("J", 3);
		mapSample.put("M", 4);
	

		double probabilityB = 0.001;
		double probabilityE = 0.002;
		double probabilityA[][] = {{0.001,0.29},{0.94,0.95}};
		double probabilityJ[] = {0.05,0.9};
		double probabilityM[] = {0.01,0.7}; 
		List<List<Integer>> sampleList = new ArrayList<List<Integer>>();
		
		
		Random random = new Random();
		
		for(int i=0; i<sampleSize; i++)
		{
			List<Integer> singleRecord = new ArrayList(5);
			int k=0;
			double val;
		
			val=random.nextDouble();
			//System.out.println(val);
			if(val<=probabilityB){
				singleRecord.add(1);
			}else{
				singleRecord.add(0);
			}
			
			val=random.nextDouble();
			//System.out.println(val);
			if(val<=probabilityE){
				singleRecord.add(1);
			}else{
				singleRecord.add(0);
			}
			
			val = random.nextDouble();
			//System.out.println(val);
			if( val <= probabilityA[singleRecord.get(0)][singleRecord.get(1)]){
				singleRecord.add(1);
			}else{
				singleRecord.add(0);
			}
			
			val= random.nextDouble();
			if(val <= probabilityJ[singleRecord.get(2)]){
				//record[3] = 1;
				singleRecord.add(1);
			}else{
				//record[3] = 0;
				singleRecord.add(0);
			}
			
			val= random.nextDouble();
			if(val <= probabilityM[/*record[2]*/singleRecord.get(2)]){
				//record[4] = 1;
				singleRecord.add(1);
			}else{
				//record[4] = 0;
				singleRecord.add(0);
			}
			
			
			boolean isSampleSatisfied=true;
			for(Map.Entry<String, String> me: evidence.entrySet())
			{
				String key = me.getKey();
				String evidenceValue = me.getValue();
				int truthValueInt;
				if(evidenceValue.equalsIgnoreCase("f")){
					truthValueInt=0;
				}else{
					truthValueInt=1;
				}
				
				int index = mapSample.get(key); 	// A =0;
				if(singleRecord.get(index)!=truthValueInt){
					isSampleSatisfied = false;
					break;
				}
				
				if(singleRecord.get(index) != truthValueInt){
					isSampleSatisfied = false;
					break;
				}
				
			}
			
			if(isSampleSatisfied){
				/*for(int j=0; j<5;j++){
					sample[i][j] = record[j];
				}*/
				sampleList.add(singleRecord);
			}
		}
		
		
		
		
		return sampleList;
	}
	
	
}
