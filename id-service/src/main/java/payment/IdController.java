package payment;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IdController {

    private AtomicLong sequence = new AtomicLong(System.currentTimeMillis());

    @PostMapping("/generate-id")
    @ResponseBody
    public Long generateId() {
        return sequence.incrementAndGet();
    }

}
