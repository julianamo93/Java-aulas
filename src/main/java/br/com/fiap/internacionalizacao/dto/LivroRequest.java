package br.com.fiap.internacionalizacao.dto;

import br.com.fiap.internacionalizacao.model.Categoria;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record LivroRequest(
    @NotBlank(message = "O título não pode ser vazio")
    String titulo,
    @NotBlank(message = "O autor é uma informação obrigatória")
    @Size(min = 4, max = 150, message = "O nome do autor deve ter entre 4 e 150 caracteres")
    String autor,
    @NotNull(message = "A categoria é obrigatória")
    Categoria categoria,
    @DecimalMin(value = "0.99", message = "O preço deve ser no mínimo 0.99")
    BigDecimal preco,
    @Pattern(regexp = "^970\\d{7}$|^970\\d{7}$")
    String isbn
) {
}
