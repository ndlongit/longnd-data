import gui.MainFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class Application {

    private static final boolean DEBUG;
    static {
        DEBUG = "debug".equalsIgnoreCase(System.getProperty("mode"));
    }

    public static void main(String[] args) {
        String title = "Encryption and Decryption";
        if (DEBUG) {
            title = "[Debug mode] " + title;
        }
        final JFrame mainForm = new MainFrame();
        mainForm.setTitle(title);
        mainForm.setSize(750, 550);
        mainForm.setLocationRelativeTo(null);
        mainForm.setResizable(false);

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = mainForm.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        mainForm.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        String lnf = WindowsLookAndFeel.class.getName();
//        lnf = MetalLookAndFeel.class.getName(); //Java LNF
//        lnf = MotifLookAndFeel.class.getName(); //
        
        try {
            UIManager.setLookAndFeel(lnf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (DEBUG) {
            mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            mainForm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            WindowAdapter windowListener = new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                    int close = JOptionPane.showConfirmDialog(mainForm, "Are you sure to close the window?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (close == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            };
            mainForm.addWindowListener(windowListener);
        }

        // show Frame
        mainForm.setVisible(true);
    }
}
