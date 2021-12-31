package com.example.desafiocnab.routine;

import com.example.desafiocnab.enums.TipoTransacao;
import com.example.desafiocnab.model.Cnab;
import com.example.desafiocnab.model.UploadedCnab;
import com.example.desafiocnab.repository.CnabRepository;
import com.example.desafiocnab.repository.UploadCnabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CnabSchedule {

    private final CnabRepository cnabRepository;

    private final UploadCnabRepository uploadCnabRepository;

    @Autowired
    CnabSchedule(CnabRepository cnabRepository, UploadCnabRepository uploadCnabRepository) {
        this.cnabRepository = cnabRepository;
        this.uploadCnabRepository = uploadCnabRepository;
    }

    @Scheduled(fixedDelayString = "PT5M")
    public void ProcessCnabContent() {
        List<UploadedCnab> cnabToProcess = this.uploadCnabRepository.findAllByProcessed(false);
        List<Cnab> cnabProcessed = new ArrayList<>();

        if (cnabToProcess.size() > 0) {
            cnabToProcess.forEach(element -> {
                Cnab cnab = new Cnab();
                String[] content = element.getContent().split("");

                TipoTransacao tipoTransacao = TipoTransacao.getByType(Long.parseLong(content[0]));
                String date = this.getFromContent(content, 1, 8);
                String valor = this.getFromContent(content, 9, 18);
                String cpf = this.getFromContent(content, 19, 29);
                String cartao = this.getFromContent(content, 30, 41);
                String horaTransacao = this.getFromContent(content, 42, 47);
                String donoLoja = this.getFromContent(content, 48, 61);
                String nomeLoja = this.getFromContent(content, 62, 79);

                cnab.setTipoTransacao(tipoTransacao);
                cnab.setData(new Date(this.formatDate(date)));
                cnab.setValor(Double.valueOf(valor));
                cnab.setCpf(cpf);
                cnab.setCartao(cartao);
                cnab.setHoraTranferencia(this.formatHour(horaTransacao));
                cnab.setDonoLoja(donoLoja.trim());
                cnab.setNomeLoja(nomeLoja.trim());

                cnabProcessed.add(cnab);
                element.setProcessed(true);
            });

            this.cnabRepository.saveAll(cnabProcessed);
            this.uploadCnabRepository.saveAll(cnabToProcess);
        }

    }


    private String getFromContent(String[] content, int init, int end) {
        StringBuilder object = new StringBuilder();

        for (int x = init; x <= end; x++) {
            object.append(content[x]);
        }

        return object.toString();
    }

    private String formatHour(String horaTransacao) {
        StringBuilder formatted = new StringBuilder();
        String[] hour = horaTransacao.split("");

        formatted.append(hour[0])
                .append(hour[1])
                .append(":")
                .append(hour[2])
                .append(hour[3])
                .append(":")
                .append(hour[4])
                .append(hour[5]);

        return formatted.toString();
    }

    private String formatDate(String date) {
        StringBuilder formatted = new StringBuilder();
        String[] hour = date.split("");

        formatted.append(hour[0])
                .append(hour[1])
                .append(hour[2])
                .append(hour[3])
                .append("/")
                .append(hour[4])
                .append(hour[5])
                .append("/")
                .append(hour[6])
                .append(hour[7]);

        return formatted.toString();
    }
}
