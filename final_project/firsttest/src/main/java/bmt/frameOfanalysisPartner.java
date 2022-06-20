package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frameOfanalysisPartner{
    private int[][][] TableSource;          //資料來源
    private int[][] sortedData;             //實際寫進表格的資料
    private AnalysisTool539 AnalysisData;   //分析工具

    private JButton sortbtn;        //照次數排序
    private JButton unsortbtn;      //照號碼排序

    private JButton partnerUpbtn;       //往下一個號碼
    private JButton partnerDownbtn;     //往上一個號碼
    private JButton partnerCheckbtn;    //讀取並前往輸入號碼

    private JTextField partnerCheck;    //號碼輸入區

    private JPanel analysisPartnerPanel;    //本頁面的容器
    private JPanel btnPanel;                //所有按鈕區塊的容器
    private JPanel sortbtnPanel;            //裝排序按鈕
    private JPanel checkbtnPanel;           //搜尋按鈕容器
    private JPanel TablePanel;              //裝表格用
    private JScrollPane PartnerTablePanel;  //用來讓表格可以卷軸化

    private JLabel btnhint;

    private int numbern;                    //當前號碼
    private SortStatus sortStatus;          //當前排序狀態

    public JPanel getPanel(){   //回傳此分頁
        return analysisPartnerPanel;
    }

    public frameOfanalysisPartner(int CheckNum, int Times){
        AnalysisData = new AnalysisTool539("final_project\\data\\lotonumber.csv");
        TableSource = AnalysisData.AnalysisbyPartner(AnalysisData.getLotteryDatabyT(CheckNum, Times));

        ////////////////設定按鈕///////////////////
        partnerUpbtn = new JButton("UP");                   
        partnerDownbtn = new JButton("DOWN");
        partnerCheckbtn = new JButton("搜尋");
        ActionListener cListener = new checkbtnAction();        
        partnerUpbtn.addActionListener(cListener);          
        partnerDownbtn.addActionListener(cListener);
        partnerCheckbtn.addActionListener(cListener);
        partnerCheck = new JTextField("1", 3);
        btnhint = new JLabel("輸入號碼搜尋", JLabel.CENTER);
        checkbtnPanel = new JPanel();
        checkbtnPanel.add(btnhint);
        checkbtnPanel.add(partnerUpbtn);
        checkbtnPanel.add(partnerCheck);
        checkbtnPanel.add(partnerDownbtn);
        checkbtnPanel.add(partnerCheckbtn);

        sortbtn = new JButton("照次數大小排序");
        unsortbtn = new JButton("照號碼大小排序");
        ActionListener listener = new sortbtnAction();
        sortbtn.addActionListener(listener);
        unsortbtn.addActionListener(listener);
        sortbtnPanel = new JPanel(new FlowLayout());
        sortbtnPanel.add(sortbtn);
        sortbtnPanel.add(unsortbtn);

        btnPanel = new JPanel(new GridLayout(2, 1));
        btnPanel.add(checkbtnPanel);
        btnPanel.add(sortbtnPanel);

        ////////////設定頁面格式//////////////////////////
        TablePanel = new JPanel();
        analysisPartnerPanel = new JPanel(new BorderLayout());
        analysisPartnerPanel.add(btnPanel, BorderLayout.NORTH);
        analysisPartnerPanel.add(TablePanel, BorderLayout.CENTER);

        ////////////初始化表格////////////////////////////
        numbern = 0;        //預設表格是01
        sortedData = TableSource[numbern];
        sortStatus = SortStatus.SORTBYSCORE;
        setPartnerTable(SortStatus.SORTBYNUM); 
    }

    private void setPartnerTable(SortStatus e){//設定表格
        if(TablePanel != null){
            TablePanel.removeAll();
        }
        if(e == SortStatus.SORTBYSCORE){
            if(e != sortStatus){    //變換排序方法時
                sortedData = ArrayTool.sortArray(TableSource[numbern]);
                sortStatus = e;
            }
            else{                   //未變換排序法則使當前排序相反
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        else{//同上邏輯
            if(e != sortStatus){
                sortedData = TableSource[numbern];
                sortStatus = e;
            }
            else{
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        //設定表格
        String[] ColumnName = {"號碼", "次數"};
        String[][] RawData = new String[sortedData.length][2];
        for(int i = 0; i < RawData.length; i++){
            RawData[i][0] = Integer.toString(sortedData[i][0]);
            RawData[i][1] = Integer.toString(sortedData[i][1]);
        }
        JTable PartnerTable = new JTable(RawData, ColumnName);
        PartnerTable.setPreferredScrollableViewportSize(new Dimension(600,400));    
        PartnerTablePanel = new JScrollPane(PartnerTable);
        TablePanel.add(PartnerTablePanel);
        analysisPartnerPanel.revalidate();
        analysisPartnerPanel.repaint();
    }

    private class checkbtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            switch(e.getActionCommand()){
                case "UP":  
                    numbern = (numbern + 1) % 39;
                    break;
                case "DOWN":
                    numbern = (numbern + 38) % 39;
                    break;
                case "搜尋":
                    String tmp = partnerCheck.getText();
                    numbern = Integer.parseInt(tmp) - 1;
                    break;
                default:
                    break;
            }
            partnerCheck.setText(Integer.toString(numbern + 1));    //更新輸入號碼區域的顯示文字為當前號碼
            sortedData = TableSource[numbern];
            sortStatus = SortStatus.SORTBYSCORE;
            setPartnerTable(SortStatus.SORTBYNUM);      //重新設定表格
        }
    }

    private class sortbtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("照次數大小排序")){
                setPartnerTable(SortStatus.SORTBYSCORE);
            }
            else{
                setPartnerTable(SortStatus.SORTBYNUM);
            }
        }
    }
}
