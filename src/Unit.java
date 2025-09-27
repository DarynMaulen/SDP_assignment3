// Enum representing supported measurement units. Each entry has:
// - a UnitCategory (LENGTH, MASS, VOLUME, TEMPERATURE)
// - a toBaseFactor for converting to the category base (meters, kilograms, liters).
// For TEMPERATURE units, conversions use Kelvin as the base via formula methods.
public enum Unit {
    // LENGTH (base = meter)
    CENTIMETER(UnitCategory.LENGTH,0.01),
    METER(UnitCategory.LENGTH,1.0),
    KILOMETER(UnitCategory.LENGTH,1000.0),
    INCH(UnitCategory.LENGTH,0.0254),
    FOOT(UnitCategory.LENGTH,0.3048),
    MILE(UnitCategory.LENGTH,1609.344),

    // MASS (base = kilogram)
    KILOGRAM(UnitCategory.MASS,1.0),
    GRAM(UnitCategory.MASS,0.001),
    POUND(UnitCategory.MASS,0.45359237),

    // VOLUME (base = liter)
    LITER(UnitCategory.VOLUME,1.0),
    US_GALLON(UnitCategory.VOLUME,3.785411784),

    // TEMPERATURE (base = Kelvin)
    CELSIUS(UnitCategory.TEMPERATURE,Double.NaN),
    FAHRENHEIT(UnitCategory.TEMPERATURE,Double.NaN),
    KELVIN(UnitCategory.TEMPERATURE,Double.NaN);

    private final UnitCategory category;
    private final double toBaseFactor;

    Unit(UnitCategory category,double toBaseFactor){
        this.category = category;
        this.toBaseFactor = toBaseFactor;
    }
    public UnitCategory getCategory(){
        return category;
    }

    public double toBase(double value){
        if(UnitCategory.TEMPERATURE.equals(category)){
            return toKelvin(value);
        }
        return value * toBaseFactor;
    }
    public double fromBase(double baseValue){
        if(UnitCategory.TEMPERATURE.equals(category)){
            return fromKelvin(baseValue);
        }
        return baseValue / toBaseFactor;
    }

    public double toKelvin(double value){
        return switch (this) {
            case CELSIUS -> value + 273.15;
            case FAHRENHEIT -> (value - 32.0) * 5.0 / 9.0 + 273.15;
            case KELVIN -> value;
            default -> throw new UnsupportedOperationException("Not a temperature unit");
        };
    }

    private double fromKelvin(double baseValue) {
        return switch (this) {
            case CELSIUS -> baseValue - 273.15;
            case FAHRENHEIT -> (baseValue - 273.15) * 9.0 / 5.0 + 32.0;
            case KELVIN -> baseValue;
            default -> throw new UnsupportedOperationException("Not a temperature unit");
        };
    }
}