package bmt;

import java.awt.*;
import javax.swing.*;

public class frameOfanalysisAdapter {   //轉接器，統計頁面的子分頁在這邊轉接給母頁面
    private JPanel mainOfanalysis;
    private frameOfanalysisNext       panelOfanalysisNext;
    private frameOfanalysisDoW        panelOfanalysisDoW;
    private frameOfanalysisPeriod     paneOfanalysisPeriod;
    private frameOfanalysisMod10      paneOfanalysisMod10;
    private frameOfanalysisMod10inDoW paneOfanalysisMod10inDoW;
    private frameOfanalysisPartner    paneOfanalysisPartner;
    private CardLayout clo;

    public JPanel getPlainPanel(){
        clo.show(mainOfanalysis, "Plain");
        return mainOfanalysis;
    }

    public JPanel getanalysisNextPanel(){
        clo.show(mainOfanalysis, "AnalysisbyNext");
        return mainOfanalysis;
    }

    public JPanel getanalysisDoWPanel(){
        clo.show(mainOfanalysis, "AnalysisbyDoW");
        return mainOfanalysis;
    }

    public JPanel getanalysisPeriodPanel(){
        clo.show(mainOfanalysis, "AnalysisbyPeriod");
        return mainOfanalysis;
    }

    public JPanel getanalysisMod10Panel(){
        clo.show(mainOfanalysis, "AnalysisbyMod10");
        return mainOfanalysis;
    }

    public JPanel getanalysisMod10inDoWPanel(){
        clo.show(mainOfanalysis, "AnalysisbyMod10inDoW");
        return mainOfanalysis;
    }

    public JPanel getanalysisPartnerPanel(){
        clo.show(mainOfanalysis, "AnalysisbyPartner");
        return mainOfanalysis;
    }

    public frameOfanalysisAdapter(int CheckNum, int Times){
        clo = new CardLayout();
        mainOfanalysis = new JPanel(clo);
        panelOfanalysisNext      = new frameOfanalysisNext(CheckNum, Times);
        panelOfanalysisDoW       = new frameOfanalysisDoW(CheckNum, Times);
        paneOfanalysisPeriod     = new frameOfanalysisPeriod(CheckNum, Times); 
        paneOfanalysisMod10      = new frameOfanalysisMod10(CheckNum, Times);
        paneOfanalysisMod10inDoW = new frameOfanalysisMod10inDoW(CheckNum, Times); 
        paneOfanalysisPartner    = new frameOfanalysisPartner(CheckNum, Times);
        mainOfanalysis.add(new JPanel(), "Plain");
        mainOfanalysis.add(panelOfanalysisNext.getPanel(), "AnalysisbyNext");
        mainOfanalysis.add(panelOfanalysisDoW.getPanel() , "AnalysisbyDoW");
        mainOfanalysis.add(paneOfanalysisPeriod.getPanel(), "AnalysisbyPeriod");
        mainOfanalysis.add(paneOfanalysisMod10.getPanel(), "AnalysisbyMod10");
        mainOfanalysis.add(paneOfanalysisMod10inDoW.getPanel(), "AnalysisbyMod10inDoW");
        mainOfanalysis.add(paneOfanalysisPartner.getPanel(), "AnalysisbyPartner");
    }
}
