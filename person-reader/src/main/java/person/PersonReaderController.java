package person;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class PersonReaderController {

    @Resource
    PersonRegistry registry;

    @GetMapping("/list")
    @ResponseBody
    public List<String> listPersons() {
        return registry.getPersons();
    }

}
