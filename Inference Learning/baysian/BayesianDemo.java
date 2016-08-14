package baysian;
import java.util.*;

public class BayesianDemo {

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		BayesianNetwork bn = new BayesianNetwork();
		
		
		String algorithm = args[0];
		String numSamples = args[1];
		HashMap<String,String> evidence = new HashMap<String,String>();
		int samplesize=Integer.parseInt(numSamples);
		//Node queryNode = bn.mapNode.get(querynode);
		//double res = enumerate(queryNode,evidence,bn);
		double res1=0;
		
		
		int n = s.nextInt();
		int m = s.nextInt();
		
		for(int i=0 ; i<n;i++)
		{
			evidence.put(s.next(), s.next());
		}
		
		HashMap<String,Double> output = new HashMap<String,Double>();
		
		for(int k=0;k<m;k++)
		{
			res1=0;
			String querynode = s.next();
			switch(algorithm)
			{
				
				case "e":   //System.out.println("Inside enumeration");
							Node node = bn.mapNode.get(querynode);
							Enumeration e = new Enumeration();							
							res1 = e.enumerate(node, evidence, bn);
							output.put(querynode, res1);
							break;
				
			
				case "p":	PriorSampling p = new PriorSampling();
							for(int i=0;i<10;i++){
								 res1 += p.priorSampling(samplesize,evidence,querynode); 
							}
							res1/=10;
							//System.out.println(querynode+" "+res1);
							output.put(querynode,res1);
							break;
							
				case "r":	RejectionSampling rejSampling = new RejectionSampling();
							
							for(int i=0;i<10;i++){
								res1 += rejSampling.rejectionSampling(samplesize, evidence, querynode);
							}
							res1/=10;
							//System.out.println(querynode+" "+res1);
							output.put(querynode,res1);
							break;
							
				case "l":	LikelihoodSampling likelihood = new LikelihoodSampling();
							res1=0;
							for(int i=0;i<10;i++){
								res1 += likelihood.likelihoodsampling(samplesize, evidence, querynode);
							}
							res1/=10;
							//System.out.println(querynode+" "+res1);
							output.put(querynode,res1);
							break;
							
				default:	System.out.println("Wrong algorithm option. Please input e,p,r,l only");
			
			}	
		}
		//evidence.put("J", "t");
		//evidence.put("M", "t");
		//evidence.put("E", "t");
		//evidence.put("B", "f");
		//evidence.put("J", "f");
		
		for(Map.Entry<String, Double> me : output.entrySet())
		{
			System.out.println(me.getKey()+" "+me.getValue());
		}
		
		
		
		//System.out.println("Likelihood Sampling Probability:" +res1);
		
		//System.out.println(res);
		
	}
	
	


	
	
	
	

}
