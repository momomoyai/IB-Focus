package ibfocus;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;


public class MainFrame extends javax.swing.JFrame {

    private boolean ibInten;
    
    public MainFrame() {
        initComponents();
        quitLabel.setVisible(false);
        CresBut.setVisible(false);
        CresDesc.setVisible(false);
        
        ytStatSET();
        ibIntenSET();
        ibSET();
        luckStatSET();
        verSET();
                
        if(ibInten){
            QuitFoc.setVisible(false);
            QuitFoc.setEnabled(false);
            CresBut.setVisible(true);
            CresDesc.setVisible(true);
        }
    }
    
    public void ibIntenSET(){
        String inten = loadMap("/Users/dzakwanfarabi/.IB-Focus/ib-intensive.txt");
        if (inten.equals("true")){
            ibInten = true;
        }
        else if (inten.equals("false")){
            ibInten = false;
        }
        else{
            System.out.println("Error Occured in IB-Intensive Mode.");
        }
    }
    public void verSET(){
        String ver = loadMap("/Users/dzakwanfarabi/.IB-Focus/ver-frame.txt");
        verLabel.setText(ver);
    }
    
    
    public void ytStatSET(){
        String data = loadMap("/Users/dzakwanfarabi/.IB-Focus/ib-youtube.txt");
        String data2 = loadMap("/Users/dzakwanfarabi/.IB-Focus/ib-mlbbchess.txt");
        String data3 = loadMap("/Users/dzakwanfarabi/.IB-Focus/ib-twt.txt");
        if (data.equals("true")){
            ytStat.setText("Disabled");
        }
        else if (data.equals("false")){
            ytStat.setText("Enabled");
        }
        else{
            System.out.println("Error Occured in ytStatSET.");
        }
        
        if (data2.equals("true")){
            mlbbStat.setText("Disabled");
        }
        else if (data2.equals("false")){
            mlbbStat.setText("Enabled");
        }
        else{
            System.out.println("Error Occured in ytStatSET-mlbb.");
        }
        
        if (data3.equals("true")){
            twtStat.setText("Disabled");
        }
        else if (data2.equals("false")){
            twtStat.setText("Enabled");
        }
        else{
            System.out.println("Error Occured in ytStatSET-twt.");
        }
    }
    
    public void luckStatSET(){
        /*
        String data = loadMap("/Users/dzakwanfarabi/.IB-Focus/luck.txt");
        int hisL = Integer.parseInt(loadMap("/Users/dzakwanfarabi/.IB-Focus/luckNum.txt"));
        int hisI = Integer.parseInt(loadMap("/Users/dzakwanfarabi/.IB-Focus/immNum.txt"));
        if (data.equals("gojo")){
            luckStat.setText("Infinite Void (Jajan x1)");
        }
        else if (data.equals("normal")){
            luckStat.setText("Idle Death Gamble: Normal (Jajan x1)");
        }
        else if (data.equals("luck")){
            luckStat.setText("Idle Death Gamble: JACKPOT! (Jajan x2)");
            setMap("/Users/dzakwanfarabi/.IB-Focus/luckNum.txt", String.valueOf(hisL+1));
        }
        else if (data.equals("immerse")){
            luckStat.setText("IDLE DEATH GAMBLE: IMMERSIVE LUCK! (Jajan x5)");
            setMap("/Users/dzakwanfarabi/.IB-Focus/immNum.txt", String.valueOf(hisI+1));
        }
        String data1 = loadMap("/Users/dzakwanfarabi/.IB-Focus/luckNum.txt");
        String data2 = loadMap("/Users/dzakwanfarabi/.IB-Focus/immNum.txt");
        luckStat1.setText(data1);
        luckStat2.setText(data2);
        */
    }
    
