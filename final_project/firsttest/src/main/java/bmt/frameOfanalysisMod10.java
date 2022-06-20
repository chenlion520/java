package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frameOfanalysisMod10{
    private int[][] TableSource;
    private int[][] sortedData;
    private AnalysisTool539 AnalysisData;

    private JButton sortbtn;
    private JButton unsortbtn;

    private JPanel analysisMod10Panel;
    private JPanel TablePanel;
    private JPanel sortbtnPanel;
    private JScrollPane Mod10TablePanel;

    private SortStatus sortStatus;

    public JPanel getPanel(){
        return analysisMod10Panel;
    }

    public frameOfanalysisMod10(int CheckNum, int Times){
        AnalysisData = new AnalysisTool539("final_project\\data\\lotonumber.csv");
        TableSource = AnalysisData.AnalysisbyMod10(AnalysisData.getLotteryDatabyT(CheckNum, Times));
        
        sortbtn = new JButton("照次數大小排序");
        unsortbtn = new JButton("照號碼大小排序");
        ActionListener listener = new sortbtnAction();
        sortbtn.addActionListener(listener);
        unsortbtn.addActionListener(listener);
        sortbtnPanel = new JPanel(new FlowLayout());
        sortbtnPanel.add(sortbtn);
        sortbtnPanel.add(unsortbtn);

        TablePanel = new JPanel();
        analysisMod10Panel = new JPanel(new BorderLayout());
        analysisMod10Panel.add(sortbtnPanel, BorderLayout.NORTH);
        analysisMod10Panel.add(TablePanel, BorderLayout.CENTER);
        sortStatus = SortStatus.SORTBYSCORE;
        sortedData = TableSource;
        setMod10Table(SortStatus.SORTBYNUM);
    }

    private void setMod10Table(SortStatus e){
        if(TablePanel != null){
            TablePanel.removeAll();
        }
        if(e == SortStatus.SORTBYSCORE){
            if(e != sortStatus){
                sortedData = ArrayTool.sortArray(TableSource);
                sortStatus = e;
            }
            else{
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        else{
            if(e != sortStatus){
                sortedData = TableSource;
                sortStatus = e;
            }
            else{
                sortedData = ArrayTool.reverseArray2D(sortedData);
            }
        }
        String[] ColumnName = {"尾數", "次數"};
        String[][] RawData = new String[sortedData.length][2];
        for(int i = 0; i < RawData.length; i++){
            RawData[i][0] = Integer.toString(sortedData[i][0]);
            RawData[i][1] = Integer.toString(sortedData[i][1]);
        }
        JTable Mod10Table = new JTable(RawData, ColumnName);
        Mod10Table.setRowHeight(0, Mod10Table.getRowHeight());
        Mod10Table.setPreferredScrollableViewportSize(new Dimension(600,Mod10Table.getRowHeight()*10));
        Mod10TablePanel = new JScrollPane(Mod10Table);
        TablePanel.add(Mod10TablePanel);
        analysisMod10Panel.revalidate();
        analysisMod10Panel.repaint();
    }

    private class sortbtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("照次數大小排序")){
                setMod10Table(SortStatus.SORTBYSCORE);
            }
            else{
                setMod10Table(SortStatus.SORTBYNUM);
            }
        }
    }
}
