// Concrete helper class that converts values within non-imperial units
// by converting to the category base unit and then to the target unit.
// Handles temperature too via Unit.toBase()/fromBase() helpers.
public class MetricConverter {
    public double convertMetric(double value,Unit from,Unit to) {
        if(from.getCategory()!=to.getCategory()){
            throw new IllegalArgumentException("Units must be same category : "
                    + from.getCategory() + "!= " + to.getCategory());
        }
        double base = from.toBase(value);
        return to.fromBase(base);
    }
}
