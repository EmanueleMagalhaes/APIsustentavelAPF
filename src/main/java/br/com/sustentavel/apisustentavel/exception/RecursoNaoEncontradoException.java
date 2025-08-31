package br.com.sustentavel.apisustentavel.exception;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
