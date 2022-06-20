package bmt;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LinkpicLable extends JLabel{
    private String  url;
    private boolean isSupported;
    private ImageIcon pic;

    public LinkpicLable(String picurl, String url) {
        this.pic = new ImageIcon(picurl);
        pic.setImage(pic.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT));
        setIcon(pic);
        this.url = url;
        try {
            this.isSupported = Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (isSupported)    setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(
                    new java.net.URI(LinkpicLable.this.url));
                } catch (Exception ex) {
                }
            }
        });
    }
}