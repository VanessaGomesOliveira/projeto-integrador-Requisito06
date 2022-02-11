package com.example.demo.serviceTest;

import com.example.demo.dto.CarrinhoDTO;
import com.example.demo.entity.Comprador;
import com.example.demo.repository.CompradorRepository;
import com.example.demo.service.HistoricoPedidosService;
import com.example.demo.utils.MockComprador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoricoPedidosServiceTest {

    private MockComprador mockComprador;
    private Comprador comprador;

    @BeforeEach
    public void init() {
        mockComprador = new MockComprador();
        comprador = mockComprador.getComprador();
    }

    @Test
    public void deveVerificarListaCarrinhos() {

        CompradorRepository mock = Mockito.mock(CompradorRepository.class);
        Mockito.when(mock.findById(1L)).thenReturn(Optional.of(comprador));
        HistoricoPedidosService historicoPedidosService = new HistoricoPedidosService(mock);
        Set<CarrinhoDTO> carrinhosDTOS = historicoPedidosService.listarPedidoFechado(1L);

        assertEquals(carrinhosDTOS.size(),1);

    }
}
