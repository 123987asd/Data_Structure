package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class BIpartite_graphBFS {

	private static int[] xset=new int[9];
	private static int[] yset=new int[9];
	private  static	int[] source=new int[9];
	private  static 	int[] target=new int[9];
	private  static   int[][] edges=new int[9][9];
	private static boolean[] visted=null;
	public static void main(String[] args) {
		  readInit("/home/hu/data/graph/binary_graph");
		  BFS();
	}
	public static  void BFS()
	{
		int num=0;
		int[] path=new int[9];
		Queue<Integer> queue=new LinkedList<>();
		for(int i=0;i<source.length;i++)
		{
		
			visted=new boolean[9];
			if(source[i]!=0)
			{
				while(!queue.isEmpty()) queue.poll();
			  if(xset[i]==0)//还没有匹配上 进行 匹配 看是否能匹配上
			  {
				  queue.offer(i);
				  path[i]=-1;
				  boolean flag=false;
				  System.out.println("*************************分割线××××××××××××××××××××");
				  while(!queue.isEmpty()&&!flag)//只要匹配上就停止  或者增广路走完  可能也没有匹配上
				  {
					  int xnode=queue.poll(); 
					  for(int j=0;j<target.length;j++)
					  {
						  int targetId=target[j];
						  if(targetId!=0)
						  {
						  if(edges[xnode][targetId]==1&&!visted[targetId])
						  {
							  visted[targetId]=true;
							  queue.offer(yset[targetId]);//这里的bfs进入队列 就和dfs的DFS(yset(targetid))是一样的 你要知道走增广路时 你需要什么
							  //这时候要查看y是否已经被匹配 
							  if(yset[targetId]>0)//如果是dfs的话 会传入他的匹配节点向上找 所以 也应该记录匹配的节点
							  {
								  path[yset[targetId]]=xnode;
							  }
							  else
							  {
								  flag=true;
									int d=xnode,e=targetId; //可以画图 举例 x1,x2 有大大的道理
			    					while(d!=-1)
			    					{
			    						int t=xset[d];
			    						yset[e]=d;
			    						xset[d]=e; //匹配完这个 匹配他的上一个  和找路一样 这个找到了 找上一个 有个链接
			    					   d=path[d];
			    					   e=t;
			    					}
			    					break;//只要你匹配到了 你就应该停止 要不然你后面 满足条件的会把你的覆盖
							  }
						  }
					  }
					  }
				  }
				  if(xset[i]>0)
				  {
					  num++;
				  }
			  }
			}
		}
		System.out.println(num);
     for(int i=0;i<xset.length;i++)
     {
    	 if(xset[i]!=0)
    	 {
    		 System.out.println(xset[i]);
    	 }
     }
	}
	public static void readInit(String path)
	{
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			String tmp=br.readLine();
			String[] array;
			int i=0;//如果文件里面的全是 String类型的 需要再写一个分配下标的函数
			while(tmp!=null)
			{
				array=tmp.split("\t");
				int sourceId=Integer.parseInt(array[0]);
				int targetId=Integer.parseInt(array[1]);
			    source[sourceId]=sourceId;
			    target[targetId]=targetId;
			   edges[sourceId][targetId]=1;
			   tmp=br.readLine();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("感觉有问题的代码可以写到异常里面");
			e.printStackTrace();
		}
}
}
