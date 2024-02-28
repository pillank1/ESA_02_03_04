package ESA24.aspect;

import ESA24.model.Logging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {

    private JmsTemplate jmsTemplate;

    private Topic topic;

    @Autowired
    public AspectLogging(JmsTemplate jmsTemplate) throws JMSException {
        this.jmsTemplate = jmsTemplate;

        this.topic = jmsTemplate.getConnectionFactory()
                .createConnection()
                .createSession()
                .createTopic("event");
    }

    @AfterReturning(value = "within(ESA24.service.*) && @annotation(Loggable)", returning = "returnValue")
    public void performLogging(JoinPoint joinPoint, Object returnValue) throws JMSException {
        String methodName = joinPoint.getSignature().getName();
        String[] packageSplit = joinPoint.getTarget().getClass().getName().split("\\.", 0);

        String entity = packageSplit[packageSplit.length - 1].split("Service")[0];
        String arguments = Arrays.toString(joinPoint.getArgs());

        String eventType = "";
        if (methodName.equals("create")) {
            eventType += "CREATE";
        } else if (methodName.equals("update")){
            eventType += "UPDATE";
        } else if (methodName.equals("delete")){
            eventType += "DELETE";
        }

        Logging logging = new Logging();
        logging.setEntity(entity);
        logging.setEventType(eventType);
        logging.setSubstance(arguments);

        jmsTemplate.convertAndSend(topic, logging);
    }

}
