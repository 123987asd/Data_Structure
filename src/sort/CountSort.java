package sort;

public class CountSort{
    public static void main(String[]args){
      //排序的数组
        int a[]={100,93,97,92,96,99,92,89,93,97,90,94,92,95};
        int b[]=countSort(a);
        for(int i:b){
           System.out.print(i+"");
        }
      //  System.out.println();
    }
  public  static int[] countSort(int[] a)
  {
	  int b[]=new int[a.length];
	  int max=a[0];
	  int min=a[0];
	  for(int i:a)//对数组a的遍历
	  {
		  if(i>max)
		  {
			  max=i;
		  }
		  if(i<min)
		  {
			  min=i;
		  }
	  }//找出最大的数 和最小的数 
	  int k=max-min+1;//辅助数组的大小 辅助数组的作用是统计从min-max中间整数的个数
	  int[] c=new int[k];
	  for(int i=0;i<a.length;i++)
	  {
		  //对于相同的数字次数就叠加 对于没有出现的数字 就还是初始化的值 0
		  c[a[i]-min]+=1;//这里的a[i]-min的数字大小肯定在0=k之间(因为max-min为k)
	  }
	  for(int i=1;i<c.length;++i){
          c[i]=c[i]+c[i-1];//记录小于等于本身的个数
      }
	  for(int i=a.length-1;i>=0;--i){
         // b[--c[a[i]-min]]=a[i];
          //可以 分步拆解
   	     int pos=a[i]-min;//表示在c数组里面的位置 其实完全理解是位置是不对的 因为a[i]有重复的  如果a[i]的值越大 a[i]-min的数值就越大 在c数组里面的下标就大
   		  System.out.println(pos+"==================="); 
   		  System.out.println(c[pos]+"============================");
   		   int index=--c[pos]; //c[pos]表示 你的前面还有几个元素 
   		   //--第一是为了分配下标 一旦给你在数组b分配下标了  你就不和其他排序 小于和等于的数字就 减少一个
   		   //f第二为了下标0和第一个元素
   		   b[index]=a[i];
      }
	  return b;
	  
  }
    
}
