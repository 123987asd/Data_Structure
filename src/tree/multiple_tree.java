package tree;

import java.util.*;

class TreeNode {

	private int value;
	private int level;
	private TreeNode parent;
	private List<TreeNode> childList;


	public TreeNode(int value) {
		this.value = value;
	}

	public TreeNode(int value, TreeNode parent) {
		this.value = value;
		this.parent = parent;
		this.level = 0;
	}

	public TreeNode(int value, int level, TreeNode parent) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.level = level;
		this.parent = parent;
	}

	public void addNode(TreeNode node) {
		if (getchildList() == null) {
			childList = new LinkedList<>();
		} else {
			childList = getchildList();
		}
		childList.add(node);
	}

	public List<TreeNode> getchildList() {

		return childList;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

}
public class multiple_tree {
	private static TreeNode  root = new TreeNode(1);

	public static void main(String[] args) {
		multiple_tree tree = new multiple_tree();
		tree.add(9, 15);
		tree.add(6, 15);
		tree.add(5, 9);
		tree.add(4, 9);
		tree.add(3, 6);
		tree.add(3, 6);
		tree.add(2, 1);
		tree.add(1, 3);
		System.out.println(tree.expense(root));
	}

	public void add(int child, int parent) {
		TreeNode parentNode = findParent(parent);
		TreeNode childNode=new TreeNode(child, parentNode);
		level(childNode);
		parentNode.addNode(childNode);
	}

	public TreeNode findParent(int value) {
		// 从根开始查找 找到元素值相同的 多叉树 用递归的比较少 些 二叉树用的递归多一些
		TreeNode node = null;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (queue != null) {
			//这块 设定一个值 就可以求节点的个数
			node = queue.poll();
			if (node != null) {
				if (node.getValue() == value) {
					break;
				} else {
					if (node.getchildList() != null) {
						List<TreeNode> childList = node.getchildList();
						for (int i = 0; i < childList.size(); i++) {
							queue.offer(childList.get(i));
						}
					}
				}
			}
		}
		return node;
	}

	public void level(TreeNode node) {

		int level = 1;
		// 改成非递归 使用栈来实现 队列 是先进先出 栈树先进后出和递归有点类似
		// 在这里用 队列也是可以的
		TreeNode parent = node.getParent();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(parent);
		while (stack != null) {
			TreeNode Pnode = stack.pop();
			level++;
			if (Pnode.getParent() == null) {
				break;
			}
			parent = Pnode.getParent();
			stack.push(parent);

		}
		node.setLevel(level);
	}

	// 层序遍历 对代价
	public int expense(TreeNode node) {
		int expenses = 0;
		// 用子类的方法 还是用父类的方法 看前面声明的
		Queue<TreeNode> queue = new LinkedList<>();
		expenses = expenses + node.getValue() * node.getLevel();
		queue.offer(node);
		while (queue != null) {
			node = queue.poll();
			if (node == null) {
				break;
			}
			if (node.getchildList() != null) {
				List<TreeNode> childList = node.getchildList();
				int size = childList.size();

				for (int i = 0; i < size; i++) {
					TreeNode child = childList.get(i);
					expenses = expenses + child.getValue() * child.getLevel();
					queue.offer(child);
				}
			}
		}

		System.out.println(expenses);
		return expenses;
	}
}
