package br.com.fiap.FilmeApi.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{filme.title.notblank}")
    @Size(max = 150, message = "{filme.title.size}")
    private String titulo;

    @NotBlank(message = "{filme.genero.notblank}")
    @Size(max = 100, message = "{filme.genero.size}")
    private String genero;

    @NotNull(message = "{filme.ano.notnull}")
    @Min(value = 1888, message = "{filme.ano.min}")
    @Max(value = 2100, message = "{filme.ano.max}")
    private Integer anoLancamento;

    @NotBlank(message = "{filme.diretor.notblank}")
    @Size(max = 120, message = "{filme.diretor.size}")
    private String diretor;

    @Column(name = "capa_url")
    @Size(max = 500, message = "A URL da capa deve ter no máximo 500 caracteres.")
    private String capaUrl; // pode ser nulo se não encontrado no TMDB

    @PastOrPresent(message = "A data de cadastro não pode estar no futuro.")
    private LocalDateTime dataCadastro = LocalDateTime.now();

}