    public void ibSET(){
        setMap("/Users/dzakwanfarabi/.IB-Focus/quitStat.txt", "false");
        String data = loadMap("/Users/dzakwanfarabi/.IB-Focus/ib-runtime.txt");
        ibmTime.setText(data);
        String inten = loadMap("/Users/dzakwanfarabi/.IB-Focus/ib-intensive.txt");
        if (inten.equals("true")){
            ibIntensive.setText("Enabled");
        }
        else if (inten.equals("false")){
            if (!ibInten){
                ibIntensive.setText("Disabled");
            }
            else{
                ibIntensive.setText("Disabled (Reactivate BiFoc to take effect)");
            } 
        }
    }
    
    
    public void setMap(String path, String text){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            String data = text;
            byte[] bytes = data.getBytes();
            fos.write(bytes);
            fos.close();
            System.out.println("Data written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void switchVisible(){
        QuitFoc.setVisible(false);
        headerLabel.setVisible(false);
        ibmRunLabel.setVisible(false);
        ibmRunLabel1.setVisible(false);
        ibIntensiveLabel.setVisible(false);
        ibIntensive.setVisible(false);
        ibmTime.setVisible(false);
        jScrollPane1.setVisible(false);
        jTextArea1.setVisible(false);
        refreshBut.setVisible(false);
        verLabel.setVisible(false);
        ytLabel1.setVisible(false);
        ytLabel2.setVisible(false);
        ytStat.setVisible(false);
        mlbbLabel.setVisible(false);
        mlbbStat.setVisible(false);
        twtLabel.setVisible(false);
        twtStat.setVisible(false);
        /*luckLabel.setVisible(false);
        luckLabel1.setVisible(false);
        luckLabel2.setVisible(false);
        luckStat.setVisible(false);
        luckStat1.setVisible(false);
        luckStat2.setVisible(false);*/
        verLabel1.setVisible(false);
        quitLabel.setVisible(true);
    }
    
    public String loadMap(String path){
        String ans = "Failed to fetch data.";
        try{
            //InputStream is = getClass().getResourceAsStream(path);
            InputStream is = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ans = br.readLine();
            System.out.println(ans);
            br.close();     
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        verLabel1 = new javax.swing.JLabel();
        quitLabel = new javax.swing.JLabel();
        verLabel = new javax.swing.JLabel();
        headerLabel = new javax.swing.JLabel();
        CresDesc = new javax.swing.JLabel();
        CresBut = new javax.swing.JLabel();
        QuitFoc = new javax.swing.JButton();
        ibIntensiveLabel = new javax.swing.JLabel();
        ibIntensive = new javax.swing.JLabel();
        ibmRunLabel1 = new javax.swing.JLabel();
        ibmRunLabel = new javax.swing.JLabel();
        ibmTime = new javax.swing.JLabel();
        mlbbLabel = new javax.swing.JLabel();
        twtLabel = new javax.swing.JLabel();
        ytLabel1 = new javax.swing.JLabel();
        ytLabel2 = new javax.swing.JLabel();
        mlbbStat = new javax.swing.JLabel();
        twtStat = new javax.swing.JLabel();
        ytStat = new javax.swing.JLabel();
        refreshBut = new javax.swing.JButton();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 600));

        mainPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        mainPanel.setSize(new java.awt.Dimension(800, 600));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        verLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        verLabel1.setText("Part of Niners Universe");
        mainPanel.add(verLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        quitLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        quitLabel.setText("IB-Focus Mode will be turned off shortly.");
        mainPanel.add(quitLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 700, -1));

        verLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        verLabel.setText("Version 2.110 (20/04/2024)");
        mainPanel.add(verLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        headerLabel.setFont(new java.awt.Font("Academy Engraved LET", 0, 48)); // NOI18N
        headerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfocus/res/IBDP.png"))); // NOI18N
        headerLabel.setText("IB-Focus Mode");
        mainPanel.add(headerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        CresDesc.setText("Crescendo Mode has disabled Quit function.");
        mainPanel.add(CresDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, -1, -1));

        CresBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfocus/res/Crescendo_Quit.png"))); // NOI18N
        mainPanel.add(CresBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 200, 130));

        QuitFoc.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        QuitFoc.setText("Quit");
        QuitFoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitFocActionPerformed(evt);
            }
        });
        mainPanel.add(QuitFoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 130, 40));

        ibIntensiveLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        ibIntensiveLabel.setText("IB-Focus Intensive Mode (Crescendo):");
        mainPanel.add(ibIntensiveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 220, -1));

        ibIntensive.setText("ibIntensive");
        mainPanel.add(ibIntensive, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 250, -1));

        ibmRunLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        ibmRunLabel1.setText("Restricted Applications:");
        mainPanel.add(ibmRunLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 150, -1));

        ibmRunLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        ibmRunLabel.setText("IB-Focus Mode Runtime:");
        mainPanel.add(ibmRunLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 220, -1));

        ibmTime.setText("ibmTime");
        mainPanel.add(ibmTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 180, -1));

        mlbbLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        mlbbLabel.setText("MLBB-MCGG Restriction:");
        mainPanel.add(mlbbLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 150, -1));

        twtLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        twtLabel.setText("Twitter Restriction:");
        mainPanel.add(twtLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 130, -1));

        ytLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        ytLabel1.setText("YouTube Restriction:");
        mainPanel.add(ytLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 130, -1));

        ytLabel2.setText("[ Enable/Disable for Learning Process by pressing ( ⌃ + Globe + Y) ]");
        mainPanel.add(ytLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        mlbbStat.setText("mlbbStat");
        mainPanel.add(mlbbStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 180, -1));

        twtStat.setText("twtStat");
        mainPanel.add(twtStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 180, -1));

        ytStat.setText("ytStat");
        mainPanel.add(ytStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 180, -1));

        refreshBut.setText("Refresh");
        refreshBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButActionPerformed(evt);
            }
        });
        mainPanel.add(refreshBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("- YouTube\n- MLBB x MCGG\n- Anime/Drakoran\n- Manga Reading\n- Opera & Firefox\n- Social Medias\n- Roblox");
        jTextArea1.setEnabled(false);
        mainPanel.add(jTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 203, 120, 130));
        mainPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 160, 150));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButActionPerformed
        ytStatSET();
        ibSET();
    }//GEN-LAST:event_refreshButActionPerformed

    private void QuitFocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitFocActionPerformed
        if (!ibInten){
            int confirm = JOptionPane.showConfirmDialog(null,"Quitting will end your current IB-Focus Mode session. Proceed?", "Quit BiFoc Confirmation", JOptionPane.YES_NO_OPTION);
        
            //If the user confirmed to logout, the account used will be logged out
            if (confirm == 0){
                setMap("/Users/dzakwanfarabi/.IB-Focus/ib-runtime.txt", "0");
                setMap("/Users/dzakwanfarabi/.IB-Focus/ib-youtube.txt", "false");
                setMap("/Users/dzakwanfarabi/.IB-Focus/quitStat.txt", "true");
                switchVisible();
            }
        }
        else {
            JOptionPane.showMessageDialog(rootPane, "Quitting IB-Focus Mode in Intensive Mode is not permitted.");
        }
    }//GEN-LAST:event_QuitFocActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CresBut;
    private javax.swing.JLabel CresDesc;
    private javax.swing.JButton QuitFoc;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel ibIntensive;
    private javax.swing.JLabel ibIntensiveLabel;
    private javax.swing.JLabel ibmRunLabel;
    private javax.swing.JLabel ibmRunLabel1;
    private javax.swing.JLabel ibmTime;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mlbbLabel;
    private javax.swing.JLabel mlbbStat;
    private javax.swing.JLabel quitLabel;
    private javax.swing.JButton refreshBut;
    private javax.swing.JLabel twtLabel;
    private javax.swing.JLabel twtStat;
    private javax.swing.JLabel verLabel;
    private javax.swing.JLabel verLabel1;
    private javax.swing.JLabel ytLabel1;
    private javax.swing.JLabel ytLabel2;
    private javax.swing.JLabel ytStat;
    // End of variables declaration//GEN-END:variables
}
