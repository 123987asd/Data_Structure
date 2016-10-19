package algorithm;

public class Huisu {

	/*
	 * 迷宫问题 只能向前探寻 不能回退到上一步
	 */

	/*
	 * 递归是层层进 层层出 按顺序执行程序 满足条件就进入 不满足就退出这部分
	 * 向下接着执行
	 */
    private int[][] array = {
            {0, 0, 0, 0, 1, 0, 1, 0},
            {1, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 1, 0, 1, 1, 0, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0}

    };
    private int maxLine = 8;
    private int maxRow = 9;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        new Huisu().check(0, 0);
        System.out.println(System.currentTimeMillis());
    }

    private void check(int i, int j) {
        //如果到达右下角出口
        if (i == maxRow - 1 && j == maxLine - 1) {
            print();
            return;
        }

        //向右走
        if (canMove(i, j, i, j + 1)) {
            array[i][j] = 5;
            System.out.println("start============"+i+"========"+j);
            check(i, j + 1);//递归的一直往右走 不满足条件了 就退出它的本次递归
            System.out.println("end============"+i+"========"+j);
            array[i][j] = 0;
        }
       
        //向左走
        if (canMove(i, j, i, j - 1)) {
            array[i][j] = 5;
            check(i, j - 1);
            array[i][j] = 0;
        }
        //向下走
        if (canMove(i, j, i + 1, j)) {
            array[i][j] = 5;
            check(i + 1, j);
            array[i][j] = 0;
        }
        //向上走
        if (canMove(i, j, i - 1, j)) {
            array[i][j] = 5;
            check(i - 1, j);
            array[i][j] = 0;
        }
       
    }

    private boolean canMove(int i, int j, int targetI, int targetJ) {
//        System.out.println("从第" + (i + 1) + "行第" + (j + 1) + "列，走到第" + (targetI + 1) + "行第" + (targetJ + 1) + "列");
        if (targetI < 0 || targetJ < 0 || targetI >= maxRow || targetJ >= maxLine) {
//            System.out.println("到达最左边或最右边，失败了");
            return false;
        }
        if (array[targetI][targetJ] == 1) {
//            System.out.println("目标是墙，失败了");
            return false;
        }
        //避免在两个空格间来回走
        if (array[targetI][targetJ] == 5) {
//            System.out.println("来回走，失败了");
            return false;
        }

        return true;
    }

    private void print() {
       // System.out.println("得到一个解：");
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxLine; j++) {
              System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}