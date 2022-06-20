package bmt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame {
    private JFrame frame;

    private JPanel returnpanel;// 返回按鈕的panel
    private JPanel menubtnpanel;// 三個功能的按鈕

    private JPanel tmppanel;// 編排用
    private JPanel tmp2panel;//

    private JButton buttontomenu;// 返回主頁
    private JButton buttonforDev;
    private JButton buttonforUse;
    private JButton buttonhistory;// 歷史資料
    private JButton buttondata;// 累積資料
    private JButton buttonnext;// 預測號碼

    private CardLayout midlayout;
    private JPanel midpanel;// 存放card的panel

    // 5個功能的主要程式
    private frameOfHistory historypanel;
    private frameOfCount countpanel;
    private FrameOfPredict predictpanel;
    private StaffSheet staffSheet;
    private Use use;
    Menu() {
        // 主頁
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(0, 0, 1000, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        tmppanel = new JPanel(new GridLayout(3, 1, 0, 100));// 拿來編排按鈕用
        tmp2panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        historypanel = new frameOfHistory();// 查詢歷史
        countpanel = new frameOfCount();// 查詢累積資料
        predictpanel = new FrameOfPredict();// 預測號碼
        staffSheet = new StaffSheet();//開發人員
        use = new Use();//使用手冊
        
        returnpanel = new JPanel();// 返回主頁的按鈕存放的地方
        midpanel = new JPanel(null);// 中間的card
        menubtnpanel = new JPanel(new BorderLayout());// 主頁的三個按鈕

        // 返回主頁按鈕加入
        buttontomenu = new JButton("返回主頁");
        buttontomenu.setBounds(0, 0, 100, 50);
        // label = new JLabel("");
        returnpanel.add(buttontomenu);
        ActionListener BackToMenu = new BackToMenu();
        buttontomenu.addActionListener(BackToMenu);
        // 中間三個按鈕
        midlayout = new CardLayout();
        midpanel.setLayout(midlayout);

        buttonhistory = new JButton("查看歷屆開獎號碼");
        buttonhistory.setFont(new Font("TimesRoman", Font.BOLD, 30));
        tmppanel.add(buttonhistory, BorderLayout.NORTH);
        ActionListener GoToHistory = new GoToHistory();
        buttonhistory.addActionListener(GoToHistory);
        buttondata = new JButton("查看統計資料");
        buttondata.setFont(new Font("TimesRoman", Font.BOLD, 30));
        tmppanel.add(buttondata, BorderLayout.CENTER);
        ActionListener GoToData = new GoToData();
        buttondata.addActionListener(GoToData);
        buttonnext = new JButton("生產下期預測號碼");
        buttonnext.setFont(new Font("TimesRoman", Font.BOLD, 30));
        tmppanel.add(buttonnext, BorderLayout.SOUTH);
        ActionListener GoToNext = new GoToNext();
        buttonnext.addActionListener(GoToNext);
        tmppanel.setPreferredSize(new Dimension(100, 100));
        // tmppanel.setSize(10,10);
        menubtnpanel.add(tmppanel, BorderLayout.CENTER);
        // tmppanel.removeAll();
        ActionListener GoToDev = new GoToDev();
        buttonforDev = new JButton("開發人員");
        buttonforDev.addActionListener(GoToDev);
        tmp2panel.add(buttonforDev);
        ActionListener GoToUse = new GoToUse();
        buttonforUse = new JButton("使用手冊");
        buttonforUse.addActionListener(GoToUse);
        
        tmp2panel.add(buttonforUse);
        menubtnpanel.add(tmp2panel, BorderLayout.SOUTH);

        // menubtnpanel.add(DevandUsepanel);

        midpanel.add(menubtnpanel, "menu");// 加入中間的
        // 將三個按鈕加入mainpanel
        midpanel.add(historypanel.getPanel(), "history");
        midpanel.add(countpanel.getPanel(), "count");
        midpanel.add(predictpanel.getPanel(), "predict");
        midpanel.add(staffSheet.getPanel(), "staff");
        midpanel.add(use.getPanel(), "use");

        frame.getContentPane().add(midpanel, BorderLayout.CENTER);
        frame.getContentPane().add(returnpanel, BorderLayout.WEST);
    }
    private class BackToMenu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            historypanel.BackToSurvey();
            countpanel.BackToSurvey();
            staffSheet.BackToSurvey();
            midlayout.show(midpanel, "menu");
        }
    }
    private class GoToHistory implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            midlayout.show(midpanel, "history");
        }
    }
    private class GoToData implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            midlayout.show(midpanel, "count");    
        }
    }
    private class GoToNext implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            midlayout.show(midpanel, "predict");       
        }
    }
    private class GoToDev implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            midlayout.show(midpanel, "staff");      
        }
    }
    private class GoToUse implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            midlayout.show(midpanel, "use");      
        }
    }

}
