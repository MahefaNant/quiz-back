package com.dev.quiz.controller.candidate;

import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Admin;
import com.dev.quiz.entity.Candidate;
import com.dev.quiz.service.AdminService;
import com.dev.quiz.service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    /*
        add new candidate
     */
    @PostMapping("/add")
    public ResponseEntity<?> addCandidate(@RequestBody Candidate candidate,
                                          @RequestParam String retapePassword) {
        try {
            Candidate saveCandidate = candidateService.add(candidate , retapePassword);
            return new ResponseEntity<>(saveCandidate, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        update candidate
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateCandidate(@RequestBody Candidate candidate ,
                                         @RequestParam String newPassword ,
                                         @RequestParam String retapePassword ) {
        try {
            Candidate savedcandidate = candidateService.update(candidate , newPassword, retapePassword).join();
            return new ResponseEntity<>(savedcandidate, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        method for sign-in "candidate"
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Candidate candidate) {
        CompletableFuture<MessageMod> message = candidateService.login(candidate);
        MessageMod mess = message.join();
        return new ResponseEntity<>(mess , HttpStatus.ACCEPTED);
    }
}
