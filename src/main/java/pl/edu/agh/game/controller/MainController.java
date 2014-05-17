package pl.edu.agh.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "startgame")
    public String startGame(HttpServletRequest request) {

        System.out.println(request.getParameter("algo1"));
        System.out.println(request.getParameter("algo2"));
        return "results";
    }

}