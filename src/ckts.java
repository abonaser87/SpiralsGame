import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by 84170 on 14/12/2015.
 */
public class ckts {
    //TODO: Checkbox data to populate from map?
    //TODO: Parts map implementation
    final JFrame frame = new JFrame("lines");
    int part = 1;
    JPanel rootPanel = new JPanel(new BorderLayout(3, 3));
    JPanel substationPanel = new JPanel(new GridLayout(6, 0));
    JPanel transmission = new JPanel(new GridBagLayout());
    JPanel btns = new JPanel(new GridLayout(0, 2));
    JButton addline = new JButton("Add Line");
    JButton createPython = new JButton("Create Python");
    GridBagConstraints c = new GridBagConstraints();
    Map<Integer, Integer> totalParts = new TreeMap<Integer, Integer>();
    Map<Integer, ArrayList<JTextField>> fromTxt = new TreeMap<>();
    Map<Integer, JCheckBox> dckts = new TreeMap<>();
    float[] texxt = {1,2,3};
    Map<String, List<Double>> data = new TreeMap<String, List<Double>>();
    private int line = 1;

    public ckts() {

        data.put("ACSR-CONDOR 1x402", Arrays.asList(0.000513,0.002753,0.000627,0.001103,0.005573));
        data.put("ACSR-CONDOR 2x402",Arrays.asList(0.000257,0.001795,0.000897,0.000981,0.005173));
        data.put("ACSR-HAWK 1x240",Arrays.asList(0.000855,0.002697,0.000602,0.001265,0.005625));
        data.put("GTACSR-CONDOR 1x402",Arrays.asList(0.000513,0.002753,0.000627,0.001414,0.006851));
        data.put("OSTRICH 1x152",Arrays.asList(0.001358,0.002797,0.000580,0.001505,0.005675));
        data.put("XLPE 1x1000",Arrays.asList(0.000134,0.001483,0.013787,0.000562,0.000581));
        data.put("XLPE 1x1200",Arrays.asList(0.000103,0.001426,0.015194,0.000461,0.000495));
        data.put("GTACSR-HAWK 1x240",Arrays.asList(0.000855,0.002697,0.000602,0.001265,0.005625));
        data.put("H-resistance-SAPS 1x282",Arrays.asList(0.000730,0.002675,0.000607,0.001206,0.005614));
        data.put("XLPE 1x1600",Arrays.asList(0.000097,0.001406,0.015765,0.000421,0.000520));
        data.put("XLPE 1x2000",Arrays.asList(0.000077,0.001363,0.017079,0.000335,0.000504));
        data.put("ACSR-HAWK 1x240",Arrays.asList(0.000427,0.001348,0.001204,0.000633,0.002813));
        data.put("Condor-Banked 1x402",Arrays.asList(0.000256,0.001376,0.001254,0.000551,0.002786));


        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        substationPanel.setBorder(new TitledBorder("Substation"));
        transmission.setBorder(new TitledBorder("Transmission Lines"));

        btns.add(addline);
        btns.add(createPython);
        addline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                line++;
                addlines(line);
                frame.pack();
            }
        });

        createPython.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.print(totalParts.values());
//                System.out.print(totalParts.keySet());
//                System.out.println(fromTxt.values());
//                System.out.println(fromTxt.keySet());
                System.out.println(fromTxt.keySet());

                for (Integer key : fromTxt.keySet()) {
                    for (JTextField jTextField : fromTxt.get(key)) {
                        System.out.println(jTextField.getText());
                    }
                }
                for (Integer key : dckts.keySet()) {
                    System.out.println(dckts.get(key).isSelected());
                }
            }
        });
        rootPanel.add(substationPanel, BorderLayout.NORTH);
        rootPanel.add(transmission, BorderLayout.CENTER);
        rootPanel.add(btns, BorderLayout.SOUTH);
        addlines(line);

        rootPanel.invalidate();
        rootPanel.repaint();
        frame.setMinimumSize(frame.getSize());
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        ckts test = new ckts();
    }

    private void addlines(final int line) {
        //part=1;
        ArrayList<JTextField> temp = new ArrayList<>();
        setPart(1);
        totalParts.put(line, part);

        JPanel from = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        final JPanel parts = new JPanel(new GridLayout(0, 3));
        final JPanel btn = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        // From panel component
        JLabel ckt = new JLabel("Line " + String.valueOf(line));
        JLabel fromL = new JLabel("From");
        JTextField txtFrom = new JTextField();
        JLabel to = new JLabel("To");
        JTextField txtTo = new JTextField();
        JCheckBox doubleCkts = new JCheckBox("Check if double circuits");
        txtFrom.setPreferredSize(new Dimension(40, 30));
        txtTo.setPreferredSize(new Dimension(40, 30));
        c.gridy = 0;
        c.gridx = line - 1;
        transmission.add(ckt);
        from.add(fromL);
        from.add(txtFrom);
        from.add(to);
        from.add(txtTo);
        from.add(doubleCkts);
        from.setBorder(new TitledBorder(""));
        c.gridy = 1;
        c.gridx = line - 1;
        //c.ipadx=5;

        transmission.add(from, c);
        temp.add(txtFrom);
        temp.add(txtTo);
        fromTxt.put(line, temp);
        //temp.clear();
        dckts.put(line, doubleCkts);

        // Parts

        // Add Part
        final JButton addBtn = new JButton("Add Part");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setPart(totalParts.get(line));
                part++;
                addParts(part, line, parts);
                frame.pack();

            }
        });
        addParts(part, line, parts);
        btn.add(addBtn);

        c.gridy = 3;
        c.gridx = line - 1;

        transmission.add(btn, c);
        transmission.revalidate();

    }

    private void addParts(int part, final int line, JPanel parts) {
        //part++;
        totalParts.put(line, part);
        String prt = "Part " + String.valueOf(part);
        JLabel partLabel = new JLabel(prt);
        JTextField lnth = new JTextField();
        JComboBox type = new JComboBox(data.keySet().toArray());

        lnth.setPreferredSize(new Dimension(40, 33));

        parts.add(partLabel);
        parts.add(lnth);
        parts.add(type);
        c.gridy = 2;
        c.gridx = line - 1;
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

