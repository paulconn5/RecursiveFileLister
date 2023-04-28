import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class FileChooser extends JFrame {
    private JPanel cp;
    private JLabel lblTitle;
    private JButton btnStart;
    private JButton btnQuit;
    private JTextArea ta;
    private JScrollPane sp;
    private JFileChooser fc;

    public FileChooser() {
        setTitle("File Chooser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        cp = new JPanel();
        cp.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(cp);
        cp.setLayout(new BorderLayout(0, 0));

        lblTitle = new JLabel("Choose a directory:");
        lblTitle.setFont(new Font("Calibri", Font.PLAIN, 18));
        cp.add(lblTitle, BorderLayout.NORTH);

        ta = new JTextArea();
        sp = new JScrollPane(ta);
        cp.add(sp, BorderLayout.CENTER);

        btnStart = new JButton("Select File");
        btnStart.addActionListener(e -> {
            fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fc.showOpenDialog(FileChooser.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                listFiles(selectedFile);
            }
        });
        cp.add(btnStart, BorderLayout.WEST);

        btnQuit = new JButton("Exit");
        btnQuit.addActionListener(e -> System.exit(0));
        cp.add(btnQuit, BorderLayout.EAST);
    }

    private void listFiles(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        listFiles(f);
                    } else {
                        ta.append(f.getAbsolutePath() + "\n");
                    }
                }
            }
        } else {
            ta.append(file.getAbsolutePath() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FileChooser fc = new FileChooser();
                fc.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}