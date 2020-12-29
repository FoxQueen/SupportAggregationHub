package com.fox.main.model;

import java.util.Map;

public enum CRM {
    BANANA("https://fakebanky.herokuapp.com/fruit/banana"),
    STRAWBERRY("https://fakebanky.herokuapp.com/fruit/strawberry");

    private final String endpoint;

    CRM(String endpoint) {
        this.endpoint = endpoint;
    }

    public Map<String, String> asMap() {
        return Map.of(
                "type", this.name(),
                "endpoint", this.endpoint
        );
    }
}
