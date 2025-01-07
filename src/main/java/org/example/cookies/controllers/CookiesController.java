package org.example.cookies.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.cookies.entities.Counter;
import org.example.cookies.entities.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CookiesController {
    @GetMapping("/")
    public String handleRequest(HttpSession session, Model model) {
        Counter counter = (Counter)
                session.getAttribute("counter");
        if (counter == null) {
            counter = new Counter();
        }
        counter.increment();
        session.setAttribute("counter", counter);
        model.addAttribute("count", counter.getCount());
        return "counter";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario() {
        return "formulario";
    }

    @PostMapping("/guardar-nombre")
    public String guardarNombre(String name, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario.setNombre(name);
        session.setAttribute("usuario", usuario);
        return "redirect:/mostrar-nombre";
    }

    @GetMapping("/mostrar-nombre")
    public String mostrarNombre(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null && usuario.getNombre() != null) {
            model.addAttribute("nombre", usuario.getNombre());
            return "mostrar-nombre";
        } else {
            return "redirect:/formulario";
        }
    }

}
