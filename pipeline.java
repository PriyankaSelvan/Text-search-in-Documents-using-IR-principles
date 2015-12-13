
import java.io.*;

import irUtilities.*;

public class pipeline {
	public void call(int fname)throws IOException
	   {
		 
		 
		 StringBuilder sb = new StringBuilder();
		 Porter po=new Porter();
		
		 
		 BufferedReader br = new BufferedReader(new FileReader("Text"+fname+".txt"));
	   
	      try {
	    	  
			
	        
	         String line = br.readLine();
	         

	         while (line != null) {
	             sb.append(line);
	             sb.append("\n");
	             line = br.readLine();
	         }
	        // System.out.println(sb.toString()); Prints file
	     } finally {
	         br.close();
	     }
			//System.out.println("closed "+fname);

	         String fs=new String();
	         
	         fs=new String(sb);
	         //String finalstr[]=new String[]{};
	         String fs2[]=fs.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
	         
	         int it=0;
	         int x=fs2.length;
	        // List<String> wordList = Arrays.asList(fs2);
	        //(po.stopwords(wordList.get(i)))
	         for(it=0;it<x;it++){
	         if(po.stopwords(fs2[it]))
	        		 {
	        	 //System.out.println(wordList.get(i));
	        	 //System.out.println(fs2[it]);
	        	 //wordList.remove(i);
	        	 fs2[it]=null;
	        	 
	        		 }
	         }
	         
	         it=0;
	          //System.out.println(x);
	         String finalstr[]=new String[5500];
	         int h=0;
	         while(it<x)
	        	 
	         {		
	        	// System.out.println(fs2[i]);
	        	if(fs2[it]!=null){
	        		finalstr[h++]=po.stripAffixes(fs2[it]);
	        	 
	        	 //byte[] contentInBytes = fs2[it].getBytes();
	        	 //fop.write(contentInBytes);
	        		
	        	}
                 
	        	 it++;
	        	 //System.out.println("Stripping affixes");
	         }//---somewhere inside the above loop null pointer exception for file 2 and 4----
	         

	         int kk=0;
	         
	         while(finalstr[kk]!=null)
	         {
	        	// System.out.println(finalstr[kk++]);
	        	 kk++;
	         }
	        // System.out.println("\n\n\n--------"+kk+"----------\n\n\n");
	         
	        // System.out.println(i);
	         
	        
	         //Inverted index creation
	         /*TreeMap<String, Integer> frequencyData = new TreeMap<String, Integer>( );

	         Map<String,Integer>[] mapArray = new Map[h+50];
	        // mapArray[0] = new HashMap<String, Integer>();
	         int total = 0;
	         
	          int finalnum=h; 
	          int r=0;
	         Integer count;   
	         int counter = 0;
	         int res=0;
	         System.out.println(finalnum);
            // for(int d=0;d<=h;d++)
            //	 System.out.println(finalstr[d]);
	         for(r=0;r<=h;r++)
	         {   //System.out.println(r);
	        	 while(res<mapArray.length)
	        	 {
	        		if(mapArray[res].containsKey(finalstr[r]))
	        			mapArray[res].put(finalstr[r],mapArray.getValue()+1);
	        			
	        	 }
	        	 count = getCount(finalstr[r], frequencyData) + 1;
	             frequencyData.put(finalstr[r], count);
	             total = total + count;
	             counter++; 
	         }
	         System.out.println(finalnum);
	         printAllCounts(frequencyData);*/
	        // strnum index[]=new strnum(4000);
	         
	         String strings[]=new String[h+50];
	         int num[]=new int[h+50];
	         strings[0]=finalstr[0];
	         num[0]=1;
	         int wcon=1,flags=0;
	         
	         for(int con=1;con<kk;con++)
	         {
	        	 flags=0;
	        	 for(int con1=0;con1<wcon;con1++)
	        	 {
	        		 
	        		// System.out.println("finalstr : "+finalstr[con]);
	        		 //System.out.println("strings : "+strings[con1][sha]);
	        		 
	        		 
	        		 if((finalstr[con].compareTo(strings[con1]))==0)
	        		 {
	        			// System.out.println("num[con1]="+num[con1][sha]);
	        			 num[con1]=++num[con1];
		        		 //System.out.println("entered if : "+asd++);
		        		 //System.out.println(strings[con1][sha]+" --> "+num[con1][sha]);
		        		 flags=0;
		        		 break;
		        		 
	        			 
	        			 
	        		 }
	        		 else
	        		 {
	        			 flags=1;
	        		 }
	        		 
	        		 //System.out.println("finished loop 2 going to next");
	        	 }
	        	 if(flags==1)
	        	 {
	        		// System.out.println("entered if two");
        			 wcon++;
        			 strings[wcon-1]=finalstr[con];
        			 num[wcon-1]=1;
	        	 }
        		// System.out.println("finished loop 1 going to next");

	         }
	         for(int con=0;con<wcon;con++)
	         {
	        	 //System.out.println(strings[con][sha]+"--->"+num[con][sha]);
	         }
	         
	        // System.out.println("######   "+wcon);
	        int ii=0,jj=0,tt=0;
	         String asd;
	         for(ii=0;ii<wcon;ii++)

	         {
	        	 for(jj=0;jj<ii;jj++)
	        	 {
	        		 if((strings[ii].compareTo(strings[jj]))<0)
	        		 {
	        			 asd=strings[ii];
	        			 strings[ii]=strings[jj];
	        			 strings[jj]=asd;
	        			 tt=num[ii];
	        			 num[ii]=num[jj];
	        			 num[jj]=tt;
	        		 }
	        	 }
	         }
	         
	        // System.out.println("~~~~~~~~~~~SORTED~~~~~~~~~~~");
	         //System.out.println(" &&&&&&  "+sha+"   &&&&&&&");
	         int max=-1;
	         double[] ntf=new double[5500];
	         for(int con=0;con<wcon;con++)
	         {
	        	 if(num[con]>max)
	        		 max=num[con];
	         }
	         for(int fk=0;strings[fk]!=null;fk++)
	         {
	        	 ntf[fk]=((double)(num[fk])/(max));
	         }
	         
	         PrintWriter writer = new PrintWriter("Index"+fname+".txt", "UTF-8");
	         for(int fk=0;strings[fk]!=null;fk++)
	         {
	        	 writer.println(strings[fk]+" "+num[fk]+" "+ntf[fk]);
	         }
	         writer.close();
	        
	        	         
	         
		 
		 
		 
		 
		 
		 
		 
	   }//end of main

	
	/*public static int getCount(String word, TreeMap<String, Integer> frequencyData)
	    {
	        if (frequencyData.containsKey(word))
	        {  // The word has occurred before, so get its count from the map  
	            return frequencyData.get(word); // Auto-unboxed  
	        }
	        else
	        {  // No occurrences of this word  
	            return 0;
	        }
	    }
	 public static void printAllCounts(TreeMap<String, Integer> frequencyData)
	    {
	        System.out.println("-----------------------------------------------");
	        System.out.println("    Occurrences    Word");

	        for(String word : frequencyData.keySet( ))
	        {
	            System.out.printf("%15d    %s\n", frequencyData.get(word), word);
	        }

	        System.out.println("-----------------------------------------------");
	    }*/

}//end of class
	

