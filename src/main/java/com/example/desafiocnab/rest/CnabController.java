package com.example.desafiocnab.rest;

import com.example.desafiocnab.helper.ResponseMessage;
import com.example.desafiocnab.helper.ResponseTransactions;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = CnabController._PATH)
public class CnabController {

    public final static String _PATH = "cnab";

    private final CnabService cnabService;

    @Autowired
    CnabController(CnabServiceImpl cnabService) {
        this.cnabService = cnabService;
    }


    @ApiOperation(value = "Upload do arquivo para leitura dos dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseMessage.class),
            @ApiResponse(code = 417, message = "Falha no Upload do arquivo", response = ResponseMessage.class),
    })
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> uploadCnabFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            this.cnabService.readFile(file);
            message = "Upload de arquivo feito com sucesso, dados serão processados em breve";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (IOException ioException) {
            message = "Falha ao fazer upload do arquivo";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @ApiOperation(value = "Buscar as transações por loja (O nome da loja deve estar completo)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseTransactions.class)
    })
    @GetMapping(value = "/transacoes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTransactions> getTransactionsByStore(@RequestParam("store") String store) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cnabService.getTransactionsByStore(store));
    }

}
