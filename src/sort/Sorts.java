package sort;

public class Sorts {

	
	public static void main(String[] args) {
		
		int[] a={1,5,6,7,2,3,8,9};
		//int b[]=insertSort(a);
		//int[] b=Sort(a);
		int[] b=shellSort(a);
		for(int i:b)
		{
			System.out.println(i);
		}
		
	}
	
	//直接插入排序的思想 主要是 对已经排好顺序的队列 选择一个位置插入进去
	//第一个开始的元素是 第二数字 看他是放在第一个前面 还是后面
	public static int[] insertSort(int[] a)
	{
		for(int i=1;i<a.length;i++)
		{
			int temp=a[i]; //插入排序 的 每一个元素 只和它前面 排好序的队列元素 进行比较 
											//插入嘛 你就理解成指针的移动 移出一个空隙 不用都移动 满足条件才移动
		    int j;
			for(j=i-1;j>=0&&(temp<a[j]);j-- ) //这里使用 a[i] 就不对 而使用 temp就对 有时间思考一下
			{
		    	  a[j+1]=a[j];
			}
		 a[j+1]=temp; //值进行覆盖
		}
		return a;
	}
	//冒泡排序 和直接插入排序 都需要移动   另外一个选择排序 是找最大值或者最小值的下标 然后调整位置
	public static int[] Sort(int[] a)
	{
		for(int i=0;i<a.length-1;i++)
		{
			//表示走几趟
			for(int j=0;j<a.length-1;j++)
			{
				if(a[j+1]<a[j])//逐渐移动
				{
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
		return a;
	}
	//shell 排序是和直接插入排序思想是一样的 只是shell排序 跳动的步伐比较大 把所有的 i++ 改为i+d i--变为i-d
	public static int[] shellSort(int[] a)
	{
		 double d1=a.length;  
		    int temp=0;  
		    while(true){  
		        d1= Math.ceil(d1/2);  
		        int d=(int) d1;  
		        for(int x=0;x<d;x++){  
		            for(int i=x+d;i<a.length;i+=d){  
		                int j=i-d;  
		                temp=a[i];  
		                for(;j>=0&&temp<a[j];j-=d){  
		                a[j+d]=a[j];  
		                }  
		                a[j+d]=temp;  
		            }  
		        }  
		        if(d==1)  
		            break;  
		    }  
		return a;
	}
	
	//快排
    /** 
    * @param int[] 未排序数组 
    * @return int[] 排完序数组 
    */ 
     
    public int[] sortQuick(int[] array){ 
    return quickSort(array, 0, array.length-1); 
    } 
     
    private int[] quickSort(int[] arr, int low, int heigh) { 
    if (low < heigh) { 
    int division = partition(arr, low, heigh); 
    quickSort(arr, low, division - 1); 
    quickSort(arr, division + 1, heigh); 
    } 
    return arr; 
    } 
     
    // 分水岭,基位,左边的都比这个位置小,右边的都大 
    private int partition(int[] arr, int low, int heigh) { 
    int base = arr[low]; //用子表的第一个记录做枢轴(分水岭)记录 
    while (low < heigh) { //从表的两端交替向中间扫描 
    while (low < heigh && arr[heigh] >= base) { 
    heigh--; 
    } 
    // base 赋值给 当前 heigh 位,base 挪到(互换)到了这里,heigh位右边的都比base大 
    swap(arr, heigh, low); 
    while (low < heigh && arr[low] <= base) { 
    low++; 
    } 
    // 遇到左边比base值大的了,换位置 
    swap(arr, heigh, low); 
    } 
    // now low = heigh; 
    return low; 
    } 
     
    private void swap(int[] arr, int a, int b) { 
    int temp; 
    temp = arr[a]; 
    arr[a] = arr[b]; 
    arr[b] = temp; 
    } 
	//归并
    /** 
    * @param int[] 未排序数组 
    * @return int[] 排完序数组 
    */ 
    private int[] sort(int[] nums, int low, int high) { 
    int mid = (low + high) / 2; 
    if (low < high) { 
    // 左边 
    sort(nums, low, mid); 
    // 右边 
    sort(nums, mid + 1, high); 
    // 左右归并 
    merge(nums, low, mid, high); 
    } 
    return nums; 
    } 
     
    private void merge(int[] nums, int low, int mid, int high) { 
    int[] temp = new int[high - low + 1]; 
    int i = low;// 左指针 
    int j = mid + 1;// 右指针 
    int k = 0; 
    // 把较小的数先移到新数组中 
    while (i <= mid && j <= high) { 
    if (nums[i] < nums[j]) { 
    temp[k++] = nums[i++]; 
    } else { 
    temp[k++] = nums[j++]; 
    } 
    } 
    // 把左边剩余的数移入数组 
    while (i <= mid) { 
    temp[k++] = nums[i++]; 
    } 
    // 把右边边剩余的数移入数组 
    while (j <= high) { 
    temp[k++] = nums[j++]; 
    } 
    // 把新数组中的数覆盖nums数组 
    for (int k2 = 0; k2 < temp.length; k2++) { 
    nums[k2 + low] = temp[k2]; 
    } 
    } 
     
    public int[] sortMerge(int[] array) { 
    return sort(array, 0, array.length - 1); 
    } 
}
