package com.ambitoscala.tarantula;

import com.ambitoscala.config.BasicResponse;
import com.ambitoscala.tarantula.domain.TarantulaRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(value = "Tarantula Service", tags = "Tarantula Service")
public interface TarantulaAPI {

    @ApiOperation(value = "Add Tarantulas", nickname = "addTarantulas", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarantula added"),
            @ApiResponse(code = 409, message = "Tarantula with same name already exists"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping(value = "/tarantulas/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<Void> addTarantulas(@ApiParam(value = "Tarantula to add") @Valid @RequestBody final TarantulaRequestDTO tarantulaRequest);

}
