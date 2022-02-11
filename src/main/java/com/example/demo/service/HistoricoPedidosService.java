package com.example.demo.service;

import com.example.demo.entity.Carrinho;
import com.example.demo.entity.Comprador;
import com.example.demo.enums.Status;
import com.example.demo.exception.PedidoNaoExisteException;
import com.example.demo.repository.CompradorRepository;
import com.example.demo.dto.CarrinhoDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que contem a logica de negocio da entidade Comprador com a visão de histórios de vendas
 */
@AllArgsConstructor
@Service
public class HistoricoPedidosService {

    @Autowired
    CompradorRepository compradorRepository;

    /**
     * Esse método obtem uma lista de carrinhos do comprador, somente com pedidos fechados e cancelados
     * @param comprador_id (Long)
     * @return carrinhoDTOS (CarrinhoDTO) uma lista com os pedidos fechados ou cancelados
     */
    public Set<CarrinhoDTO> listarPedidoFechado(Long comprador_id){

        Optional<Comprador> compradorOptional = this.compradorRepository.findById(comprador_id);
        Comprador comprador = compradorOptional.get();

        Set<Carrinho> carrinhoSet = comprador.getCarrinhos().stream().filter(x -> x.getStatus() == (Status.FINALIZADO.getCodigo()) || x.getStatus() == (Status.CANCELADO.getCodigo())).collect(Collectors.toSet());

        Set<CarrinhoDTO> carrinhoDTOS = CarrinhoDTO.converte(carrinhoSet);

        if (carrinhoDTOS.isEmpty()) throw new PedidoNaoExisteException("Não há pedido para esse cliente.");

        return carrinhoDTOS;
    }
}

