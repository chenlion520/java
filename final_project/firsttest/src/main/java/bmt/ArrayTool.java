package bmt;

public class ArrayTool {
    public static int[][][] sortArray(int[][][] rawdata){
        int[][][] ReturnData = rawdata;
        for(int i = 0; i < ReturnData.length; i++){
            for(int j = 0; j < ReturnData[i].length; j++){
                int max = j;
                for(int k = j + 1; k < ReturnData[i].length; k++){
                    if(ReturnData[i][max][1] < ReturnData[i][k][1] || (ReturnData[i][max][1] == ReturnData[i][k][1] && ReturnData[i][max][0] > ReturnData[i][k][0])){
                        max = k;
                    }
                }
                int[] tmp = ReturnData[i][j];
                ReturnData[i][j] = ReturnData[i][max];
                ReturnData[i][max] = tmp;
            }
        }
        return ReturnData;
    }

    public static int[][] sortArray(int[][] rawdata){
        int[][] ReturnData = new int[rawdata.length][2];
        for(int i = 0; i < ReturnData.length; i++){
            ReturnData[i] = rawdata[i];
        }
        for(int i = 0; i < ReturnData.length; i++){
            int max = i;
            for(int j = i + 1; j < ReturnData.length; j++){
                if(ReturnData[j][1] > ReturnData[max][1] || (ReturnData[j][1] == ReturnData[max][1] && ReturnData[j][0] < ReturnData[max][0])){
                    max = j;
                }
            }
            int[] tmp = ReturnData[i];
            ReturnData[i] = ReturnData[max];
            ReturnData[max] = tmp;
        }
        return ReturnData;
    }

    public static double[][] sortArray(double[][] rawdata){
        double[][] ReturnData = new double[rawdata.length][2];
        for(int i = 0; i < ReturnData.length; i++){
            ReturnData[i] = rawdata[i];
        }
        for(int i = 0; i < ReturnData.length; i++){
            int max = i;
            for(int j = i + 1; j < ReturnData.length; j++){
                if(ReturnData[j][1] > ReturnData[max][1] || (ReturnData[j][1] == ReturnData[max][1] && ReturnData[j][0] < ReturnData[max][0])){
                    max = j;
                }
            }
            double[] tmp = ReturnData[i];
            ReturnData[i] = ReturnData[max];
            ReturnData[max] = tmp;
        }
        return ReturnData;
    }

    public static int[][] reverseArray2D(int[][] rawdata){
        int[][] ReturnData = new int[rawdata.length][2];
        for(int i = 0; i < ReturnData.length; i++){
            ReturnData[i] = rawdata[i];
        }
        int l = ReturnData.length - 1;
        for(int i = 0; i < ReturnData.length/2; i++){
            int[] tmp = ReturnData[i];
            ReturnData[i] = ReturnData[l - i];
            ReturnData[l - i] = tmp;
        }
        return ReturnData;
    }
}