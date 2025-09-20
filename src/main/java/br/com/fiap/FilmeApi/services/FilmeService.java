package br.com.fiap.FilmeApi.services;


import br.com.fiap.FilmeApi.models.Filme;
import br.com.fiap.FilmeApi.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private TmdbService tmdbService; // Serviço que tenta buscar capa

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Optional<Filme> buscarPorId(Long id) {
        return filmeRepository.findById(id);
    }

    public Filme salvar(Filme filme) {
        // Se ainda não tiver capa, tenta buscar no TMDB
        if (filme.getCapaUrl() == null || filme.getCapaUrl().isBlank()) {
            String capa = tmdbService.buscarCapa(filme.getTitulo(), filme.getAnoLancamento());
            if (capa != null) {
                filme.setCapaUrl(capa);
            }
        }
        return filmeRepository.save(filme);
    }

    public void deletar(Long id) {
        filmeRepository.deleteById(id);
    }
}
