package com.jscyril.meditrack.model;

public record LoginResponse(String token, long expiresInSeconds) {
}
