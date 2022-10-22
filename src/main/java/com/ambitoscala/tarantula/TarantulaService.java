package com.ambitoscala.tarantula;

import com.ambitoscala.commons.MessageSourceService;
import com.ambitoscala.tarantula.domain.TarantulaEntity;
import com.ambitoscala.tarantula.exception.TarantulaAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TarantulaService {

    private final TarantulaRepository tarantulaRepository;
    private final MessageSourceService messageSourceService;

    public TarantulaService(final TarantulaRepository tarantulaRepository, final MessageSourceService messageSourceService) {
        this.tarantulaRepository = tarantulaRepository;
        this.messageSourceService = messageSourceService;
    }

    @Transactional
    public void addTarantula(final TarantulaEntity tarantulaEntity) {
        validateTarantula(tarantulaEntity.getName());
        this.tarantulaRepository.save(tarantulaEntity);
    }

    private void validateTarantula(final String name) {
        final Optional<TarantulaEntity> optionalTarantulaEntity = this.tarantulaRepository.findTarantulaByName(name);
        optionalTarantulaEntity.ifPresent(tarantulaEntity -> {
            throw new TarantulaAlreadyExistsException(this.messageSourceService.getMessage("tarantula.already.exists.exception", name));
        });
    }

}
