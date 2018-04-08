package payment;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IdService {
    private AtomicLong sequence = new AtomicLong();

    private RestTemplate template = new RestTemplate();

    @HystrixCommand(fallbackMethod = "fallbackIdGenerator")
    public Long generateId() {
        return template.postForObject("http://localhost:9001/generate-id", null, Long.class);
    }

    public Long fallbackIdGenerator() {
        return sequence.incrementAndGet();
    }
}