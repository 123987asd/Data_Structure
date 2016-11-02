package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//基本思想 读取的时候 用 一个map 做目标节点 和 源节点的映射 用另外一个MAP存储节点的邻居节点  这部可以计算节点的骨干度 并找出最大的边
//计算膨胀度 和扩展边的个数有关   可以利用边的组合 来计算 如果出现二组 源节点 目标节点 1-3 3-1这样 就不再是扩展边
//后者 第二种 方法 记录访问过的节点 看新加入的节点是不是和已访问的节点有边 有的话所有邻居节点个数减一 再把所用已访问节点邻居数加和

//现在正在思考的问题是 怎么样更好的从最大边开始 遍历 划分社区

//设置边缘节点的list
//划分社区 这样划分 为每一个社区 都创建一个set集合 从第一个几点 开始 设置队列 每次的出队表示新社区的开始 set重新初始化
//对于满足条件的节点加入社区  加入社区的时候判断 它是否在边缘节点的集合里面 在就去除 因为给他分配社区了 
//对这些边缘节点 做一个标志 在 发现结构洞的时候使用

//计算是否 满足加入一个社区的条件 

//存字符节点对 和对应的权重  这样 取邻居的时候 形成 节点对 找权重
public class Community {
private  static Map<Integer, LinkedList<Integer>> neighborMap=new HashMap<>();
private static List<String> edgesList=new LinkedList<>();
private static List<String> weightList=new LinkedList<>();
	public static void main(String[] args) {
		readData("/home/hu/data/graph/football_biaozhun.csv");
		cal();
	   write("/home/hu/data/graph/test.csv");
	   
	}
	public static void write(String filepath)
	{
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			fw=new FileWriter(filepath,true);
			bw=new BufferedWriter(fw);
			for(String first:weightList)
			{
				bw.write(first+"\n");
				bw.flush();
			}
	
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	public  static void readData(String filepath)
	{
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(filepath);
			br=new BufferedReader(fr);
			String tem=br.readLine();
			String[] array;
			while(tem!=null)
			{
				array=tem.split(",");
				int source=Integer.parseInt(array[0]);
				int target=Integer.parseInt(array[1]);
				put(source, target);
				put(target, source);
				edgesList.add(source+","+target);
				tem=br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(neighborMap.get(1));
	}
	public static void put(int source,int target)
	{
		LinkedList<Integer> node=null;
		if(neighborMap.get(source)!=null)
		{
			System.out.println(source);
			node=neighborMap.get(source);
			node.add(target);
			neighborMap.put(source, node);
		}
		else//if else 加括号
		{
      node=new LinkedList<Integer>();
		node.add(target);
		neighborMap.put(source, node);
		}
	}
   public static double calweight(int source,int target)
   {
	   LinkedList<Integer> A=neighborMap.get(source);
	   LinkedList<Integer> B=neighborMap.get(target);
	   int combinNum=0;
	   /*
	   LinkedList<Integer> copyA=A;
	   LinkedList<Integer> copyB=A;
	   A.addAll(B);//指向一个方向 A变化了 CopyA是不是也变化 做个实验
	   */
	   int DA=A.size();
	   int DB=B.size();
	   if(DA>=DB)
	   {
		   for(int i=0;i<DB;i++)
		   {
			  int value= B.get(i);
			  if(A.contains(value))
			  {
				  combinNum++;
			  }
		   }
	   }
	   else
	   {
		   for(int i=0;i<DA;i++)
		   {
			  int value= A.get(i);
			  if(B.contains(value))
			  {
				  combinNum++;
			  }
		   }
	   }
	double NO=(double)combinNum/(DA+DB-combinNum);
	double weight=(DA+DB)*NO+0.01;
	return weight;
   }
   public static void cal()
   {
	  String[] array;
		for(String first:edgesList)
		{
			array=first.split(",");
			int source=Integer.valueOf(array[0]);
			int target=Integer.valueOf(array[1]);
			weightList.add(source+","+target+","+calweight(source, target));
		}
   }
}
