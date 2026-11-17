package ca.daltaxi.backend.dto;

public record UserResponse(Long id, String name, String dalEmail, boolean verified, String activeRole) {}