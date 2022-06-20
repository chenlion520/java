package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Staff3eg extends JLabel{
    private int egclickedtime;
    private ImageIcon cat;
    private String nowpic;
    private int w;
    private int h;
    public Staff3eg(){
        setSize(new Dimension(600, 400));
        w = getWidth();
        h = getHeight();
        egclickedtime = 0;
        nowpic = "final_project\\picture\\Staff3.JPG";
        cat = new ImageIcon(nowpic); 
        cat.setImage(cat.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));  
        setIcon(cat);
        
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if(egclickedtime >= 8){
                    finalset();
                    return;
                }
                egclickedtime += 1;
                nowpic = "final_project\\picture\\CAT_" + Integer.toString(egclickedtime) + ".jpg";
                cat = new ImageIcon(nowpic);
                cat.setImage(cat.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));    
                setIcon(cat);
                revalidate();
                repaint();
            }
        });
    }

    private void finalset(){
        nowpic = "final_project\\picture\\jumpscare.JPG";
        cat = new ImageIcon(nowpic);
        cat.setImage(cat.getImage().getScaledInstance(h, w, Image.SCALE_SMOOTH));    
        setIcon(cat);
        revalidate();
        repaint();
    }


}
