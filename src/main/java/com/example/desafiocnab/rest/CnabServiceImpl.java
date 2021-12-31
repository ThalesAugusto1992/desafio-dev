package com.example.desafiocnab.rest;

import com.example.desafiocnab.helper.ResponseTransactions;
import com.example.desafiocnab.model.Cnab;
import com.example.desafiocnab.model.UploadedCnab;
import com.example.desafiocnab.repository.CnabRepository;
import com.example.desafiocnab.repository.UploadCnabRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CnabServiceImpl implements CnabService {

    private final UploadCnabRepository uploadCnabRepository;

    private final CnabRepository cnabRepository;

    @Autowired
    CnabServiceImpl(UploadCnabRepository uploadCnabRepository,
                    CnabRepository cnabRepository) {
        this.uploadCnabRepository = uploadCnabRepository;
        this.cnabRepository = cnabRepository;
    }

    @Override
    public void readFile(MultipartFile file) throws IOException {

        InputStream initialStream = file.getInputStream();
        byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);

        File targetFile = new File("src/main/resources/targetFile.tmp");

        try (OutputStream outStream = new FileOutputStream(targetFile)) {
            outStream.write(buffer);
        }

        List<UploadedCnab> listOfCnab = new ArrayList<>();

        LineIterator it = FileUtils.lineIterator(targetFile, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                UploadedCnab lineToSave = new UploadedCnab(line, file.getOriginalFilename(), false);
                listOfCnab.add(lineToSave);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        this.uploadCnabRepository.saveAll(listOfCnab);
    }

    @Override
    public ResponseTransactions getTransactionsByStore(String store) {
        List<Cnab> listOfCnab = this.cnabRepository.findAllByNomeLoja(store);
        ResponseTransactions responseTransactions = new ResponseTransactions();

        responseTransactions.setTransacoes(listOfCnab);

        Double saldoPosito = listOfCnab.stream()
                .filter(element -> element.getTipoTransacao().getNature().equals("in"))
                .mapToDouble(Cnab::getValor)
                .sum();

        Double saldoNegativo = listOfCnab.stream()
                .filter(element -> element.getTipoTransacao().getNature().equals("out"))
                .mapToDouble(Cnab::getValor)
                .sum();

        Double saldoTotal = saldoPosito - saldoNegativo;

        responseTransactions.setSaltoTotal(saldoTotal);
        return responseTransactions;
    }
}
