package com.FireSentinel.Fire.Sentinel.controller;

import com.FireSentinel.Fire.Sentinel.model.Foco;
import com.FireSentinel.Fire.Sentinel.model.FocoRisco;
import com.FireSentinel.Fire.Sentinel.model.ResumoDashboard;
import com.FireSentinel.Fire.Sentinel.service.FocoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focos")
@RequiredArgsConstructor
public class FocoController {

    private final FocoService focoService;

    @GetMapping
    public List<Foco> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        return focoService.getFocosPaginados(page, size);
    }

    @GetMapping("/estado/{estado}")
    public List<Foco> porEstado(
            @PathVariable String estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        return focoService.getFocosPorEstado(estado, page, size);
    }

    @GetMapping("/municipio/{municipio}")
    public List<Foco> porMunicipio(
            @PathVariable String municipio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        return focoService.getFocosPorMunicipio(municipio, page, size);
    }

    @GetMapping("/risco")
    public List<FocoRisco> classificarRiscos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        return focoService.getFocosClassificadosPorRisco(page, size);
    }

    @GetMapping("/resumo")
    public ResumoDashboard getResumo() {
        return focoService.getResumo();
    }
}
