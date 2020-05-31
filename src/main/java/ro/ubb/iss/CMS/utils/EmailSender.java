package ro.ubb.iss.CMS.utils;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailSender {
    public static final String ORIGIN_EMAIL = "rcie2615@scs.ubbcluj.ro";
    public static final String WELCOME_SUBJECT = "Welcome to CMS!";
    public static final String JOIN_SUBJECT = "Conference Invitation";
    public static final String PURCHASE_SUBJECT = "Your Purchase";
    public static final String REGISTER_LINK = "http://localhost:4200/register";
    public static final String LOGIN_LINK = "http://localhost:4200/login";
    public static final String TICKETS_MSG = "You have succesfully bought the ticket for\n";
    public static void send(String from, String to, String subj, String msg) {
        Email sender = new Email(from);
        Email recipient = new Email(to);
        Content content = new Content("text/plain", msg);
        Mail mail = new Mail(sender, subj, recipient, content);
        SendGrid sg = new SendGrid(System.getenv("sENDGRID_API_KEY"));
        Request request = new Request();
        try {
            System.out.println(sender);
            System.out.println(recipient);
            System.out.println(content);
            System.out.println(mail);
            System.out.println(sg);
            System.out.println(request);
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            System.out.println("Email successfully sent!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
