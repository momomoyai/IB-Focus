package ibfocus;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dzakwanfarabi
 */
public class TitleScreen extends JPanel {
    public TitleScreen() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("", new ImageIcon("/ibfocus/res/IB-Foc-TS.png"), JLabel.CENTER);
        label.setBounds(200, 0, 1200, 800);
        add(label, BorderLayout.CENTER);
    }
}
