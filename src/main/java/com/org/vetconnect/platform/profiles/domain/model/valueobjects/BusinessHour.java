package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class BusinessHours {

    @Getter
    @Setter
    private String days;

    @Getter
    @Setter
    private String open;

    @Getter
    @Setter
    private String close;

    public BusinessHours() {}

    public BusinessHours(String days, String open, String close) {
        this.days = days;
        this.open = open;
        this.close = close;
    }
}
