
package ibfocus;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IBFocus extends JFrame {
    private StarField starFieldPanel;
    private HakariGacha hakariPanel;
    private TS otherPanel;
    private FalsePermit falsePermit;
    String ans, clar;
    int hasilDE;
    
    public void setMap(String path, String text){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            String data1 = text;
            byte[] bytes = data1.getBytes();
            fos.write(bytes);
            fos.close();
            System.out.println("Data written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String path){
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
    }
    public void loadMapCres(String path){
        try{
            //InputStream is = getClass().getResourceAsStream(path);
            InputStream is = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            clar = br.readLine();
            System.out.println(clar);
            br.close();     
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public IBFocus(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        loadMap("/Users/dzakwanfarabi/.IB-Focus/onstat.txt");
        loadMapCres("/Users/dzakwanfarabi/.IB-Focus/ib-intensive.txt");
        
        //Testing
        //ans = "true";
        
        if (ans.equals("false")){
            falsePermit = new FalsePermit();
            starFieldPanel = null;
            getContentPane().add(falsePermit, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
        else if (ans.equals("true")){
            setMap("/Users/dzakwanfarabi/.IB-Focus/onstat.txt", "false");
            otherPanel = new TS(clar);
            otherPanel.setMainFrame(this);
            getContentPane().add(otherPanel, BorderLayout.CENTER); // Add the other panel
            revalidate();
            repaint();
            
            /*hasilDE = new java.util.Random().nextInt(2);
            if (hasilDE == 0){
                setMap("/Users/dzakwanfarabi/.IB-Focus/luck.txt", "gojo");
                starFieldPanel = new StarField(1500, 1000);
                starFieldPanel.setMainFrame(this); // Set the reference to MainFrame
                add(starFieldPanel, BorderLayout.CENTER); // Start with the StarField panel
            }
            else if (hasilDE == 1){
                hakariPanel = new HakariGacha();
                hakariPanel.setMainFrame(this); // Set the reference to MainFrame
                add(hakariPanel, BorderLayout.CENTER); // Start with the StarField panel
            }   */
        }
        
        
        

        

        



        // Create a button to switch to the other panel
        /*JButton switchPanelButton = new JButton("Switch to Other Panel");
        switchPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel();
            }
        });
        add(switchPanelButton, BorderLayout.SOUTH);
        */
        setSize(1000, 820);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void switchPanel() {
        if (hasilDE == 0){
            getContentPane().remove(starFieldPanel); // Remove the StarField panel
            starFieldPanel = null; // Set StarField panel to null to release its reference
        }
        else if (hasilDE == 1){
            getContentPane().remove(hakariPanel);
            hakariPanel = null;
        }
        
        System.gc(); // Request garbage collection to reclaim memory
        otherPanel = new TS(clar);
        otherPanel.setMainFrame(this);
        getContentPane().add(otherPanel, BorderLayout.CENTER); // Add the other panel
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IBFocus("IB-Focus");
            }
        });
    }
}

class FalsePermit extends JPanel {
    public FalsePermit() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Please use (⌃⌘I) to run IB-Focus Mode.");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 48));
        add(label, BorderLayout.CENTER);
    }
}


