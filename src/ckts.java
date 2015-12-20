import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
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
    Map<Integer, Integer> totalParts = new TreeMap<>();
    Map<Integer, ArrayList<JTextField>> fromTxt = new TreeMap<>();
    Map<Integer, JCheckBox> dckts = new TreeMap<>();
    Map<Integer, ArrayList> paramters = new TreeMap<>();
    Map<String, List<Double>> data = new TreeMap<>();
    Map<String, Integer> mva = new TreeMap<>();
    private int line = 1;

    public ckts() {

        data.put("ACSR-CONDOR 1x402", Arrays.asList(0.0005125115, 0.0027525253, 0.0006271000, 0.0011025023, 0.0055727732));
        data.put("ACSR-CONDOR 2x402", Arrays.asList(0.0002565427, 0.0017952250, 0.0008966069, 0.0009808310, 0.0051733242));
        data.put("ACSR-HAWK 1x240", Arrays.asList(0.0008545684, 0.0026968549, 0.0006019419, 0.0012654959, 0.0056250000));
        data.put("GTACSR-CONDOR 1x402", Arrays.asList(0.0005125115, 0.0027525253, 0.0006271000, 0.0014135675, 0.0068514692));
        data.put("OSTRICH 1x152", Arrays.asList(0.0013584711, 0.0027967172, 0.0005797332, 0.0015048209, 0.0056749311));
        data.put("XLPE 1x1000", Arrays.asList(0.0001342975, 0.0014834711, 0.0137870014, 0.0005624426, 0.0005809803));
        data.put("XLPE 1x1200", Arrays.asList(0.0001033058, 0.0014261938, 0.0151937280, 0.0004612029, 0.0004951217));
        data.put("GTACSR-HAWK 1x240", Arrays.asList(0.0008545684, 0.0026968549, 0.0006019419, 0.0012654959, 0.0056250000));
        data.put("H-resistance-SAPS 1x282", Arrays.asList(0.0007300275, 0.0026750459, 0.0006069407, 0.0012058081, 0.0056140955));
        data.put("XLPE 1x1600", Arrays.asList(0.0000972796, 0.0014063603, 0.0157648638, 0.0004208549, 0.0005195086));
        data.put("XLPE 1x2000", Arrays.asList(0.0000774793, 0.0013630877, 0.0170786024, 0.0003351942, 0.0005035237));
        data.put("Banked-HAWK 1x240", Arrays.asList(0.0004272842, 0.0013484275, 0.0012038838, 0.0006327479, 0.0028125000));
        data.put("Banked-Condor 1x402", Arrays.asList(0.0002562557, 0.0013762626, 0.0012542000, 0.0005512511, 0.0027863866));
        mva.put("ACSR-CONDOR 1x402", 151);
        mva.put("ACSR-CONDOR 2x402", 274);
        mva.put("ACSR-HAWK 1x240", 102);
        mva.put("GTACSR-CONDOR 1x402", 274);
        mva.put("OSTRICH 1x152", 77);
        mva.put("XLPE 1x1000", 187);
        mva.put("XLPE 1x1200", 209);
        mva.put("GTACSR-HAWK 1x240", 203);
        mva.put("H-resistance-SAPS 1x282", 226);
        mva.put("XLPE 1x1600", 242);
        mva.put("XLPE 1x2000", 267);
        mva.put("Banked-HAWK 1x240", 204);
        mva.put("Banked-Condor 1x402", 302);

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
                // Store the data in 2D String array as PSSE format
                String[][] finaldata = new String[fromTxt.size()][6];
                // Get the From and To Text
                for (Integer key : fromTxt.keySet()) {
                    finaldata[key - 1][0] = "psspy.branch_data(1" + fromTxt.get(key).get(0).getText() + ",1" + fromTxt.get(key).get(1).getText() + ",r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i],";
                    finaldata[key - 1][4] = "psspy.seq_branch_data(1" + fromTxt.get(key).get(0).getText() + ",1" + fromTxt.get(key).get(1).getText() + ",r\"\"\"1\"\"\",";
                }
                // Get the Double Circuit check
                for (Integer key : dckts.keySet()) {
                    System.out.println(dckts.get(key).isSelected());
                    // use replace with
                }
                // Get the parameters and calculate the Length * Type
                for (Integer key : paramters.keySet()) {
                    // Temporary Arrays to store the data
                    ArrayList<List> para = new ArrayList<>();
                    ArrayList<Double> km = new ArrayList<>();
                    ArrayList<Integer> mvaVal = new ArrayList<>();
                    // The first column of the array is the Length and the second is the Type
                    // This is a for loop to extract each one
                    for (int i = 0; i < paramters.get(key).size(); i++) {
                        if (i % 2 == 0 | i == 0) {
                            System.out.println(((JTextField) paramters.get(key).get(i)).getText());
                            km.add(Double.valueOf(((JTextField) paramters.get(key).get(i)).getText()));
                        } else {
                            System.out.println(data.get(((JComboBox) paramters.get(key).get(i)).getSelectedItem()));
                            System.out.println(mva.get(((JComboBox) paramters.get(key).get(i)).getSelectedItem()));
                            para.add(data.get(((JComboBox) paramters.get(key).get(i)).getSelectedItem()));
                            mvaVal.add(mva.get(((JComboBox) paramters.get(key).get(i)).getSelectedItem()));
                        }
                    }
                    finaldata[key - 1][2] = String.valueOf(Collections.min(mvaVal));

                    // Calculate the total length
                    double totallength = 0;
                    for (Double aDouble : km) {
                        totallength += aDouble;
                    }
                    finaldata[key - 1][3] = ",_f,_f,_f,_f,_f,_f," + String.valueOf(totallength) + ",_f,_f,_f,_f])";

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
                    // Write to file
                    finaldata[key - 1][1] = "[" + String.format("%.6f", parameters.get(0)) + "," + String.format("%.6f", parameters.get(1)) + "," + String.format("%.6f", parameters.get(2)) + ",";
                    finaldata[key - 1][5] = "[" + String.format("%.6f", parameters.get(3)) + "," + String.format("%.6f", parameters.get(4)) + ",_f,_f,_f,_f,_f])";

                }
                // Get the Double Circuit check and write to file
                try {
                    FileWriter writer = new FileWriter("test2.py", false);
                    for (Integer key : dckts.keySet()) {
                        if (dckts.get(key).isSelected()) {
                            writer.write(finaldata[key - 1][0] + finaldata[key - 1][1] + finaldata[key - 1][2] + finaldata[key - 1][3]);
                            writer.write("\r\n");
                            writer.write(finaldata[key - 1][4] + finaldata[key - 1][5]);
                            writer.write("\r\n");
                            writer.write(finaldata[key - 1][0].replace(",r\"\"\"1\"\"\",", ",r\"\"\"2\"\"\",") + finaldata[key - 1][1] + finaldata[key - 1][2] + finaldata[key - 1][3]);
                            writer.write("\r\n");
                            writer.write(finaldata[key - 1][4].replace(",r\"\"\"1\"\"\",", ",r\"\"\"2\"\"\",") + finaldata[key - 1][5]);
                            writer.write("\r\n");
                        } else {
                            writer.write(finaldata[key - 1][0] + finaldata[key - 1][1] + finaldata[key - 1][2] + finaldata[key - 1][3]);
                            writer.write("\r\n");
                            writer.write(finaldata[key - 1][4] + finaldata[key - 1][5]);
                            writer.write("\r\n");
                        }
                    }
                    writer.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
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

