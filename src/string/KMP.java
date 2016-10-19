package string;

public class KMP {

	/*
	 * KMP的算法思路 先把模式串做预处理 预处理的规则：
	 * 预处理的规则 先初始化 next[0]=-1 next[1]=0; 还有两个指针 一个设置为k=0 一个设置为j=1
	 * 第一步先找和第一字符相同字符 在找的过程中 如果第一个指针始终没有移动 也就是说它始终为0 那么next[j+1]=0;j++表示向后移动
	 *如果找到和第一个字符相同的字符 两个指针都移动  同时next[j+1]=k+1; 
	 *如果再移动 上述两个条件都不满足了 就进入 第三个条件k=next[k]
	 */
	public static void main(String[] args) {
		KMP kmp=new KMP();
	 boolean 	flag=kmp.KMP("ababcabdabcabca", "abcabc");
	 System.out.println(flag);
	}
	public int[]  getnext(String seq)
	{
		int[] next=new int[seq.length()];
		next[0]=-1;
		next[1]=0;//初始化
		int j=1;
		int k=0;
				while(j<seq.length()-1)
				{
		         if(seq.charAt(j)==seq.charAt(k))
		         {
		        	 next[j+1]=k+1;
		        	 j++;
		        	 k++;//不相等的时候 k始终不变 也就是k==0还没有出现和模式串 第一个字符相等的字符 然后 接着 让模式串的另一个指针向后移动
		         }else if(k==0)
		         {
		        	 next[j+1]=0;
		        	 j++;
		         }
		         else
		         {
		        	 k=next[k];
		         }
		         
				}
				//用来记录 从那个位置 起 前面已经有  多少个重复的字符数了  例如 字符串 abcabc next[5]=2 表示在下标5之前有两个相同的字符
				//如果 你和主字符串 比较的时候 你移动到下标为5的位置 证明你前面的一些字符串和主字符串是一样的 所以你不用从0下=下标开始了 从下标2开始
				//因为前面和主字符串是一样的
				return next;
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	public boolean KMP(String s,String seq)
	{
	   //随着指针的移动  模式匹配的字符串 不一定要从0开始
		int[] next=getnext(seq);
		Boolean in=false;
		int i=0;
		int j=0;
		while(i<s.length()&&j<seq.length())
		{
			if(j==-1||s.charAt(i)==seq.charAt(j))
			{
				i++;
				j++;
			}
			else if(j==0)
			{
				System.out.println("aaaasssa");
				i++;
			}
			else
			{
				System.out.println(j+"=============="); //你能移动多少步 证明你前面 你匹配多少
				j=next[j];
				System.out.println(j);
			}
		}
		if(j==seq.length()) in=true;
		return in;
	}
}
