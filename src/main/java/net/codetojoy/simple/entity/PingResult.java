package net.codetojoy.simple.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PingResult {
    @JsonProperty("message")
    private String message;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
