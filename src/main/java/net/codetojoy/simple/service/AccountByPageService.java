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

    private String now() {
        return new Date().toString();
    }

    private void log(String msg) {
        System.out.println("TRACER " + now() + " : " + msg);
    }

    @GetMapping("/page")
    public ResponseEntity<List<Result>> fetchByPage(
                                    @RequestParam(name="page_num") int pageNum,
                                    @RequestParam(name="page_size") int pageSize,
                                    @RequestParam(name="meta_max_size") int maxSize,
                                    @RequestParam(name="meta_delay_in_seconds", required=false) Integer delayInSeconds
                                    ) throws InputException {

        if (delayInSeconds != null) {
            try {
                log("pathological delay: " + delayInSeconds + " sec");
                Thread.sleep(delayInSeconds * 1000);
            } catch (Exception ex) {
            }
        }

        int start = (pageNum * pageSize) + 1;

        // guard
        if (start >= maxSize) {
            log("empty! start: " + start + " maxSize: " + maxSize);
            return new ResponseEntity<List<Result>>(List.of(), HttpStatus.OK);
        }

        log("pageNum: " + pageNum + " pageSize: " + pageSize + " start: " + start);

        List<Result> results = new ArrayList<>();
        boolean isDone = false;
        int count = 0;

        while (!isDone) {
            int id = start + count; 
            boolean enrolled = true;
            String name = "name-" + id;
            String message = now() + " OK [" + id + "]";

            Result result = new Result();
            result.setId(id);
            result.setEnrolled(enrolled);
            result.setName(name);
            result.setMessage(message);

            results.add(result);
    
            count++;
            isDone = (count >= pageSize) || (id == maxSize);
        }

        return new ResponseEntity<List<Result>>(results, HttpStatus.OK);
    }
}
