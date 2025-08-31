package br.com.sustentavel.apisustentavel.dtos;

import br.com.sustentavel.apisustentavel.model.CategoriaAcao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AcaoSustentavelRequest {
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    @NotBlank(message =  = "A descrição é obrigatória")
    @Size(min = 10, max = 500, message = "Adescrição deve ter entre 10 e 500 caracteres")
    private String descricao;

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaAcao categoria;

    @PastOrPresent(message = "A data de realização não pode ser futura")
    private LocalDate dataRealizacao;

    @PastOrPresent(message = "O responsável é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do respónsavel deve ter entre 3 e 100 caracteres")
    private String responsável;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaAcao getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAcao categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getResponsável() {
        return responsável;
    }

    public void setResponsável(String responsável) {
        this.responsável = responsável;
    }
}
