package com.idbrasil.processoseletivo.number.api;

import com.idbrasil.processoseletivo.number.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/numeros")
public class NumberAPI {

    @Autowired
    private NumberService numberService;


    @GetMapping(value = "/convertIndoArabico/{indoArabico}")
    public String convertIndoArabico(@PathVariable("indoArabico") Integer indoArabico){
        if(indoArabico >= 4000) return "informe um valor abaixo de 4000";
        return numberService.convertIndoArabico(indoArabico);
    }

    @GetMapping(value = "/convertRoman/{roman}")
    public String convertRoman(@PathVariable("roman") String roman){
        return numberService.convertRoman(roman);
    }
}
