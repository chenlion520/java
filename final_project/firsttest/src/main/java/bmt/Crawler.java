package bmt;

import org.jsoup.Jsoup;
import org.jsoup.select.*;
import org.jsoup.nodes.*;
import java.io.*;

import java.util.*;
import java.util.Date;
import java.text.*;
import au.com.bytecode.opencsv.CSVReader;

public class Crawler {
    // File File = new
    // File("C:\\Users\\user\\Desktop\\java\\final_project\\firsttest\\src\\main\\java\\bmt\\lotonumber.csv");
    public int totaldata = 0;
    public String basicyear = "2007";
    public int index = 1;// 存第幾期
    public int gettotaldata() {return totaldata;}
    public Crawler() throws Exception {
        // 創建目的文件
        File File = new File("final_project\\data\\lotonumber.csv");

        BufferedWriter writer = new BufferedWriter(new FileWriter(File));
        // 創立標題
        writer.write("編號,年份,月,日,星期,號碼1,號碼2,號碼3,號碼4,號碼5");
        writer.newLine();

    }

    public void LoadData(int datanum, String basicyear, int index, BufferedWriter writer) {
        int page = 0;
        totaldata = datanum;
        while (true) {// 爬到最後一頁之後會自動偵測是否到最後一項了
            page++;
            System.out.println("讀取頁數 :" + page);
            final String url = "https://www.lotto-8.com/listlto539bbk.asp?indexpage=" + page + "&orderby=old";

            try {
                if (page == 1) {
                    writer.write("編號,年份,月,日,星期,號碼1,號碼2,號碼3,號碼4,號碼5");
                    writer.newLine();
                    totaldata += 10;
                }
                // System.out.println("index ======" + index);
                final Document doc = Jsoup.connect(url).get();

                // 設立好table body tr各個標籤的接收變數
                Element table = doc.select("table.auto-style4").last();
                Element body = table.select("tbody").first();
                Elements trs = body.select("tr");

                // 年份加期數 ex 2007 000001
                int flag = 0;// 用來判斷數字還是日期
                String[] numtmp;// 接收數字
                String[] datetmp;// 接收非數字
                String[] monthdaytemp;// 接收月日
                String date = "";
                int endtestflag = 0;
                for (Element tr : trs) {// 整理tr們
                    Elements tds = tr.select("td");
                    for (Element td : tds) {
                        if (td.text().equals("日期") || td.text().equals("今彩539中獎號碼") || td.text().equals("備註")) {
                            continue;
                        } else if (td.text().equals("")) {
                            continue;
                        } else {
                            if (flag == 1) {
                                // 整理號碼
                                numtmp = td.text().split(",");// 用逗號分割號碼
                                for (int i = 0; i < numtmp.length; i++) {
                                    // 將數字分割後依序寫數文件中
                                    writer.write(numtmp[i]);
                                    totaldata += 1;
                                    writer.write(",");
                                }
                                writer.newLine();
                                flag = 0;
                            } else {
                                // 整理日期
                                datetmp = td.text().split(" ");
                                for (int j = 0; j < datetmp.length - 1; j++) {
                                    if (j == 0) {
                                        date += datetmp[j] + "-";
                                    } else {
                                        date += datetmp[j];
                                    }
                                }
                                if ((CompareDate(date))) {// 每次先檢查是否更新到昨天的資料了
                                    endtestflag = 1;
                                }
                                date = "";
                                for (int i = 0; i < datetmp.length; i++) {
                                    if (i == 2) {// 星期拆除
                                        writer.write(ChangeDay(datetmp[i].charAt(1)));
                                        totaldata += 1;
                                    } else {// 寫入年跟月日
                                        if (!(datetmp[i].matches("-?\\d+"))) {// 寫入月日
                                            monthdaytemp = datetmp[i].split("/");
                                            writer.write(monthdaytemp[0] + "," + monthdaytemp[1]);
                                            totaldata += 2;
                                        } else {// 寫入年份
                                            if (datetmp[i].equals(basicyear)) {// 寫入編號

                                                writer.write(datetmp[i] + String.format("%05d", index++) + ",");// 寫入編號
                                                                                                                // 號碼用正則表達
                                                totaldata += 1;
                                            } else {
                                                index = 1;
                                                basicyear = datetmp[i];
                                                writer.write(datetmp[i] + String.format("%05d", index++) + ",");
                                                totaldata += 1;
                                            }
                                            writer.write(datetmp[i]);
                                            totaldata += 1;
                                        }
                                    }
                                    writer.write(",");

                                }
                                flag = 1;
                            }
                        }

                    }
                    if (endtestflag == 1) {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public boolean CompareDate(String date) throws Exception {// 檢查是否到最後一筆資料了
        Calendar calendar = Calendar.getInstance(); // 獲取當前時間
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (week == 1) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM/dd");// 調整日期格式
            Date as = new Date(new Date().getTime() - (24 * 60 * 60 * 1000 * 2));// 昨天的時間
            Date date1 = sdf.parse(date);
            Date date2 = sdf.parse(sdf.format(as));// 將日期換成Date的型態
            if (date1.equals(date2)) {
                return true;
            }
            return false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM/dd");// 調整日期格式
            Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);// 昨天的時間
            Date date1 = sdf.parse(date);
            Date date2 = sdf.parse(sdf.format(as));// 將日期換成Date的型態
            if (date1.equals(date2)) {
                return true;
            }
            return false;
        }

    }

    public String LoadLatest(int totaldata) {
        try {
            String latest ="";
            File File = new File("final_project\\data\\lotonumber.csv");
            CSVReader reader = new CSVReader(new FileReader(File), ',', '"', 1);
            List<String[]> nowdata = reader.readAll();
            int i;
            String[] tmp = nowdata.get((totaldata / 10) - 2);
            for (i = 0; i < tmp.length - 7; i++) {
                latest += tmp[i] + " ";
                //System.out.printf("%s ", tmp[i]);
            }
            return latest;
           // System.out.printf("\n--------------------------------");
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
        

    }

    public static String ChangeDay(char date) {
        String tmp = "0";
        switch (date) {
            case '一':
                tmp = "1";
                break;
            case '二':
                tmp = "2";
                break;
            case '三':
                tmp = "3";
                break;
            case '四':
                tmp = "4";
                break;
            case '五':
                tmp = "5";
                break;
            case '六':
                tmp = "6";
                break;
        }
        return tmp;

    }
}