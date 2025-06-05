package com.FireSentinel.Fire.Sentinel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FocoRisco {
    private Foco foco;
    private String nivelRisco; // "Alto", "Médio", "Baixo"
}
