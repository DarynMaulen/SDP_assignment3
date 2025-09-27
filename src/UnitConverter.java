// Target interface for conversion services. Defines a single method:
// double convert(double value, Unit from, Unit to).
// Clients depend on this interface for unit conversions.
public interface UnitConverter {
    double convert(double value,Unit from,Unit to);
}
