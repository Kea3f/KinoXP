package com.example.kinoxp.Backend.dto;

import com.example.kinoxp.Backend.model.Showing;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class CalendarDto {

    private String title;
    private LocalDate showingDate;

    private LocalTime showingTime;

    public LocalTime getShowingTime() {
        return showingTime;
    }

    public void setShowingTime(LocalTime showingTime) {
        this.showingTime = showingTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getShowingDate() {
        return showingDate;
    }

    public void setShowingDate(LocalDate showingDate) {
        this.showingDate = showingDate;
    }
}
