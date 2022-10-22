package com.ambitoscala.tarantula;

import com.ambitoscala.commons.MessageSourceService;
import com.ambitoscala.config.BasicResponse;
import com.ambitoscala.tarantula.domain.TarantulaEntity;
import com.ambitoscala.tarantula.domain.TarantulaRequestDTO;
import org.springframework.web.bind.annotation.RestController;

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
        this.tarantulaService.addTarantula(TarantulaEntity.of(tarantulaRequest));
        return BasicResponse.ok(this.messageSourceService.getMessage("tarantula.added"));
    }

}
