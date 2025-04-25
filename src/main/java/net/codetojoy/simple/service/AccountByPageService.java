package net.codetojoy.simple.service;

import java.util.*;
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
@RequestMapping("/account")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountByPageService {

    @GetMapping("/page")
    public ResponseEntity<List<Result>> fetchByPage(
                                    @RequestParam(name="pageNum") int pageNum,
                                    @RequestParam(name="pageSize") int pageSize,
                                    @RequestParam(name="delay_in_seconds", required=false) Integer delayInSeconds
                                    ) throws InputException {

        if (delayInSeconds != null) {
            try {
                System.out.println("TRACER pathological delay: " + delayInSeconds + " sec");
                Thread.sleep(delayInSeconds * 1000);
            } catch (Exception ex) {
            }
        }

        int start = (pageNum * pageSize) + 1;
        String now = new Date().toString();
        String prefix = "TRACER " + now + " ";
        System.out.println(prefix + "pageNum: " + pageNum 
                                  + " pageSize: " + pageSize
                                  + " start: " + start);

        List<Result> results = new ArrayList<>();
        boolean isDone = false;
        int count = 0;

        while (!isDone) {
            int id = start + count; 
            boolean enrolled = true;
            String name = "name-" + id;
            String message = now + " OK [" + id + "]";

            Result result = new Result();
            result.setId(id);
            result.setEnrolled(enrolled);
            result.setName(name);
            result.setMessage(message);

            results.add(result);
    
            count++;
            isDone = (count >= pageSize);
        }

        return new ResponseEntity<List<Result>>(results, HttpStatus.OK);
    }
}
