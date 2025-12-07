

public class Conversion {
    private final double input;
    private final String fromUnit;
    private final String toUnit;
    private final double result;
    private final String category;

    public Conversion(double input, String fromUnit, String toUnit, double result, String category) {
        this.input = input;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.result = result;
        this.category = category;
    }

    public String toFileString() {
        return String.format("%s: %.2f %s -> %.2f %s", category, input, fromUnit, result, toUnit);
    }
}