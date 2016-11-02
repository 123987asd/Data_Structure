package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

	private static double residualNetwork[][]=null;  
    private static double flowNetwork[][]=null;  
    public final int N;  
    int parent[];  
    public FordFulkerson(int N)  
    {  
        this.N=N;  
        parent=new int[N];  
    }  
	public static void main(String[] args) {
		 double graph[][]={{0,16,13,0,0,0},  
                 {0,0,10,12,0,0},  
                 {0,4,0,0,14,0},  
                 {0,0,9,0,0,20},  
                 {0,0,0,7,0,4},  
                 {0,0,0,0,0,0}};  
		 FordFulkerson f=new FordFulkerson(6);
	        System.out.println(f.edmondsKarpMaxFlow(graph,0,5));  

	}
	public double edmondsKarpMaxFlow(double graph[][],int s,int t)  
    {  
        int length=graph.length;  
        double f[][]=new double[length][length];  
        for(int i=0;i<length;i++)  
        {  
            Arrays.fill(f[i], 0);  //
        }  
        
        double r[][]=residualNetwork(graph,f);  
        double result=augmentPath(r,s,t);  
          
        double sum=0;  
          
        while(result!=-1)  
        {  //残留网络
            int cur=t;  
            while(cur!=s)  
            {  
                f[parent[cur]][cur]+=result;  
                f[cur][parent[cur]]=-f[parent[cur]][cur];  
                r[parent[cur]][cur]-=result;  
                r[cur][parent[cur]]+=result;  
                cur=parent[cur];  
            }  
              
            sum+=result;  
            result=augmentPath(r,s,t);  
        }  
          
        residualNetwork=r;  
        flowNetwork=f;  
          
        return sum;  
    }  

    /** 
     * deepCopy 
     * @param c 
     * @param f 
     * @return 
     */  
    private double[][] residualNetwork(double c[][],double f[][]) {  
        int length=c.length;  
        double r[][]=new double[length][length];  
        for(int i=0;i<length;i++)  
        {  
            for(int j=0;j<length;j++)  
            {  
                r[i][j]=c[i][j]-f[i][j];  
            }  
        }  
          
        return r;  
    }  

    /** 
     * 广度优先遍历，寻找增光路径，也是最短增广路径 
     * @param graph 
     * @param s 
     * @param t 
     * @return 
     */  
    public double augmentPath(double graph[][],int s,int t)  
    {  
          
        double maxflow=Integer.MAX_VALUE;  
        Arrays.fill(parent, -1);  //Arrays.fill填充数组

        Queue<Integer> queue=new LinkedList<Integer>();  
        queue.add(s);  
        parent[s]=s;  

        while(!queue.isEmpty())  
        {  
            int p=queue.poll();  
            if(p==t)  
            {  
                while(p!=s)  
                {  
                    if(maxflow>graph[parent[p]][p])  
                        maxflow=graph[parent[p]][p];  
                    p=parent[p];  
                }  
                break;  
            }  
            for(int i=0;i<graph.length;i++)  
            {  
                if(i!=p&&parent[i]==-1&&graph[p][i]>0)  
                {  
                    //flow[i]=Math.min(flow[p], graph[p][i]);  
                    parent[i]=p;  
                    queue.add(i);  
                }  
            }  
        }  
        if(parent[t]==-1)  
            return -1;  
        return  maxflow;  
          
    }  

    public double[][] getResidualNetwork() {  
        return residualNetwork;  
    }  

    public double[][] getFlowNetwork() {  
        return flowNetwork;  
    }  
}  
	

