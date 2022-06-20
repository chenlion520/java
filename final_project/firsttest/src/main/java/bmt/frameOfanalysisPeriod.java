package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frameOfanalysisPeriod{
    private int[][] TableSource;
    private int[][] sortedData;
    private AnalysisTool539 AnalysisData;

    private JButton sortbtn;
    private JButton unsortbtn;

    private JPanel analysisPeriodPanel;
    private JPanel sortbtnPanel;
    private JPanel TablePanel;
    private JScrollPane PeriodTablePanel;

    private SortStatus sortStatus;

    public JPanel getPanel(){
        return analysisPeriodPanel;
    }

    public frameOfanalysisPeriod(int CheckNum, int Times){
        AnalysisData = new AnalysisTool539("final_project\\data\\lotonumber.csv");
        TableSource = AnalysisData.AnalysisbyPeriod(AnalysisData.getLotteryDatabyT(CheckNum, Times));
        
        sortbtn = new JButton("照次數大小排序");
        unsortbtn = new JButton("照號碼大小排序");
        ActionListener listener = new sortbtnAction();
        sortbtn.addActionListener(listener);
        unsortbtn.addActionListener(listener);
        sortbtnPanel = new JPanel(new FlowLayout());
        sortbtnPanel.add(sortbtn);
        sortbtnPanel.add(unsortbtn);

        TablePanel = new JPanel();

        analysisPeriodPanel = new JPanel(new BorderLayout());
        analysisPeriodPanel.add(sortbtnPanel, BorderLayout.NORTH);
        analysisPeriodPanel.add(TablePanel, BorderLayout.CENTER);
        sortedData = TableSource;
        sortStatus = SortStatus.SORTBYSCORE;
        setPeriodTable(SortStatus.SORTBYNUM);
    }

    private void setPeriodTable(SortStatus e){
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
        String[] ColumnName = {"號碼", "次數"};
        String[][] RawData = new String[TableSource.length][2];
        for(int i = 0; i < RawData.length; i++){
            RawData[i][0] = Integer.toString(sortedData[i][0]);
            RawData[i][1] = Integer.toString(sortedData[i][1]);
        }
        JTable PeriodTable = new JTable(RawData, ColumnName);
        PeriodTable.setPreferredScrollableViewportSize(new Dimension(600,400));
        PeriodTablePanel = new JScrollPane(PeriodTable);
        TablePanel.add(PeriodTablePanel);
        analysisPeriodPanel.revalidate();
        analysisPeriodPanel.repaint();
    }

    private class sortbtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("照次數大小排序")){
                setPeriodTable(SortStatus.SORTBYSCORE);
            }
            else{
                setPeriodTable(SortStatus.SORTBYNUM);
            }
        }
    }
}
