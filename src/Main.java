// Demo entry point showing example conversions using the ObjectAdapter.
// Shows length, mass, volume and temperature conversions.
public class Main {
    public static void main(String[] args) {
        LegacyImperialConverter legacy = new LegacyImperialConverter();
        UnitConverter adapter = new ObjectAdapter(legacy);

        // LENGTH
        System.out.printf("12 inch -> meter = %.6f%n", adapter.convert(12.0, Unit.INCH, Unit.METER));

        // MASS
        System.out.printf("5 pound -> kilogram = %.6f%n", adapter.convert(5.0, Unit.POUND, Unit.KILOGRAM));

        // VOLUME
        System.out.printf("1 us gallon -> Liter = %.6f%n", adapter.convert(1.0, Unit.US_GALLON, Unit.LITER));

        // TEMPERATURE (metric -> imperial)
        System.out.printf("100 C -> F = %.6f%n", adapter.convert(100.0, Unit.CELSIUS, Unit.FAHRENHEIT));
        // TEMPERATURE (imperial -> metric)
        System.out.printf("32 F -> C = %.6f%n", adapter.convert(32.0, Unit.FAHRENHEIT, Unit.CELSIUS));
        // TEMPERATURE (Kelvin -> Celsius)
        System.out.printf("0 K -> C = %.6f%n", adapter.convert(0.0, Unit.KELVIN, Unit.CELSIUS));
    }
}