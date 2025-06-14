// src/com/mycompany/unitconverter/ConversionRecord.java
package com.mycompany.unitconverter;

import java.time.LocalDateTime;

// Model class to represent a single conversion record
// (Even without DB, this can be useful if you wanted to store history in memory later)
class ConversionRecord {
    String category;
    String fromUnit;
    String toUnit;
    double inputValue;
    double result;
    LocalDateTime timestamp; // Timestamp for when the conversion occurred

    public ConversionRecord(String category, String fromUnit, String toUnit, double inputValue, double result) {
        this.category = category;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.inputValue = inputValue;
        this.result = result;
        this.timestamp = LocalDateTime.now(); // Automatically set creation timestamp
    }
}