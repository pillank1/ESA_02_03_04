package ESA24.jms;

import ESA24.model.EmailNotification;
import ESA24.model.Logging;
import ESA24.repo.EmailNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailReceiver {

    @Autowired
    private EmailNotificationRepository emailNotificationRepository;

    @JmsListener(destination = "event", containerFactory = "myFactory")
    public void receive(Logging event) {
        String message = String.format(" action performed " + event.getEventType() + " with an object of type " + event.getEntity());

        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setAddress("admin");
        emailNotification.setContent(message);

        emailNotificationRepository.save(emailNotification);

        System.out.println("JMS_MESSAGE: New notification!!! JMS message: " + message);
    }
}
