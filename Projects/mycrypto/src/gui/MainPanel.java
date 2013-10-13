package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.crypto.SecretKey;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mycrypto.Encrypter;
import mycrypto.asymmetric.AsymmetricEncrypter;
import mycrypto.asymmetric.AsymmetricEncrypterFactory;
import mycrypto.symmetric.BPEEncrypterFactory;
import mycrypto.symmetric.SymmetricEncrypter;
import mycrypto.symmetric.SymmetricEncrypterFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MainPanel extends JPanel {

    private static final int COLUMN = 25;
    private static final int ROW = 200;

    SecretKey key = null;
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    JPanel southPanel = new JPanel();
    JRadioButton radSymmetricCipher = new JRadioButton();
    JRadioButton radAsymmetricCipher = new JRadioButton();
    JLabel jLabel1 = new JLabel();
    JPanel scrollPanePanel = new JPanel();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel inputFile = new JLabel();
    JLabel keyFile = new JLabel();
    JLabel outputFile = new JLabel();

    JButton encryptFile = new JButton("Encrypty File");
    JButton decryptFile = new JButton("Decrypt File");

    JButton encryptText = new JButton("Encrypt Text >>");
    JButton decryptText = new JButton("<< Decrypt Text");

    JTextField txtInputFile = new JTextField();
    JTextField txtKeyFile = new JTextField();
    JTextField txtOutputFile = new JTextField();
    JTextField txtKeySize = new JTextField();

    JButton btnBrowseInputFile = new JButton();
    JButton btnBrowseKeyFile = new JButton();
    JButton btnBrowseOutputFile = new JButton();
    JLabel algorithm = new JLabel();
    JLabel keySize = new JLabel();
    JComboBox cmbAlgorithmSymC = new JComboBox();
    JLabel password = new JLabel();
    ButtonGroup buttonGroup1 = new ButtonGroup();
    JPasswordField jPasswordField1 = new JPasswordField();
    JRadioButton textEncryption = new JRadioButton();
    JRadioButton fileEncryption = new JRadioButton();
    JPanel northPanel = new JPanel();
    BorderLayout borderLayout2 = new BorderLayout();
    JPanel jPanel2 = new JPanel();
    JPanel centerPanel = new JPanel();
    JTextArea plainTextArea = new JTextArea(null, ROW, COLUMN);
    JTextArea cipherTextArea = new JTextArea(null, ROW, COLUMN);
    JScrollPane scrollPane1 = null;
    JScrollPane scrollPane2 = null;
    JLabel publicKey = new JLabel("Public Key");
    JTextField txtPublicKey = new JTextField();
    JButton btnBrowsePublicKey = new JButton("Browse");

    private static final boolean DEBUG;
    static {
        DEBUG = "debug".equalsIgnoreCase(System.getProperty("mode"));
    }

    public MainPanel() {
        try {
            jbInit();
            updateGUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        Dimension d = new Dimension(400, 25);
        txtInputFile.setPreferredSize(d);
        txtKeyFile.setPreferredSize(d);
        txtPublicKey.setPreferredSize(d);
        txtOutputFile.setPreferredSize(d);
        jPasswordField1.setPreferredSize(d);
        cmbAlgorithmSymC.setPreferredSize(new Dimension(200, 25));
        txtKeySize.setPreferredSize(new Dimension(100, 25));
        radSymmetricCipher.setSelected(true);
        radSymmetricCipher.setText("SymmetricCipher");
        radAsymmetricCipher.setText("AsymmetricCipher");

        jLabel1.setText("Encryption Type: ");
        jPanel5.setLayout(gridBagLayout1);
        inputFile.setText("Input File: ");
        keyFile.setText("Secret Key: ");
        outputFile.setText("Output File: ");
        btnBrowseInputFile.setText("Browse");
        btnBrowseKeyFile.setText("Browse");
        btnBrowseOutputFile.setText("Browse");
        algorithm.setText("Algorithm: ");
        keySize.setText("Key Size: ");
        password.setText("Password: ");
        borderLayout1.setHgap(5);
        borderLayout1.setVgap(5);
        textEncryption.setText("Text Encryption");
        textEncryption.setSelected(true);
        fileEncryption.setText("File Encryption");
        ButtonGroup group2 = new ButtonGroup();
        group2.add(textEncryption);
        group2.add(fileEncryption);
        northPanel.setLayout(borderLayout2);
        southPanel.add(encryptFile);
        southPanel.add(decryptFile);
        buttonGroup1.add(radSymmetricCipher);
        buttonGroup1.add(radAsymmetricCipher);
        Insets insets1 = new Insets(15, 5, 15, 5);
        Insets insets2 = new Insets(3, 3, 3, 3);
        jPanel4.setLayout(new GridBagLayout());
        jPanel4.add(encryptText, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets1, 0, 0));
        jPanel4.add(decryptText, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets1, 0, 0));
        scrollPanePanel.setLayout(new BorderLayout());

        scrollPane1 = new JScrollPane(plainTextArea);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePanel.add(scrollPane1, BorderLayout.WEST);

        scrollPane2 = new JScrollPane(cipherTextArea);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePanel.add(scrollPane2, BorderLayout.EAST);

        scrollPanePanel.add(jPanel4, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(jPanel5, BorderLayout.NORTH);
        centerPanel.add(scrollPanePanel, BorderLayout.CENTER);

        jPanel5.add(inputFile, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(txtInputFile, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(btnBrowseInputFile, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0,
                0));

        jPanel5.add(outputFile, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(txtOutputFile, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(btnBrowseOutputFile, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0,
                0));

        jPanel5.add(keyFile, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(txtKeyFile, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(btnBrowseKeyFile, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0, 0));

        jPanel5.add(publicKey, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(txtPublicKey, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(btnBrowsePublicKey, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0,
                0));

        jPanel5.add(algorithm, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(cmbAlgorithmSymC, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2,
                0, 0));

        jPanel5.add(keySize, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(txtKeySize, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));

        jPanel5.add(password, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));
        jPanel5.add(jPasswordField1, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0, 0));
        JLabel temLabel = new JLabel();
        temLabel.setPreferredSize(new Dimension(70, 25));
        jPanel5.add(temLabel, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets2, 0, 0));

        jPanel5.add(new JLabel(), new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets2, 0, 0));

        northPanel.add(jPanel1, BorderLayout.NORTH);
        jPanel1.add(jLabel1, null);
        jPanel1.add(radSymmetricCipher, null);
        jPanel1.add(radAsymmetricCipher, null);
        northPanel.add(jPanel2, BorderLayout.SOUTH);
        jPanel2.add(textEncryption, null);
        jPanel2.add(fileEncryption, null);

        radSymmetricCipher.addActionListener(new ActionRadioCipherType());
        radAsymmetricCipher.addActionListener(new ActionRadioCipherType());

        textEncryption.addActionListener(new ActionRadioEncryptionType());
        fileEncryption.addActionListener(new ActionRadioEncryptionType());

        ActionButtonBrowse actionButtonBrowse = new ActionButtonBrowse();
        btnBrowseInputFile.addActionListener(actionButtonBrowse);
        btnBrowseKeyFile.addActionListener(actionButtonBrowse);
        btnBrowseOutputFile.addActionListener(actionButtonBrowse);
        btnBrowsePublicKey.addActionListener(actionButtonBrowse);

        ActionButtonEncryptAndDecrypt encryptDecryptAction = new ActionButtonEncryptAndDecrypt();
        encryptFile.addActionListener(encryptDecryptAction);
        decryptFile.addActionListener(encryptDecryptAction);

        encryptText.addActionListener(encryptDecryptAction);
        decryptText.addActionListener(encryptDecryptAction);

        cmbAlgorithmSymC.addActionListener(new ActionCmbAlgorithmSymC());

        String[] algorithmList = null;
        if (radSymmetricCipher.isSelected()) {
            algorithmList = SymmetricEncrypter.getAlgorithmList();
        } else {
            algorithmList = AsymmetricEncrypter.getAlgorithmList();
        }

        for (int i = 0; i < algorithmList.length; i++) {
            cmbAlgorithmSymC.addItem(algorithmList[i]);
        }
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(new JPanel(), BorderLayout.EAST);
    }

    private void updateGUI() {
        boolean textcrypto = textEncryption.isSelected();
        txtInputFile.setVisible(!textcrypto);
        inputFile.setVisible(!textcrypto);
        btnBrowseInputFile.setVisible(!textcrypto);

        txtOutputFile.setVisible(!textcrypto);
        outputFile.setVisible(!textcrypto);
        btnBrowseOutputFile.setVisible(!textcrypto);

        plainTextArea.setVisible(textcrypto);
        cipherTextArea.setVisible(textcrypto);

        encryptFile.setVisible(!textcrypto);
        decryptFile.setVisible(!textcrypto);
        scrollPanePanel.setVisible(textcrypto);
    }

    private class ActionRadioCipherType implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cmbAlgorithmSymC.removeAllItems();
            JRadioButton radio = (JRadioButton) e.getSource();
            String[] algorithmList = null;
            if (radSymmetricCipher == radio) {
                algorithmList = SymmetricEncrypter.getAlgorithmList();
            } else {
                algorithmList = AsymmetricEncrypter.getAlgorithmList();
            }

            for (int i = 0; i < algorithmList.length; i++) {
                cmbAlgorithmSymC.addItem(algorithmList[i]);
            }
        }
    }

    private boolean isPBEAlgorithm(String algorithmName) {
        return algorithmName != null && algorithmName.startsWith("PBE");
    }

    private class ActionCmbAlgorithmSymC implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String algorithmName = (String) cb.getSelectedItem();
            updateGUI(isPBEAlgorithm(algorithmName));
        }

        private void updateGUI(boolean isPBE) {
            jPasswordField1.setVisible(isPBE);
            password.setVisible(isPBE);
            txtKeyFile.setVisible(!isPBE);
            keyFile.setVisible(!isPBE);
            btnBrowseKeyFile.setVisible(!isPBE);

            boolean b2 = radSymmetricCipher.isSelected();
            if (b2) {
                keyFile.setText("Secret Key");
            } else {
                keyFile.setText("Private Key");
            }

            txtPublicKey.setVisible(!b2);
            btnBrowsePublicKey.setVisible(!b2);
            publicKey.setVisible(!b2);
        }
    }

    private class ActionButtonBrowse implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            try {
                JFileChooser fileChoooser = new JFileChooser();
                if (source == btnBrowseOutputFile) {
                    fileChoooser.showSaveDialog(MainPanel.this);
                } else {
                    fileChoooser.showOpenDialog(MainPanel.this);
                }

                File selectedPfile = fileChoooser.getSelectedFile();
                if (selectedPfile == null) {
                    return;
                }

                String filePath = selectedPfile.getPath();
                if (filePath != null) {
                    if (source == btnBrowseOutputFile) {
                        txtOutputFile.setText(filePath);
                    } else if (source == btnBrowseKeyFile) {
                        txtKeyFile.setText(filePath);
                    } else if (source == btnBrowseInputFile) {
                        txtInputFile.setText(filePath);
                    } else if (source == btnBrowsePublicKey) {
                        txtPublicKey.setText(filePath);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class ActionButtonEncryptAndDecrypt implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();

            String algorithm = (String) cmbAlgorithmSymC.getSelectedItem();
            int keySize = -1;
            try {
                keySize = Integer.parseInt(txtKeySize.getText().trim());
            } catch (Exception e2) {
            }

            Encrypter encrypter = null;
            try {
                if (radAsymmetricCipher.isSelected()) {
                    encrypter = AsymmetricEncrypterFactory.getInstance().buildEncrypter(algorithm, keySize);
                } else if (isPBEAlgorithm(algorithm)) {

                    // Password-based encryption
                    String password = new String(jPasswordField1.getPassword());
                    encrypter = BPEEncrypterFactory.getInstance().buildEncrypter(algorithm, keySize, password);
                } else {
                    encrypter = SymmetricEncrypterFactory.getInstance().buildEncrypter(algorithm, keySize);
                }

                String message = encrypter.getKeySizeSuggestion();
                if (message != null) {
                    showMessage(message);
                    return;
                }

                if (encrypter instanceof AsymmetricEncrypter) {
                    ((AsymmetricEncrypter) encrypter).setKeyFile2(txtPublicKey.getText());
                }

                String inputFile = txtInputFile.getText();
                String outputFile = txtOutputFile.getText();
                File keyFile = new File(txtKeyFile.getText());

                if (textEncryption.isSelected()) {
                    if (source == encryptText) {
                        encryprtString(plainTextArea, cipherTextArea, keyFile, encrypter);
                    } else {
                        decryprtString(cipherTextArea, plainTextArea, keyFile, encrypter);
                    }
                } else {
                    if (source == encryptFile) {
                        encrypter.encrypt(inputFile, outputFile, keyFile);
                    } else {
                        encrypter.decrypt(inputFile, outputFile, keyFile);
                    }
                }

                if (source == encryptFile || source == encryptText) {
                    message = "Encrypt successfully";
                } else {
                    message = "Decrypt successfully";
                }

                showMessage(message);

            } catch (Exception e2) {
                e2.printStackTrace();
                showMessage(e2.getMessage());
            }
        }

        private void encryprtString(JTextArea textArea1, JTextArea textArea2, File keyFile, Encrypter encrypter) throws Exception {
            byte[] resultText = encrypter.encrypt(textArea1.getText().getBytes(), keyFile);
            BASE64Encoder encoder = new BASE64Encoder();
            textArea2.setText(new String(encoder.encode(resultText)));
        }

        private void decryprtString(JTextArea textArea1, JTextArea textArea2, File keyFile, Encrypter encrypter) throws Exception {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] clearText = decoder.decodeBuffer(textArea1.getText());
            byte[] resultText = encrypter.decrypt(clearText, keyFile);
            textArea2.setText(new String(resultText, Encrypter.ENCODING));

        }
    }

    private class ActionRadioEncryptionType implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            updateGUI();
        }
    }

    public void showMessage(String message) {
        this.showMessage(message, "Message Dialog");
    }

    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        String title = "Encryption and Decryption Tool";
        if (DEBUG) {
            title = "[Debug mode] " + title;
        }
        final JFrame frame = new JFrame(title);
        MainPanel cal = new MainPanel();
        frame.getContentPane().add(cal);
        frame.setSize(750, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        WindowAdapter windowListener = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int close = JOptionPane.showConfirmDialog(frame, "Are you sure to close the window?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (close == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        };

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(windowListener);

        // for testing purpose only
        if (DEBUG) {
            frame.removeWindowListener(windowListener);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
            String filePath = "C:/Documents and Settings/Administrator/My Documents/";
            String inputFile = filePath + "Test.txt";
            String keyFile = filePath + "Key.txt";
            String pubKeyFile = filePath + "PublicKey.txt";
            String outputFile = filePath + "Test_E.txt";
            String keySize = "56";
            cal.txtInputFile.setText(inputFile);
            cal.txtKeyFile.setText(keyFile);
            cal.txtPublicKey.setText(pubKeyFile);
            cal.txtOutputFile.setText(outputFile);
            cal.txtKeySize.setText(keySize);
            cal.jPasswordField1.setText("123456");
            cal.cmbAlgorithmSymC.setSelectedItem(AsymmetricEncrypter.RSA);
        }
    }
}
