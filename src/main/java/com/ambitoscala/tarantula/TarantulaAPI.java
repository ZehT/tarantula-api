package com.ambitoscala.tarantula;

import com.ambitoscala.config.BasicResponse;
import com.ambitoscala.tarantula.domain.TarantulaDTO;
import com.ambitoscala.tarantula.domain.TarantulaRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation(value = "Find All Tarantulas", nickname = "getTarantulas", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarantulas found"),
            @ApiResponse(code = 204, message = "There are no Tarantulas with given name"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<TarantulaDTO>> getTarantulas(@ApiParam(value = "Tarantula's name") @RequestParam final String name);

    @ApiOperation(value = "Add Molt", nickname = "addMolt", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Molt added"),
            @ApiResponse(code = 425, message = "Molts does not happen that fast"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping(value = "/tarantulas/{id}/molts/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<Void> addMolt(@ApiParam(value = "Tarantula Identifier") @PathVariable final String id);

}
