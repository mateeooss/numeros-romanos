package com.idbrasil.processoseletivo.Counter.service;

import com.idbrasil.processoseletivo.Counter.Counter;
import com.idbrasil.processoseletivo.Counter.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    @Autowired
    CounterRepository counterRepository;

    public String getRomanManyTimesExecuted(){
        Integer manyTimesExecuted = counterRepository.findAll().get(0).getCount_roman();
        return "\n\nessa api ja foi executada: "+ manyTimesExecuted;
    }

    public String getIndoArabicoManyTimesExecuted(){
        Integer manyTimesExecuted = counterRepository.findAll().get(0).getCount_arabico();
        return "\n\nessa api ja foi executada: "+ manyTimesExecuted;
    }

    private void checkCounter(){
        if(this.counterRepository.findAll().isEmpty()){
            Counter counter = new Counter(null,0,0);
            this.counterRepository.save(counter);
        }
    }

    public void incrementRoman() {
        this.checkCounter();
        Counter counter = this.counterRepository.findAll().get(0);
        counter.setCount_roman((counter.getCount_roman()+1));
        this.counterRepository.save(counter);
    }

    public void incrementIndoArabico() {
        this.checkCounter();
        Counter counter = this.counterRepository.findAll().get(0);
        counter.setCount_arabico((counter.getCount_arabico()+1));
        this.counterRepository.save(counter);
    }
}
