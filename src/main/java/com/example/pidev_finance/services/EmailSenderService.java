package com.example.pidev_finance.services;

import com.example.pidev_finance.entities.Event;
import com.example.pidev_finance.entities.ShareHolder;
import com.example.pidev_finance.repositories.IShareholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendSimpleEmail(String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("samar.saidana@esprit.tn");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }
    @Autowired
    private IShareholderRepository shareHolderRepository;

    public void sendEmailsForEvent(Event event) {
        // Get all ShareHolders associated with the given event
        List<ShareHolder> shareHolders = shareHolderRepository.findByEventId(event.getIdEvent());

        // Loop through each ShareHolder and send an email to them
        for (ShareHolder shareHolder : shareHolders) {
            String toEmail = shareHolder.getEmail();
            String subject = "Reminder: " + event.getNameEvent() + " event is happening tomorrow!";
            String body = "Dear " + shareHolder.getFirstNameShareholder() + ",\n\n" +
                    "We wanted to remind you that the " + event.getNameEvent() + " event is happening tomorrow at " +
                    event.getDateEvent() + ". We hope to see you there!\n\n" +
                    "Best regards,\n" +
                    "The Microfinance team";
            sendSimpleEmail(toEmail, body, subject);
        }
    }


}


