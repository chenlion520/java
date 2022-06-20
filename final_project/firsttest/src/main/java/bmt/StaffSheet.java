package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

public class StaffSheet extends JFrame {
    private JPanel StaffSheetPanel;
    private JPanel Staff1Panel;
    private JPanel Staff2Panel;
    private JPanel Staff3Panel;
    private JPanel Survey;
    private JPanel btnPanel;

    private JButton Staff1Button;
    private JButton Staff2Button;
    private JButton Staff3Button;
    private JButton BacktoSurvey1;
    private JButton BacktoSurvey2;
    private JButton BacktoSurvey3;
    
    private wavPlayer musicPlayer1;
    private wavPlayer musicPlayer2;
    private wavPlayer musicPlayer3;
    private ExecutorService es = Executors.newCachedThreadPool();

    private CardLayout clo;

    public JPanel getPanel() {

        return StaffSheetPanel;
    }

    public StaffSheet() {
        clo = new CardLayout();
        StaffSheetPanel = new JPanel(clo);
        Staff1Button = new JButton("鄭立揚");
        Staff2Button = new JButton("劉胤賢");
        Staff3Button = new JButton("林家安");
        btnPanel = new JPanel();
        btnPanel.add(Staff1Button);
        btnPanel.add(Staff2Button);
        btnPanel.add(Staff3Button);
        ActionListener listener = new toStaffAction();
        Staff1Button.addActionListener(listener);
        Staff2Button.addActionListener(listener);
        Staff3Button.addActionListener(listener);

        BacktoSurvey1 = new JButton("回製作人員名單首頁");
        BacktoSurvey2 = new JButton("回製作人員名單首頁");
        BacktoSurvey3 = new JButton("回製作人員名單首頁");
        ActionListener backlistener = new Bact2Survey();
        BacktoSurvey1.addActionListener(backlistener);
        BacktoSurvey2.addActionListener(backlistener);
        BacktoSurvey3.addActionListener(backlistener);

        Survey = new JPanel(new BorderLayout());
        Survey.add(btnPanel, BorderLayout.CENTER);

        StaffSheetPanel.add(Survey, "Survey");
        setStaff1();
        setStaff2();
        setStaff3();
        add(StaffSheetPanel);
        clo.show(StaffSheetPanel, "Survey");
    }

