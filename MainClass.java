import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class MainClass {

	public static void main(String[] args) {
		pipeline pob=new pipeline();
		int n=4;//Change number here :)
		try{
			for(int i=1;i<=n;i++){
		pob.call(i);}
		
	
		}
		catch(Exception e)
		{
			System.out.println(e.getCause());
		}
		
		System.out.println("Enter query");
		Scanner sc=new Scanner(System.in);
		String query=sc.nextLine();
		//System.out.println(query);
		try{
		
		PrintWriter wri = new PrintWriter("Text5.txt","UTF-8");
		wri.println(query);
		wri.close();
		pob.call(5);
		BufferedReader brr = new BufferedReader(new FileReader("Index5.txt"));
		String temp=new String();
		String[] tempa=new String[5];
		int fg;
		String[] strarr=new String[100];
		for(fg=0;(temp=brr.readLine())!=null;fg++)
		{
			tempa=temp.split(" ");
			strarr[fg]=tempa[0];
			//System.out.println(strarr[fg]);
		}
		int doc;
		String lines=new String();
		//System.out.println(fg);---//fg is num of query terms
		for(doc=1;doc<=n;doc++)
		{
			//System.out.println("file"+doc);
			//PrintWriter kk=new PrintWriter("Ans"+doc+".txt","UTF-8");
			//FileReader tempfile=new FileReader("Index"+doc+".txt");
			//BufferedReader hfile=new BufferedReader(tempfile);
			PrintWriter kk=new PrintWriter("Ans"+doc+".txt","UTF-8");
			for(int i=0;i<fg;i++)
			{
				
				FileReader tempfile=new FileReader("Index"+doc+".txt");
				BufferedReader hfile=new BufferedReader(tempfile);
				//System.out.println("query word "+i);
				for(int nb=0;(lines=hfile.readLine())!=null;nb++)
				{
					//System.out.println(lines);
					tempa=lines.split(" ");
					//System.out.println(tempa[0]);
					if(tempa[0].equals(strarr[i]))
					{
						//System.out.println("Compring "+tempa[0]+" "+strarr[i]);
						kk.println(lines);
					}
				}hfile.close();
				//--------here---------//
			}kk.close();//hfile.close();
		}
		//from tody
		double[] freq=new double[100];
		for(int i=0;i<100;i++)
			freq[i]=0;
		for(int gg=0;gg<strarr.length;gg++)
		{   //System.out.println(strarr[gg]);
			for(int dd=1;dd<=n;dd++)
			{   //System.out.println("doc"+dd);
				FileReader tempfiles=new FileReader("Ans"+dd+".txt");
				BufferedReader ffile=new BufferedReader(tempfiles);
				for(int nb=0;(lines=ffile.readLine())!=null;nb++)
				{//System.out.println(lines);
				//lines=ffile.readLine();
				tempa=lines.split(" ");
				if(tempa[0].equals(strarr[gg]))
				{
					freq[gg]++;
				}
			}ffile.close();}
		}
		for(int i=0;strarr[i]!=null;i++)
		{
			freq[i]=Math.log((n/freq[i]));
			//System.out.println(strarr[i]+"-->"+freq[i]);
		}
		double[] finals=new double[100];
		for(int i=0;i<100;i++)
		{
			finals[i]=0.0;
		}
		for(int i=1;i<=n;i++)
		{
			FileReader tempfiless=new FileReader("Ans"+i+".txt");
			BufferedReader fffile=new BufferedReader(tempfiless);
			for(int nb=0;(lines=fffile.readLine())!=null;nb++)
			{
				tempa=lines.split(" ");
				for(int j=0;strarr[j]!=null;j++)
				{
					if(strarr[j].equals(tempa[0]))
					{
						finals[i]+=(freq[j]*Double.parseDouble(tempa[2]));
					}
				}
			}fffile.close();
			
		}
		System.out.println("----RANKS FOR DOCUMENTS---------");
		for(int i=1;i<=n;i++)
		{
			System.out.println("Document "+i+"--->"+finals[i]);
		}
		int[] ser=new int[100];
		for(int i=1;i<100;i++)
		{
			ser[i]=i;
		}
		int nemp;
		double demp;
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<i;j++)
			{
				if(finals[i]>finals[j])
				{
					demp=finals[i];
					finals[i]=finals[j];
					finals[j]=demp;
					nemp=ser[i];
					ser[i]=ser[j];
					ser[j]=nemp;
				}
			}
		}
		System.out.println("-------Order of document retrieval---------");
		for(int i=1;i<=n;i++)
		{
			if(finals[i]!=0.0){
			System.out.println("Document "+ser[i]);}
		}
		}
		catch(Exception e)
		{
			System.out.println("Exception found");
		}
		
		
		
		
		
	}

}
