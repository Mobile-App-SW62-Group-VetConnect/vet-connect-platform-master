package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class BusinessHour {

    @Getter
    @Setter
    private String days;

    @Getter
    @Setter
    private String open;

    @Getter
    @Setter
    private String close;

    public BusinessHour() {}

    public BusinessHour(String days, String open, String close) {
        this.days = days;
        this.open = open;
        this.close = close;
    }
}
