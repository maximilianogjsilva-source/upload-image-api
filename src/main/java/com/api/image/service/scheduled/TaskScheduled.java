package com.api.image.service.scheduled;

import com.api.image.service.ImageStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class TaskScheduled {

    @Autowired
    private ImageStorageService imageStorageService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 15000)
    public void reportCurrentTime(){

        this.imageStorageService.getAll().forEach((image)->{
            log.info("Fecha de expiracion es {}", image.exp());
        });

        log.info("The time is now {}", dateFormat.format(new Date()));
    }

}
