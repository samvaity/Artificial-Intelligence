package baysian;

import java.util.HashMap;
import java.util.Random;

public class LikelihoodSampling {

	public double likelihoodsampling(int samplesize, HashMap<String,String> evidence, String querynode)
	{
		double[] weightArray = new double[samplesize];
		
		HashMap<String,Integer> mapSample = new HashMap<String,Integer>();
		
		mapSample.put("B", 0);
		mapSample.put("E", 1);
		mapSample.put("A", 2);
		mapSample.put("J", 3);
		mapSample.put("M", 4);
		
		int[][] sample = getLikelihoodSample(samplesize,evidence,weightArray);
		
		double totalweights=0.0;
		double queryweights=0.0;
		int index = mapSample.get(querynode);
		for(int i=0;i<samplesize;i++)
		{
			if(sample[i][index] == 1){
				queryweights+=weightArray[i];
			}
			totalweights+=weightArray[i];
			
		}
		
		double probability=0;
		if(totalweights>0){
			probability= queryweights/totalweights;
		}
		
		return probability;
	}
	
	public int[][] getLikelihoodSample(int samplesize, HashMap<String,String> evidence,double[] weightArray )
	{
		int[][] sample = new int[samplesize][6];
		
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
		Random random = new Random();
		double val=0;
	
		for(int i=0;i<samplesize;i++){
			int record[] = new int[6];
			double weight=1;
			int truthvalue=0;
			
			if(evidence.containsKey("B")){
				String value=evidence.get("B");
				
				if(value.equalsIgnoreCase("f")){
					truthvalue=0;
					weight=weight*(1-probabilityB);					
				}else{
					truthvalue=1;
					weight=weight*probabilityB;
				}
				record[0] = truthvalue;
				
				
			}else{
			
				val=random.nextDouble();
				//System.out.println(val);
				if(val<=probabilityB){
					record[0]=1;
				}else{
					record[0]=0;
				}
				
			}
			
			
			if(evidence.containsKey("E")){
				String value=evidence.get("E");
				
				
				
				if(value.equalsIgnoreCase("f")){
					truthvalue=0;
					weight=weight*(1-probabilityE);
				}else{
					truthvalue=1;
					weight=weight*(probabilityE);
				}
				
				record[1] = truthvalue;
				
				
			}else{
			
				val=random.nextDouble();
				//System.out.println(val);
				if(val<=probabilityE){
					record[1]=1;
				}else{
					record[1]=0;
				}
				
			}
			
			if(evidence.containsKey("A")){
				
				double probA = probabilityA[record[0]][record[1]];
				String value=evidence.get("A");
				
				if(value.equalsIgnoreCase("f")){
					truthvalue=0;
					weight=weight*(1-probA);
				}else{
					truthvalue=1;
					weight=weight*(probA);
				}
				
				record[2] = truthvalue;
				
				
			}else{
			
				val=random.nextDouble();
				//System.out.println(val);
				if(val<=probabilityA[record[0]][record[1]]){
					record[2]=1;
				}else{
					record[2]=0;
				}
				
			}
			
			if(evidence.containsKey("J")){
			
				double probJ = probabilityJ[record[2]];
				String value=evidence.get("J");
				
				if(value.equalsIgnoreCase("f")){
					truthvalue=0;
					weight=weight*(1-probJ);
				}else{
					truthvalue=1;
					weight=weight*(probJ);
				}
				
				record[3] = truthvalue;
				
			}else{
			
				val=random.nextDouble();
				//System.out.println(val);
				if(val<=probabilityJ[record[2]]){
					record[3]=1;
				}else{
					record[3]=0;
				}
				
			}
			
			if(evidence.containsKey("M")){
				
				double probM = probabilityM[record[2]];
				String value=evidence.get("M");
				
				if(value.equalsIgnoreCase("f")){
					truthvalue=0;
					weight=weight*(1-probM);
				}else{
					truthvalue=1;
					weight=weight*(probM);
				}
				
				record[4] = truthvalue;				
			}else{
			
				val=random.nextDouble();
				//System.out.println(val);
				if(val<=probabilityM[record[2]]){
					record[4]=1;
				}else{
					record[4]=0;
				}
				
			}
			
			weightArray[i] = weight;
			for(int j=0; j<5;j++){
				sample[i][j] = record[j];
			}
		}
		
		return sample;
	}
	
}
