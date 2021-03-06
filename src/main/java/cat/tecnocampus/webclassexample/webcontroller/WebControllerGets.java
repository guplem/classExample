package cat.tecnocampus.webclassexample.webcontroller;

import cat.tecnocampus.webclassexample.repositories.NoteLabDAO;
import domain.NoteLab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class WebControllerGets {
    NoteLabDAO noteLabDAO;

    public WebControllerGets(NoteLabDAO noteLabDAO) {
        this.noteLabDAO = noteLabDAO;
    }

    @GetMapping("welcome")
    public String welcome(Model model) {
        model.addAttribute("message", "We are the best students in the world");

        return "hola";
    }

    @GetMapping("welcomeParam")
    public String welcomeParam(Model model, @RequestParam String message) {
        model.addAttribute("message", message);
        return "hola";
    }

    @GetMapping("welcomePath/{message}")
    public String welcomePathVariable(Model model, @PathVariable String message) {
        model.addAttribute("message", message);
        return "hola";
    }

    @GetMapping("getNote/{id}")
    public String getNote(Model model, @PathVariable int id) {
        model.addAttribute("note", noteLabDAO.getNoteById(id));

        return "getNote";
    }

    @GetMapping("getAllNotes")
    public String getAllNotes(Model model) {
        model.addAttribute("noteLabList", noteLabDAO.getAllNotes());
        return "getNoteS";
    }

    @GetMapping("getNotes/{userId}")
    public String getNotesUser(Model model, Principal principal) {
        System.out.println("Principal: " + principal.getName());
        model.addAttribute("noteLabList", noteLabDAO.getAllNotes());
        return "getNoteS";
    }

    //most implicit: hard to understand and maintain
    // the view is the same as the mapping (getNoteS)
    // the returned value is automatically added to model with attribute name build from the returned type: noteLabList
    @GetMapping("getNoteS")
    public List<NoteLab> getNoteS() {
        return noteLabDAO.getAllNotes();
    }
}
