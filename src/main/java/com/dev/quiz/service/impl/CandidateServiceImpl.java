package com.dev.quiz.service.impl;

import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Admin;
import com.dev.quiz.entity.Candidate;
import com.dev.quiz.repository.CandidateRepo;
import com.dev.quiz.service.CandidateService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepo candidateRepo;

    public CandidateServiceImpl(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    @Override
    public Candidate add(Candidate candidate, String retapePassword) {
        try{
            if(candidate.getMail() == null || candidate.getMail().trim().isEmpty() ||
                    (candidate.getPassword() == null || candidate.getPassword().trim().isEmpty()) ) {
                throw new Exception("formulaire obligatoire");
            }

            if(!candidate.getPassword().equals(retapePassword)) {
                throw new Exception("not the same password !");
            }
            candidate.setPasswordEncode(candidate.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            return candidateRepo.save(candidate);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save candidate or existing mail", e);
        }
    }

    @Override
    public CompletableFuture<Candidate> update(Candidate candidate, String newPassword, String retapePassword) {
        try {
            BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
            if(!newPassword.equals(retapePassword)) throw new Exception("Mots de passe non Identique !");
            Candidate oldCandidate = candidateRepo.findFirstByMailContainingIgnoreCase(candidate.getMail()).orElseThrow(() -> new Exception("Compte inexistant!!!"));
            if(!crypt.matches(candidate.getPassword() , oldCandidate.getPassword())) throw new Exception("mots de passe Invalide!");
            if(!candidate.getMail().equalsIgnoreCase(oldCandidate.getMail()))
                throw new Exception("Vous ne pouvez pas changer votre mail !");
            oldCandidate.setPasswordEncode(retapePassword);
            return CompletableFuture.completedFuture(candidateRepo.save(oldCandidate));
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CompletableFuture<MessageMod> login(Candidate candidate) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Candidate oldCandidate = candidateRepo.findFirstBy().orElseThrow(() -> new Exception("Candidate not found"));

            MessageMod messageMod = new MessageMod();
            messageMod.num = 1;
            if( !passwordEncoder.matches(candidate.getPassword() , oldCandidate.getPassword()) || !oldCandidate.getMail().equalsIgnoreCase(candidate.getMail())) {
                messageMod.num = 0;
                messageMod.message = "mail ou mots de passe Invalide";
            }
            else messageMod.message = "Authentification avec succé";
            return CompletableFuture.completedFuture(messageMod);
        } catch (Exception e) {
            MessageMod messageMod = new MessageMod();
            messageMod.num = 0;
            messageMod.message = "Server error ! Contact the Administrator";
            return CompletableFuture.completedFuture(messageMod);
        }
    }
}
