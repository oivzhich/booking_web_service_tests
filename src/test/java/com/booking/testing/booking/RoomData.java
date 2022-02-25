package com.booking.testing.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomData {
    private Integer numOfDays;
    private String checkInDate;
    private String checkOutDate;
    private Integer totalPrice;

    public RoomData(Map<String, String> entry) {
        this.numOfDays = entry.get("numOfDays") == null ? null : Integer.parseInt(entry.get("numOfDays"));
        this.checkInDate = entry.getOrDefault("checkInDate", null);
        this.checkOutDate = entry.getOrDefault("checkOutDate", null);
        this.totalPrice = entry.get("totalPrice") == null ? null : Integer.parseInt(entry.get("totalPrice"));
    }
}
