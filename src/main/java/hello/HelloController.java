package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        System.out.println("adding something here to fail the build");
        return "Greetings from Springboot..!!!";
    }
    
}
