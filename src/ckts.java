import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 84170 on 14/12/2015.
 */
public class ckts {
    final JFrame frame = new JFrame("circuits");
    int part = 1;
    JPanel rootPanel = new JPanel(new BorderLayout(3, 3));
    JPanel substationPanel = new JPanel(new GridLayout(6, 0));
    JPanel transmission = new JPanel(new GridBagLayout());
    JPanel btns = new JPanel(new GridLayout(0, 2));
    JButton addCircuit = new JButton("Add Circuit");
    JButton createPython = new JButton("Create Python");
    GridBagConstraints c = new GridBagConstraints();
    Map<Integer, Integer> totalParts = new TreeMap<Integer, Integer>();
    int oldcircuit = 1;
    private int circuit = 1;
    public ckts() {

        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        substationPanel.setBorder(new TitledBorder("Substation"));
        transmission.setBorder(new TitledBorder("Transmission Lines"));

        btns.add(addCircuit);
        btns.add(createPython);
        addCircuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                circuit++;
                addCircuits(circuit);
                frame.pack();
            }
        });
        oldcircuit = circuit;
        createPython.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(totalParts.values());
                System.out.print(totalParts.keySet());
//                for(Integer i : totalParts.keySet()){
//                    System.out.println(totalParts.get(i).getText());
//                }

            }
        });
        rootPanel.add(substationPanel, BorderLayout.NORTH);
        rootPanel.add(transmission, BorderLayout.CENTER);
        rootPanel.add(btns, BorderLayout.SOUTH);
        addCircuits(circuit);

        rootPanel.invalidate();
        rootPanel.repaint();
        frame.setMinimumSize(frame.getSize());
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        ckts test = new ckts();
    }

    private void addCircuits(final int circuit) {
        //part=1;
        setPart(1);
        totalParts.put(circuit, part);

        JPanel from = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        final JPanel parts = new JPanel(new GridLayout(0, 3));
        final JPanel btn = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        // From panel component
        JLabel ckt = new JLabel("Circuit" + String.valueOf(circuit));
        JLabel fromL = new JLabel("From");
        JTextField txtFrom = new JTextField();
        JLabel to = new JLabel("To");
        JTextField txtTo = new JTextField();
        JCheckBox doubleCkts = new JCheckBox("Check if double circuits");
        txtFrom.setPreferredSize(new Dimension(40, 30));
        txtTo.setPreferredSize(new Dimension(40, 30));
        c.gridy = 0;
        c.gridx = circuit - 1;
        transmission.add(ckt);
        from.add(fromL);
        from.add(txtFrom);
        from.add(to);
        from.add(txtTo);
        from.add(doubleCkts);
        from.setBorder(new TitledBorder(""));
        c.gridy = 1;
        c.gridx=circuit-1;
        //c.ipadx=5;

        transmission.add(from, c);
        // Parts

        // Add Part
        final JButton addBtn = new JButton("Add Part");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setPart(totalParts.get(circuit));
                part++;
                addParts(part, circuit, parts);
                frame.pack();

            }
        });
        addParts(part, circuit, parts);
        btn.add(addBtn);

        c.gridy = 3;
        c.gridx=circuit-1;

        transmission.add(btn, c);
        transmission.revalidate();

    }

    private void addParts(int part, final int circuit, JPanel parts) {
        //part++;
        totalParts.put(circuit, part);
        String prt = "Part " + String.valueOf(part);
        JLabel partLabel = new JLabel(prt);
        JTextField lnth = new JTextField();
        JComboBox type = new JComboBox();
        lnth.setPreferredSize(new Dimension(50,33));

        parts.add(partLabel);
        parts.add(lnth);
        parts.add(type);
        c.gridy = 2;
        c.gridx=circuit-1;
        transmission.add(parts, c);
        parts.revalidate();

    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }
}

