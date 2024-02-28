package ESA24.jms;

import ESA24.model.Logging;
import ESA24.repo.LoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventReceiver {

    @Autowired
    private LoggingRepository loggingRepository;

    @JmsListener(destination = "event", containerFactory = "myFactory")
    public void receive(Logging event) {
        loggingRepository.save(event);

        System.out.println("JMS_log: <" + event + ">");
    }
}
