package com.unity.multi.sources.invoicing.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.unity.multi.sources.invoicing.controller.ClientController;
import com.unity.multi.sources.invoicing.controller.CustomUtils;
import com.unity.multi.sources.invoicing.dto.ClientDto;
import com.unity.multi.sources.invoicing.mapper.ClientMapper;
import com.unity.multi.sources.invoicing.mapper.EntityMapper;
import com.unity.multi.sources.invoicing.model.Client;
import com.unity.multi.sources.invoicing.service.ClientService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class ClientControllerTest {
    private static final String ENDPOINT_URL = "/api/client";
    @InjectMocks
    private ClientController clientController;
    @Mock
    private ClientService clientService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<ClientDto> page = new PageImpl<>(Collections.singletonList(ClientBuilder.getDto()));

        Mockito.when(clientService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(clientService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(clientService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(clientService.findById(ArgumentMatchers.anyInteger())).thenReturn(ClientBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(clientService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(clientService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(clientService.save(ArgumentMatchers.any(ClientDto.class))).thenReturn(ClientBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ClientBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(clientService, Mockito.times(1)).save(ArgumentMatchers.any(ClientDto.class));
        Mockito.verifyNoMoreInteractions(clientService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(clientService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(ClientBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ClientBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(clientService, Mockito.times(1)).update(ArgumentMatchers.any(ClientDto.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(clientService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(clientService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(ClientBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(clientService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(clientService);
    }
}