package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * 注解一个又向无欢图的拓扑排序 不只是只有一个 深度遍历 和 广度遍历 都可以访问节点
 * 主要的拓扑排序是来判断无向图的 如果有环的话 在遍历节点的时候 计算的数量就要比真是的节点数量要多
 * 书上的解题思路是这样的 
 * 在AOV网中选择一个入度为零的节点 我的理解是他没有前驱节点 
 * 在AOV网中删除该该节点以及从他出发的边 (待考虑 这种方法 是不是可以用别的方法代替)
 * 上述的方法是为了 让其他的节点的入度减少
 */
class Node {
	private int value;
	private List<Node> per;// 前驱
	private List<Node> pos;// 后继

	public Node(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public Node(int value, List<Node> per, List<Node> pos) {
		super();
		this.value = value;
		this.per = per;
		this.pos = pos;
	}

	public void addpre(Node preNode) {
		if(per==null)
		{
			per=new LinkedList<>();
		}
		else
		{
		per = getPer();
		}
		per.add(preNode);
	}

	public void addpos(Node posNode) {
		if(pos==null)
		{
			pos=new LinkedList<>();
		}
		else
		{
		pos =getPos();
		}
		pos.add(posNode);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Node> getPer() {
		return per;
	}

	public void setPer(List<Node> per) {
		this.per = per;
	}

	public List<Node> getPos() {
		return pos;
	}

	public void setPos(List<Node> pos) {
		this.pos = pos;
	}

}

class Graph {
	private Set<Node> nodes;

	public Graph() {
		// TODO Auto-generated constructor stub
		nodes = new HashSet<>();
	}

	public void addNode(Node node) {
		nodes = getNodes();
		nodes.add(node);
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
}
public class DAGAOV {
	private List<Node> TopolSortList = new LinkedList<>();
	private Queue<Node> queue = new LinkedList<>();
	private static Graph graph = new Graph();
	private static FileReader fr = null;
	private static BufferedReader br = null;

	public static void main(String[] args) {
		String filepath = "/home/hu/data/graph/graph";
		DAGAOV aov = new DAGAOV();
		aov.readFile(filepath);
		aov.Tupu();
	}

	public void readFile(String filepath) {
		try {
			fr = new FileReader(filepath);
			br = new BufferedReader(fr);
			String line = br.readLine();
			String[] array;

			while (line != null) {
				array = line.split("\t");
				int sourcevalue = Integer.parseInt(array[0]);
				int targetvalue = Integer.parseInt(array[1]);
				Node pre = null;
				Node pos = null;
				Set<Node> set = graph.getNodes();
				for (Node first : set) {
					if (sourcevalue == first.getValue()) {
						pre = first;
					}
					if (targetvalue == first.getValue()) {
						pos = first;
					}
				}
				if (pre == null) {
					pre = new Node(sourcevalue);
					graph.addNode(pre);
				}
				if (pos == null) {
					pos = new Node(targetvalue);
					graph.addNode(pos);
				}
				pre.addpos(pos);// 前节点 添加后继
				pos.addpre(pre);// 后节点 添加前驱
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void Tupu()
	{
		// 这里分开
	
		int count=0;
		Set<Node> set = graph.getNodes();
	//	System.out.println(set.size()+"=============");
		for (Node first : set)// 三个for循环 怎么把算法复杂度降低 //分割出来 不是一定都写在一个for循环里面的
		{
			if (first.getPer() == null) {
				count++;
				queue.offer(first);// 然后得到他的后继节点 后继节点的前驱个数减1 因为加入队列 就把这条边删除
				TopolSortList.add(first);
			}
		}
		
		while (!queue.isEmpty()) {
			Node first = queue.poll();
			System.out.println(first.getValue()+"==============");
			if (first != null) {
				if(first.getPos()!=null)
				{
				List<Node> PosList = first.getPos();
				
				for (Node node : PosList) {
					//System.out.println(node.getValue()+"==========");
					
					node.getPer().remove(first);
					if (node.getPer().size() == 0) {
						count++;
						queue.offer(node);
						TopolSortList.add(node);
					}
				}
				}
			}
			
		}
	//	System.out.println(count+"===========");
		if(count<set.size())
		{
			System.out.println("有环路");
		}
		else if(count==set.size())
		{
			System.out.println("无环路");
		}
	}

}
