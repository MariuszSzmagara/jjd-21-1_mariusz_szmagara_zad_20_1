package pl.javastart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public String getAllUsers() {
        List<User> usersList = usersRepository.getAll();
        StringBuilder result = new StringBuilder();
        for (User user : usersList) {
            result.append(("Imię: ")).append(user.getFirstName())
                    .append((", Nazwisko: ")).append(user.getLastName())
                    .append(", wiek: ").append(user.getAge()).append("<br/>");
        }
        return result.toString();
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam(name = "imie", required = false) String firstName,
                          @RequestParam(name = "nazwisko") String lastName,
                          @RequestParam(name = "wiek") Integer age) {
        if (!isNameCorrect(firstName)) {
            return "redirect:/err.html";
        } else {
            usersRepository.add(new User(firstName, lastName, age));
            return  "redirect:/success.html";
        }
    }

    private boolean isNameCorrect(String name) {
        return name != null && !name.equals("");
    }
}
