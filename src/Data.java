import java.util.*;

/**
 * Created by abdullah on 12/25/15.
 */
public interface Data {
    // Area , Zone , Voltage
    //  Python Class

    String[] area();

    ArrayList zone(int areaNum);

    String[] voltage(int areaNum);

    Map<String, List<Double>> data();

    Map<String, Integer> mva();
}

class COA implements Data {

    @Override
    public String[] area() {
        String[] areas = {"100 COA"};
        return areas;
    }

    @Override
    public ArrayList zone(int areaNum) {
        ArrayList<String> zone = new ArrayList<String>();
        zone.addAll(Arrays.asList("110 Qassim", "120 Hail", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"));
        return zone;
    }

    @Override
    public String[] voltage(int areaNum) {

        String[] voltages = {"132/13.8kV", "132/33kV"};
        return voltages;
    }

    @Override
    public Map<String, List<Double>> data() {
        Map<String, List<Double>> data = new TreeMap<String, List<Double>>();
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
        return data;
    }

    @Override
    public Map<String, Integer> mva() {
        Map<String, Integer> mva = new TreeMap<String, Integer>();
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
        return mva;
    }
}

class WOA implements Data {

    @Override
    public String[] area() {
        String[] areas = {"204 RABIGH","211 JED SOUTH","212 JED CENTRAL","213 JED NORTH","202 MAKKAH","207 SEC-SHB","209 SWCC-DTA","210 IWPP-DTA","203 MEDINAH","208 TAIF","215 YANBU","500 WNA"};
        return areas;
    }

    @Override
    public ArrayList zone(int areaNum) {
        ArrayList<String> zone = new ArrayList<String>();
        switch (areaNum) {
            case 204:
                zone.clear();
                zone.addAll(Arrays.asList("207 RABIGH","227 TWL","266 DHBAN BLK"));
                return zone;

            case 211:
                zone.clear();
                zone.addAll(Arrays.asList("200 PP3","221 HVP-2 IP3-3","231 BAHRA","242 JDS","243 MODON","248 JDS-PP","269 JED-JAH","275 JPFH"));
                return zone;
            case 212:
                zone.clear();
                zone.addAll(Arrays.asList("200 PP3","201 HVP IP3-1&2","202 IP3-2","203 WHA","214 KND","217 JAMIA","220 JNOR","230 KHZAM","247 AL-ADEL","254 JDC"));
                return zone;
            case 213:
                zone.clear();
                zone.addAll(Arrays.asList("204 HVE","205 DSP","212 FSL","215 KHULAIS","220 JNOR","228 JDNW","232 DAHBAN","244 JED N EAST","247 AL-ADEL","254 JDC","255 HHR2","257 SMR","262 KLD","263 JEDDAH CENTR","266 DHBAN BLK","269 JED-JAH","273 ABN","277 KAU-ASFAN"));
                return zone;
            case 203:
                zone.clear();
                zone.addAll(Arrays.asList("206 MADINAH","210 MUSAJEED","218 WADIFARA","222 MAD 33KV","225 MAD-EAST","235 HINAK","236 KHYBER","238 MED-BULK","239 MDW","260 MEDC","270 MAHD","272 MED-ICY","274 MDS PP"));
                return zone;
            case 202:
                zone.clear();
                zone.addAll(Arrays.asList("208 SAZ-MAK","213 WDJ","215 KHULAIS","224 HVM","226 MAK-WEST","229 MKN","240 LITH","241 SEC-SHB","246 MAKKAH CENTR","252 MASHAER","261 MAKKAH CENTR","264 MAKKAH NW","271 HNY","276 MKH","280 BAB-MAKKAH"));
                return zone;
            case 207:
                zone.clear();
                zone.addAll(Arrays.asList("241 SEC-SHB"));
                return zone;
            case 209:
                zone.clear();
                zone.addAll(Arrays.asList("223 SWCC-AUX","251 IPP-DTA"));
                return zone;
            case 210:
                zone.clear();
                zone.addAll(Arrays.asList("223 SWCC-AUX","251 IPP-DTA"));
                return zone;
            case 208:
                zone.clear();
                zone.addAll(Arrays.asList("211 TAIF","233 SISED","245 TAIF-EAST","256 SISED BULK"));
                return zone;
            case 215:
                zone.clear();
                zone.addAll(Arrays.asList("209 YANBU"));
                return zone;
            case 500:
                zone.clear();
                zone.addAll(Arrays.asList("501 TABUK","502 TAIMAH","503 DHUBA","504 WAJH","505 UALA","507 TABUK-2","510 TABUK BULK"));
                return zone;
        }


        return null;
    }

    @Override
    public String[] voltage(int areaNum) {
        switch(areaNum){
            case 500:
                String[] voltages500 = {"132/13.8kV", "132/33kV"};
                return voltages500;
            default:
                String[] voltages = {"110/13.8kV", "110/33kV"};
                return voltages;
        }

    }

    @Override
    public Map<String, List<Double>> data() {
        Map<String, List<Double>> data = new TreeMap<String, List<Double>>();
        data.put("WOA-CONDOR 1x402", Arrays.asList(0.0005125115, 0.0027525253, 0.0006271000, 0.0011025023, 0.0055727732));
        data.put("WOA-CONDOR 2x402", Arrays.asList(0.0002565427, 0.0017952250, 0.0008966069, 0.0009808310, 0.0051733242));
        data.put("WOA-HAWK 1x240", Arrays.asList(0.0008545684, 0.0026968549, 0.0006019419, 0.0012654959, 0.0056250000));
        data.put("GTWOA-CONDOR 1x402", Arrays.asList(0.0005125115, 0.0027525253, 0.0006271000, 0.0014135675, 0.0068514692));
        data.put("OSTRICH 1x152", Arrays.asList(0.0013584711, 0.0027967172, 0.0005797332, 0.0015048209, 0.0056749311));
        data.put("XLPE 1x1000", Arrays.asList(0.0001342975, 0.0014834711, 0.0137870014, 0.0005624426, 0.0005809803));
        data.put("XLPE 1x1200", Arrays.asList(0.0001033058, 0.0014261938, 0.0151937280, 0.0004612029, 0.0004951217));
        data.put("GTWOA-HAWK 1x240", Arrays.asList(0.0008545684, 0.0026968549, 0.0006019419, 0.0012654959, 0.0056250000));
        data.put("H-resistance-SAPS 1x282", Arrays.asList(0.0007300275, 0.0026750459, 0.0006069407, 0.0012058081, 0.0056140955));
        data.put("XLPE 1x1600", Arrays.asList(0.0000972796, 0.0014063603, 0.0157648638, 0.0004208549, 0.0005195086));
        data.put("XLPE 1x2000", Arrays.asList(0.0000774793, 0.0013630877, 0.0170786024, 0.0003351942, 0.0005035237));
        data.put("Banked-HAWK 1x240", Arrays.asList(0.0004272842, 0.0013484275, 0.0012038838, 0.0006327479, 0.0028125000));
        data.put("Banked-Condor 1x402", Arrays.asList(0.0002562557, 0.0013762626, 0.0012542000, 0.0005512511, 0.0027863866));
        return data;
    }

    @Override
    public Map<String, Integer> mva() {
        Map<String, Integer> mva = new TreeMap<String, Integer>();
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
        return mva;
    }
}

class EOA implements Data {

    @Override
    public String[] area() {
        return null;
    }

    @Override
    public ArrayList zone(int areaNum) {
//        String[] zone = {"110 HASSA", "120 mbaraz", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"};
        return null;
    }

    @Override
    public String[] voltage(int areaNum) {
        String[] voltages = {"110/13.8kV", "110/33kV"};
        return voltages;
    }

    @Override
    public Map<String, List<Double>> data() {
        return null;
    }

    @Override
    public Map<String, Integer> mva() {
        return null;
    }
}

class SOA implements Data {

    @Override
    public String[] area() {
        String[] areas = {"401 ASIR-JIZAN","402 BBT","403 NAJRAN","408 SHARURAH","404 SWCC-PLT"};
        return areas;
    }

    @Override
    public ArrayList zone(int areaNum) {
        ArrayList<String> zone = new ArrayList<String>();
        switch (areaNum) {
            case 401:
                zone.clear();
                zone.addAll(Arrays.asList("401 ASIR","402 JIZAN"));
                return zone;
            case 402:
                zone.clear();
                zone.addAll(Arrays.asList("404 BAHA","406 TIHAMA"));
                return zone;
            case 403:
                zone.clear();
                zone.addAll(Arrays.asList("407 NAJRAN"));
                return zone;
            case 404:
                zone.clear();
                zone.addAll(Arrays.asList("419 SHAR"));
                return zone;
            case 408:
                zone.clear();
                zone.addAll(Arrays.asList("403 SWCC"));
                return zone;
        }
        return null;
    }

    @Override
    public String[] voltage(int areaNum) {
        String[] voltages = {"132/13.8kV", "132/33kV"};
        return voltages;
    }


    @Override
    public Map<String, List<Double>> data() {
        Map<String, List<Double>> data = new TreeMap<String, List<Double>>();
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
        return data;
    }

    @Override
    public Map<String, Integer> mva() {
        Map<String, Integer> mva = new TreeMap<String, Integer>();
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
        return mva;
    }
}