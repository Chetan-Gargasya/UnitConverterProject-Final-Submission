import javax.swing.*;
import java.awt.event.*;

public class UnitConverter extends JFrame {
    private JComboBox<String> categoryBox, fromUnitBox, toUnitBox;
    private JTextField inputField;
    private JLabel resultLabel;
    private JButton convertButton;

    public UnitConverter() {
        setTitle("Unit Converter");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] categories = {"Length", "Weight", "Temperature"};
        categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(30, 20, 150, 25);
        add(categoryBox);

        inputField = new JTextField();
        inputField.setBounds(200, 20, 150, 25);
        add(inputField);

        fromUnitBox = new JComboBox<>();
        fromUnitBox.setBounds(30, 60, 150, 25);
        add(fromUnitBox);

        toUnitBox = new JComboBox<>();
        toUnitBox.setBounds(200, 60, 150, 25);
        add(toUnitBox);

        convertButton = new JButton("Convert");
        convertButton.setBounds(140, 100, 100, 30);
        add(convertButton);

        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(30, 150, 300, 25);
        add(resultLabel);

        updateUnits();

        categoryBox.addActionListener(e -> updateUnits());

        convertButton.addActionListener(e -> convert());

        setVisible(true);
    }

    private void updateUnits() {
        String category = (String) categoryBox.getSelectedItem();
        fromUnitBox.removeAllItems();
        toUnitBox.removeAllItems();

        if ("Length".equals(category)) {
            String[] units = {"Millimeter", "Centimeter", "Meter", "Kilometer", "Inches", "Yard"};
            for (String u : units) {
                fromUnitBox.addItem(u);
                toUnitBox.addItem(u);
            }
        } else if ("Weight".equals(category)) {
            String[] units = {"Kilogram", "Hectogram", "Decagram", "Gram", "Decigram", "Centigram", "Milligram"};
            for (String u : units) {
                fromUnitBox.addItem(u);
                toUnitBox.addItem(u);
            }
        } else if ("Temperature".equals(category)) {
            String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
            for (String u : units) {
                fromUnitBox.addItem(u);
                toUnitBox.addItem(u);
            }
        }
    }

    private void convert() {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String fromUnit = (String) fromUnitBox.getSelectedItem();
            String toUnit = (String) toUnitBox.getSelectedItem();
            String category = (String) categoryBox.getSelectedItem();

            double result = 0;

            switch (category) {
                case "Length":
                    result = convertLength(inputValue, fromUnit, toUnit);
                    break;
                case "Weight":
                    result = convertWeight(inputValue, fromUnit, toUnit);
                    break;
                case "Temperature":
                    result = convertTemperature(inputValue, fromUnit, toUnit);
                    break;
            }

            resultLabel.setText("Result: " + result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private double convertLength(double value, String from, String to) {
        // All in meters
        double meterValue = switch (from) {
            case "Millimeter" -> value / 1000;
            case "Centimeter" -> value / 100;
            case "Meter" -> value;
            case "Kilometer" -> value * 1000;
            case "Inches" -> value * 0.0254;
            case "Yard" -> value * 0.9144;
            default -> 0;
        };

        return switch (to) {
            case "Millimeter" -> meterValue * 1000;
            case "Centimeter" -> meterValue * 100;
            case "Meter" -> meterValue;
            case "Kilometer" -> meterValue / 1000;
            case "Inches" -> meterValue / 0.0254;
            case "Yard" -> meterValue / 0.9144;
            default -> 0;
        };
    }

    private double convertWeight(double value, String from, String to) {
        // All in grams
        double gramValue = switch (from) {
            case "Kilogram" -> value * 1000;
            case "Hectogram" -> value * 100;
            case "Decagram" -> value * 10;
            case "Gram" -> value;
            case "Decigram" -> value / 10;
            case "Centigram" -> value / 100;
            case "Milligram" -> value / 1000;
            default -> 0;
        };

        return switch (to) {
            case "Kilogram" -> gramValue / 1000;
            case "Hectogram" -> gramValue / 100;
            case "Decagram" -> gramValue / 10;
            case "Gram" -> gramValue;
            case "Decigram" -> gramValue * 10;
            case "Centigram" -> gramValue * 100;
            case "Milligram" -> gramValue * 1000;
            default -> 0;
        };
    }

    private double convertTemperature(double value, String from, String to) {
        if (from.equals(to)) return value;

        // First convert to Celsius
        double celsius = switch (from) {
            case "Fahrenheit" -> (value - 32) * 5 / 9;
            case "Kelvin" -> value - 273.15;
            default -> value;
        };

        // Then convert to target unit
        return switch (to) {
            case "Fahrenheit" -> (celsius * 9 / 5) + 32;
            case "Kelvin" -> celsius + 273.15;
            default -> celsius;
        };
    }

    public static void main(String[] args) {
        new UnitConverter();
    }
}
