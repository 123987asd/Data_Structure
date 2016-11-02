package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Prim {
    //prim 和城市间最短距离 是求一条连通的路径 
	private boolean[] vistied=new boolean[100];
	private static	int[] dist=new int[100];
	private static  int[] per=new int[100];
	private static	int[][] city=new int[100][100];
	private static	int minLen=100;
	public static void main(String[] args) {
		
		Prim prim=new Prim();
		prim.read();
		prim.prim(1);
	  
		
	}
	public void read()
	{
		
		for(int i=0;i<100;i++)
		{
			for(int j=0;j<100;j++)
			{
				if(i!=j)
				{
					city[i][j]=minLen;
				}
			}
		}
		for(int i=0;i<100;i++)
		{
			dist[i]=100;
		}
		FileReader fr=null;
		BufferedReader br=null;
		try {
			
			fr=new FileReader("/home/hu/spark_data/weight");
			br=new BufferedReader(fr);
			String[] array;
			String tem=br.readLine();
			while(tem!=null)
			{
				array=tem.split("\t");
				int source=Integer.parseInt(array[0]);
				int target=Integer.parseInt(array[1]);
				int weight=Integer.parseInt(array[2]);
				System.out.println(source+"\t"+target+"\t"+weight);
				city[source][target]=weight;
				city[target][source]=weight;
				tem=br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(br!=null)
			{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void prim(int target)
	{
		int totallength=0;
		dist[target]=0;
		
		for(int i=0;i<100;i++)
		{
			int min=minLen;
			int mini=target;
			for(int j=1;j<100;j++)
			{
				if(vistied[j]==false&&dist[j]<min)
				{
					min=dist[j];
					mini=j;
				}
			}
			vistied[mini]=true;
			totallength=totallength+dist[mini];
			for(int j=1;j<100;j++)
			{
				if(vistied[j]==false&&dist[j]>city[mini][j])
				{
					dist[j]=city[mini][j];
				}
			}
		}
		System.out.println(totallength);
	}
}
