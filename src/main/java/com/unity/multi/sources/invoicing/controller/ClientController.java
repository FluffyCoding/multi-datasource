package com.unity.multi.sources.invoicing.controller;

import com.unity.multi.sources.invoicing.dtos.ClientDto;
import com.unity.multi.sources.invoicing.model.Client;
import com.unity.multi.sources.invoicing.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "clients")
public class ClientController {

    private final ClientService clientService;


    @GetMapping(path = "/all")
    public Page<ClientDto> getClintesByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return clientService.getClinetByPage(page, size);
    }

    //    @GetMapping
//    public ResponseEntity<ClientDto> getClientById(@RequestParam Integer id) {
//        return ResponseEntity.ok(clientService.getClinetById(id));
//    }
    @GetMapping
    public ResponseEntity<Client> getClientById(@RequestParam Integer id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

}
