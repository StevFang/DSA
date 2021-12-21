package org.java.data.structure.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 压缩数据，提高存储效率（减小了IO）
 *
 * @author Administrator
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组11*11
        //0:表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 1;
        // 输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        // 将二维数组转稀疏数组
        // 1. 遍历原始数组，求得非0数据的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        // 2. 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为");
        for (int[] row : sparseArr) {
            System.out.printf("%d\t%d\t%d\t", row[0], row[1], row[2]);
            System.out.println();
        }

        File file = new File("D:\\map.data");
        if (file.exists()) {
            boolean suc = file.delete();
            if (!suc) {
                throw new RuntimeException("map.data文件删除失败");
            }
        }

        // 存储到文件
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\map.data", true))) {
            for (int[] row : sparseArr) {
                bw.write(Arrays.toString(row));
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("稀疏数组保存到文件map.data异常", e);
        }

        // 读取文件
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\map.data"))) {
            while (true) {
                String line = br.readLine();
                if (line == null || "".equals(line.trim())) {
                    break;
                }
                lines.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("从map.data读取数据异常", e);
        }
        int[][] sparseArr2 = new int[lines.size()][3];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] arr = line.substring(1, line.length() - 1).split(",");
            for (int j = 0; j < arr.length; j++) {
                sparseArr2[i][j] = Integer.parseInt(arr[j].trim());
            }
        }

        // 将稀疏数组恢复成原始的二维数组
        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        // 2. 再读取稀疏数组后几行的数据，并赋给原始的二维数组即可
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
