package com.ifba.email.dto;

import java.util.List;

import com.ifba.email.models.TipoMedalha;

public record PaisEmailDto(String nomePais, List<String> emails, String nomeAtleta, String descricao, String esporte, TipoMedalha tipoMedalha) {
}
