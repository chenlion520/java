package bmt;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.*;

public class ReadData {
    protected ArrayList<int[]> LotteryData;    //樂透資料  格式 num year month day DoW n1 n2 n3 n4 n5 ...
    protected ArrayList<int[]> YearTable;         //建立表格以快速查詢特定日期資料
    protected int UpperYear;                       //檔案最晚一筆資料的時間
    protected int UpperMonth;
    protected int LowerYear;                      //檔案最早一筆資料的時間
    protected int LowerMonth;

    public ReadData(String datapath){    //從路徑讀取檔案建立資料
        LotteryData = new ArrayList<int[]>();
        YearTable = new ArrayList<int[]>();

        try{
            File csvFile = new File(datapath);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(csvFile)); 
            BufferedReader br = new BufferedReader(reader);

            br.readLine();  //第一行是標題
            String line = br.readLine();    //開始讀取資料

            LowerYear = Integer.parseInt(line.substring(10, 14));  //設定檔案第一個資料的年分
            LowerMonth = Integer.parseInt(line.substring(15, 17)); //設定檔案第一個資料的月份
            int year = LowerYear, yp = 0;        //yp是當前資料的位置
            int month = LowerMonth; 
            int[] mt = {-1, -1, -1, -1, -1 , -1, -1, -1, -1, -1, -1, -1};           //設定陣列儲存每個月的起始位置，-1為沒有資料
            mt[LowerMonth - 1] = 0;
            while(line != null){
                line = line.replace(" ", "");
                String[] tmp = line.split(","); //預設爬蟲下來的資料是csv檔，用,分開成一個個資料
                
                int[] dataline = new int[tmp.length];

                for(int i = 0; i < tmp.length; i++){
                    dataline[i] = Integer.parseInt(tmp[i]);
                }

                LotteryData.add(dataline);       //將資料存起來
                if(dataline[1] != year){//當年份變更時將每個月的起始值add到表格內
                    int[] tt = new int[12];
                    for(int i = 0; i < 12; i++){
                        tt[i] = mt[i];
                    }
                    YearTable.add(tt);
                    year++;
                    for(int i = 0; i < 12; i++){
                        mt[i] = -1;
                    }
                }
                if(dataline[2] != month){//當月份變更時記錄下當前位置
                    mt[dataline[2] - 1] = yp;
                    month = dataline[2];
                }
                yp++;
                line = br.readLine();   //讀取下一行資料
            }
            UpperYear = year;
            UpperMonth = month;
            YearTable.add(mt);  
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<int[]> getLotteryData(){return LotteryData;}

    public ArrayList<int[]> getLotteryDatabyM(int Year, int Month) throws IndexOutOfBoundsException{
        //回傳特定月份資料
        ArrayList<int[]> ReturnData = new ArrayList<>();
        int BegingPointer = YearTable.get(Year - LowerYear)[Month - 1];
        int EndPointer = YearTable.get(Year - LowerYear + Month / 12)[Month % 12];
        for(int i = BegingPointer; i < EndPointer; i++){
            ReturnData.add(LotteryData.get(i));
        }
        return ReturnData;
    }
    public ArrayList<int[]> getLotteryDatabyD(int BeginYear, int BeginMonth, int EndYear, int EndMonth)throws IndexOutOfBoundsException{//給定一範圍時間回傳範圍內的所有資料
        ArrayList<int[]> ReturnData = new ArrayList<>();
        int BegingPointer = 0; 
        int EndPointer = 0;
        BegingPointer = YearTable.get(BeginYear - LowerYear)[BeginMonth - 1];
        EndPointer = YearTable.get(EndYear - LowerYear)[EndMonth - 1];
        for(int i = BegingPointer; i < EndPointer; i++){
            ReturnData.add(LotteryData.get(i));
        }
        return ReturnData;
    }
    public ArrayList<int[]> getLotteryDatabyT(int num, int times)throws IndexOutOfBoundsException{
        //回傳從起始時間開始前n期的資料
        ArrayList<int[]> ReturnData = new ArrayList<>();
        int BegingPointer = 0;  
        int T = times;
        BegingPointer = YearTable.get(num / 100000 - LowerYear)[0] + num % 1000 - 1;
        if(BegingPointer < times){
            T = BegingPointer;
        }
        for(int i = T; i >= 0; i--){
            ReturnData.add(LotteryData.get(BegingPointer - i));
        } 
        return ReturnData;
    }
    public int[] getLastData(){
        return LotteryData.get(LotteryData.size() - 1);
    }

}
