import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by abdullah on 12/25/15.
 */
public interface Data {
    // Area , Zone , Voltage
    //  Python Class
    String area();

    String[] zone();

    String[] voltage();

    Map<String, List<Double>> data();

    Map<String, Integer> mva();
}

class COA implements Data {

    @Override
    public String area() {
        return "100";
    }

    @Override
    public String[] zone() {
        String[] zone = {"110 Qassim", "120 Hail", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"};
        return zone;
    }

    @Override
    public String[] voltage() {

        String[] voltages = {"132/13.8kV", "132/33kV"};
        return voltages;
    }

    @Override
    public Map<String, List<Double>> data() {
        Map<String, List<Double>> data = new TreeMap<>();
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
        Map<String, Integer> mva = new TreeMap<>();
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
    public String area() {
        return "200";
    }

    @Override
    public String[] zone() {
        String[] zone = {"110 Makkah", "120 Jeddah", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"};
        return zone;
    }

    @Override
    public String[] voltage() {
        String[] voltages = {"115/13.8kV", "115/33kV"};
        return voltages;
    }

    @Override
    public Map<String, List<Double>> data() {
        Map<String, List<Double>> data = new TreeMap<>();
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
        Map<String, Integer> mva = new TreeMap<>();
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
    public String area() {
        return null;
    }

    @Override
    public String[] zone() {
        String[] zone = {"110 HASSA", "120 mbaraz", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"};
        return zone;
    }

    @Override
    public String[] voltage() {
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
    public String area() {
        return null;
    }

    @Override
    public String[] zone() {
        String[] zone = {"110 Assir", "120 z8h", "130 Kharj", "140 R-Rural", "150 Dawadmi", "160 Riyadh City", "190 Juba"};
        return zone;
    }

    @Override
    public String[] voltage() {
        String[] voltages = {"132/13.8kV", "132/33kV"};
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