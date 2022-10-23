package com.ambitoscala.tarantula;

import com.ambitoscala.commons.MessageSourceService;
import com.ambitoscala.config.BasicResponse;
import com.ambitoscala.tarantula.domain.TarantulaDTO;
import com.ambitoscala.tarantula.domain.TarantulaDocument;
import com.ambitoscala.tarantula.domain.TarantulaRequestDTO;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TarantulaController implements TarantulaAPI {

    private final TarantulaService tarantulaService;
    private final MessageSourceService messageSourceService;

    public TarantulaController(final TarantulaService tarantulaService, final MessageSourceService messageSourceService) {
        this.tarantulaService = tarantulaService;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public BasicResponse<Void> addTarantulas(final TarantulaRequestDTO tarantulaRequest) {
        this.tarantulaService.addTarantula(TarantulaDocument.of(tarantulaRequest));
        return BasicResponse.ok(this.messageSourceService.getMessage("tarantula.added"));
    }

    @Override
    public BasicResponse<List<TarantulaDTO>> getTarantulas(final String name) {
        final List<TarantulaDocument> tarantulasDocument = this.tarantulaService.findTarantulas(name);
        final List<TarantulaDTO> tarantulas = tarantulasDocument.stream()
                .map(TarantulaDTO::ofTarantulaDocument)
                .collect(Collectors.toList());
        return BasicResponse.withData(tarantulas);
    }

    @Override
    public BasicResponse<Void> addMolt(final String id) {
        this.tarantulaService.addMolt(new ObjectId(id));
        return BasicResponse.ok(this.messageSourceService.getMessage("molt.added"));
    }

}
