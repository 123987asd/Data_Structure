package tree;

import java.util.LinkedList;
import java.util.Queue;

class Node
{
	private int value;
	private Node left;
	private Node right;
	
	public Node(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}

	public Node(int value,Node left,Node right) {
		// TODO Auto-generated constructor stub
		this.value=value;
		this.left=left;
		this.right=right;
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
public class Binary_tree {

	private static Node root=new Node(1);
	public static void main(String[] args) {
		Binary_tree tree=new Binary_tree();
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.preorder(root);
	}
	//二叉树 添加 节点的特点 就是 找 左右树 那个是 空的 
	public void add(int value)
	{
		Node child=new Node(value);
		Node parent=find();
		if(parent.getLeft()==null)
		{
			parent.setLeft(child);
		}
		else if(parent.getRight()==null)
		{
			parent.setRight(child);
		}
	}
	//先使用queue  再说 递归  
	public Node find()
	{
		Node node=null;
		Queue<Node> queue=new LinkedList<>();
		queue.offer(root);
		while(queue!=null)
		{
			node=queue.poll();
			Node left=node.getLeft();
			Node right=node.getRight();
			queue.offer(left);
			queue.offer(right);
			if(left==null)
			{
				break;
			}
			else if(right==null)
			{
				break;
			}
		}
		return node;
	}
	public void preorder(Node node)
	{
		if(node!=null)
		{
			System.out.println(node.getValue());
			preorder(node.getLeft());
			preorder(node.getRight());
		}
	}
}
