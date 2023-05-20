package net.codetojoy.simple.service;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.codetojoy.simple.entity.PingResult;

@RestController
@RequestMapping("/simple")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PingService {

    @GetMapping("/ping")
    public ResponseEntity<PingResult> ping(@RequestParam(name="delay_in_seconds", required=false) Integer delayInSeconds) {
        if (delayInSeconds != null) {
            try {
                System.out.println("TRACER pathological delay: " + delayInSeconds + " sec");
                Thread.sleep(delayInSeconds * 1000);
            } catch (Exception ex) {
            }
        }

        String now = new Date().toString();
        String prefix = "TRACER ping " + now + " ";
        String message = now + " OK";

        PingResult result = new PingResult();
        result.setMessage(message);

        return new ResponseEntity<PingResult>(result, HttpStatus.OK);
    }
}
