/**
 * Created by Abdullah on 12/30/2015.
 */
public class PythonFactory {
    public Python getPython(int area, String zone, String[] subLoad, String subNum) {
        switch (area) {
            case 0:
                return new PyCOA(zone, subLoad, subNum);

            case 1:
                return new PyWOA(zone, subLoad, subNum);

            case 2:
                return new PyEOA(zone, subLoad, subNum);

            case 3:
                return new PySOA(zone, subLoad, subNum);
        }
        return null;
    }
}
