package dev.semijoias.silvanasemijoias.ItemMaleta;

import dev.semijoias.silvanasemijoias.Joia.JoiaMapper;
import dev.semijoias.silvanasemijoias.Joia.JoiaModel;
import dev.semijoias.silvanasemijoias.Joia.JoiaRepository;
import dev.semijoias.silvanasemijoias.Joia.JoiaResponseDTO;
import dev.semijoias.silvanasemijoias.Maleta.MaletaModel;
import dev.semijoias.silvanasemijoias.Maleta.MaletaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemMaletaService {

    private final ItemMaletaRepository itemMaletaRepository;
    private final MaletaRepository maletaRepository;
    private final JoiaRepository joiaRepository;

    public ItemMaletaService(ItemMaletaRepository itemMaletaRepository, MaletaRepository maletaRepository, JoiaRepository joiaRepository) {
        this.itemMaletaRepository = itemMaletaRepository;
        this.maletaRepository = maletaRepository;
        this.joiaRepository = joiaRepository;
    }

    public List<ItemMaletaDTO> listarItemMaleta() {
        return itemMaletaRepository.findAll()
                .stream()
                .map(ItemMaletaMapper::map).toList();
    }

    public List<ItemMaletaDTO> listarPorMaleta(Long maletaId) {
        return itemMaletaRepository.findAll()
                .stream()
                .filter(item -> item.getMaleta().getId().equals(maletaId))
                .map(ItemMaletaMapper::map).toList();
    }

    private MaletaModel buscarMalete(Long maletaId) {
        return this.maletaRepository.findById(maletaId).orElseThrow(() -> new RuntimeException("Maleta nao encontrada"));
    }

    private JoiaModel buscarJoia(Long joiaId) {
        return this.joiaRepository.findById(joiaId).orElseThrow(() -> new RuntimeException("Joia nao encontrado"));
    }

    public ItemMaletaDTO buscarPorId(Long id) {
        return ItemMaletaMapper.map(buscaPorId(id));
    }

    private ItemMaletaModel buscaPorId(Long id) {
        return this.itemMaletaRepository.findById(id).orElseThrow(() -> new RuntimeException("Item maleta não encontrado"));
    }

    public ItemMaletaDTO cadastrarItemMaleta(ItemMaletaDTO itemMaletaDTO) {
        MaletaModel maleta = maletaRepository.findById(itemMaletaDTO.getMaletaId())
                .orElseThrow(() -> new RuntimeException("Maleta não encontrada"));
        JoiaModel joia = joiaRepository.findById(itemMaletaDTO.getJoiaId())
                .orElseThrow(() -> new RuntimeException("Joia não encontrada"));

        ItemMaletaModel item = ItemMaletaMapper.map(itemMaletaDTO, joia, maleta);
        item.setMaleta(maleta);
        item.setJoia(joia);

        maleta.getItens().add(item);

        ItemMaletaModel salvo = itemMaletaRepository.save(item);
        return ItemMaletaMapper.map(salvo);
    }

    @Transactional
    public ItemMaletaDTO atualizarItemMaleta(ItemMaletaDTO itemMaletaDTO /* itemMaletaDTO */) {
        ItemMaletaModel itemExistente = itemMaletaRepository.findById(itemMaletaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (!itemExistente.getMaleta().getId().equals(itemMaletaDTO.getMaletaId())) {
            itemExistente.getMaleta().getItens().remove(itemExistente);
            MaletaModel novaMaleta = maletaRepository.findById(itemMaletaDTO.getMaletaId())
                    .orElseThrow(() -> new RuntimeException("Nova maleta não encontrada"));
            itemExistente.setMaleta(novaMaleta);
            novaMaleta.getItens().add(itemExistente);

            }

        JoiaModel novaJoia = joiaRepository.findById(itemMaletaDTO.getJoiaId())
                .orElseThrow(() -> new RuntimeException("Joia não encontrada"));
        itemExistente.setJoia(novaJoia);

        itemExistente.setQuantidade(itemMaletaDTO.getQuantidade());
        itemExistente.setPrecoSugerido(itemMaletaDTO.getPrecoSugerido());
        itemExistente.setDataInsercao(itemMaletaDTO.getDataInsercao());

        ItemMaletaModel atualizado = itemMaletaRepository.save(itemExistente);
        return ItemMaletaMapper.map(atualizado);
    }


    @Transactional
    public void removerItemMaleta(Long id) {
        ItemMaletaModel item = itemMaletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Long maletaId = item.getMaleta().getId();
        MaletaModel maleta = maletaRepository.findById(maletaId)
                .orElseThrow(() -> new RuntimeException("Maleta não encontrada"));

        maleta.getItens().remove(item);

        itemMaletaRepository.delete(item);
    }
}
