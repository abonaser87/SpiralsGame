/**
 * Created by Abdullah on 12/30/2015.
 */
public class PythonFactory {
    public Python getPython(int region, String area, String zone, String[] subLoad, String subNum) {
        switch (region) {
            case 0:
                return new PyCOA(zone, subLoad, subNum);

            case 1:
                return new PyWOA(area, zone, subLoad, subNum);

            case 2:
                return new PyEOA(area, zone, subLoad, subNum);

            case 3:
                return new PySOA(area, zone, subLoad, subNum);
        }
        return null;
    }
}
