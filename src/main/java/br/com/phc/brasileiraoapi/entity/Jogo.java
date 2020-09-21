package br.com.phc.brasileiraoapi.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Jogo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "timeCasa")
	private Time timeCasa;
	
	@ManyToOne
	@JoinColumn(name = "timeVisitante")
	private Time timeVisitante;
	
	@Column(name = "gols_time_casa")
	private Integer golsTimeCasa;
	@Column(name = "gols_time_visitante")
	private Integer golsTimeVisitante;
	@Column(name = "publico_pagante")
	private Integer publicoPagante;
	@Column(name = "uuid", nullable = false)
	private String uuId;
	@Column(name = "data")
	private LocalDateTime data;
	@Column(name = "encerrado")
	private Boolean encerrado;
	@Column(name = "rodada")
    private Integer rodada;
}