    private void setStaff1() {
        musicPlayer1 = new wavPlayer();
        musicPlayer1.setPath("final_project\\audio\\Staff1_dejavu.wav");
        musicPlayer1.set();
        Staff1Panel = new JPanel(new BorderLayout());
        JPanel main1Panel = new JPanel(new BorderLayout());
        JLabel staff1Label = new JLabel("鄭立揚", JLabel.CENTER);
        // ImageIcon staff1pic = new ImageIcon("final_project\\picture\\staff1.jpg");
        LinkpicLable piclink = new LinkpicLable("final_project\\picture\\staff1.jpg",
                "https://youtube.com/clip/UgkxAY1FqgRiMUE9IT5RKKtxSmgZxM7clLY0");
        JButton button = new JButton("你不敢按");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "好了啦特哥椅子哥丹利哥死哥維爾戈靈魂收割多佛朗明哥豬大哥蒼藍鴿中華民國國歌UC姐K7\n姐好運姐冰冰姐美鳳姐小燕姐法拉利姐曾聖傑林俊傑傳奇羅傑端午節中秋節萬聖節聖誕節水\n銀秒解卍解暈還解夜班保全寒冰霸拳第一神拳昇龍拳獸魂轟拳流水岩碎拳男女平權邊境牧羊\n犬林益全搬磚工人小金人糖豆人力量人衣吊人內褲超人空幹人朵莉人鋼鐵人蟻人神力女超人\n進擊的巨人李李仁壯烈成仁木葉三忍閃電十一人");
            }
        });
        main1Panel.add(button, BorderLayout.EAST);
        main1Panel.add(staff1Label, BorderLayout.NORTH);
        main1Panel.add(piclink, BorderLayout.CENTER);
        Staff1Panel.add(main1Panel, BorderLayout.CENTER);
        Staff1Panel.add(BacktoSurvey1, BorderLayout.SOUTH);
        StaffSheetPanel.add(Staff1Panel, "Staff1");
    }

    private void setStaff2() {
        musicPlayer2 = new wavPlayer();
        musicPlayer2.setPath("final_project\\audio\\Staff2_boom.wav");
        musicPlayer2.set();
        Staff2Panel = new JPanel(new BorderLayout());
        JLabel nameLabe2 = new JLabel("劉胤賢", JLabel.CENTER);
        JPanel main1Pane2 = new JPanel(new BorderLayout());
        JPanel buttonpanel = new JPanel(new BorderLayout());
        LinkpicLable link = new LinkpicLable("final_project\\picture\\staff2_5.jpg",
                "https://www.facebook.com/profile.php?id=100006854466740");
        JButton button = new JButton("自摸中洞");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageIcon img = new ImageIcon("final_project\\picture\\staff2_4.jpg");
                img.setImage(img.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(null, img);
                JOptionPane.showMessageDialog(null, "奉勸各位勤洗手戴口罩 不然怎麼死的也不知道");
            }
        });
        main1Pane2.add(nameLabe2, BorderLayout.NORTH);
        main1Pane2.add(link, BorderLayout.CENTER);
        buttonpanel.add(button, BorderLayout.CENTER);
        main1Pane2.add(buttonpanel, BorderLayout.SOUTH);
        Staff2Panel.add(main1Pane2, BorderLayout.CENTER);
        Staff2Panel.add(BacktoSurvey2, BorderLayout.SOUTH);
        StaffSheetPanel.add(Staff2Panel, "Staff2");
    }
    
    private void setStaff3() {
        //////////////////////////////////////////////////////////////
        musicPlayer3 = new wavPlayer();
        musicPlayer3.setPath("final_project\\audio\\Staff3_bgaudio.wav");
        musicPlayer3.set();
        ///////////////////////////////////////////////////////////////
        Staff3Panel = new JPanel(new BorderLayout());
        JPanel mainS3Panel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("林家安", JLabel.CENTER);
        LinkLable fbicon = new LinkLable("final_project\\picture\\fb_icon.png", "https://www.facebook.com/profile.php?id=100006854466740", "臉書連結");
        LinkLable twittwericon = new LinkLable("final_project\\picture\\twitter_icon.png", "https://twitter.com/VvzL5596", "推特連結");
        JPanel iconPanel = new JPanel();
        iconPanel.add(fbicon);
        iconPanel.add(twittwericon);
        Staff3eg staff3eg = new Staff3eg();
        mainS3Panel.add(staff3eg, BorderLayout.CENTER);
        mainS3Panel.add(iconPanel,BorderLayout.SOUTH);
        Staff3Panel.add(nameLabel, BorderLayout.NORTH);
        Staff3Panel.add(mainS3Panel, BorderLayout.CENTER);
        Staff3Panel.add(BacktoSurvey3, BorderLayout.SOUTH);
        StaffSheetPanel.add(Staff3Panel, "Staff3");
    }

    private class toStaffAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "鄭立揚":
                    setStaff1();
                    es.execute(musicPlayer1);

                    ImageIcon img = new ImageIcon("final_project\\picture\\staff1_1.jpg");
                    JOptionPane.showMessageDialog(null, img);
                    clo.show(StaffSheetPanel, "Staff1");
                    break;
                case "劉胤賢":
                    setStaff2();
                    es.execute(musicPlayer2);
                    ImageIcon img2 = new ImageIcon("final_project\\picture\\staff2_3.jpg");
                    img2.setImage(img2.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT));
                    JOptionPane.showMessageDialog(null, img2);
                    ImageIcon img3 = new ImageIcon("final_project\\picture\\staff2_2.jpg");
                    img3.setImage(img3.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT));
                    JOptionPane.showMessageDialog(null, img3);
                    clo.show(StaffSheetPanel, "Staff2");
                    break;
                case "林家安":
                    ///////////////////////////////////////////////////////////////////////
                    setStaff3();
                    es.execute(musicPlayer3);
                    ////////////////////////////////////////////////////////////////
                    clo.show(StaffSheetPanel, "Staff3");
                    break;
                default:
                    break;
            }
            revalidate();
            repaint();
        }
    }

    public void BackToSurvey() {
        if(musicPlayer1.isPlaying()){
            musicPlayer1.stopplaying();
        }
        if(musicPlayer2.isPlaying()){
            musicPlayer2.stopplaying();
        }
        if(musicPlayer3.isPlaying()){
            musicPlayer3.stopplaying();
        }
        
        clo.show(StaffSheetPanel, "Survey");
        revalidate();
        repaint();
    }

    private class Bact2Survey implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BackToSurvey();
        }
    }

}
