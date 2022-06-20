package bmt;

import java.awt.*;

import javax.swing.*;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.io.*;
import java.util.*;

public class Frame extends JFrame implements ActionListener {
    private JPanel panel1;
    private JButton buttonstart;
    public String latest;
    public Frame() {
        super("539號碼預測"); // 建立標題名稱
        setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon("final_project\\picture\\logo.png");// create an ImageIcon
        setIconImage(image.getImage());// change icon of frame

        Image icon1 = new ImageIcon("final_project\\picture\\cover.jpg").getImage();
        panel1 = new BackgroundPanel(icon1);
        panel1.setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);
        buttonstart = new JButton("Press Here To Start!!!");
        buttonstart.setFont(new Font("Comic Sans", Font.BOLD, 20));
        buttonstart.setPreferredSize(new Dimension(0, 30));
        buttonstart.addActionListener(this);
        ImageIcon icon2 = new ImageIcon("final_project\\picture\\money.png");
        icon2.setImage(icon2.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        buttonstart.setIcon(icon2);
        panel1.add(buttonstart, BorderLayout.SOUTH);
        
    }

    public class BackgroundPanel extends JPanel {
        private Image image = null;

        public BackgroundPanel(Image image) {
            this.image = image;
        }

        // 固定背景圖片，允許這個JPanel可以在圖片上新增其他元件
        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == buttonstart) {
            try {
                Scanner scanner = new Scanner(System.in);
                Crawler test = new Crawler();
                int number = 0;
                File File = new File("final_project\\data\\lotonumber.csv");
                BufferedWriter writer = new BufferedWriter(new FileWriter(File));
                test.LoadData(0, "2007", 1, writer);
                writer.close();
                latest = "最新期數(請記住期號!!!) 日期為:\n";
                latest += test.LoadLatest(test.gettotaldata());
                JOptionPane.showMessageDialog(null,"資料已載入完成");
                JOptionPane.showMessageDialog(null,latest);
                scanner.close();
                Menu menu = new Menu();
                this.dispose();
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
    }
}