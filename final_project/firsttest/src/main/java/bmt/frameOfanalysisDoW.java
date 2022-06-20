package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frameOfanalysisDoW{
    private int[][][] TableSource;
    private int[][] sortedData;
    private AnalysisTool539 AnalysisData;

    private JPanel analysisDoWPanel;
    private JPanel TablePanel;
    private JPanel DoWbtnPanel;
    private JPanel sortbtnPanel;
    private JPanel btnPanel;
    private JScrollPane DoWTablePanel;
    
    private JButton sortbtn;
    private JButton unsortbtn;

    private JButton MonBtn;
    private JButton TueBtn;
    private JButton WedBtn;
    private JButton ThuBtn;
    private JButton FriBtn;
    private JButton SatBtn;

    private JLabel ShowDoW;

    private int DoW;
    private SortStatus sortStatus;

    public JPanel getPanel(){
        return analysisDoWPanel;
    }

    public frameOfanalysisDoW(int CheckNum, int Times){
        AnalysisData = new AnalysisTool539("final_project\\data\\lotonumber.csv");
        TableSource = AnalysisData.AnalysisbyDoWs(AnalysisData.getLotteryDatabyT(CheckNum, Times));
        
        MonBtn = new JButton("星期一");
        TueBtn = new JButton("星期二");
        WedBtn = new JButton("星期三");
        ThuBtn = new JButton("星期四");
        FriBtn = new JButton("星期五");
        SatBtn = new JButton("星期六");

        ActionListener DoWlistener = new DoWBtnAction();
        MonBtn.addActionListener(DoWlistener);
        TueBtn.addActionListener(DoWlistener);
        WedBtn.addActionListener(DoWlistener);
        ThuBtn.addActionListener(DoWlistener);
        FriBtn.addActionListener(DoWlistener);
        SatBtn.addActionListener(DoWlistener);

        DoWbtnPanel = new JPanel(new FlowLayout());
        DoWbtnPanel.add(MonBtn);
        DoWbtnPanel.add(TueBtn);
        DoWbtnPanel.add(WedBtn);
        DoWbtnPanel.add(ThuBtn);
        DoWbtnPanel.add(FriBtn);
        DoWbtnPanel.add(SatBtn);

        sortbtn = new JButton("照次數大小排序");
        unsortbtn = new JButton("照號碼大小排序");
        ActionListener listener = new sortbtnAction();
        sortbtn.addActionListener(listener);
        unsortbtn.addActionListener(listener);
        sortbtnPanel = new JPanel(new FlowLayout());
        sortbtnPanel.add(sortbtn);
        sortbtnPanel.add(unsortbtn);

        ShowDoW = new JLabel("星期一", JLabel.CENTER);
        btnPanel = new JPanel(new GridLayout(3, 1));
        btnPanel.add(sortbtnPanel);
        btnPanel.add(DoWbtnPanel);
        btnPanel.add(ShowDoW);

        TablePanel = new JPanel();
        analysisDoWPanel = new JPanel(new BorderLayout());
        analysisDoWPanel.add(btnPanel, BorderLayout.NORTH);
        analysisDoWPanel.add(TablePanel, BorderLayout.CENTER);
        DoW = 0;
        sortedData = TableSource[DoW];
        sortStatus = SortStatus.SORTBYSCORE;
        setDoWTable(SortStatus.SORTBYNUM);
    }

    private void setDoWTable(SortStatus e){
        if(DoWTablePanel != null){
            TablePanel.remove(DoWTablePanel);
        }
        String[][] RawData = new String[39][2];
        String[]   ColumnName = {"號碼", "次數"};
        if(e == SortStatus.SORTBYSCORE){
            if(e != sortStatus){
                sortedData = ArrayTool.sortArray(TableSource[DoW]);
                sortStatus = e;
            }
            else{
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        else{
            if(e != sortStatus){
                sortedData = TableSource[DoW];
                sortStatus = e;
            }
            else{
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        for(int i = 0; i < 39; i ++){
            RawData[i][0] = Integer.toString(sortedData[i][0]);
            RawData[i][1] = Integer.toString(sortedData[i][1]);   
        }
        JTable DoWTable = new JTable(RawData, ColumnName);
        DoWTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
        DoWTablePanel = new JScrollPane(DoWTable);
        TablePanel.add(DoWTablePanel, BorderLayout.CENTER);
        analysisDoWPanel.revalidate();
        analysisDoWPanel.repaint();
    }

    private class sortbtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("照號碼大小排序")){
                setDoWTable(SortStatus.SORTBYNUM);
            }
            else{
                setDoWTable(SortStatus.SORTBYSCORE);
            }
        }
    }

    private class DoWBtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            switch(e.getActionCommand()){
                case "星期一":
                    DoW = 0;
                    break;
                case "星期二":
                    DoW = 1;
                    break;
                case "星期三":
                    DoW = 2;
                    break;
                case "星期四":
                    DoW = 3;
                    break;
                case "星期五":
                    DoW = 4;
                    break;
                case "星期六":
                    if(TableSource.length < 6){
                        DoW = 0;
                    }
                    else        DoW = 5;
                    break;
                default:
                    break;
            }
            ShowDoW.setText(e.getActionCommand());
            sortedData = TableSource[DoW];
            sortStatus = SortStatus.SORTBYSCORE;
            setDoWTable(SortStatus.SORTBYNUM);
        }
    }
}
