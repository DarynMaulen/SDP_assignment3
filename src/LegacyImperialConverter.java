// Example "legacy" converter representing an external or older API.
// Assumes it can convert between imperial units.
// Kept separate to demonstrate the Object Adapter pattern.
public class LegacyImperialConverter {
    public double convertImperial(double value,Unit from,Unit to) {
        if(from.getCategory()!=to.getCategory()){
            throw new IllegalArgumentException("Units must be same category : "
                    + from.getCategory() + "!= " + to.getCategory());
        }
        double base = from.toBase(value);
        return to.fromBase(base);
    }
}
