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
    // TODO: Add MVA to the line
    //TODO: Make a Python format var and store it in a file
    final JFrame frame = new JFrame("lines");
    int part = 1;
    JPanel rootPanel = new JPanel(new BorderLayout(3, 3));
    JPanel substationPanel = new JPanel(new GridLayout(6, 0));
    JPanel transmission = new JPanel(new GridBagLayout());
    JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton addline = new JButton("Add Line");
    JButton createPython = new JButton("Create Python");
    GridBagConstraints c = new GridBagConstraints();
    Map<Integer, Integer> totalParts = new TreeMap<Integer, Integer>();
    Map<Integer, ArrayList<JTextField>> fromTxt = new TreeMap<>();
    Map<Integer, JCheckBox> dckts = new TreeMap<>();
    Map<Integer, ArrayList> paramters = new TreeMap<>();
    Map<String, List<Double>> data = new TreeMap<String, List<Double>>();
    Map<String,Integer> mva = new TreeMap<>();
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
        mva.put("ACSR-CONDOR 1x402 ",151);
        mva.put("ACSR-CONDOR 2x402 ",274);
        mva.put("ACSR-HAWK 1x240 ",102);
        mva.put("GTACSR-CONDOR 1x402 ",274);
        mva.put("OSTRICH 1x152 ",77);
        mva.put("XLPE 1x1000 ",187);
        mva.put("XLPE 1x1200 ",209);
        mva.put("GTACSR-HAWK 1x240 ",203);
        mva.put("H-resistance-SAPS 1x282 ",226);
        mva.put("XLPE 1x1600 ",242);
        mva.put("XLPE 1x2000 ",267);
        mva.put("ACSR-HAWK 1x240 ",204);
        mva.put("Condor-Banked 1x402 ",302);

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
                // Get the From and To Text
                for (Integer key : fromTxt.keySet()) {
                    for (JTextField jTextField : fromTxt.get(key)) {
                        System.out.println(jTextField.getText());
                    }
                }
                // Get the Double Circuit check
                for (Integer key : dckts.keySet()) {
                    System.out.println(dckts.get(key).isSelected());
                }
                // Get the parameters and calculate the Length * Type
                for (Integer key : paramters.keySet()) {
                    // Temporary Arrays to store the data
                    ArrayList<List> para = new ArrayList<>();
                    ArrayList<Double> km = new ArrayList<Double>();
                    // The first coulmn of the array is the Length and the second is the Type
                    // This is a for loop to extract each one
                    for (int i = 0; i < paramters.get(key).size(); i++) {
                        if (i % 2 == 0 | i == 0) {
                            System.out.println(((JTextField) paramters.get(key).get(i)).getText());
                            km.add(Double.valueOf(((JTextField) paramters.get(key).get(i)).getText()));
                        } else {
                            System.out.println(data.get(((JComboBox) paramters.get(key).get(i)).getSelectedItem()));
                            para.add(data.get(((JComboBox) paramters.get(key).get(i)).getSelectedItem()));
                        }
                    }
                    System.out.println(km);
                    System.out.println(para);
                    // Calculate the Length * Type for each part
                    List temp = new ArrayList();
                    int j = 0;
                    for (Double aDouble : km) {
                        // List to store the sum
                        List test = new ArrayList();
                        // List to get the paramters
                        List list = para.get(j);
                        for (int i = 0; i < list.size(); i++) {
                            test.add((Double) list.get(i) * aDouble);
                            //System.out.println(i);
                        }
                        j++;
                        temp.add(test);
                    }

                    // Store the data in 2D Array to calculate the sum of the parts
                    double[][] num = new double[temp.size()][5];
                    for (int i = 0; i < temp.size(); i++) {
                        List test = (List) temp.get(i);
                        for (int x = 0; x < test.size(); x++) {
                            num[i][x] = Double.valueOf(test.get(x).toString());
                        }
                    }
                    // The sum
                    List parameters = new ArrayList();
                    for (int i = 0; i < num[0].length; i++) {
                        double sum = 0;
                        for (int x = 0; x < num.length; x++) {
                            sum += num[x][i];
                        }
                        parameters.add(sum);
                    }
                    System.out.println(parameters);
                }
            }
        });
        rootPanel.add(substationPanel, BorderLayout.NORTH);
        rootPanel.add(new JScrollPane(transmission), BorderLayout.CENTER);
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
        final ArrayList tempParts = new ArrayList<>();
        setPart(1);
        totalParts.put(line, part);

        JPanel from = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        final JPanel parts = new JPanel(new GridBagLayout());
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
                addParts(part, line, parts, tempParts);
                frame.pack();

            }
        });
        addParts(part, line, parts, tempParts);
        btn.add(addBtn);

        c.gridy = 3;
        c.gridx = line - 1;

        transmission.add(btn, c);
        rootPanel.revalidate();

    }

    private void addParts(int part, final int line, JPanel parts, ArrayList tempParts) {
        //part++;
        totalParts.put(line, part);

        String prt = "Part " + String.valueOf(part);
        JLabel partLabel = new JLabel(prt);
        JTextField lnth = new JTextField();
        lnth.setMaximumSize(new Dimension(40, 33));
        lnth.setPreferredSize(new Dimension(40, 33));
        JComboBox type = new JComboBox(data.keySet().toArray());

        type.setPreferredSize(new Dimension(150, 33));
        tempParts.add(lnth);
        tempParts.add(type);
        paramters.put(line, tempParts);
        c.gridy = part;
        c.gridx = 0;
        parts.add(partLabel, c);
        c.gridx = 1;

        parts.add(lnth, c);
        c.gridx = 3;
        parts.add(type, c);
        c.gridy = 2;
        c.gridx = line - 1;
        c.weighty = 2;
        transmission.add(parts, c);
        rootPanel.revalidate();


    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }
}

