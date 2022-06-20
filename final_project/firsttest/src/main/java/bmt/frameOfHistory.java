package bmt;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class frameOfHistory {
    private ReadData Data539;

    private JLabel label1;// 顯示文字
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    private JPanel mainPanel; // CardLayout的容器
    private JPanel Survey; // 詢問結束期號頁
    private JPanel DataTablePanel; // 表格頁的大框架\
    private JPanel TitlePanel;
    private JPanel TablePanel; // 裝表格
    private JScrollPane HistoryTablePanel; // 使表格卷軸化用的
    private JPanel ButtonPanel; // 裝期數按鈕

    private JButton CheckButton; // 按下後開始查詢
    private JRadioButton time100Button; // 100期
    private JRadioButton time200Button; // 200期
    private JRadioButton time300Button; // 300期
    private JRadioButton timeallButton; // 全部
    private ButtonGroup timeButtonGroup;

    private JTextField EndDate; // 結束期號

    private CardLayout clo;

    private int[] latest;

    public JPanel getPanel() {
        return mainPanel;
    }

    public void BackToSurvey() {
        clo.show(mainPanel, "Survey");
        TablePanel.removeAll();
    }

    public frameOfHistory() {
        Data539 = new ReadData("final_project\\data\\lotonumber.csv");// 資料來源
        latest = Data539.getLastData();
        clo = new CardLayout();
        mainPanel = new JPanel(clo);

        setSurveypanel();
        setDataTablepanel();
        mainPanel.add(Survey, "Survey");
        mainPanel.add(DataTablePanel, "History");
    }

    private void setSurveypanel() {
        CheckButton = new JButton("開始查詢");
        CheckButton.setFont(new Font("TimesRoman", Font.BOLD, 25));
        ActionListener Checkbtnlistener = new cbtnAction();
        CheckButton.addActionListener(Checkbtnlistener);
        // 查詢頁面
        String end = String.valueOf(latest[0]);
        EndDate = new JTextField(end, 11);
        EndDate.setFont(new Font("TimesRoman", Font.BOLD, 25));
        Survey = new JPanel(new GridLayout(5, 1));

        label1 = new JLabel("查詢號碼", JLabel.CENTER);
        label1.setFont(new Font("TimesRoman", Font.BOLD, 30));
        label2 = new JLabel("輸入開始期號:");
        label2.setFont(new Font("TimesRoman", Font.BOLD, 25));
        label3 = new JLabel("按下後開始:");
        label3.setFont(new Font("TimesRoman", Font.BOLD, 25));
        Survey.add(label1);
        Survey.add(label2);
        Survey.add(EndDate);
        Survey.add(label3);
        Survey.add(CheckButton);
    }

    private void setDataTablepanel() {
        // 期數的RadioButton
        ItemListener RBlistener = new SwitchtimeButton();
        time100Button = new JRadioButton("100", true);
        // time100Button.setActionCommand("100");
        time200Button = new JRadioButton("200", false);
        // time200Button.setActionCommand("200");
        time300Button = new JRadioButton("300", false);
        // time300Button.setActionCommand("300");
        timeallButton = new JRadioButton("all", false);
        time100Button.addItemListener(RBlistener);
        time200Button.addItemListener(RBlistener);
        time300Button.addItemListener(RBlistener);
        timeallButton.addItemListener(RBlistener);
        timeButtonGroup = new ButtonGroup();
        timeButtonGroup.add(time100Button);
        timeButtonGroup.add(time200Button);
        timeButtonGroup.add(time300Button);
        timeButtonGroup.add(timeallButton);
        ButtonPanel = new JPanel(new FlowLayout());
        ButtonPanel.add(time100Button);
        ButtonPanel.add(time200Button);
        ButtonPanel.add(time300Button);
        ButtonPanel.add(timeallButton);

        TablePanel = new JPanel();
        TitlePanel = new JPanel();
        label4 = new JLabel("查詢號碼", JLabel.CENTER);
        label4.setFont(new Font("TimesRoman", Font.BOLD, 20));
        TitlePanel.add(label4, JPanel.CENTER_ALIGNMENT);
        DataTablePanel = new JPanel(new BorderLayout());
        DataTablePanel.add(TitlePanel, BorderLayout.NORTH);
        DataTablePanel.add(TablePanel, BorderLayout.CENTER);
        DataTablePanel.add(ButtonPanel, BorderLayout.SOUTH);
    }

    private void setDataTable() {
        if (TablePanel != null) {
            TablePanel.removeAll();
        }
        int times = 0;
        if (time100Button.isSelected())
            times = 100;
        else if (time200Button.isSelected())
            times = 200;
        else if (time300Button.isSelected())
            times = 300;
        else
            times = 100000;
        int i, j;
        ArrayList<int[]> DataRead = Data539.getLotteryDatabyT(Integer.parseInt(EndDate.getText()), times);
        String[] columnName = { "序號", "日期", "星期", "號碼1", "號碼2", "號碼3", "號碼4", "號碼5" };
        String[][] rawData = new String[DataRead.size()][8];
        for (i = 0; i < DataRead.size(); i++) {
            int[] tmp = DataRead.get(i);
            rawData[i][0] = Integer.toString(tmp[0]);
            for (j = 4; j < 10; j++) {
                rawData[i][j - 2] = Integer.toString(tmp[j]);
            }
            rawData[i][1] = String.format("%d/%d/%d", tmp[1], tmp[2], tmp[3]);
        }
        JTable DataTable = new JTable(rawData, columnName);
        DataTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
        HistoryTablePanel = new JScrollPane(DataTable);
        TablePanel.add(HistoryTablePanel);
    }

    private class SwitchtimeButton implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            //System.out.println(timeButtonGroup.getSelection().getActionCommand());
            setDataTable();
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private class cbtnAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String readline = EndDate.getText();
                if (readline.equals("")) {
                    return;
                }
                setDataTable();
                mainPanel.revalidate();
                mainPanel.repaint();
                clo.show(mainPanel, "History");
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "!!系統錯誤!!\n請確認輸入資料是否正確");
                System.out.println(exception);
            }
            
        }
    }

}
