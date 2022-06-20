package bmt;

import java.util.*;

public class AnalysisTool539 extends ReadData{
    public AnalysisTool539(String datapath){
        super(datapath);
    }

    public int[][][] AnalysisbyDoWs(ArrayList<int[]> AnalysisData){ //星期幾開最多的牌
        int[][][] ReturnData;
        int i, j;
        int nextDoW = AnalysisData.get(AnalysisData.size() - 1)[1];
        if(nextDoW > 2009){
            ReturnData = new int[6][39][2];
        }
        else{
            ReturnData = new int[5][39][2];
        }
        //初始化
        for(i = 0; i < ReturnData.length; i++){
            for(j = 0; j < 39; j++){
            ReturnData[i][j][0] = j + 1;
        }
        }
        //插入
        for(int[] ele : AnalysisData){
            for(i = 5; i < 10; i++){
                ReturnData[ele[4] - 1][ele[i] - 1][1]++;
            }
        }
        return ReturnData;
    }

    public int[][] AnalysisbyDoW(ArrayList<int[]> AnalysisData){ //星期幾開最多的牌
        int[][] ReturnData = new int[39][2];
        int nextDoW = AnalysisData.get(AnalysisData.size() - 1)[4];
        if(AnalysisData.get(AnalysisData.size() - 1)[1] > 2009){
            nextDoW = nextDoW % 6 + 1;
        }
        else{
            nextDoW = nextDoW % 5 + 1;
        }
        //初始化
        for(int j = 0; j < 39; j++){
            ReturnData[j][0] = j + 1;
        }
        //插入
        for(int[] ele : AnalysisData){
            if(ele[4] == nextDoW){
                for(int i = 5; i < ele.length; i++){
                    ReturnData[ele[i] - 1][1]++;
                }
            }
        }
        return ReturnData;
    }

    public int[][] AnalysisbyPeriod(ArrayList<int[]> AnalysisData){   //一段時間內開出最多次的牌
        int[][] ReturnData = new int[39][2];
        //初始化
        for(int i = 0; i < 39; i++){
            ReturnData[i][0] = i + 1;
            ReturnData[i][1] = 0;
        }
        //插入
        for(int[] ele : AnalysisData){
            for(int i = 5; i < ele.length; i++){
                ReturnData[ele[i] - 1][1]++;
            }
        }
        return ReturnData;
    }

    public int[][][] AnalysisbyPartner(ArrayList<int[]> AnalysisData){ //最常搭在一起的兩個數字分析
        int[][][] ReturnData = new int[39][39][2];
        //初始化
        for(int i = 0; i < 39; i++){
            for(int j = 0; j < 39; j++){
                ReturnData[i][j][0] = j + 1;
                ReturnData[i][j][1] = 0;
            }
        }
        //插入
        for(int[] ele : AnalysisData){
            for(int i = 5; i < ele.length - 1; i++){
                for(int j = i + 1; j < ele.length; j++){
                    ReturnData[ele[i] - 1][ele[j] - 1][1]++;
                    ReturnData[ele[j] - 1][ele[i] - 1][1]++;
                }
            }
        }
        return ReturnData;
    }
    
    public int[][][] AnalysisbyNext(ArrayList<int[]> AnalysisData){ //某號碼下次最會開的號碼
        int[][][] ReturnData = new int[39][39][2];
        //初始化
        for(int i = 0; i < 39; i++){
            for(int j = 0; j < 39; j++){
                ReturnData[i][j][0] = j + 1;
                ReturnData[i][j][1] = 0;
            }
        }
        //插入
        int datalength = AnalysisData.get(0).length;
        for(int i = 0; i < AnalysisData.size() - 1; i++){
            int[] tmp1 = AnalysisData.get(i);
            int[] tmp = AnalysisData.get(i + 1);
            for(int j = 5; j < datalength; j++){
                for(int k = 5; k < datalength; k++){
                    ReturnData[tmp1[j] - 1][tmp[k] - 1][1]++;
                }
            }
        }
        return ReturnData;
    }

    public int[][] AnalysisbyMod10(ArrayList<int[]> AnalysisData){    //尾數分析
        int[][] ReturnData = new int[10][2];
        for(int i = 0; i < 10; i++){
            ReturnData[i][0] = i;
        }
        for(int[] ele : AnalysisData){
            for(int i = 5; i < ele.length; i++){
                ReturnData[ele[i] % 10][1]++;
            }
        }
        return ReturnData;
    }

