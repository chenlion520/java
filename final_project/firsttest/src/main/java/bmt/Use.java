
package bmt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;

public class Use extends JFrame{
    private JPanel Use;
    private JPanel Survey;//主畫面

    private JLabel Labeltest;

    private CardLayout clo;
   
    public JPanel getPanel() {
        return Use;
    }
    public Use(){
       try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("final_project\\data\\using.txt"), Charset.forName("UTF-8")));
        String str = "";
        String tmp = "<html>";

        
        Survey = new JPanel(new FlowLayout(FlowLayout.CENTER));
        while((str = reader.readLine()) != null){
            for(int i = 0 ; i < str.length() ; i++){
                //System.out.printf("%c",str.charAt(i));
                if(str.charAt(i) == 'o'){
                    tmp += "<br>";
                    //System.out.printf("hi");
                }
                else if(str.charAt(i) == 'x'){
                    tmp += "&nbsp &nbsp &nbsp &nbsp";
                }
                else{
                    tmp += str.charAt(i); 
                }
            }
            //System.out.println("");
        }
        tmp += "</html>";
        Labeltest = new JLabel(tmp);
        //Container cp=this.getContentPane();
        JScrollPane js = new JScrollPane(Labeltest);
        js.setBorder(BorderFactory.createEmptyBorder());
        js.setPreferredSize(new Dimension(800,500));
        //cp.add(js,BorderLayout.CENTER);
        Survey.add(js);
        //System.out.println(tmp);
        reader.close();
        clo = new CardLayout();
        Use = new JPanel(clo);
        Use.add(Survey,"Survey");
        add(Use);
        clo.show(Use,"Survey");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
