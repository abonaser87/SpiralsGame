/**
 * Created by Abdullah on 12/30/2015.
 */
public interface Python {
    String v33T2();

    String v33T3();

    String v13T2();

    String v13T3();
}

class PyCOA implements Python {
    private String zone;
    private String[] subLoad;
    private String cc;
    private String temp;
    private String subNum;

    public PyCOA(String zone, String[] subLoad, String subNum) {
        this.zone = zone;
        this.subLoad = subLoad;
        this.cc = "1";
        if (subNum.startsWith("1")) {
            subNum = subNum.substring(2);
        } else {
            subNum = subNum.substring(1);
        }
        this.subNum = subNum;
    }

    @Override
    public String v33T2() {
        if (zone.equals("110") | zone.equals("120") | zone.equals("150")) {
            cc = "2";
        }
        temp = "#Python File for s/s 8717\n" +
                "psspy.bus_data_2(18717,[_i,100,130,_i],[ 132.0, 0.9728,-31.72],r\"\"\"Name\"\"\")\n" +
                "psspy.bus_data_2(177171,5[_i,100,130,_i],[ 33.0, 0.9367,-38.34],r\"\"\"Name LV-1\"\"\")\n" +
                "psspy.load_data_3(177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[0] + "," + subLoad[1] + ",_f,_f,_f,_f])\n" +
                "psspy.load_data_3(177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[2] + "," + subLoad[3] + ",_f,_f,_f,_f])\n" +
                "psspy.switched_shunt_data_3(177171,[2,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 10.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 30.0,_f],\"\")\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF1\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF2\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])";
        temp = temp.replace("717", subNum);
        temp = temp.replace("130", zone);
        return temp;
    }

    @Override
    public String v33T3() {
        if (zone.equals("110") | zone.equals("120") | zone.equals("150")) {
            cc = "2";
        }
        temp = "#Python File for s/s 8717\n" +
                "psspy.bus_data_2(18717,[_i,100,130,_i],[ 132.0, 0.9728,-31.72],r\"\"\"Name\"\"\")\n" +
                "psspy.bus_data_2(177171,[_i,100,130,_i],[ 33.0, 0.9367,-38.34],r\"\"\"Name LV-1\"\"\")\n" +
                "psspy.load_data_3(177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[0] + "," + subLoad[1] + ",_f,_f,_f,_f])\n" +
                "psspy.load_data_3(177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[2] + "," + subLoad[3] + ",_f,_f,_f,_f])\n" +
                "psspy.switched_shunt_data_3(177171,[3,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 10.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 30.0,_f],\"\")\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF1\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF2\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"3\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,1,_i,_i,_i],[_f, 0.175,_f,_f,_f,_f,_f,_f, 100.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF3\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"3\"\"\"," + cc + ",[_f,_f,_f, 0.175,_f,_f])";
        temp = temp.replace("717", subNum);
        temp = temp.replace("130", zone);
        return temp;
    }

    @Override
    public String v13T2() {
        if (zone.equals("130")) {
            cc = "2";

        }
        temp = "#Python File for s/s 8717\n" +
                "psspy.bus_data_2(18717,[_i,100,130,_i],[ 132.0, 0.9957,-17.85],r\"\"\"Name\"\"\") \n" +
                "psspy.bus_data_2(177171,[_i,100,130,_i],[ 13.8, 0.9681,-24.87],r\"\"\"Name LV-1\"\"\")\n" +
                "psspy.load_data_3(177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[0] + "," + subLoad[1] + ",_f,_f,_f,_f])\n" +
                "psspy.load_data_3(177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i],[ " + subLoad[2] + "," + subLoad[3] + ",_f,_f,_f,_f])\n" +
                "psspy.switched_shunt_data_3(177171,[2,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i,_i],[ 7.0,_f,_f,_f,_f,_f,_f,_f, 1.05,_f, 14.0,_f],\"\")\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF1\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF2\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])";
        temp = temp.replace("717", subNum);
        temp = temp.replace("130", zone);
        return temp;
    }

    @Override
    public String v13T3() {
        if (zone.equals("130")) {
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
                "psspy.two_winding_data_3(18717,177171,r\"\"\"1\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF1\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"1\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])\n" +
                "psspy.two_winding_data_3(18717,177171,r\"\"\"2\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177171,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF2\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177171,r\"\"\"2\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])\n" +
                "psspy.two_winding_data_3(18717,177172,r\"\"\"3\"\"\",[_i,_i,_i,_i,_i,_i,_i,_i,18717,177172,_i,0,_i,_i,_i],[_f, 0.44,_f,_f,_f,_f,_f,_f, 67.0,_f,_f,_f,_f,_f,_f,_f,_f,_f,_f, 1.05, 0.95,_f,_f,_f],r\"\"\"TF3\"\"\")\n" +
                "psspy.seq_two_winding_data(18717,177172,r\"\"\"3\"\"\"," + cc + ",[_f,_f,_f, 0.44,_f,_f])";
        temp = temp.replace("717", subNum);
        temp = temp.replace("130", zone);
        return temp;
    }
}

class PyWOA implements Python {
    private String area;
    private String zone;
    private String[] subLoad;
    private String cc;
    private String temp;

    public PyWOA(String area, String zone, String[] subLoad, String subNum) {
        this.zone = zone;
        this.subLoad = subLoad;
        this.area = area;
    }

    @Override
    public String v33T2() {
        return null;
    }

    @Override
    public String v33T3() {
        return null;
    }

    @Override
    public String v13T2() {
        return null;
    }

    @Override
    public String v13T3() {
        return null;
    }
}

class PyEOA implements Python {
    private String area;
    private String zone;
    private String[] subLoad;
    private String cc;
    private String temp;

    public PyEOA(String area, String zone, String[] subLoad, String subNum) {
        this.zone = zone;
        this.subLoad = subLoad;
        this.area = area;
    }

    @Override
    public String v33T2() {
        return null;
    }

    @Override
    public String v33T3() {
        return null;
    }

    @Override
    public String v13T2() {
        return null;
    }

    @Override
    public String v13T3() {
        return null;
    }
}

class PySOA implements Python {
    private String area;
    private String zone;
    private String[] subLoad;
    private String cc;
    private String temp;

    public PySOA(String area, String zone, String[] subLoad, String subNum) {
        this.zone = zone;
        this.subLoad = subLoad;
        this.area = area;
    }

    @Override
    public String v33T2() {
        return null;
    }

    @Override
    public String v33T3() {
        return null;
    }

    @Override
    public String v13T2() {
        return null;
    }

    @Override
    public String v13T3() {
        return null;
    }
}