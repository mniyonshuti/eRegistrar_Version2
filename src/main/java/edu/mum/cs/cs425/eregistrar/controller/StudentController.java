package edu.mum.cs.cs425.eregistrar.controller;

import edu.mum.cs.cs425.eregistrar.model.Student;
import edu.mum.cs.cs425.eregistrar.servirce.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = {"/eregistrar/students/list"})
    public ModelAndView listStudents(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageCurrent", pageno);
        modelAndView.addObject("flashback", "/eregistrar/students/list");
        modelAndView.addObject("students", studentService.getAllStudents(pageno));
        modelAndView.setViewName("students/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eregistrar/students/new"})
    public String displayNewBookForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/new";
    }

    @PostMapping(value = "/eregistrar/students/new")
    public String addNewStudent( @Valid @ModelAttribute("student") Student student, BindingResult bindingResult,
    Model model){
            if(bindingResult.hasErrors()){
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "students/new";
            }
            student = studentService.saveStudent(student);
            return "redirect:/eregistrar/students/list";
    }

    @GetMapping(value = {"/eregistrar/students/edit/{studentId}"})
    public String editStudent(@PathVariable Integer studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            model.addAttribute("student", student);
            return "students/edit";
        }
        return "students/list";
    }

    @GetMapping(value = {"/eregistrar/students/delete/{studentId}"})
    public String deleteStudent(@PathVariable Integer studentId, Model model) {
        studentService.deleteStudentByID(studentId);
        return "redirect:/eregistrar/students/list";
    }

    @GetMapping(value = {"/eregistrar/students/search"})
    public ModelAndView searchStudents(@RequestParam ("searchStudent") String searchStudent) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.searchStudent(searchStudent));
        modelAndView.setViewName("students/search");
        return modelAndView;
    }

}
