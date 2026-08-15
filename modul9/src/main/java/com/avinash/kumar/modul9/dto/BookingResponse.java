package com.avinash.kumar.modul9.dto;

import com.avinash.kumar.modul9.entity.BookingStatus;

import java.time.Instant;

public record BookingResponse(Long id, String destination, Instant departureTime, BookingStatus status) {}