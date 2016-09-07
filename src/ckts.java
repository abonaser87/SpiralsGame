import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

import static java.awt.GridBagConstraints.LINE_START;

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
    Map<Integer, Integer> totalParts = new TreeMap<Integer, Integer>();
    Map<Integer, ArrayList<JTextField>> fromTxt = new TreeMap<Integer, ArrayList<JTextField>>();
    Map<Integer, JCheckBox> dckts = new TreeMap<Integer, JCheckBox>();
    Map<Integer, ArrayList> paramters = new TreeMap<Integer, ArrayList>();
    Map<Integer, ArrayList> delParts = new TreeMap<Integer, ArrayList>();
    Map<String, List<Double>> data = new TreeMap<String, List<Double>>();
    Map<String, Integer> mva = new TreeMap<String, Integer>();
    Map<Integer, List> totalLines = new TreeMap<Integer, List>();
    String[] area;
    String[] zones = {"110 Qassim", "120 Hail", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"};
    String[] voltage;
    private int line = 1;

    public ckts() {


        DefaultComboBoxModel zoneModel = new DefaultComboBoxModel(zones);
        data = new AreaFactory().checkarea(0).data();
        mva = new AreaFactory().checkarea(0).mva();

        voltage = new AreaFactory().checkarea(0).voltage(0);
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        substationPanel.setBorder(new TitledBorder("Substation"));
        transmission.setBorder(new TitledBorder("Transmission Lines"));
        //Substation
        JLabel region = new JLabel("Region:");
        String[] regionArray = {"COA", "WOA", "EOA", "SOA"};
        final JComboBox regionNum = new JComboBox(regionArray);
        JLabel areas = new JLabel("Area:");
        String[] areaArray = {"100 COA"};
        final JComboBox areaNum = new JComboBox(areaArray);
        JLabel ssName = new JLabel("S/S Name:");
        JLabel rights = new JLabel("By:A.Al-Othman");
        rights.setFont(new javax.swing.plaf.FontUIResource(Font.SANS_SERIF, Font.PLAIN, 10));
        JLabel ssNum = new JLabel("S/S Number:");
        final JTextField txtName = new JTextField();
        final JTextField txtNum = new JTextField();
        JLabel lvNum = new JLabel("Low Voltage Bus Number:");
        final JTextField lvTxtNum = new JTextField();
        JLabel zone = new JLabel("Zone Number:");
        final JComboBox zoneNum = new JComboBox(zoneModel);
        JPanel voltPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel volt = new JLabel("S/S Voltage:");
        final JRadioButton v138 = new JRadioButton(voltage[0]);
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
        JLabel ssLoad = new JLabel("S/S Load(MVA):");
        final JTextField txtLoad = new JTextField();
        JPanel loadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel df = new JLabel("Diversity Factor:");
        final JTextField txtDf = new JTextField();
        txtDf.setColumns(4);
        JLabel pf = new JLabel("Power Factor:");
        final JTextField txtPf = new JTextField();
        txtPf.setColumns(4);
        loadPanel.add(df);
        loadPanel.add(txtDf);
        loadPanel.add(pf);
        loadPanel.add(txtPf);
        JPanel tempPanel = new JPanel(new GridLayout(7, 2, 3, 3));
        tempPanel.add(region);
        tempPanel.add(regionNum);
        tempPanel.add(areas);
        tempPanel.add(areaNum);
        tempPanel.add(ssNum);
        tempPanel.add(txtNum);
        tempPanel.add(lvNum);
        tempPanel.add(lvTxtNum);
        // TODO : Grey low voltge for COA , and add DF and PF
        lvTxtNum.setEnabled(false);
        tempPanel.add(ssName);
        tempPanel.add(txtName);
        tempPanel.add(zone);
        tempPanel.add(zoneNum);
        tempPanel.add(ssLoad);
        tempPanel.add(txtLoad);

        c.anchor=LINE_START;
        c.gridy = 0;
        substationPanel.add(tempPanel, c);
        c.gridy = 1;

        substationPanel.add(loadPanel,c);
        c.gridy = 2;
        substationPanel.add(voltPanel, c);
        c.gridy = 3;
        substationPanel.add(trfPanels, c);


        delline.setEnabled(false);
        btns.add(addline);
        btns.add(delline);
        btns.add(createPython);
        btns.add(rights);
        // Areas
        regionNum.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        ArrayList zoneS;
                        Data factory = new AreaFactory().checkarea(regionNum.getSelectedIndex());
                        area = factory.area();
                        DefaultComboBoxModel areas = new DefaultComboBoxModel(area);
                        areaNum.setModel(areas);
                        zoneS = factory.zone(Integer.valueOf(areaNum.getSelectedItem().toString().split(" ")[0]));
                        DefaultComboBoxModel coa = new DefaultComboBoxModel(zoneS.toArray());
                        zoneNum.setModel(coa);
                        data = factory.data();
                        mva = factory.mva();
                        voltage = factory.voltage(Integer.valueOf(areaNum.getSelectedItem().toString().split(" ")[0]));
                        v138.setText(voltage[0]);
                        v33.setText(voltage[1]);
                        removeall();
                        if(regionNum.getSelectedIndex()!=0){lvTxtNum.setEnabled(true);}
                        else{lvTxtNum.setEnabled(false);}
                    }
                }
        );
        areaNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList zoneS;
                Data factory = new AreaFactory().checkarea(regionNum.getSelectedIndex());
                area = factory.area();
                zoneS = factory.zone(Integer.valueOf(areaNum.getSelectedItem().toString().split(" ")[0]));
                DefaultComboBoxModel coa = new DefaultComboBoxModel(zoneS.toArray());
                zoneNum.setModel(coa);
                data = factory.data();
                mva = factory.mva();
                voltage = factory.voltage(Integer.valueOf(areaNum.getSelectedItem().toString().split(" ")[0]));
                v138.setText(voltage[0]);
                v33.setText(voltage[1]);
                removeall();
            }
        });
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
                    String from = fromTxt.get(key).get(0).getText();
                    String to = fromTxt.get(key).get(1).getText();
                    if (from.startsWith("9")) {
                        from = "19" + from.substring(2);
                    }
                    if (to.startsWith("9")) {
                        to = "19" + to.substring(2);
                    }
                    finaldata[key - 1][0] = "psspy.branch_data(1" + from + ",1" + to + ",r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i],";
                    finaldata[key - 1][4] = "psspy.seq_branch_data(1" + from + ",1" + to + ",r\"\"\"1\"\"\",";
                }
                // Get the parameters and calculate the Length * Type
                for (Integer key : paramters.keySet()) {
                    // Temporary Arrays to store the data
                    ArrayList<List> para = new ArrayList<List>();
                    ArrayList<Double> km = new ArrayList<Double>();
                    ArrayList<Integer> mvaVal = new ArrayList<Integer>();
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
                    // Prepare the file and store it in the Desktop
                    String userHomeFolder = System.getProperty("user.home");
                    File textFile = new File(userHomeFolder + "/Desktop", txtNum.getText() + " Substation.py");
                    FileWriter writer = new FileWriter(textFile, false);
                    // Get the data
                    String[] areaNumber = areaNum.getSelectedItem().toString().split(" ");
                    String subName = txtName.getText();
                    String subNum = txtNum.getText();
                    String[] zNum = zoneNum.getSelectedItem().toString().split(" ");
                    String[] subLoad = txtLoad.getText().split("\\s+");
                    String temp = "";

                    if (three.isSelected()) {
                        if (v33.isSelected()) {
                            //33kV Three Trfs
                            if (subLoad.length > 4) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");

                            } else {
                                temp = new PythonFactory().getPython(areaNum.getSelectedIndex(), areaNumber[0], zNum[0], subLoad, subNum).v33T3();
                            temp = temp.replace("Name", subName);
                            writer.write(temp);
                                writer.write("\r\n");
                            }
                        } else {
                            //13.8kV Three Trfs
                            if (subLoad.length != 8) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");
                            } else {
                                temp = new PythonFactory().getPython(areaNum.getSelectedIndex(), areaNumber[0], zNum[0], subLoad, subNum).v13T3();
                                temp = temp.replace("Name", subName);
                                writer.write(temp);
                                writer.write("\r\n");
                            }
                        }
                        // Two Transformers
                    } else {
                        if (v33.isSelected()) {
                            //33kV Two Trfs
                            if (subLoad.length > 4) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");
                            } else {
                                temp = new PythonFactory().getPython(areaNum.getSelectedIndex(), areaNumber[0], zNum[0], subLoad, subNum).v33T2();
                                temp = temp.replace("Name", subName);
                                writer.write(temp);
                                writer.write("\r\n");
                            }
                        } else {
                            //13.8kV Two Trfs
                            if (subLoad.length > 4) {
                                JOptionPane.showMessageDialog(rootPanel, "Please check that you input the correct load");
                            } else {
                                temp = new PythonFactory().getPython(areaNum.getSelectedIndex(), areaNumber[0], zNum[0], subLoad, subNum).v13T2();
                                temp = temp.replace("Name", subName);
                                writer.write(temp);
                                writer.write("\r\n");
                            }
                        }
                    }
                    writer.write("# Connection");
                    writer.write("\r\n");
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
        String imagePath = "Ico.png";
        InputStream imgStream = ckts.class.getResourceAsStream(imagePath);
        try {
            BufferedImage myImg = ImageIO.read(imgStream);
            frame.setIconImage(myImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void removeall() {
        fromTxt.clear();
        paramters.clear();
        dckts.clear();
        transmission.removeAll();
        line = 1;
        addlines(line);
        rootPanel.revalidate();
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


        ArrayList<JTextField> temp = new ArrayList<JTextField>();
        final ArrayList tempo = new ArrayList();
        final ArrayList tempParts = new ArrayList();
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
                // can we use linked list here as it will be easier ?
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