// Object adapter implementing UnitConverter. It contains a
// LegacyImperialConverter and a MetricConverter and routes requests:
// - metric -> metric via MetricConverter
// - imperial -> imperial via LegacyImperialConverter
// - mixed conversions via the common base unit (value -> base -> target)
public class ObjectAdapter implements UnitConverter {
    private final MetricConverter metricConverter;
    private final LegacyImperialConverter imperialAdaptee;

    public ObjectAdapter(LegacyImperialConverter imperialAdaptee) {
        this.metricConverter = new MetricConverter();
        this.imperialAdaptee = imperialAdaptee;
    }

    @Override
    public double convert(double value, Unit from, Unit to) {
        if(from.getCategory()!=to.getCategory()){
            throw new IllegalArgumentException("Units must be same category : "
                    + from.getCategory() + "!= " + to.getCategory() );
        }

        boolean fromIsImperial = isImperialUnit(from);
        boolean toIsImperial = isImperialUnit(to);

        if(!fromIsImperial && !toIsImperial){
            return metricConverter.convertMetric(value,from,to);
        }
        else if(fromIsImperial && toIsImperial){
            return imperialAdaptee.convertImperial(value,from,to);
        }
        else {
            double base = from.toBase(value);
            return to.fromBase(base);
        }
    }

    private boolean isImperialUnit(Unit unit) {
        return switch (unit){
            case INCH, FOOT, MILE, POUND, US_GALLON, FAHRENHEIT -> true;
            default -> false;
        };
    }
}
