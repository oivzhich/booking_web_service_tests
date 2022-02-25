package com.booking.testing.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.DateHelper;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Availability {
    private String date;
    private Integer rooms_available;
    private Integer price;

    public Availability(Map<String, String> entry) {
        this.date = entry.get("date") == null ? null : transformDate(entry.get("date"));
        this.rooms_available =
                entry.get("rooms_available") == null ? null : Integer.parseInt(entry.get("rooms_available"));
        this.price = entry.get("price") == null ? null : Integer.parseInt(entry.get("price"));
    }

    private String transformDate(String dateLiteral) {
        if (dateLiteral.equals("today")) {
            return DateHelper.today();
        } else {
            return dateLiteral;
        }
    }
}
