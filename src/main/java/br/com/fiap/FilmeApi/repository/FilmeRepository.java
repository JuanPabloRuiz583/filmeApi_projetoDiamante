package br.com.fiap.FilmeApi.repository;
import br.com.fiap.FilmeApi.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
