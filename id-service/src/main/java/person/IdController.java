package person;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IdController {

    @PostMapping("/generate-id")
    @ResponseBody
    public int generateId() {
        return new Random().nextInt();
    }

}
