/**
 * Created by Abdullah on 12/30/2015.
 */
public class AreaFactory {

    public Data checkarea(int index) {
        switch (index) {
            case 0:
                return new COA();

            case 1:
                return new WOA();

            case 2:
                return new EOA();

            case 3:
                return new SOA();

        }
        return null;
    }
}
