
package ibfocus;

/*import static ibfocus.StarField.sound;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;*/ 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HakariGacha extends JPanel implements Runnable {
    
    private final int FPS = 60;
    private JLabel BG, BGLuck, BGimm;
    private boolean immerse = false;
    private boolean luck = false;
    private int scenePhase = 0;
    static Sound sound = new Sound();
    int counter = 0;
    private IBFocus mainFrame; // Reference to the main frame
    
    HakariGacha() {
        //Bound di Randomnya itu ngerangenya sampe n-1 (contoh Boundnya 5, berarti rangenya dari 0-4)    
        int spins = 0;
        
        //Gif-Delay Debug
        //System.out.println(getDelayFromGif("/Users/dzakwanfarabi/NetBeansProjects/IB-Focus/src/ibfocus/res/hakariNormalDE.gif"));
        
        while (spins < 30){
            spins++;
            int hasilGacha = new Random().nextInt(239) + 1;
            if (hasilGacha == 111){
                if (spins == 1){
                    immerse = true;
                }
                luck = true;
            }
        }
        Thread animationThread = new Thread(this);
        animationThread.start();
        
        BG = new javax.swing.JLabel();
        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfocus/res/hakariNormalDE.gif")));
        add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 1470, 940));
        BGLuck = new javax.swing.JLabel();
        BGLuck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfocus/res/LuckDE.gif")));
        add(BGLuck, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 1470, 940));
        BGimm = new javax.swing.JLabel();
        BGimm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfocus/res/ImmersiveLuck.gif")));
        add(BGimm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 1470, 940));
        
        BG.setVisible(false);
        BGLuck.setVisible(false);
        BGimm.setVisible(false);
        
    }
    /*public static int getDelayFromGif(String gifFilePath) {
    try {
        ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
        ImageInputStream stream = new FileImageInputStream(new File(gifFilePath));
        reader.setInput(stream);

        int numFrames = reader.getNumImages(true);
        int timeLength = 0; // Total time length in milliseconds

        for (int i = 0; i < numFrames; i++) {
            IIOMetadata metadata = reader.getImageMetadata(i);
            Node root = metadata.getAsTree("javax_imageio_gif_image_1.0");
            NodeList childNodes = root.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {
                Node node = childNodes.item(j);
                if (node.getNodeName().equals("GraphicControlExtension")) {
                    NamedNodeMap attributes = node.getAttributes();
                    Node delayNode = attributes.getNamedItem("delayTime");
                    if (delayNode != null) {
                        int delay = Integer.parseInt(delayNode.getNodeValue());
                        timeLength += delay * 10; // Convert to milliseconds
                    }
                }
            }
        }

        return timeLength;
    } catch (IOException e) {
        e.printStackTrace();
        return -1; // Error handling
    }
}*/
    
    public void run(){
        double val = 1000000000/FPS;
        double delta = 0;
        long timeLast = System.nanoTime();
        long timeRn;
        long timer = 0;
        int drawCount = 0;
        
        while(true){
            
            timeRn = System.nanoTime();
            delta += (timeRn-timeLast)/val;
            timer += (timeRn-timeLast);
            timeLast = timeRn;
            
            if (delta >= 1){
                sceneRun();
                

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        
                
                delta--;
                drawCount++;
            }
            
            if (timer >= 1000000000){
                System.out.println("Cek FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
            
    
            
            
            
        }
    }
    
    private void sceneRun(){
        switch(scenePhase){
            case 0:
                if (luck){
                    if (immerse){
                        setMap("/Users/dzakwanfarabi/.IB-Focus/luck.txt", "immerse");
                        sound.set(3);
                        sound.play();
                    }
                    else{
                        setMap("/Users/dzakwanfarabi/.IB-Focus/luck.txt", "luck");
                        sound.set(2);
                        sound.play();
                    }
                }
                else{
                    setMap("/Users/dzakwanfarabi/.IB-Focus/luck.txt", "normal");
                    sound.set(1);
                    sound.play();
                }
                scenePhase++;
                break;
                
            case 1:
                if (luck){
                    if (immerse){
                        BGimm.setVisible(true);
                        if (counterCheck(1500)){
                            scenePhase++;
                        }
                    }
                    else{
                        BGLuck.setVisible(true); //Executed
                    
                        if (counterCheck(1260)){
                            scenePhase++;
                        }
                    }
                }
                else{
                    BG.setVisible(true); //Executed
                    
                    if (counterCheck(600)){
                        scenePhase++;
                    }
                }
                
                
                break;
            case 2:
                switchPanel();
                break;
        }
    }
    public boolean counterCheck(int target){
        boolean counterCheck = false;
        counter++;
        if (counter > target){
            counterCheck = true;
            counter = 0;
        }
        return counterCheck;
    }
    public void setMainFrame(IBFocus mainFrame) {
        this.mainFrame = mainFrame;
    }
    private void switchPanel() {
        mainFrame.switchPanel(); // Call the method in MainFrame to switch to another panel
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HakariGacha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HakariGacha hakariGacha = new HakariGacha();
        frame.add(hakariGacha);
        frame.setSize(1450, 820);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
