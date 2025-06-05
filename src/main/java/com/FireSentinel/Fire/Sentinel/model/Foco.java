package com.FireSentinel.Fire.Sentinel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foco {
    private String idBdq;
    private String focoId;
    private double lat;
    private double lon;
    private String dataPas;
    private String pais;
    private String estado;
    private String municipio;
    private String bioma;
}