    public int[][][] AnalysisbyMod10inDoWs(ArrayList<int[]> AnalysisData){
        int[][][] ReturnData = new int[6][10][2];
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 10; j++){
                ReturnData[i][j][0] = j;     
            }
        }
        for(int[] ele : AnalysisData){
            for(int i = 5; i < 10; i++){
                ReturnData[ele[4] - 1][ele[i] % 10][1]++;
            }
        }
        return ReturnData;
    }

    public int[][] AnalysisbyMod10inDoW(ArrayList<int[]> AnalysisData){
        int[][] ReturnData = new int[10][2];
        for(int i = 0; i < 10; i++) ReturnData[i][0] = i;
        int[] LastData = AnalysisData.get(AnalysisData.size() - 1);
        int NextDow = LastData[4] % 5;
        if(LastData[2] > 2009)  NextDow = LastData[4] % 6;
        for(int[] ele : AnalysisData){
            if(ele[4] == NextDow){
                for(int i = 5; i < ele.length; i++){
                    ReturnData[ele[i] % 10][1]++;
                }
            }
        }
        return ReturnData;
    }

    public int[][] AnalysisbyPlusMod10(ArrayList<int[]> AnalysisData){ //(個位數+十位數)%10分組
        int[][] ReturnData = new int[10][2];
        //初始化
        for(int i = 0; i < 10; i++){
            ReturnData[i][0] = i;
            ReturnData[i][1] = 0;
        }
        //插入
        for(int[] ele : AnalysisData){
            for(int i = 5; i < ele.length; i++){
                int tmp = ele[i];
                ReturnData[(tmp / 10 + tmp % 10) % 10][1] ++;
            }
        }
        return ReturnData;
    }

    public int[][] AnalysisbyFrequency(ArrayList<int[]> AnalysisData){
        int[][] ReturnData = new int[39][2];
        int i, j, k, l = 0;
        for(int t = 0; t < 39; t++){
            ReturnData[t][0] = 0;
            ReturnData[t][1] = 0;
        }
        for(i = 0; i < 39; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            l = 0;
            for(j = 0; j < AnalysisData.size(); j++){
                int[] D = AnalysisData.get(j);
                for(k = 5; k < D.length; k++){
                    if(D[k] == i + 1){
                        tmp.add(j - l);
                        l = j;
                    }
                }
            }
            int tot = 0;
            for(int ele : tmp){
                tot += ele;
            }
            if(tmp.size() == 0){
                ReturnData[i][0] = 10000;
                ReturnData[i][0] = 10000;
            }
            else{
                tot /= tmp.size();
                ReturnData[i][0] = tot;     //平均幾期開這個號碼
                ReturnData[i][1] = tot - j + l;   //預測還需幾期才會再開,若出現1則代表下期很大的機會出現,負數代表應該要開了但還沒開,數字過大代表失準
            }   
        }
        return ReturnData;
    }

    public int[] sameNumopen(ArrayList<int[]> AnalysisData){
        int[] ReturnData = new int[5];
        int i, j, count = 0;
        for(i = 0; i < 39; i++){
            count = 0;
            for(int[] ele : AnalysisData){
                for(j = 5; j < ele.length; j++){
                    if(ele[j] == i + 1){
                        count++;
                        if(count > 1){
                            ReturnData[count - 2]++;
                        }
                        break;
                    }
                }
                if(j == ele.length){
                    count = 0;
                }
            }
        }
        return ReturnData;
    }

    public int[][] Analysis(){
        
        int DataSize = getLotteryData().size();
        int[] LastDay = getLotteryData().get(DataSize - 1);
        //System.out.println(LastDay[0]);
        int EY = LastDay[1], EM = LastDay[2];
        int i, j, k;
        int[][] powerNumber = new int[39][2];
        for(i = 0; i < 39; i++){
            powerNumber[i][0] = i + 1;
        }

        //////建立前1,2,3,4,8個月的資料///////
        ArrayList<int[]> datafor8m = getLotteryDatabyD(EY - 1 + EM / 9, (EM + 3) % 12 + 1, EY, EM);
        ArrayList<int[]> datafor1m = getLotteryDatabyD(EY - 1 + (EM + 12) / 14, (EM + 10) % 12 + 1, EY, EM);
        ArrayList<int[]> datafor2m = getLotteryDatabyM(EY - 1 + (EM + 12) / 15, (EM +  9) % 12 + 1);
        ArrayList<int[]> datafor3m = getLotteryDatabyM(EY - 1 + (EM + 12) / 16, (EM +  8) % 12 + 1);
        ArrayList<int[]> datafor4m = getLotteryDatabyM(EY - 1 + (EM + 12) / 17, (EM +  7) % 12 + 1);

        //////用前一期預測下一期//////
        int[][][] dataAnalysisbyNext = ArrayTool.sortArray(AnalysisbyNext(datafor8m));
        int[][]   PowerNextNumber = new int[39][2];
        for(i = 0; i < 39; i++) PowerNextNumber[i][0] = i + 1;
        for(i = 5; i < LastDay.length; i++){//積分每個資料的前3名，得分為(10-名次)，(未達第3名超過5個資料就不往下)
            k = 0; j = 0;
            int tmp = LastDay[i] - 1;
            while(j < 3){
                PowerNextNumber[dataAnalysisbyNext[tmp][k][0] - 1][1] += (10 - j);
                if(dataAnalysisbyNext[tmp][k][1] != dataAnalysisbyNext[tmp][k + 1][1]){
                    if(k >= 5)   j+=1000;
                    else        j++;
                }
                k++;
            }
        }
        PowerNextNumber = ArrayTool.sortArray(PowerNextNumber);  
        i = 0;
        while(PowerNextNumber[i][1] > 0){
            powerNumber[PowerNextNumber[i][0] - 1][1] += (PowerNextNumber[i][1] / 10 + 1) * 10;
            i++;
        }

        //////用星期幾預測//////////////
        int[][][] dataAnalysisbyDoW = new int[5][][];
        int[][]   recordOfAnalysisDoW = new int[39][2];
        for(i = 0; i < 39; i++){//init...
            recordOfAnalysisDoW[i][0] = i + 1;
        }
        dataAnalysisbyDoW[0] = ArrayTool.sortArray(AnalysisbyDoW(datafor8m));
        j = 0;  k = 0;
        while(j < 6 && k < 39){
            if(j < 3){
                powerNumber[dataAnalysisbyDoW[0][k][0] - 1][1] += 15;
            }
            if(j > 2 && j < 6){
                powerNumber[dataAnalysisbyDoW[0][k][0] - 1][1] += 7;
            }
            if(k < 38 && dataAnalysisbyDoW[0][k][1] != dataAnalysisbyDoW[0][k + 1][1]){
                j++;
            }
            k++;
        }

        ///////星期幾趨勢預測//////////
        dataAnalysisbyDoW[1] = ArrayTool.sortArray(AnalysisbyDoW(datafor1m));
        dataAnalysisbyDoW[2] = ArrayTool.sortArray(AnalysisbyDoW(datafor2m));
        dataAnalysisbyDoW[3] = ArrayTool.sortArray(AnalysisbyDoW(datafor3m));
        dataAnalysisbyDoW[4] = ArrayTool.sortArray(AnalysisbyDoW(datafor4m));
        int[][] recordOfDoWtendency = new int[39][2];
        for(i = 0; i < 39; i++) recordOfDoWtendency[i][0] = i + 1;      //init....
        for(i = 1; i < 5; i++){//1~4個月的累積得分，分數為(10-名次)，取前3，未到第3名已取超過5個則不往下取
            j = 0; k = 0;
            while(dataAnalysisbyDoW[i][k][1] > 0){
                recordOfDoWtendency[dataAnalysisbyDoW[i][k][0] - 1][1] += (10 - j);
                if(dataAnalysisbyDoW[i][k][1] != dataAnalysisbyDoW[i][k + 1][1]){
                    if(k >= 5)  j+= 1000;
                    else        j++;
                }
                if(j > 3)   break;
                k++;
            }
        }
        for(i = 0; i < 38; i++){//sort....
            int max = i;
            for(j = i + 1; j < 39; j++){
                if(recordOfDoWtendency[j][1] > recordOfDoWtendency[max][1] || (recordOfDoWtendency[j][1] == recordOfDoWtendency[max][1] && recordOfDoWtendency[j][0] < recordOfDoWtendency[max][0])){
                    max = j;
                }
            }
            int[] tmp = recordOfDoWtendency[i];
            recordOfDoWtendency[i] = recordOfDoWtendency[max];
            recordOfDoWtendency[max] = tmp;
        }
        i = 0;
        while(recordOfDoWtendency[i][1] > 0){
            powerNumber[recordOfDoWtendency[i][0] - 1][1] += recordOfDoWtendency[i][1] / 2;
            i++;
        }

        //////8個月內開最多次的號碼//////////////
        int[][][] dataAnalysisbyPeriod = new int[5][][];
        dataAnalysisbyPeriod[0] = ArrayTool.sortArray(AnalysisbyPeriod(datafor8m));
        i = 0;
        while(i < 39){//printtest
            if(dataAnalysisbyPeriod[0][i][1] >= 25){
                powerNumber[dataAnalysisbyPeriod[0][i][0] - 1][1] += (dataAnalysisbyPeriod[0][i][1] - 25) * 5 - 10;
            }
            else{
                powerNumber[dataAnalysisbyPeriod[0][i][0] - 1][1] -= 10;
            }
            i++;
        }
        

        /////1~4個月的累積開最多次號碼的趨勢///////
        dataAnalysisbyPeriod[1] = ArrayTool.sortArray(AnalysisbyPeriod(datafor1m));
        dataAnalysisbyPeriod[2] = ArrayTool.sortArray(AnalysisbyPeriod(datafor2m));
        dataAnalysisbyPeriod[3] = ArrayTool.sortArray(AnalysisbyPeriod(datafor3m));
        dataAnalysisbyPeriod[4] = ArrayTool.sortArray(AnalysisbyPeriod(datafor4m));
        int[][] recordOfPeriodtend = new int[39][2];
        for(i = 0; i < 39; i++) recordOfPeriodtend[i][0] = i + 1; //init
        for(i = 1; i < 5; i++){//積分
            j = 0; k = 0;
            while(j < 3){
                recordOfPeriodtend[dataAnalysisbyPeriod[i][k][0] - 1][1] += (10 - j);
                if(dataAnalysisbyPeriod[i][k][1] != dataAnalysisbyPeriod[i][k + 1][1]){
                    if(k > 5)   j += 1000;
                    else        j++;
                }
                k++;
            }
        }
        for(i = 0; i < 38; i++){//sort
            int max = i;
            for(j = i + 1; j < 39; j++){
                if(recordOfPeriodtend[j][1] > recordOfPeriodtend[max][1] || (recordOfPeriodtend[j][1] == recordOfPeriodtend[max][1] && recordOfPeriodtend[j][0] < recordOfPeriodtend[max][0])){
                    max = j;
                }
            }
            int[] tmp = recordOfPeriodtend[i];
            recordOfPeriodtend[i] = recordOfPeriodtend[max];
            recordOfPeriodtend[max] = tmp;
        }
        i = 0;
        while(recordOfPeriodtend[i][1] > 0){//printtest
            powerNumber[recordOfPeriodtend[i][0] - 1][1] += recordOfPeriodtend[i][1] / 2;
            i++;
        }

        //////尾數////////////////////////////////
        int[][][] dataAnalysisbyMod10 = new int[5][][];
        dataAnalysisbyMod10[0] = AnalysisbyMod10(datafor8m);

        /////尾數趨勢///////////////////////////////
        dataAnalysisbyMod10[1] = ArrayTool.sortArray(AnalysisbyMod10(datafor1m));
        dataAnalysisbyMod10[2] = ArrayTool.sortArray(AnalysisbyMod10(datafor2m));
        dataAnalysisbyMod10[3] = ArrayTool.sortArray(AnalysisbyMod10(datafor3m));
        dataAnalysisbyMod10[4] = ArrayTool.sortArray(AnalysisbyMod10(datafor4m));
        int[][] recordOfMod10 = new int[10][2];
        for(i = 0; i < 10; i++) recordOfMod10[i][0] = i;
        for(i = 1; i < 5; i++){
            j = 0;  k = 0;
            while(j < 3){
                recordOfMod10[dataAnalysisbyMod10[i][k][0]][1] += (10 - j);
                if(dataAnalysisbyMod10[i][k][1] != dataAnalysisbyMod10[i][k + 1][1]){
                    if(k > 5)   j+=1000;
                    else        j++;
                }
                k++;
            }
        }

        /////星期n最會開的尾數/////////////////////////
        int[][] dataAnalysisbyMod10inDoW = AnalysisbyMod10inDoW(datafor8m);
        double[][] dataMod10inDoWpercent = new double[10][2];
        for(i = 0; i < 10; i++){
            for(j = 0; j < 10; j++){
                if(dataAnalysisbyMod10[0][i][0] == dataAnalysisbyMod10inDoW[j][0]){
                    dataMod10inDoWpercent[i][0] = dataAnalysisbyMod10[0][i][0];
                    dataMod10inDoWpercent[i][1] = (double)(dataAnalysisbyMod10inDoW[j][1]) * 100 / dataAnalysisbyMod10[0][i][1];
                }
            }
        }
        int nowtail = 0;
        for(i = 0; i < 39; i++){
            nowtail = i % 10;
            powerNumber[i][1] += (dataAnalysisbyMod10[0][nowtail][1] - 100 + recordOfMod10[nowtail][1] / 2 + (dataAnalysisbyMod10inDoW[nowtail][1] - 18) * (4 + (int)(dataMod10inDoWpercent[nowtail][1] - 18)));
        }

        powerNumber = ArrayTool.sortArray(powerNumber);
        return powerNumber;
    }

}
    