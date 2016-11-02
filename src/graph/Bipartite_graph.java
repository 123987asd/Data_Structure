package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.css.ViewCSS;


//使用一个简单的数组 记录一些 路径 有的时候是可以使用的
public class Bipartite_graph {
	
	private static int[] yset=new int[9];
	private static int[] xset=new int[9];
	private  static	int[] source=new int[9];
	private  static 	int[] target=new int[9];
	private  static   int[][] edges=new int[9][9];
	private static boolean[] visted=null;
	/*
	 * 二分图的最大匹配的基本思路(刚开始的时候都是非匹配点) 从x集合一个节点出发 找匹配点
	 * 如果你所要匹配的节点被 其他节点匹配 这时候开始走增广路 直到找到非匹配点
	 * 增广路就是交互路线 先非匹配节点 非匹配边 匹配节点 匹配边 依次走下去 这里面的技巧是 假如x3的匹配点 y1 被 x1匹配了就从x1接着向下走
	 * 因为这样符合 增广路的特点
	 */
	public static void main(String[] args) {
		
       readInit("/home/hu/data/graph/binary_graph");
       int num=0;
       for(int x=1;x<source.length;x++)
       {
    	   visted=new boolean[9];  //每次找的时候 更新一下 但在一次的查找中 做访问 控制
    	   if (DFSpath(source[x]))
    	   { num++ ;//分成 x y 两个集合
    	   }
    	   }
		System.out.println(num);
	}
	public static boolean  DFSpath(int sourceId)
	{//尽量匹配上 只要匹配上 只要走 增广路就行了  无论 你怎么走  只要有符合的路就行 画图推导
		boolean flag=false;
		for(int y=1;y<target.length;y++)
		{
			int targetId=target[y];
	  if(targetId!=0)//这个是对于特殊的数据 测试用的
	  {
			if(edges[sourceId][targetId]==1&&!visted[targetId]) //访问 控制符的使用 和 标志符的使用
			{
				visted[targetId]=true;
                  if(yset[targetId]==0 || DFSpath(yset[targetId]))//明晚非递归  //递归的去里面找 就是看看 还有没有增广线
                  {//不满足 taggetId 接着++
                	  System.out.println(sourceId+"============"+targetId);//记录最新的匹配对  源节点相同的 更新路线
                	  yset[targetId]=sourceId;//这就是匹配上了  
                	flag= true;
                	break;  
                  }      
			}	
	  }//核心思想 要不 有匹配上的  要不 有增广路
		}
		return flag;
	}
	public static void BFSpath()//用prev来记录前驱节点 记录路径
	{
    int[] prev=new int[9];
    Queue<Integer> queue=new LinkedList<>();
    for(int i=0;i<source.length;i++)
    {
    	while(!queue.isEmpty()) queue.poll();
    	visted=new boolean[9];
    	if(xset[i]==0)
    	{
    		queue.offer(i);
    		prev[i]=-1;
    		boolean flag=false;
    		while(!queue.isEmpty()&&!flag)
    		{
    			int xnode=queue.poll();
    		for(int j=1;j<target.length;j++)
    		{
    			int targetId=target[j];
    			
    			if(edges[xnode][targetId]==1&&!visted[targetId])
    			{
    				visted[targetId]=true;
    				queue.offer(yset[targetId]);//为了实现走增广路 和 dfs递归遍历那里一样 
    				
    				if(yset[targetId]==0)//还不是匹配点  
    				{
    					//一旦 进入 就匹配了 不用再走了 做一个访问控制
    					flag=true;
    					int d=xnode,e=targetId; //可以画图 举例 x1,x2 有大大的道理
    					while(d!=-1)
    					{
    						int t=xset[d];
    						yset[e]=d;
    						xset[d]=e; //匹配完这个 匹配他的上一个  和找路一样 这个找到了 找上一个 有个链接
    					   d=prev[d];
    					   e=t;
    					}
    				}
    				else if(yset[targetId]!=0)//已经是匹配点了
    				{
    					prev[yset[targetId]]=xnode;  //形成 路程记录的关系 根据 路程移动的关系 重新匹配
    				}
    			}
    		}
    	}
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
	/*
	 * 补充定义和定理：

最大匹配数：最大匹配的匹配边的数目
最小点覆盖数：选取最少的点，使任意一条边至少有一个端点被选择
最大独立数：选取最多的点，使任意所选两点均不相连
最小路径覆盖数：对于一个 DAG（有向无环图），选取最少条路径，使得每个顶点属于且仅属于一条路径。路径长可以为 0（即单个点）。

定理1：最大匹配数 = 最小点覆盖数（这是 Konig 定理）
定理2：最大匹配数 = 最大独立数
定理3：最小路径覆盖数 = 顶点数 – 最大匹配数
	 * 
	 */
}
