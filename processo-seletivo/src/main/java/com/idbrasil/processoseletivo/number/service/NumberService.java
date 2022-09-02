package com.idbrasil.processoseletivo.number.service;

import com.idbrasil.processoseletivo.Counter.service.CounterService;
import com.idbrasil.processoseletivo.number.enums.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NumberService {
    @Autowired
    CounterService counterService;

    private Integer indoArabicoRemaining;
    private String romanReturn;
    private Integer indoArabicoReturn;

    public String convertIndoArabico(Integer indoArabicoValue){
        this.romanReturn = new String();
        this.indoArabicoRemaining = indoArabicoValue;

        while(indoArabicoRemaining > 0){
            this.compareValues();
        }

        this.counterService.incrementIndoArabico();
        return romanReturn + this.counterService.getIndoArabicoManyTimesExecuted();
    }

    public String convertRoman(String romans) {
        romans = romans.toUpperCase();
         return this.verifyRoman(romans) ? this.assignIndoArabico(romans) + this.counterService.getRomanManyTimesExecuted()
                                         : "algum numero informado, não condiz com um numero romano";
    }

    public Boolean verifyRoman(String romans){
        Boolean result=true;
        String allRomans =  Roman.getEnumValues().stream().map(value -> value.getRoman()).collect(Collectors.joining());
        for (char roman : romans.toCharArray()){
            if(!allRomans.contains(String.valueOf(roman))){
               result = false;
               break;
            }
        }

        this.counterService.incrementRoman();
        return result;
    }

    public String assignIndoArabico(String roman){
        this.indoArabicoReturn=0;

        for(int count=0; count < roman.length(); count++){
            if((count+2) <= roman.length()){
                String actualRoman = String.valueOf(roman.toCharArray()[count]);
                String nextRoman = String.valueOf(roman.toCharArray()[count+1]);

                if(this.validateActualAndNextRoman(count, roman, actualRoman, nextRoman)){
                    this.indoArabicoReturn += Roman.valueOf(nextRoman).getIndoArabicos() - Roman.valueOf(actualRoman).getIndoArabicos();
                    if((count + 2) >= roman.length()) break;
                    count += 1;

                }else{
                    this.indoArabicoReturn += Roman.valueOf(String.valueOf(roman.toCharArray()[count])).getIndoArabicos();
                }
            }else{
                this.indoArabicoReturn += Roman.valueOf(String.valueOf(roman.toCharArray()[count])).getIndoArabicos();
            }
        }

        return String.valueOf(this.indoArabicoReturn);
    }

    public boolean validateActualAndNextRoman(int count, String roman, String actualRoman, String nextRoman){
       Integer actualIndoArabico = Roman.valueOf(actualRoman).getIndoArabicos();
       Integer nextIndoArabico = Roman.valueOf(nextRoman).getIndoArabicos();
       return  (actualIndoArabico < nextIndoArabico);
    }

    public void compareValues(){
        for(Roman roman : Roman.getEnumValues()){
            Roman lowerRoman = this.getLowerRomanByGroup(roman.getRoman());
            Integer differentLowerRoman = roman.getIndoArabicos() - (Objects.isNull(lowerRoman) ? 0 : lowerRoman.getIndoArabicos());
            
            if(this.indoArabicoRemaining >= roman.getIndoArabicos()){
                this.setValueIfBiggerCurrentRoman(roman);
                break;
            }else if(this.indoArabicoRemaining >= differentLowerRoman){
                this.setValuesIfBiggerDifferentLowerRoman(roman, lowerRoman);
                break;
            }
        }
    }

    public void setValueIfBiggerCurrentRoman(Roman roman){
        this.indoArabicoRemaining -= roman.getIndoArabicos();
        this.romanReturn = this.romanReturn + roman.getRoman();
    }

    public void setValuesIfBiggerDifferentLowerRoman(Roman roman, Roman lowerRoman){
        this.indoArabicoRemaining -= roman.getIndoArabicos() - lowerRoman.getIndoArabicos();
        this.romanReturn = this.romanReturn + lowerRoman.getRoman() + roman.getRoman();
    }

    public Roman getLowerRomanByGroup(String currentRoman){
        switch(currentRoman){
            case "V":
            case "X":
                return Roman.I;
            case "L":
            case "C":
                return Roman.X;
            case "D":
            case "M":
                return Roman.C;
            default:
                return null;
        }
    }
}