package com.idbrasil.processoseletivo.number.enums;

import java.util.Arrays;
import java.util.List;

public enum Roman {
    I("I",1),
    V("V",5),
    X("X",10),
    L("L",50),
    C("C",100),
    D("D",500),
    M("M",1000);

    private String roman;

    private Integer indoArabicos;

    public static List<Roman> getEnumValues(){
        return Arrays.asList(M,D,C,L,X,V,I);
    }

    public String getRoman() {
        return roman;
    }

    public Integer getIndoArabicos() {
        return indoArabicos;
    }

    Roman(String roman, Integer indoArabicos) {
        this.roman = roman;
        this.indoArabicos = indoArabicos;
    }
}
