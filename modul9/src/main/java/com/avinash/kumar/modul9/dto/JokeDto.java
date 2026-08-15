package com.avinash.kumar.modul9.dto;

public record JokeDto (
    String text,
    String category,
    Boolean laughScore,
    Boolean isNSFW
    ){}
