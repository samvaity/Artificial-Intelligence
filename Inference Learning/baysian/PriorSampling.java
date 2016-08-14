package baysian;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PriorSampling {

	public double priorSampling(int sampleSize,HashMap<String,String> evidence,String querynode)
	{
		int[][] sample = getPriorSample(sampleSize);
		int evidenceSamples=0;
		int querySamples=0;
		
		HashMap<String,Integer> mapSample = new HashMap<String,Integer>();
		
		mapSample.put("B", 0);
		mapSample.put("E", 1);
		mapSample.put("A", 2);
		mapSample.put("J", 3);
		mapSample.put("M", 4);
		
		
		for(int i=0;i<sample.length;i++)
		{
			int num_evidences_satisfied=0;
			for(Map.Entry<String, String> me : evidence.entrySet())
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
				
			}
			
			//System.out.println("num_evidences_satisfied"+num_evidences_satisfied);
			if(num_evidences_satisfied == evidence.size()){
				evidenceSamples++;
				
				//Logic for query nodes
				int queryIndex = mapSample.get(querynode);
				if(sample[i][queryIndex] == 1){
					querySamples++;
				}
			}
				
		}
		
		double probability=0;
		if(evidenceSamples!=0){
			probability = (double)querySamples/evidenceSamples;
		}
		
		return probability;
	}
	
private int[][] getPriorSample(int sampleSize){
		
		double probabilityB = 0.001;
		double probabilityE = 0.002;
		double probabilityA[][] = {{0.001,0.29},{0.94,0.95}};
		double probabilityJ[] = {0.05,0.9};
		double probabilityM[] = {0.01,0.7}; 
		int sample[][] = new int[sampleSize][5];
		
		Random random = new Random();
		
		for(int i=0; i<sampleSize; i++)
		{
			int[] record = new int[5];
			double val;
		
			val=random.nextDouble();
			//System.out.println(val);
			if(val<=probabilityB){
				record[0]=1;
			}else{
				record[0]=0;
			}
			
			val=random.nextDouble();
			//System.out.println(val);
			if(val<=probabilityE){
				record[1]=1;
			}else{
				record[1]=0;
			}
			
			val = random.nextDouble();
			//System.out.println(val);
			if( val <= probabilityA[record[0]][record[1]]){
				record[2]=1;
			}else{
				record[2]=0;
			}
			
			val= random.nextDouble();
			if(val <= probabilityJ[record[2]]){
				record[3] = 1;
			}else{
				record[3] = 0;
			}
			
			val= random.nextDouble();
			if(val <= probabilityM[record[2]]){
				record[4] = 1;
			}else{
				record[4] = 0;
			}
			
			for(int j=0; j<5;j++){
				//System.out.println("row[j] = "+row[j]);
				sample[i][j] = record[j];
			}
		}
		
		return sample;
	}
	
}
