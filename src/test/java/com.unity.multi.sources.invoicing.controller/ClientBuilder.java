package com.unity.multi.sources.invoicing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ClientBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ClientDto getDto() {
        ClientDto dto = new ClientDto();
        dto.setId("1");
        return dto;
    }
}