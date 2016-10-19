package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class HuffNode {

	private int value;
	private String name;
	private HuffNode parent;
	private HuffNode left;
	private HuffNode right;
	private HuffNode child;
	private String code;

	public HuffNode(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public HuffNode(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public HuffNode(int value, HuffNode left, HuffNode right) {

		// TODO Auto-generated constructor stub
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public HuffNode getParent() {
		return parent;
	}

	public void setParent(HuffNode parent) {
		this.parent = parent;
	}

	public HuffNode getChild() {
		return child;
	}

	public void setChild(HuffNode child) {
		this.child = child;
	}

	public HuffNode getLeft() {
		return left;
	}

	public void setLeft(HuffNode left) {
		this.left = left;
	}

	public HuffNode getRight() {
		return right;
	}

	public void setRight(HuffNode right) {
		this.right = right;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Huffman {
	private List<HuffNode> hufflist;

	public Huffman() {
		// TODO Auto-generated constructor stub
		hufflist = new LinkedList<>();
	}

	public List<HuffNode> getHufflist() {
		return hufflist;
	}

	public void setHufflist(List<HuffNode> hufflist) {
		this.hufflist = hufflist;
	}

}

public class HuffmanTree {
	private static Huffman tree = new Huffman();
	private static HuffNode root = null;
	private static List<HuffNode> forest = new LinkedList<>();

	public static void main(String[] args) {
		HuffmanFile file = new HuffmanFile();
		file.readFile("/home/hu/data/tree/huffmanfile");
		file.order();
		List<Word> wordList = HuffmanFile.getWordList();
		System.out.println(wordList.size() + "===========");
		for (int i = 0; i < wordList.size(); i++) {
			int value = wordList.get(i).getCount();
			String name = wordList.get(i).getValue();
			HuffNode root = new HuffNode(value, name);
			tree.getHufflist().add(root);
			forest.add(i, root);
		}
		/*
		 * for(int i=0;i<5;i++) { HuffNode root=new HuffNode(i+1);
		 * tree.getHufflist().add(root); forest.add(i, root);; }
		 */
		HuffmanTree tree = new HuffmanTree();
		while (forest.size() > 1) {
			tree.CreateHuffman();
		}
		// tree.seqOrder(root);
		tree.HuffmanCode();
		tree.compress();
	}

	public void CreateHuffman() {
		HuffNode first = findMin();
		HuffNode second = findMin();
		int value = first.getValue() + second.getValue();
		root = new HuffNode(value, second, first);
		first.setParent(root);
		second.setParent(root);
		forest.add(root);

	}

	public HuffNode findMin() {
		int size = forest.size();
		int min = 10000;
		int flag = 0;
		for (int i = 0; i < size; i++) {
			HuffNode node = forest.get(i);
			int value = node.getValue();
			if (value < min) {
				min = value;
				flag = i;
			}
		}
		HuffNode miniNode = forest.get(flag);
		forest.remove(flag);
		return miniNode;
	}

	public void seqOrder(HuffNode node) {
		Queue<HuffNode> queue = new LinkedList<>();
		queue.offer(node);
		HuffNode left = null;
		HuffNode right = null;
		while (queue != null) {

			HuffNode first = queue.poll();
			if (first != null) {
				System.out.println(first.getValue());

				if (first.getLeft() != null) {
					left = first.getLeft();
					queue.offer(left);
				}
				if (first.getRight() != null) {
					right = first.getRight();
					queue.offer(right);
				}

			}
		}
	}

	public void HuffmanCode() {
		List<HuffNode> list = tree.getHufflist();
		String code = "";
		for (int i = 0; i < list.size(); i++) {
			HuffNode node = list.get(i);
			code = findPath(node);// 从叶子节点 逆向求编码 和 求该节点的高度类似（求该节点的高度是判断的父节点为空）
			// 在这里你可以判断 他是它父节点的左孩子 还是右孩子
			node.setCode(code);
		}
	}

	public String findPath(HuffNode node) {
		String code = "";
		HuffNode parent = node.getParent();
		Queue<HuffNode> queue = new LinkedList<>();
		queue.offer(parent);
		while (queue != null) {
			HuffNode parentNode = queue.poll();

			if (parentNode != null) {

				if (parentNode.getLeft().getValue() == node.getValue()) {

					code = code + "0";
					node = parentNode;
				} else if (parentNode.getRight().getValue() == node.getValue()) {

					code = code + "1";
					node = parentNode;
				}
				if (parentNode.getParent() == null) {
					break;
				}

				parent = parentNode.getParent();
				queue.offer(parent);

			}
		}

		return code;
	}
	/*
	 * 将得到的哈弗曼编码 最后再把所有字节的编码依次末尾相加合成一个10字符串 八位为一组
	 * 创建一个压缩文件，先将码表的大小写入文件，再将码表写入文件（码表里还有每个字节的哈夫曼编码长度的信息）。
        7、最后将之前生成的字节数组写入文件（文件的主要信息）。 
	 */
	
	public void compress()
	{

		Byte[] code=new Byte[4000];//字节数组
       String sumcode="";
		List<HuffNode> list = tree.getHufflist();
		for(int i=0;i<list.size();i++)
		{ 
		 sumcode=sumcode+list.get(i).getCode();
		}
		int length=sumcode.length()/8;
		int remainder=sumcode.length()%8;
		for(int i=0;i<8-remainder;i++)
		{
			sumcode=sumcode+"0";
		}
	   System.out.println(length+"===============");
		for(int i=1;i<=length+1;i++)
		{
		  String subCode=sumcode.substring(0, i*8);
		  code[i-1]=CharArrayToByte(subCode);
		}
	}
	//八个bit 转成一个字节 这一个字节是八个bit  可以用数值表示
   public byte CharArrayToByte(String code)
   {
	   char[] c = code.toCharArray();// 将字符串str转化为字符数组c  
       int len = c.length;  
       byte[] b = new byte[len];  
       byte value = 0;  
       byte value_next;  
       for (int i = 0; i < len; i++) {  
           b[i] = Byte.parseByte(c[i] + "");  
           // System.out.println(b[i]);  
       }  
       for (int i = 0; i < len; i++) {  
           value_next = (byte) (b[i] * Math.pow(2, len - i - 1));// 幂计算  
           value = (byte) (value + value_next);  
       }  
       System.out.println(value);
       return value;
   }
}
