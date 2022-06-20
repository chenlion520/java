package bmt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class frameOfCount {
    private ReadData Data539;
    private JPanel Survey;// 統計資料的框架
    private JPanel BtnPanel;// 裝五個按鈕用的
    private JPanel mainPanel;// 拿來換得Card
    private JPanel tmppanel;// 暫存按鈕用 方便編排
    private JPanel TimebtnPanel;//

    private JPanel tablepanel;

    private JLabel label1;// 顯示文字
    private JLabel label2;
    private JLabel label3;

    private JRadioButton time100Button; // 100期
    private JRadioButton time200Button; // 200期
    private JRadioButton time300Button; // 300期
    private JRadioButton timeselfButton; // 全部
    private ButtonGroup timeButtonGroup; //

    private JButton CheckButton;// survey 的按鈕
    // 下一層的五個按鈕
    private JButton ButtonAnalysisbyNext;// 下星期最會開的
    private JButton ButtonAnalysisbyPeriod;// 最會開的
    private JButton ButtonAnalysisbyDoW;// 星期n
    private JButton ButtonAnalysisbyMod10;// 尾數
    private JButton ButtonAnalysisbyMod10inDoW;// 星期n尾數
    private JButton ButtonAnalysisPartner;// 搭配

    private JButton lastButton;

    private JTextField EndDate; // 結束期號
    private JTextField TimesField; // 期數
    private CardLayout clo;

    private frameOfanalysisAdapter tmp;// 五顆按鈕的介面

    private int CheckNum;// 放確認的編號
    private int times;// 放期數
    private int[] latest;

    public JPanel getPanel() {
        return mainPanel;
    }

    public void BackToSurvey() {
        setButtonbgBack();
        lastButton = null;
        clo.show(mainPanel, "Survey");
    }

    public frameOfCount() {
        Data539 = new ReadData("final_project\\data\\lotonumber.csv");// 資料來源
        latest = Data539.getLastData();
        // System.out.println(latest[0]);
        tablepanel = new JPanel(new BorderLayout());
        clo = new CardLayout();
        mainPanel = new JPanel(clo);

        mainPanel.setPreferredSize(new Dimension());
        Survey = new JPanel(new GridLayout(8, 1));
        CheckButton = new JButton("開始查詢");

        CheckButton.addActionListener(new ActionListener() {// 查詢頁面按鈕
            public void actionPerformed(ActionEvent e) {
                try {
                    if (EndDate.getText().equals("")) {
                        return;
                    }

                    if (timeselfButton.isSelected()) {
                        if (TimesField.getText().equals("")) {
                            return;
                        }
                        times = Integer.parseInt(TimesField.getText());
                    } else if (time200Button.isSelected()) {
                        times = 200;
                    } else if (time300Button.isSelected()) {
                        times = 300;
                    } else {
                        times = 100;
                    }
                    CheckNum = Integer.parseInt(EndDate.getText());
                    if (tablepanel != null) {
                        tablepanel.removeAll();
                    }
                    tmp = new frameOfanalysisAdapter(CheckNum, times);
                    tablepanel.add(tmp.getPlainPanel(), BorderLayout.CENTER);
                    BtnPanel.add(tablepanel);
                    clo.show(mainPanel, "BtnPanel");
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "!!系統錯誤!!\n請確認輸入資料是否正確");
                    System.out.println(exception);
                }
            }
        });
        // 選擇期數的RadioButton
        time100Button = new JRadioButton("100", true);
        time100Button.setActionCommand("100");
        time200Button = new JRadioButton("200", false);
        time200Button.setActionCommand("200");
        time300Button = new JRadioButton("300", false);
        time300Button.setActionCommand("300");
        timeselfButton = new JRadioButton("自訂", false);
        time300Button.setActionCommand("300");
        timeButtonGroup = new ButtonGroup();
        timeButtonGroup.add(time100Button);
        timeButtonGroup.add(time200Button);
        timeButtonGroup.add(time300Button);
        timeButtonGroup.add(timeselfButton);
        TimebtnPanel = new JPanel(new FlowLayout());
        TimebtnPanel.add(time100Button);
        TimebtnPanel.add(time200Button);
        TimebtnPanel.add(time300Button);
        TimebtnPanel.add(timeselfButton);

        // 將東西都加入frame

        String end = String.valueOf(latest[0]);
        EndDate = new JTextField(end, 70);
        TimesField = new JTextField("", 70);

        label1 = new JLabel("查詢號碼", JLabel.CENTER);
        label1.setFont(new Font("TimesRoman", Font.BOLD, 30));
        label2 = new JLabel("輸入開始期號:");
        label2.setFont(new Font("TimesRoman", Font.BOLD, 25));
        label3 = new JLabel("統計範圍:");
        label3.setFont(new Font("TimesRoman", Font.BOLD, 25));
        EndDate.setFont(new Font("TimesRoman", Font.BOLD, 25));
        TimesField.setFont(new Font("TimesRoman", Font.BOLD, 25));
        CheckButton.setFont(new Font("TimesRoman", Font.BOLD, 25));

        Survey.add(label1);
        Survey.add(label2);
        Survey.add(EndDate);
        Survey.add(label3);
        Survey.add(TimebtnPanel);
        Survey.add(TimesField);
        Survey.add(CheckButton);

        // 新增cardlayout的第二張卡 包含五個號碼
        ButtonAnalysisbyNext = new JButton("下一期最會開的號碼");
        ButtonAnalysisbyPeriod = new JButton("輸入期數內最會開的號碼");
        ButtonAnalysisbyDoW = new JButton("星期n最會開的號碼");
        ButtonAnalysisbyMod10 = new JButton("最會開的尾數次數");
        ButtonAnalysisbyMod10inDoW = new JButton("星期n最會開的尾數");
        ButtonAnalysisPartner = new JButton("最常一起開的");

        SimpleListener Listener = new SimpleListener();
        ButtonAnalysisbyNext.addActionListener(Listener);
        ButtonAnalysisbyPeriod.addActionListener(Listener);
        ButtonAnalysisbyDoW.addActionListener(Listener);
        ButtonAnalysisbyMod10.addActionListener(Listener);
        ButtonAnalysisbyMod10inDoW.addActionListener(Listener);
        ButtonAnalysisPartner.addActionListener(Listener);
        BtnPanel = new JPanel(new BorderLayout());
        tmppanel = new JPanel(new GridLayout(6, 1));

        ButtonAnalysisbyNext.setFont(new Font("TimesRoman", Font.BOLD, 20));
        ButtonAnalysisbyPeriod.setFont(new Font("TimesRoman", Font.BOLD, 20));
        ButtonAnalysisbyDoW.setFont(new Font("TimesRoman", Font.BOLD, 20));
        ButtonAnalysisbyMod10.setFont(new Font("TimesRoman", Font.BOLD, 20));
        ButtonAnalysisbyMod10inDoW.setFont(new Font("TimesRoman", Font.BOLD, 20));
        ButtonAnalysisPartner.setFont(new Font("TimesRoman", Font.BOLD, 20));
        tmppanel.add(ButtonAnalysisbyNext);
        tmppanel.add(ButtonAnalysisbyPeriod);
        tmppanel.add(ButtonAnalysisbyDoW);
        tmppanel.add(ButtonAnalysisbyMod10);
        tmppanel.add(ButtonAnalysisbyMod10inDoW);
        tmppanel.add(ButtonAnalysisPartner);
        BtnPanel.add(tmppanel, BorderLayout.WEST);

        // 將兩個card加入mainpanel
        mainPanel.add(Survey, "Survey");
        mainPanel.add(BtnPanel, "BtnPanel");
    }

    private void setButtonbgBack(){
        if(lastButton != null) lastButton.setBackground(new JButton().getBackground());
    }

    private class SimpleListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String buttonName = e.getActionCommand();
            tablepanel.removeAll();
            if (buttonName.equals("下一期最會開的號碼")) {
                setButtonbgBack();
                lastButton = ButtonAnalysisbyNext;
                tablepanel.add(tmp.getanalysisNextPanel());
            } else if (buttonName.equals("輸入期數內最會開的號碼")) {
                setButtonbgBack();
                lastButton = ButtonAnalysisbyPeriod;
                tablepanel.add(tmp.getanalysisPeriodPanel());
            } else if (buttonName.equals("星期n最會開的號碼")) {
                setButtonbgBack();
                lastButton = ButtonAnalysisbyDoW;
                tablepanel.add(tmp.getanalysisDoWPanel());
            } else if (buttonName.equals("最會開的尾數次數")) {
                setButtonbgBack();
                lastButton = ButtonAnalysisbyMod10;
                tablepanel.add(tmp.getanalysisMod10Panel());
            } else if (buttonName.equals("星期n最會開的尾數")) {
                setButtonbgBack();
                lastButton = ButtonAnalysisbyMod10inDoW;
                tablepanel.add(tmp.getanalysisMod10inDoWPanel());
            } else {
                setButtonbgBack();
                lastButton = ButtonAnalysisPartner;
                tablepanel.add(tmp.getanalysisPartnerPanel());
            }
            mainPanel.revalidate();
            mainPanel.repaint();
            lastButton.setBackground(Color.GRAY);
        }
    }
}
