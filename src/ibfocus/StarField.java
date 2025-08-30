
package ibfocus;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class StarField extends JPanel implements Runnable {
    
    private static int w;
    private static int h;
    private static float alphaStr = 0;
    private static boolean alphaCheck = false;
    private static int scenePhase = 0;
    private static String text = "null";
    private final int maxStars = 1400;
    private final Star[] stars;
    private final int FPS = 60;
    private Star star;
    private static int counter = 0;
    private static int debug = 0;
    private static BufferedImage image;
    private static int starCondition = 0;
    static Sound sound = new Sound();
    //static JLabel sub = new JLabel("Domain Expansion:");
    Font font = new Font("Times New Roman", Font.PLAIN, 40);
    //private JPasswordField passwordField; // Declare the JPasswordField
    
    private IBFocus mainFrame; // Reference to the main frame

    // Existing constructor...
    public void setMainFrame(IBFocus mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void switchPanel() {
        mainFrame.switchPanel(); // Call the method in MainFrame to switch to another panel
    }
    
            
    

    StarField(int width, int height) {
        
        /*passwordField = new JPasswordField(10); // Initialize the JPasswordField with size 10
        add(passwordField); // Add the password field to the panel
        passwordField.setBounds(600, 500, 300, 50); // Set the bounds of the password field
        passwordField.setVisible(false); // Initially hide the password field*/
        
        this.w = width;
        this.h = height;
        this.stars = new Star[maxStars];
        this.star = new Star(1,1);

        for (int i = 0; i < maxStars; i++) {
            stars[i] = new Star(w, h);
        }

        Thread animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        Graphics2D g2d = (Graphics2D) g;
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = 1300/2 - length/2;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(0, 0, w, h);

        g2d.setColor(new Color(255, 255, 255));
        for (Star star : stars) {
            star.draw(g2d);
            
        /*if(scenePhase == 6){
            if (!passwordField.isVisible()) {
                passwordField.setVisible(true); // Show the password field when scenePhase is 7
                passwordField.requestFocusInWindow(); // Set focus to the password field
                System.out.println("JPass");
            }
        }*/
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaStr));
        g2d.setColor(Color.white);
        g2d.setFont(font);
        g2d.drawString(text, x, 450);
        
        //Image Alpha-Increment from 0 to 1
        
            
        }
    }

    @Override
    public void run() {
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
                //Update info (char position, etc.)
                moveStars();
                repaint();
                star.sceneRun();
                

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

    private void moveStars() {
        for (Star star : stars) {
            star.move();
        }
    }

    private class Star {
        private int x, y, radius, length;
        private float alpha;
        private final float alphaIncrement;
        private final int speedX, speedY;
        private final Color color;
        

        private Star(int width, int height) {
            Random random = new Random();
            this.x = random.nextInt(width);
            this.y = random.nextInt(height);
            this.radius = random.nextInt(3) + 1;
            this.length = random.nextInt(20) + 100;
            
            this.alpha = 0;
            this.alphaIncrement = 0.001f;
            this.speedX = random.nextInt(3) - 1;
            this.speedY = random.nextInt(3) - 1;
            this.color = new Color(255, 255, 255);
        }

        private void draw(Graphics2D g2d) {
            //g2d.drawImage(image, 150, 0, 1200, 800, null);
            drawRegular(g2d);
            
            /*switch (scenePhase) {
                case 0:
                    drawRegular(g2d);
                    break;
                case 1:
                    drawRegular(g2d);
                    break;
                case 2:
                    drawRegular(g2d);
                    break;
                case 3:
                    // Draw stars normally
                    drawRegular(g2d);
                    break;
                case 4:
                    // Draw stars with warp speed appearance
                    drawRegular(g2d);
                    break;
                case 5:
                    drawRegular(g2d);
                    break;
                           
            }*/
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
        private void moveStar(){
            x += speedX * 2;
            y += speedY * 2;

            if (x < 0) {
                x = w;
            } else if (x > w) {
                x = 0;
            }

            if (y < 0) {
                y = h;
            } else if (y > h) {
                y = 0;
            }
        }
        private void moveWarp() {
            // Warp speed movement logic
             // Increase the speed for a warp effect
            

            // Wrap around logic to keep stars within the panel bounds
            if (x < 0) {
                x = w;
            } else if (x > w) {
                x = 0;
            }

            if (y < 0) {
                y = h;
            } else if (y > h) {
                y = 0;
            }
        }

        private void drawWarp(Graphics2D g2d) {
            // Draw stars with warp speed appearance
            int size = radius * 2; // Double the size for warp effect
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.fillOval(x - radius, y - radius, size, size); // Draw circle instead of rectangle
            g2d.setComposite(originalComposite);
        }
        
        private void drawRegular(Graphics2D g2d){
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            
            if (starCondition == 0){
                g2d.fillRect(x, y, radius, radius);
                g2d.setComposite(originalComposite);
            }
            else if (starCondition == 1){
                x += speedX * 10;
                y += speedY * 10;
                g2d.setColor(color);
                g2d.drawLine(x, y, x + length, y); // Draw streak
                g2d.setComposite(originalComposite);
            }
            /*
            
            */
            
            
            
            
        }

        private void sceneRun(){
            switch (scenePhase){
        
            
        case 1:
            sound.set(0);
            sound.play();
            scenePhase++;
            break;
        
        case 2:
            text = "Domain Expansion:";
            alphaStr = 1;
            debug++;
            //System.out.println("SC 1: "+debug);
            if (counterCheck(155) == true){
                scenePhase++;
                debug=0;
            }
            break;
        case 3:
            //System.out.println("SC 2");
            text = "Infinite Void";
            if (counterCheck(65) == true){
                scenePhase++;
            }
            break;
        case 4:
            starCondition = 1;
            text = "";
            
            if (counterCheck(300)==true){
                scenePhase++;
            }
            /*try{
                //image = new ImageIcon("/starfield/res/ws.gif").getImage();
                //image = ImageIO.read(getClass().getResourceAsStream(""));
                image = Toolkit.getDefaultToolkit().createImage("/starfield/res/ws.gif");
            }
            catch (Exception e){
                System.out.println("Error: "+e);
            }
            //scenePhase++;
            */
            //System.out.println("Debug");
            
            break;

        case 6:
            try {
                System.out.println("Executed!");
                image = ImageIO.read(getClass().getResourceAsStream("/ibfocus/res/IB-Foc-TS.png"));
                scenePhase++;
            } catch (IOException ex) {
                Logger.getLogger(StarField.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            break;

            
        
    }
        }
                
                

        private void move() {
    
            
    switch (scenePhase){
        case 0: 
            moveStar();
            if (!alphaCheck){
                if (alpha < 0.99) {
                    alpha += alphaIncrement;
                }
                else{
                alpha = 1;
                alphaCheck = true;
                scenePhase++;
                } 
            }
            break;
        case 1:
            moveStar();
            break;
        case 2:
            moveStar();
            break;
        case 3:
            moveStar();
            break;
        case 4:
            moveWarp();
            alphaCheck = false;
            break;
        case 5:
            //System.out.println(alpha);
            if (!alphaCheck) {
                if (alpha > 0.01) {
                    if (alpha > 0.95){
                        alpha -= alphaIncrement;
                    }
                    else{
                        alpha -= 0.01f;
                    }
                }
                else {
                    alpha = 0;
                    alphaCheck = true;
                    scenePhase++;
                }
            }
            break;
        case 6:
            alphaCheck = false;
            break;
        case 7:
            switchPanel();
            /*if (!alphaCheck){
                if (alphaImg < 0.99) {
                    alphaImg += alphaIncrement;
                }
                else{
                alphaImg = 1;
                alphaCheck = true;
                scenePhase++;
                } 
            }*/
            break;
                   
    }
    
    
    
    
    
}

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StarField");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StarField starField = new StarField(1500, 1000);
        frame.add(starField);
        frame.setSize(1450, 820);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
