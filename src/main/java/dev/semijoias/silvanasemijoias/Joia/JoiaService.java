package dev.semijoias.silvanasemijoias.Joia;

import dev.semijoias.silvanasemijoias.Colecao.ColecaoModel;
import dev.semijoias.silvanasemijoias.Colecao.ColecaoRepository;
import dev.semijoias.silvanasemijoias.Colecao.ColecaoResponseDTO;
// Importe o ImagemModel para poder criar instâncias dele
import dev.semijoias.silvanasemijoias.Imagem.ImagemModel;
import dev.semijoias.silvanasemijoias.Promocao.PromocaoModel;
import dev.semijoias.silvanasemijoias.Promocao.PromocaoRepository;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoJoiaResponseDTO;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoModel;
import dev.semijoias.silvanasemijoias.TipoJoia.TipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoiaService {

    private final JoiaRepository joiaRepository;
    private final TipoRepository tipoRepository;
    private final ColecaoRepository colecaoRepository;
    private final PromocaoRepository promocaoRepository;

    private static final String URL_IMAGEM_PADRAO = "https://imgur.com/gallery/popular-shop-accented-solitaire-sywAy4I#/t/diamond"; // URL de imagem direta

    public JoiaService(JoiaRepository joiaRepository, TipoRepository tipoRepository, ColecaoRepository colecaoRepository, PromocaoRepository promocaoRepository) {
        this.joiaRepository = joiaRepository;
        this.tipoRepository = tipoRepository;
        this.colecaoRepository = colecaoRepository;
        this.promocaoRepository = promocaoRepository;
    }

    public List<JoiaResponseDTO> buscarTodas() {
        List<JoiaModel> joiasDoBanco = joiaRepository.findAll();
        return joiasDoBanco.stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public JoiaRequestDTO buscarPorId(Long id) {
        Optional<JoiaModel> joia = joiaRepository.findById(id);
        return joia.map(JoiaMapper::map).orElse(null);
    }

    public JoiaRequestDTO cadastrarJoia(JoiaRequestDTO joiaRequestDTO) {
        JoiaModel joia = JoiaMapper.map(joiaRequestDTO, null);

        // CORREÇÃO: Lógica para imagem padrão na criação
        if (joiaRequestDTO.getImagens() == null || joiaRequestDTO.getImagens().isEmpty()) {
            // 1. Crie uma instância de ImagemModel
            ImagemModel imagemPadrao = new ImagemModel();
            // 2. Defina a URL
            imagemPadrao.setUrlImagem(URL_IMAGEM_PADRAO);
            // 3. Associe a imagem à joia (importante para o relacionamento)
            imagemPadrao.setJoia(joia);
            // 4. Adicione o objeto ImagemModel à lista de imagens da joia
            joia.setImagens(List.of(imagemPadrao));
        } else {
            // Garante que a referência bidirecional está correta
            joiaRequestDTO.getImagens().forEach(imagem -> imagem.setJoia(joia));
            joia.setImagens(joiaRequestDTO.getImagens());
        }


        ColecaoModel colecao = colecaoRepository.findById(joiaRequestDTO.getColecaoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coleção não encontrada"));
        joia.setColecao(colecao);
        colecao.getJoias().add(joia);

        TipoModel tipo = tipoRepository.findById(joiaRequestDTO.getTipoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo não encontrado"));
        joia.setTipo(tipo);
        tipo.getJoias().add(joia);

        JoiaModel salvo = joiaRepository.save(joia);
        return JoiaMapper.map(salvo);
    }


    public JoiaResponseDTO atualizarJoia(Long id, JoiaRequestDTO joiaRequestDTO) {
        JoiaModel joiaExistente = joiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joia com id " + id + " não encontrada."));

        joiaExistente.setValorOriginal(joiaRequestDTO.getValorOriginal());
        joiaExistente.setQuantidadeEstoque(joiaRequestDTO.getQuantidadeEstoque());
        joiaExistente.setQuantidadeVendida(joiaRequestDTO.getQuantidadeVendida());

        // CORREÇÃO: Lógica para imagem padrão na atualização
        if (joiaRequestDTO.getImagens() == null || joiaRequestDTO.getImagens().isEmpty()) {
            // 1. Limpa a lista de imagens antigas
            joiaExistente.getImagens().clear();
            // 2. Cria a nova imagem padrão
            ImagemModel imagemPadrao = new ImagemModel();
            imagemPadrao.setUrlImagem(URL_IMAGEM_PADRAO);
            // 3. Associa à joia existente
            imagemPadrao.setJoia(joiaExistente);
            // 4. Adiciona a imagem padrão à lista
            joiaExistente.getImagens().add(imagemPadrao);
        } else {
            // Limpa a lista antiga para remover imagens que não estão mais no DTO
            joiaExistente.getImagens().clear();
            // Para cada imagem vinda do DTO, é preciso setar a referência de volta para a joia
            joiaRequestDTO.getImagens().forEach(img -> {
                img.setJoia(joiaExistente);
                joiaExistente.getImagens().add(img);
            });
        }

        if (joiaRequestDTO.getColecaoId() != null) {
            ColecaoModel colecao = colecaoRepository.findById(joiaRequestDTO.getColecaoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coleção com id " + joiaRequestDTO.getColecaoId() + " não encontrada"));
            joiaExistente.setColecao(colecao);
        }

        if (joiaRequestDTO.getTipoId() != null) {
            TipoModel tipo = tipoRepository.findById(joiaRequestDTO.getTipoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo com id " + joiaRequestDTO.getTipoId() + " não encontrado"));
            joiaExistente.setTipo(tipo);
        }

        if (joiaRequestDTO.getPromocao() != null && joiaRequestDTO.getPromocao().getId() != null) {
            PromocaoModel promocao = promocaoRepository.findById(joiaRequestDTO.getPromocao().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Promoção com id " + joiaRequestDTO.getPromocao().getId() + " não encontrada."));
            joiaExistente.setPromocao(promocao);
        } else {
            joiaExistente.setPromocao(null);
        }

        JoiaModel atualizado = joiaRepository.save(joiaExistente);

        return this.converterParaResponseDTO(atualizado);
    }


    public void removerJoia(Long id) {
        JoiaModel joia = joiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joia nao encontrada"));
        if (joia.getColecao() != null) {
            joia.getColecao().getJoias().remove(joia);
        }
        if (joia.getTipo() != null) {
            joia.getTipo().getJoias().remove(joia);
        }
        if (joia.getPromocao() != null) {
            joia.getPromocao().getJoias().remove(joia);
        }
        if (joia.getImagens() != null) {
            joia.getImagens().remove(joia);
        }
        joiaRepository.delete(joia);
    }

    private JoiaResponseDTO converterParaResponseDTO(JoiaModel joia) {
        JoiaResponseDTO dto = new JoiaResponseDTO();
        dto.setId(joia.getId());
        dto.setValorVenda(joia.getValorVenda());
        dto.setQuantidadeEstoque(joia.getQuantidadeEstoque());
        dto.setImagens(joia.getImagens());

        if (joia.getTipo() != null) {
            TipoJoiaResponseDTO tipoDTO = new TipoJoiaResponseDTO();
            tipoDTO.setId(joia.getTipo().getId());
            tipoDTO.setDescricao(joia.getTipo().getDescricao());
            dto.setTipoJoia(tipoDTO);
        }

        if (joia.getColecao() != null) {
            ColecaoResponseDTO colecaoDTO = new ColecaoResponseDTO();
            colecaoDTO.setId(joia.getColecao().getId());
            colecaoDTO.setNome(joia.getColecao().getNome());
            dto.setColecao(colecaoDTO);
        }

        return dto;
    }
}