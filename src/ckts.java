import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by 84170 on 14/12/2015.
 */
public class ckts {

    final JFrame frame = new JFrame("New Substation Python Creator");
    int part = 1;
    JPanel rootPanel = new JPanel(new BorderLayout(3, 3));
    JPanel substationPanel = new JPanel(new GridBagLayout());
    JPanel transmission = new JPanel(new GridBagLayout());
    JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton addline = new JButton("Add Line");
    JButton delline = new JButton("Delete Line");
    JButton createPython = new JButton("Create Python");
    GridBagConstraints c = new GridBagConstraints();
    Map<Integer, Integer> totalParts = new TreeMap<>();
    Map<Integer, ArrayList<JTextField>> fromTxt = new TreeMap<>();
    Map<Integer, JCheckBox> dckts = new TreeMap<>();
    Map<Integer, ArrayList> paramters = new TreeMap<>();
    Map<Integer, ArrayList> delParts = new TreeMap<>();
    Map<String, List<Double>> data = new TreeMap<>();
    Map<String, Integer> mva = new TreeMap<>();
    Map<Integer, List> totalLines = new TreeMap<>();
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
        //Substation
        JLabel ssName = new JLabel("S/S Name:");
        JLabel rights = new JLabel("By:Abdullah Al-Othman");
        JLabel ssNum = new JLabel("S/S Number:");
        final JTextField txtName = new JTextField();
        final JTextField txtNum = new JTextField();
        JLabel zone = new JLabel("Zone Number:");
        String[] zones = {"110 Qassim", "120 Hail", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"};
        final JComboBox zoneNum = new JComboBox(zones);
        JPanel voltPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel volt = new JLabel("S/S Voltage:");
        JRadioButton v138 = new JRadioButton("132/13.8kV");
        v138.setSelected(true);
        final JRadioButton v33 = new JRadioButton("132/33kV");
        ButtonGroup voltages = new ButtonGroup();
        voltages.add(v138);
        voltages.add(v33);
        voltPanel.add(volt);
        voltPanel.add(v138);
        voltPanel.add(v33);
        JPanel trfPanels = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel trf = new JLabel("No. of Transformers:");
        final JRadioButton three = new JRadioButton("Three");
        JRadioButton two = new JRadioButton("Two");
        final ButtonGroup transformers = new ButtonGroup();
        three.setSelected(true);
        transformers.add(three);
        transformers.add(two);
        trfPanels.add(trf);
        trfPanels.add(three);
        trfPanels.add(two);
        JLabel ssLoad = new JLabel("S/S Load:");
        final JTextField txtLoad = new JTextField();
        JPanel tempPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        tempPanel.add(ssNum);
        tempPanel.add(txtNum);
        tempPanel.add(ssName);
        tempPanel.add(txtName);
        tempPanel.add(zone);
        tempPanel.add(zoneNum);
        tempPanel.add(ssLoad);
        tempPanel.add(txtLoad);

        c.gridy = 0;
        substationPanel.add(tempPanel, c);
        c.gridy = 1;
        substationPanel.add(voltPanel, c);
        c.gridy = 2;
        substationPanel.add(trfPanels, c);


        delline.setEnabled(false);
        btns.add(addline);
        btns.add(delline);
        btns.add(createPython);
        btns.add(rights);
        addline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                line++;
                addlines(line);
                frame.pack();
            }
        });
        delline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dellines(line);
                line--;
                if (line == 1) {
                    delline.setEnabled(false);
                }
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
                            try {
                                km.add(Double.valueOf(((JTextField) paramters.get(key).get(i)).getText()));
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(rootPanel, "Please enter the Length");
                                e1.printStackTrace();
                            }
                        } else {
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

                    String userHomeFolder = System.getProperty("user.home");
                    File textFile = new File(userHomeFolder + "/Desktop", txtNum.getText() + " Substation.py");
                    FileWriter writer = new FileWriter(textFile, false);
                    String subName = txtName.getText();
                    String subNum = txtNum.getText().substring(1);
                    String[] zNum = zoneNum.getSelectedItem().toString().split(" ");
                    String[] subLoad = txtLoad.getText().split("\\s+");
                    String temp = "";
                    String cc = "1";
                    if (three.isSelected()) {
                        if (v33.isSelected()) {
                            //33kV Three Trfs
                            if (subLoad.length > 4) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");
                            }
                            if (zNum[0] == "110" | zNum[0] == "120" | zNum[0] == "150") {
                                cc = "2";
                            }
                            temp = "#Python File for s/s 8717\n" +
                                    "psspy.bus_data_2(18717,[_i,100,130,_i],[ 132.0, 0.9728,-31.72],r\"\"\"Name\"\"\")\n" +
                                    "psspy.bus_data_2(177171,[_i,100,130,_i],[ 33.0, 0.9367,-38.34],r\"\"\"Name LV-1\"\"\")\n" +
                                    "psspy.load_data_3(177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[0] + "," + subLoad[1] + ",_f,_f,_f,_f])\n" +
                                    "psspy.load_data_3(177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[2] + "," + subLoad[3] + ",_f,_f,_f,_f])\n" +
                                    "psspy.switched_shunt_data_3(177171,[3,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 10.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 30.0,_f],\"\")\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f, 1.5, 0.51, 1.05, 0.95,_f,_f,_f],r\"\"\"TF1\"\"\")\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f, 1.5, 0.51, 1.05, 0.95,_f,_f,_f],r\"\"\"TF2\"\"\")\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"3\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f, 1.5, 0.51, 1.05, 0.95,_f,_f,_f],r\"\"\"TF3\"\"\")\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"3\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])";
                            temp = temp.replace("717", subNum);
                            temp = temp.replace("Name", subName);
                            temp = temp.replace("130", zNum[0]);
                            writer.write(temp);
                            writer.write("\r\n");
                        } else {
                            //13.8kV Three Trfs
                            if (subLoad.length != 8) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");
                            }
                            if (zNum[0] == "130") {
                                cc = "2";
                            }
                            temp = "#Python File for s/s 8717\n" +
                                    "psspy.bus_data_2(18717,[_i,100,130,_i],[ 132.0, 0.9957,-17.85],r\"\"\"Name\"\"\") \n" +
                                    "psspy.bus_data_2(177171,[_i,100,130,_i],[ 13.8, 0.9681,-24.87],r\"\"\"Name LV-1\"\"\")\n" +
                                    "psspy.bus_data_2(177172,[_i,100,130,_i],[ 13.8, 0.9957,-17.85],r\"\"\"Name LV-2\"\"\")\n" +
                                    "psspy.load_data_3(177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[0] + "," + subLoad[1] + ",_f,_f,_f,_f])\n" +
                                    "psspy.load_data_3(177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[2] + "," + subLoad[3] + ",_f,_f,_f,_f])\n" +
                                    "psspy.load_data_3(177172,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[4] + "," + subLoad[5] + ",_f,_f,_f,_f])\n" +
                                    "psspy.load_data_3(177172,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[6] + "," + subLoad[7] + ",_f,_f,_f,_f])\n" +
                                    "psspy.switched_shunt_data_3(177171,[2,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 7.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 14.0,_f],\"\")\n" +
                                    "psspy.switched_shunt_data_3(177172,[1,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 7.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 7.0,_f],\"\")\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],_s)\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],_s)\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])\n" +
                                    "psspy.two_winding_data_3(18717,177172,r\"\"\"3\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177172,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],_s)\n" +
                                    "psspy.seq_two_winding_data(18717,177172,r\"\"\"3\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])";
                            temp = temp.replace("717", subNum);
                            temp = temp.replace("Name", subName);
                            temp = temp.replace("130", zNum[0]);
                            writer.write(temp);
                            writer.write("\r\n");
                        }
                    } else {
                        if (v33.isSelected()) {
                            //33kV Two Trfs
                            if (subLoad.length > 4) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");
                            }
                            if (zNum[0] == "110" | zNum[0] == "120" | zNum[0] == "150") {
                                cc = "2";
                            }
                            temp = "#Python File for s/s 8717\n" +
                                    "psspy.bus_data_2(18717,[_i,100,130,_i],[ 132.0, 0.9728,-31.72],r\"\"\"Name\"\"\")\n" +
                                    "psspy.bus_data_2(177171,[_i,100,130,_i],[ 33.0, 0.9367,-38.34],r\"\"\"Name LV-1\"\"\")\n" +
                                    "psspy.load_data_3(177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[0] + "," + subLoad[1] + ",_f,_f,_f,_f])\n" +
                                    "psspy.load_data_3(177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[2] + "," + subLoad[3] + ",_f,_f,_f,_f])\n" +
                                    "psspy.switched_shunt_data_3(177171,[2,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 10.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 30.0,_f],\"\")\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f, 1.5, 0.51, 1.05, 0.95,_f,_f,_f],r\"\"\"TF1\"\"\")\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f, 1.5, 0.51, 1.05, 0.95,_f,_f,_f],r\"\"\"TF2\"\"\")\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])";
                            temp = temp.replace("717", subNum);
                            temp = temp.replace("Name", subName);
                            temp = temp.replace("130", zNum[0]);
                            writer.write(temp);
                            writer.write("\r\n");
                        } else {
                            //13.8kV Two Trfs
                            if (subLoad.length > 4) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");
                            }
                            if (zNum[0] == "130") {
                                cc = "2";
                            }
                            temp = "#Python File for s/s 8717\n" +
                                    "psspy.bus_data_2(18717,[_i,100,130,_i],[ 132.0, 0.9957,-17.85],r\"\"\"Name\"\"\") \n" +
                                    "psspy.bus_data_2(177171,[_i,100,130,_i],[ 13.8, 0.9681,-24.87],r\"\"\"Name LV-1\"\"\")\n" +
                                    "psspy.load_data_3(177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[0] + "," + subLoad[1] + ",_f,_f,_f,_f])\n" +
                                    "psspy.load_data_3(177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[2] + "," + subLoad[3] + ",_f,_f,_f,_f])\n" +
                                    "psspy.switched_shunt_data_3(177171,[2,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 7.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 14.0,_f],\"\")\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],_s)\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])\n" +
                                    "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],_s)\n" +
                                    "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])";
                            temp = temp.replace("717", subNum);
                            temp = temp.replace("Name", subName);
                            temp = temp.replace("130", zNum[0]);
                            writer.write(temp);
                            writer.write("\r\n");
                        }
                        writer.write("# Connection");
                        writer.write("\r\n");
                    }
                    for (Integer key : dckts.keySet()) {
                        if (dckts.get(key).isSelected()) {
                            writer.write("# Connection");
                            writer.write("\r\n");
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
                    JOptionPane.showMessageDialog(rootPanel, "Done , The file is saved on the Desktop");
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
        frame.setLocationRelativeTo(null);

    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            setUIFont(new javax.swing.plaf.FontUIResource(Font.SANS_SERIF, Font.PLAIN, 14));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        ckts test = new ckts();
    }

    private void dellines(int line) {
// Store the containers in a list then delete ?
        for (int i = 0; i < totalLines.get(line).size(); i++) {
            transmission.remove((Component) totalLines.get(line).get(i));
        }
        fromTxt.remove(line);
        paramters.remove(line);
        dckts.remove(line);
        transmission.revalidate();
        rootPanel.validate();
        frame.pack();

    }

    private void addlines(final int line) {


        ArrayList<JTextField> temp = new ArrayList<>();
        final ArrayList tempo = new ArrayList();
        final ArrayList tempParts = new ArrayList<>();
        setPart(1);
        // Correct Number of parts for each circuit
        totalParts.put(line, part);
        // Containers
        JPanel from = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        final JPanel parts = new JPanel(new GridBagLayout());
        final JPanel btn = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        // From panel component
        JLabel ckt = new JLabel("Line " + String.valueOf(line));
        JLabel fromL = new JLabel("From");
        final JTextField txtFrom = new JTextField();
        JLabel to = new JLabel("To");
        JTextField txtTo = new JTextField();
        JCheckBox doubleCkts = new JCheckBox("Check if double circuits");
        txtFrom.setPreferredSize(new Dimension(40, 30));
        txtTo.setPreferredSize(new Dimension(40, 30));
        c.gridy = 0;
        c.gridx = line - 1;
        transmission.add(ckt, c);
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
        final JButton delBtn = new JButton("Delete Part");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delBtn.setEnabled(true);
                setPart(totalParts.get(line));
                part++;
                addParts(part, line, parts, tempParts, tempo);
                frame.pack();

            }
        });


        delBtn.setEnabled(false);
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setPart(totalParts.get(line));
                System.out.println(part);
                int i = (3 * (part - 1));
                System.out.println(i);
                parts.remove((Component) delParts.get(line).get(i));
                parts.remove((Component) delParts.get(line).get(i + 1));
                parts.remove((Component) delParts.get(line).get(i + 2));
                int j = delParts.get(line).size();
                delParts.get(line).remove(j - 1);
                j = delParts.get(line).size();
                delParts.get(line).remove(j - 1);
                j = delParts.get(line).size();
                delParts.get(line).remove(j - 1);
                parts.revalidate();
                int k = paramters.get(line).size();
                paramters.get(line).remove(k - 1);
                k = paramters.get(line).size();
                paramters.get(line).remove(k - 1);
                transmission.invalidate();
                part--;
                totalParts.put(line, part);
                frame.pack();
                if (part == 1) {
                    delBtn.setEnabled(false);
                }

            }
        });
        addParts(part, line, parts, tempParts, tempo);
        btn.add(addBtn);
        btn.add(delBtn);
        c.gridy = 3;
        c.gridx = line - 1;

        transmission.add(btn, c);

        List tempLines = new ArrayList();
        if (line != 1) {
            delline.setEnabled(true);
            tempLines.add(ckt);
            tempLines.add(from);
            tempLines.add(parts);
            tempLines.add(btn);
            totalLines.put(line, tempLines);
        }
        transmission.revalidate();
        rootPanel.invalidate();
        rootPanel.repaint();
        frame.pack();

    }

    private void addParts(int part, final int line, JPanel parts, ArrayList tempParts, ArrayList tempo) {
        // Correct Number of parts for each circuit
        totalParts.put(line, part);

        String prt = "Part " + String.valueOf(part);
        JLabel partLabel = new JLabel(prt);
        JTextField lnth = new JTextField();
        lnth.setMaximumSize(new Dimension(40, 30));
        lnth.setPreferredSize(new Dimension(40, 30));
        JComboBox type = new JComboBox(data.keySet().toArray());

        type.setPreferredSize(new Dimension(180, 30));
        tempParts.add(lnth);
        tempParts.add(type);
        paramters.put(line, tempParts);
        // Parts to delete
        tempo.add(partLabel);
        tempo.add(lnth);
        tempo.add(type);
        delParts.put(line, tempo);

        c.gridy = part;
        c.insets = new Insets(5, 5, 5, 5);
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
        frame.pack();

    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }
}