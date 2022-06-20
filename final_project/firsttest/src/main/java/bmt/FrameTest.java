package bmt;

import javax.swing.JFrame;

public class FrameTest {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(1000, 600); // 設定寬，長
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定預設的關閉視窗
        frame.setVisible(true); // 視窗預設是不可見的
    }
}