package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public enum Qualifier {
    NONE("none"),
    STRONG_REJECT("strong reject"),
    REJECT("reject"),
    WEAK_REJECT("weak reject"),
    BORDERLINE_PAPER("borderline paper"),
    WEAK_ACCEPT("weak accept"),
    ACCEPT("accept"),
    STRONG_ACCEPT("strong accept");

    @Override
    public String toString() {
        return qualifier_value ;
    }

    private final String qualifier_value;

    Qualifier(String qualifier_value) {
        this.qualifier_value=qualifier_value;
    }

    public String getQualifier_value() {
        return qualifier_value;
    }
}

