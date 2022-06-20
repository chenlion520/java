package bmt;

import javax.swing.JPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ImageIcon;

public class FrameOfPredict extends JPanel {
    private JPanel mainpanel;// 主框架
    private JPanel subpanel;// 富框架

    private JLabel label;
    private JLabel labelpic;// 放照片用
    private JPanel panelpic;// 照片的panel

    private JTable table;
    private JScrollPane pane;
    private CardLayout card;

    public JPanel getPanel() {
        return this;
    }

    public FrameOfPredict() {
        card = new CardLayout();
        mainpanel = new JPanel(card);
        subpanel = new JPanel(new BorderLayout(0, 5));
        // 標題
        label = new JLabel("下期預測號碼 !!!祝您中獎!!!");
        label.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 30));
        AnalysisTool539 tmp = new AnalysisTool539("final_project\\data\\lotonumber.csv");
        int i = 1;
        int[][] nums = ArrayTool.sortArray(tmp.Analysis());
        Object[][] tableDate = new Object[2][8];
        for (int[] k : nums) {
            if (i > 7)
                break;
            tableDate[0][i] = Integer.toString(k[0]);
            tableDate[1][i] = Integer.toString(k[1]);
            i++;
        }
        tableDate[0][0] = "強力數字";
        tableDate[1][0] = "得分累計";

        String[] name = { "", "第一強力數字", "第二強力數字", "第三強力數字", "第四強力數字", "第五強力數字", "第六強力數字", "第七強力數字" };
        table = new JTable(tableDate, name);
        // table.setPreferredScrollableViewportSize(new Dimension(550, 40));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        table.setBackground(new Color(190, 255, 255));
        // pane = new JScrollPane(table);

        ImageIcon icon1 = new ImageIcon("final_project\\picture\\earning.jpg");
        panelpic = new JPanel(new BorderLayout(0, 5));
        labelpic = new JLabel();
        labelpic.setIcon(icon1);
        panelpic.add(table, BorderLayout.NORTH);
        panelpic.add(labelpic, BorderLayout.CENTER);
        subpanel.add(label, BorderLayout.NORTH);
        subpanel.add(panelpic, BorderLayout.CENTER);
        mainpanel.add(subpanel, "sub");
        card.show(mainpanel, "sub");
        add(mainpanel);
    }

}