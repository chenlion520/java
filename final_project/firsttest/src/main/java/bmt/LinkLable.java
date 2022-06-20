package bmt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LinkLable extends JPanel{
    private String  url;
    private boolean isSupported;
    private ImageIcon pic;

    public LinkLable(String picurl, String url, String text) {
        this.pic = new ImageIcon(picurl);
        pic.setImage(pic.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        setLayout(new BorderLayout());
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(pic);
        add(iconLabel, BorderLayout.NORTH);
        add(new JLabel(text), BorderLayout.SOUTH);
        this.url = url;
        try {
            this.isSupported = Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.gray);
                pic.setImage(pic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                setSize(getSize().width + 20, getSize().height + 20);
                if (isSupported)    setCursor(new Cursor(Cursor.HAND_CURSOR));
                revalidate();
            }
            public void mouseExited(MouseEvent e) {
                setBackground(new JPanel().getBackground());
                pic.setImage(pic.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                setSize(getSize().width - 20, getSize().height - 20);
                revalidate();
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(
                    new java.net.URI(LinkLable.this.url));
                } catch (Exception ex) {
                }
            }
        });
        
    }
}
