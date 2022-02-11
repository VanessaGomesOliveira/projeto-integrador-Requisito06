package com.example.demo.utils;

import com.example.demo.dto.CarrinhoDTO;
import com.example.demo.dto.ItemCarrinhoDTO;
import com.example.demo.entity.*;
import com.example.demo.enums.Status;
import com.example.demo.enums.Tipos;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class MockComprador {

    private Set<ItemCarrinhoDTO> listaAnuncio = new HashSet<>();
    private ItemCarrinhoDTO item;
    private List<Estoque> listaEstoque = new ArrayList<>();
    private OrdemEntrada ordemEntrada;
    private Produto produto;
    private Vendedor vendedor;
    private Anuncio anuncio;
    private Estoque estoque;
    private Usuario usuario;
    private Comprador comprador;
    private Carrinho carrinho;
    private Carrinho carrinhoFechado;
    private Carrinho carrinhoSalvo;
    private CarrinhoDTO dto;
    private ItemCarrinho itemCarrinho;
    private List<ItemCarrinho> itensCarrinhos = new ArrayList<>();
    private List<ItemCarrinho> itensCarrinhosSalvo = new ArrayList<>();
    private Set<Carrinho> carrinhoSet = new HashSet<>();

    public MockComprador() {

        item = ItemCarrinhoDTO.builder()
                .id(1L)
                .anuncio_id(1L)
                .quantidade(1)
                .build();

        listaAnuncio.add(item);

        ordemEntrada = OrdemEntrada.builder()
                .id(1L)
                .dataCriacao(LocalDateTime.of(2022,1,1,2,2,2))
                .build();

        produto = Produto.builder()
                .id(1L)
                .nome("alface")
                .descricao("verde")
                .build();

        vendedor = Vendedor.builder()
                .id(1L)
                .nome("ismael")
                .build();

        anuncio = Anuncio.builder()
                .id(1L)
                .produto(produto)
                .vendedor(vendedor)
                .preco(new BigDecimal(2))
                .volume(1d)
                .temperaturaMaxima(25d)
                .temperaturaMinima(18d)
                .tipo(Tipos.FRESCO)
                .build();

        estoque = Estoque.builder()
                .id(1L)
                .dataProducao(LocalDateTime.of(2022,1,1,2,2,2))
                .quantidadeAtual(100)
                .quantidadeInicial(100)
                .anuncio(anuncio)
                .dataValidade(LocalDate.now())
                .ordemEntrada(ordemEntrada)
                .build();

        listaEstoque.add(estoque);

        usuario = Usuario.builder()
                .user("lucian")
                .senha("12345")
                .ativo(true)
                .build();

        itemCarrinho = ItemCarrinhoDTO.converte(item, anuncio, carrinho);

        itensCarrinhos.add(itemCarrinho);

        Set<ItemCarrinho> listaAnuncioSet = new HashSet<>(itensCarrinhos);
        Set<ItemCarrinho> listaAnuncioSetSalvo = new HashSet<>(itensCarrinhosSalvo);

        carrinho = Carrinho.builder()
                .id(1L)
                .dataCriacao(LocalDateTime.of(2022,1,1,2,2,2))
                .status(0)
                .comprador(comprador)
                .itensCarrinho(listaAnuncioSet)
                .build();

        carrinhoFechado = Carrinho.builder()
                .id(1L)
                .dataCriacao(LocalDateTime.of(2022,1,1,2,2,2))
                .status(1)
                .comprador(comprador)
                .itensCarrinho(listaAnuncioSet)
                .build();

        carrinhoSalvo = Carrinho.builder()
                .id(1L)
                .dataCriacao(LocalDateTime.of(2022,1,1,2,2,2))
                .status(0)
                .comprador(comprador)
                .itensCarrinho(listaAnuncioSetSalvo)
                .build();

        dto = CarrinhoDTO.builder()
                .dataCriacao(LocalDateTime.of(2022,1,1,2,2,2))
                .comprador_id(1L)
                .status(Status.ABERTO)
                .listaAnuncio(listaAnuncio)
                .build();

        carrinhoSet.add(carrinhoFechado);

        comprador = Comprador.builder()
                .id(1L)
                .carrinhos(carrinhoSet)
                .usuario(usuario)
                .build();

        carrinhoFechado.setComprador(comprador);

    }
}

