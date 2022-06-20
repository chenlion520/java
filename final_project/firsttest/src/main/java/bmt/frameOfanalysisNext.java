package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frameOfanalysisNext{
    private int[]     LastData;             //最後一期的資料
    private int[][][] TableSource;          //資料來源
    private int[][]   sortedData;           //實際寫入表格的資料
    private AnalysisTool539 AnalysisData;   //分析工具

    private JPanel analysisNextPanel;       //此分頁的容器
    private JPanel btnPanel;                //所有按鈕區塊的容器
    private JPanel numberbtnPanel;          //號碼
    private JPanel sortbtnPanel;            //排序法按鈕
    private JPanel TablePanel;              //裝表格用
    private JScrollPane nextTablePanel;     //表格卷軸頁面
    
    private JButton sortbtn;                //照次數或積分排序
    private JButton unsortbtn;              //照號碼大小排序

    private JButton num1Btn;                //號碼1~5
    private JButton num2Btn;
    private JButton num3Btn;
    private JButton num4Btn;
    private JButton num5Btn;

    private int numbern;                    //當前顯示號碼
    private SortStatus sortStatus;          //當前排序方法

    public JPanel getPanel(){               //回傳此分頁
        return analysisNextPanel;
    }

    public frameOfanalysisNext(int CheckNum, int Times){
        AnalysisData = new AnalysisTool539("final_project\\data\\lotonumber.csv");
        TableSource = AnalysisData.AnalysisbyNext(AnalysisData.getLotteryDatabyT(CheckNum, Times));
        LastData = AnalysisData.getLotteryDatabyT(CheckNum, 0).get(0);

        ///////////////建立按鈕區塊/////////////////
        //建立號碼按鈕區塊
        num1Btn = new JButton("<html>號碼1<br><center>" + LastData[5] + "</html>");    
        num2Btn = new JButton("<html>號碼2<br><center>" + LastData[6] + "</html>");
        num3Btn = new JButton("<html>號碼3<br><center>" + LastData[7] + "</html>");
        num4Btn = new JButton("<html>號碼4<br><center>" + LastData[8] + "</html>");
        num5Btn = new JButton("<html>號碼5<br><center>" + LastData[9] + "</html>");      
        ActionListener numbtnListener = new numbtnAcition();    //新增號碼按鈕事件
        num1Btn.addActionListener(numbtnListener);
        num2Btn.addActionListener(numbtnListener);
        num3Btn.addActionListener(numbtnListener);
        num4Btn.addActionListener(numbtnListener);
        num5Btn.addActionListener(numbtnListener);
        numberbtnPanel = new JPanel(new FlowLayout());
        numberbtnPanel.add(num1Btn);
        numberbtnPanel.add(num2Btn);
        numberbtnPanel.add(num3Btn);
        numberbtnPanel.add(num4Btn);
        numberbtnPanel.add(num5Btn);
        //建立排序按鈕區塊
        sortbtn = new JButton("照次數大小排序");
        unsortbtn = new JButton("照號碼大小排序");
        ActionListener listener = new sortbtnAction();  //新增排序按鈕事件
        sortbtn.addActionListener(listener);
        unsortbtn.addActionListener(listener);
        sortbtnPanel = new JPanel(new FlowLayout());
        sortbtnPanel.add(sortbtn);
        sortbtnPanel.add(unsortbtn);
        //設定按鈕區塊
        btnPanel = new JPanel(new GridLayout(2, 1));
        btnPanel.add(sortbtnPanel);
        btnPanel.add(numberbtnPanel);

        /////////////設定此分頁格式//////////////
        TablePanel = new JPanel();
        analysisNextPanel = new JPanel(new BorderLayout());
        analysisNextPanel.add(btnPanel, BorderLayout.NORTH);
        analysisNextPanel.add(TablePanel, BorderLayout.CENTER);
        
        //////////////初始化表格//////////////////
        numbern = 1;    //預設顯示最後一期資料的第一個號碼
        sortStatus = SortStatus.SORTBYSCORE;
        sortedData = TableSource[LastData[4 + numbern] - 1];
        setNextTable(SortStatus.SORTBYNUM);
    }

    private void setNextTable(SortStatus e){
        if(TablePanel != null){
            TablePanel.removeAll();
        }
        String[] ColumnName = {"號碼", "次數"};
        String[][] RawData = new String[39][2];
        if(e == SortStatus.SORTBYSCORE){
            if(e != sortStatus){
                sortedData = ArrayTool.sortArray(TableSource[numbern]);
                sortStatus = e;
            }
            else{
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        else{
            if(e != sortStatus){
                sortedData = TableSource[numbern];
                sortStatus = e;
            }
            else{
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        for(int i = 0; i < 39; i++){
            RawData[i][0] = Integer.toString(sortedData[i][0]);
            RawData[i][1] = Integer.toString(sortedData[i][1]);
        }
        ////////////////建立表格////////////////
        JTable NextTable = new JTable(RawData, ColumnName);
        NextTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
        nextTablePanel = new JScrollPane(NextTable);
        TablePanel.add(nextTablePanel);
        analysisNextPanel.revalidate();
        analysisNextPanel.repaint();
    }

    private class sortbtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("照次數大小排序")){
                setNextTable(SortStatus.SORTBYSCORE);
            }
            else{
                setNextTable(SortStatus.SORTBYNUM);
            }
        }
    }

    private class numbtnAcition implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String Acmd = e.getActionCommand();
            numbern = Integer.parseInt(Acmd.substring(8, 9));
            sortStatus = SortStatus.SORTBYSCORE;
            sortedData = TableSource[LastData[4 + numbern] - 1];
            setNextTable(SortStatus.SORTBYNUM);
        }
    }
}
