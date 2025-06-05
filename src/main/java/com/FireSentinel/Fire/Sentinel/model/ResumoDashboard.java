package com.FireSentinel.Fire.Sentinel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumoDashboard {
    private long totalFocos;
    private long totalEstados;
    private long totalMunicipios;
    private long areasAltoRisco;
    private long sensoresAtivos;
    private double precisaoIA;
}
