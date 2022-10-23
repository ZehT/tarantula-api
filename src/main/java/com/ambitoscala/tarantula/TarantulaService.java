package com.ambitoscala.tarantula;

import com.ambitoscala.commons.MessageSourceService;
import com.ambitoscala.tarantula.domain.MoltDocument;
import com.ambitoscala.tarantula.domain.TarantulaDocument;
import com.ambitoscala.tarantula.exception.MoltsDontHappenThatFastException;
import com.ambitoscala.tarantula.exception.NoContentException;
import com.ambitoscala.tarantula.exception.TarantulaAlreadyExistsException;
import com.ambitoscala.tarantula.exception.TarantulaNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
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
    public void addTarantula(final TarantulaDocument tarantulaDocument) {
        validateTarantula(tarantulaDocument.getName());
        this.tarantulaRepository.save(tarantulaDocument);
    }

    private void validateTarantula(final String name) {
        final Optional<TarantulaDocument> optionalTarantulaDocument = this.tarantulaRepository.findTarantulaByName(name);
        optionalTarantulaDocument.ifPresent(tarantulaEntity -> {
            throw new TarantulaAlreadyExistsException(this.messageSourceService.getMessage("tarantula.already.exists.exception", name));
        });
    }

    public List<TarantulaDocument> findTarantulas(final String name) {
        final List<TarantulaDocument> tarantulas = this.tarantulaRepository.findByNameIgnoreCaseRegex(name);
        if (CollectionUtils.isEmpty(tarantulas)) {
            throw new NoContentException();
        }
        return tarantulas;
    }

    public void addMolt(final ObjectId objectId) {
        validateMoltPeriod(objectId);
        final TarantulaDocument tarantulaDocument = this.tarantulaRepository.findById(objectId).orElseThrow(TarantulaNotFoundException::new);
        final MoltDocument moltDocument = MoltDocument.ofNewMolt();
        tarantulaDocument.addMolt(moltDocument);
        this.tarantulaRepository.save(tarantulaDocument);
    }

    private void validateMoltPeriod(final ObjectId objectId) {
        final LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        final Optional<TarantulaDocument> optionalTarantulaDocument = this.tarantulaRepository.findByIdAndMoltsMoltedAtGreaterThan(objectId, sixMonthsAgo);
        optionalTarantulaDocument.ifPresent(tarantulaEntity -> {
            throw new MoltsDontHappenThatFastException();
        });
    }

}
