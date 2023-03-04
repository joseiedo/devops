package br.com.cwi.sportivity.service.core;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NowService {

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}
