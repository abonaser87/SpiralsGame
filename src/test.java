import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by 84170 on 09/12/2015.
 */
public class test {
    private JButton addPartButton;
    private JButton addCircuitButton;
    private JButton createButton;
    private JPanel rootPanel;
    private JTextField fromSS;
    private JTextField toSS;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JCheckBox doubleCkts;
    private Map<String, JLabel> parts;
    private int circuit = 1;
    private int i = 1;
    private String part;

    public test() {
        addPartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addPart(circuit, i);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void addPart(int circuit, int i) {
        part = "Part " + String.valueOf(i);
        JLabel b = new JLabel(part);

        rootPanel.add(b);
        parts.put(part, b);
        rootPanel.invalidate();
    }
}
