package dev.semijoias.silvanasemijoias.Promocao;

import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromocaoService {

    private final PromocaoRepository promocaoRepository;
    private final PromocaoMapper promocaoMapper;
    private final JoiaRepository joiaRepository;

    public PromocaoService(PromocaoRepository promocaoRepository, PromocaoMapper promocaoMapper, JoiaRepository joiaRepository) {
        this.promocaoRepository = promocaoRepository;
        this.promocaoMapper = promocaoMapper;
        this.joiaRepository = joiaRepository;
    }
    
    

    public PromocaoDTO createPromocao(PromocaoDTO dto) {
        PromocaoModel model = promocaoMapper.map(dto);
        PromocaoModel salvo = promocaoRepository.save(model);
        return promocaoMapper.map(salvo);
    }


    public List<PromocaoDTO> listarPromocoes() {
        List<PromocaoModel> promocoes = promocaoRepository.findAll();
        return promocoes.stream()
                .map(promocaoMapper::map)
                .collect(Collectors.toList());
    }

    public PromocaoDTO buscarPorId(Long id) {
        Optional<PromocaoModel> promocao = promocaoRepository.findById(id);
        return promocao.map(promocaoMapper::map).orElse(null);
    }

    public PromocaoDTO atualizarPromocao(Long id, PromocaoDTO dto) {
        PromocaoModel existente = promocaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Promoção não encontrada"));

        PromocaoModel atualizado = promocaoMapper.map(dto);
        atualizado.setId(id);

        PromocaoModel salvo = promocaoRepository.save(atualizado);
        return promocaoMapper.map(salvo);
    }

    public void deletarPromocao(Long id) {
        PromocaoModel promocao = promocaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Promoção não encontrada"));

        for (JoiaModel joia : promocao.getJoias()) {
            if (joia.getValorOriginal() != null) {
                joia.setValorVenda(joia.getValorOriginal());
                joia.setValorOriginal(null);
                joiaRepository.save(joia);
            }
        }

        promocaoRepository.deleteById(id);
    }

    public PromocaoDTO adicionarJoiaEmPromocao(Long promocaoId, Long joiaId) {
        PromocaoModel promocao = promocaoRepository.findById(promocaoId)
                .orElseThrow(() -> new EntityNotFoundException("Promoção não encontrada"));

        JoiaModel joia = joiaRepository.findById(joiaId)
                .orElseThrow(() -> new EntityNotFoundException("Joia não encontrada"));

        if (!promocao.getJoias().contains(joia)) {
            promocao.getJoias().add(joia);

            if (joia.getValorOriginal() == null) {
                joia.setValorOriginal(joia.getValorVenda());
            }

            double desconto = promocao.getPercentualDesconto();
            double novoValor = joia.getValorOriginal() * (1 - desconto / 100);
            joia.setValorVenda(novoValor);

            joiaRepository.save(joia);
        }

        PromocaoModel salvo = promocaoRepository.save(promocao);
        return promocaoMapper.map(salvo);
    }

    public PromocaoDTO removerJoiaDaPromocao(Long promocaoId, Long joiaId) {
        PromocaoModel promocao = promocaoRepository.findById(promocaoId)
                .orElseThrow(() -> new EntityNotFoundException("Promoção não encontrada"));

        JoiaModel joia = joiaRepository.findById(joiaId)
                .orElseThrow(() -> new EntityNotFoundException("Joia não encontrada"));

        promocao.getJoias().removeIf(j -> j.getId().equals(joiaId));

        joia.setPromocao(null);

        if (joia.getValorOriginal() != null) {
            joia.setValorVenda(joia.getValorOriginal());
            joia.setValorOriginal(null);
        }

        joiaRepository.save(joia);
        PromocaoModel salvo = promocaoRepository.save(promocao);

        return promocaoMapper.map(salvo);
    }


}
