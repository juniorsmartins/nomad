package com.nomad.accounting.config.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO) // Usar o modo de serialização VIA_DTO no Spring Data Web é uma maneira de garantir que as respostas de APIs paginadas tenham uma estrutura JSON previsível e estável.
public class WebConfig { }

