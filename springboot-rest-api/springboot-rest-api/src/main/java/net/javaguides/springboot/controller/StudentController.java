package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){ //Response Entity represents the whole HTTP response, we can configure the HTTP response
        Student student = new Student(1,"Feras","Jamal");
      //  return new ResponseEntity<>(student,HttpStatus.OK); //this is one way
      //  return ResponseEntity.ok(student); //this another
        //we could custom configure the response header and body

        return ResponseEntity.ok()
                .header("Custom Header","Custom Header Value")
                .body(student);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Student>>getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1,"Ahmed","kamal"));
        students.add(new Student(2,"Sameer","kamal"));
        students.add(new Student(3,"Tuna","kamal"));
        students.add(new Student(4,"Tuna","Fish"));

        return ResponseEntity.ok(students);
    }

    //spring boot REST API with path variable
    @GetMapping("/{id}/{first-name}/{last-name}") //{id} = uri template variable
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName)
    { //@PathVariable used on method argument to bind it to the value of a URI template variable.
        Student student =  new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }


    //spring boot REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Rami&lastName=Essam

    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
       Student student = new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring Boot REST API that handles HTTP POST Request
     //@PostMapping @RequestBody, @ResponseStatus
    //When client sends a Post Request, client have to send data in the request body
    //Spring API extracts the JSON object from the HTTP request and convert into java object
    @PostMapping ("/create")//to map the HTTP POST request to this method
    //@ResponseStatus(HttpStatus.CREATED) //to send "201 Created" status in the response, could be also done using ReponseEntity
    public ResponseEntity<Student> createStudent(@RequestBody Student student){ //@RequestBody is responsible to retrieve a JSON object from the request and convert it into Java object
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student First Name: " + student.getFirstName());
        System.out.println("Student Last Name: " + student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    //Spring Boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable ("id") int studentId){
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());
        return ResponseEntity.ok(student);
    }

    //Spring boot REST API that handles HTTP DELETE Request - deleting existing resource
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentID){
        System.out.println("Student deleted Successfully");
        return ResponseEntity.ok("Student deleted Successfully");
    }
}

