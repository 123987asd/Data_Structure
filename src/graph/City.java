package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class City {

	private boolean[] vistied=new boolean[100];
	private static	int[] dist=new int[100];
	private static  int[] per=new int[100];
	private static	int[][] city=new int[100][100];
	private static	int minLen=100;
	public static void main(String[] args) {
		
		City city=new City();
		city.read();
	    city.dist(4, 5);
	    city.path(5);
		
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
	//有个出发点 依次找到这个出发点的最短距离 更新 距离 就是看不直达是不是更近
	public void dist(int target,int source)
	{
		
		dist[target]=0;
		for(int i=0;i<100;i++)
		{
			int min=minLen;//最短距离
			int mini=target;//对应的节点
			
			//找出最小的放到集合里面 找N次
			for(int j=1;j<100;j++)
			{
				if(vistied[j]==false&&dist[j]<min)
				{
					min=dist[j];
					mini=j;
				}
			}
			vistied[mini]=true;
			for(int j=1;j<100;j++)
			{
				if(vistied[j]==false)
				{
					if(dist[j]>dist[mini]+city[mini][j])
					{
						dist[j]=dist[mini]+city[mini][j];
						per[j]=mini;
					}
				}
			}
		}
		System.out.println(dist[source]);
	}
	public void path(int source)
	{
		if(per[source]!=0)
		{
			path(per[source]);
			System.out.println(per[source]);
		}
	}
}
