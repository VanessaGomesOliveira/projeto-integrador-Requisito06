package com.example.demo.controller;

import com.example.demo.dto.CarrinhoDTO;
import com.example.demo.service.HistoricoPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/sales")
public class HistoricoPedidosController {

    @Autowired
    private HistoricoPedidosService historicoPedidosService;

    @GetMapping("/order-history")
    public ResponseEntity<Set<CarrinhoDTO>> listarPedidoFechadoHistorico(@RequestParam Long comprador_id) {
        Set<CarrinhoDTO> listaPedidos = historicoPedidosService.listarPedidoFechado(comprador_id);
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidos);
    }
}
