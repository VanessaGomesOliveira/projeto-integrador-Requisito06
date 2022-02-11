package com.example.demo.exception;

public class PedidoNaoExisteException extends RuntimeException {

    private static final long serialVersionUID = 5695847807591193154L;

    public PedidoNaoExisteException(String mensagem) {
        super(mensagem);
    }

}
