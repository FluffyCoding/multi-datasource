package com.unity.multi.sources.invoicing.service;


import com.unity.multi.sources.invoicing.dtos.ClientDto;
import com.unity.multi.sources.invoicing.model.Client;
import com.unity.multi.sources.invoicing.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper clientMapper;


    public Page<ClientDto> getClinetByPage(int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);
        return clientRepository.findAll(pageRequest)
                .map(element -> clientMapper.map(element, ClientDto.class));
    }


    public ClientDto getClinetById(Integer id) {
        return clientRepository.findById(id)
                .map((element) -> clientMapper.map(element, ClientDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Invalid Client Id"));
    }


    public Client getClientById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Invalid"));
    }
}