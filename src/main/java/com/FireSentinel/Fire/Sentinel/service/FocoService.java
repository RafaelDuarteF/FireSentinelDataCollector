package com.FireSentinel.Fire.Sentinel.service;

import com.FireSentinel.Fire.Sentinel.model.Foco;
import com.FireSentinel.Fire.Sentinel.model.FocoRisco;
import com.FireSentinel.Fire.Sentinel.model.ResumoDashboard;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FocoService {

    private final List<Foco> focos = new ArrayList<>();

    @PostConstruct
    @SneakyThrows
    public void carregarDados() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/focos_ams_ref_2024.csv"))) {
            List<String[]> linhas = reader.readAll();
            linhas.remove(0); // remove header

            for (String[] linha : linhas) {
                Foco foco = new Foco(
                        linha[0],
                        linha[1],
                        Double.parseDouble(linha[2]),
                        Double.parseDouble(linha[3]),
                        linha[4],
                        linha[5],
                        linha[6],
                        linha[7],
                        linha[8]
                );
                focos.add(foco);
            }
        }
    }

    private <T> List<T> paginar(List<T> lista, int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, lista.size());
        if (start >= lista.size()) {
            return Collections.emptyList();
        }
        return lista.subList(start, end);
    }

    public List<Foco> getFocosPaginados(int page, int size) {
        return paginar(focos, page, size);
    }

    public List<Foco> getFocosPorEstado(String estado, int page, int size) {
        List<Foco> filtrados = focos.stream()
                .filter(f -> f.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
        return paginar(filtrados, page, size);
    }

    public List<Foco> getFocosPorMunicipio(String municipio, int page, int size) {
        List<Foco> filtrados = focos.stream()
                .filter(f -> f.getMunicipio().equalsIgnoreCase(municipio))
                .collect(Collectors.toList());
        return paginar(filtrados, page, size);
    }

    public List<FocoRisco> getFocosClassificadosPorRisco(int page, int size) {
        Random random = new Random();

        List<FocoRisco> classificados = focos.stream().map(foco -> {
            int tempSimulada = 35 + random.nextInt(15); // 35°C a 50°C
            String risco = tempSimulada > 44 ? "Alto"
                    : tempSimulada > 38 ? "Médio"
                    : "Baixo";
            return new FocoRisco(foco, risco);
        }).collect(Collectors.toList());

        return paginar(classificados, page, size);
    }

    public ResumoDashboard getResumo() {
        long altoRisco = getFocosClassificadosPorRisco(0, focos.size()).stream()
                .filter(f -> f.getNivelRisco().equals("Alto"))
                .count();

        return new ResumoDashboard(
                focos.size(),
                focos.stream().map(Foco::getEstado).distinct().count(),
                focos.stream().map(Foco::getMunicipio).distinct().count(),
                altoRisco,
                focos.size(),
                0.93
        );
    }
}
