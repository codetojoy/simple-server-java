package net.codetojoy.simple.service;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.codetojoy.simple.exceptions.InputException;
import net.codetojoy.simple.entity.Result;

@RestController
@RequestMapping("/simple")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountService {

    @GetMapping("/account")
    public ResponseEntity<Result> checkEnrollment(
                                    @RequestParam(name="id") int id,
                                    @RequestParam(name="name") String name,
                                    @RequestParam(name="delay_in_seconds", required=false) Integer delayInSeconds
                                    ) throws InputException {

        if (delayInSeconds != null) {
            try {
                System.out.println("TRACER pathological delay: " + delayInSeconds + " sec");
                Thread.sleep(delayInSeconds * 1000);
            } catch (Exception ex) {
            }
        }

        String now = new Date().toString();
        String prefix = "TRACER " + now + " ";
        System.out.println(prefix + "id: " + id + " name: " + name);
        boolean enrolled = (id % 2 == 0);
        String message = now + " OK";

        Result result = new Result();
        result.setEnrolled(enrolled);
        result.setId(id);
        result.setName(name);
        result.setMessage(message);

        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }
}
