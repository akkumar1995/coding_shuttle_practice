package com.avinash.kumar.modul9.dto;

import java.util.List;

public record BookingsListResponse(List<BookingResponse> bookings, String message) {}
