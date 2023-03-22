package com.example.pidev_finance.controllers;

import com.example.pidev_finance.entities.Event;
import com.example.pidev_finance.entities.ShareHolder;
import com.example.pidev_finance.services.ShareHolderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ShareHolder")

public class ShareHolderRestController {
    private ShareHolderService shareholderservice;

    @PostMapping("/add")
    ShareHolder addShareHolder(@RequestBody ShareHolder shareholder) {
        return shareholderservice.AddShareHolder(shareholder);
    }

    @GetMapping("/all")
    List<ShareHolder> retrieveAllShareHolder() {

        return shareholderservice.retrieveAllShareHolder();
    }

    @GetMapping("/get/{id}")
    ShareHolder retrieveShareHolder(@PathVariable("id") Integer IdShareHolder) {
        return shareholderservice.retrieveShareHolder(IdShareHolder);
    }

    @DeleteMapping("/delete/{id}")
    void RemoveShareHolder(@PathVariable("id") Integer IdShareHolder) {
        shareholderservice.removeShareHolder(IdShareHolder);
    }

    @PutMapping("/update")
    ShareHolder updateShareHolder(@RequestBody ShareHolder shareHolder) {
        return shareholderservice.updateShareHolder(shareHolder);
    }

    @GetMapping("/assignshrtoevent/{idShareHolder}/{idEvent}")
    public ShareHolder assignshrtoevent(@PathVariable("idShareHolder") Integer idShareHolder, @PathVariable("idEvent") Integer idEvent) {
        return shareholderservice.assignShareHolderToEvent(idShareHolder, idEvent);
    }


    @GetMapping("/shareholders/most-participated")
    public ResponseEntity<?> getShareholdersParticipatedInMostEvents() {
        List<ShareHolder> shareholders = shareholderservice.findShareholdersWithMoreThanOneEvent();
        if (shareholders.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(shareholders);
        }
    }
}

