package com.example.desafiocnab.rest;

import com.example.desafiocnab.helper.ResponseTransactions;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CnabService {

    void readFile(MultipartFile file) throws IOException;

    ResponseTransactions getTransactionsByStore(String store);
}
