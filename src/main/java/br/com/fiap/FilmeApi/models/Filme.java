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

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres.")
    private String titulo;

    @NotBlank(message = "O gênero é obrigatório.")
    @Size(max = 100, message = "O gênero deve ter no máximo 100 caracteres.")
    private String genero;

    @NotNull(message = "O ano de lançamento é obrigatório.")
    @Min(value = 1888, message = "O cinema começou em 1888, informe um ano válido.")
    @Max(value = 2100, message = "Informe um ano válido até 2100.")
    private Integer anoLancamento;

    @NotBlank(message = "O diretor é obrigatório.")
    @Size(max = 120, message = "O nome do diretor deve ter no máximo 120 caracteres.")
    private String diretor;

    @Column(name = "capa_url")
    @Size(max = 500, message = "A URL da capa deve ter no máximo 500 caracteres.")
    private String capaUrl; // pode ser nulo se não encontrado no TMDB

    @PastOrPresent(message = "A data de cadastro não pode estar no futuro.")
    private LocalDateTime dataCadastro = LocalDateTime.now();

}