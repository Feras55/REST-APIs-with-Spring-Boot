package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class StudentController {

    @GetMapping("/student")
    public Student getStudent(){
        Student student = new Student(1,"Feras","Jamal");
        return student;
    }

    @GetMapping("/students")
    public ArrayList<Student>getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1,"Ahmed","kamal"));
        students.add(new Student(2,"Sameer","kamal"));
        students.add(new Student(3,"Tuna","kamal"));
        students.add(new Student(4,"Tuna","Fish"));

        return students;
    }

    //spring boot REST API with path variable
    @GetMapping("students/{id}/{first-name}/{last-name}") //{id} = uri template variable
    public Student studentPathVariable(@PathVariable("id") int id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName)
    { //@PathVariable used on method argument to bind it to the value of a URI template variable.
        return new Student(id, firstName, lastName);
    }


    //spring boot REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Rami&lastName=Essam

    @GetMapping("/students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id,firstName,lastName);
    }

}

