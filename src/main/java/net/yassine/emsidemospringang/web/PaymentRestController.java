package net.yassine.emsidemospringang.web;

import net.yassine.emsidemospringang.entities.Payment;
import net.yassine.emsidemospringang.entities.PaymentStatus;
import net.yassine.emsidemospringang.entities.PaymentType;
import net.yassine.emsidemospringang.entities.Student;
import net.yassine.emsidemospringang.repository.PaymentRepository;
import net.yassine.emsidemospringang.repository.StudentRepository;
import net.yassine.emsidemospringang.services.PaymentService;
import org.apache.commons.lang3.SystemProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/payments")
    public List<Payment> allPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/ByStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/ByType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type) {
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }

    @GetMapping("/students")
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code) {
        return studentRepository.findByCode(code);
    }

    @GetMapping("/studentsByProgramId")
    public List<Student> getStudentsByProgramId(@RequestParam String programId) {
        return studentRepository.findByProgramId(programId);
    }
    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long id) {
       return paymentService.updatePaymentStatus(status,id);

    }
    @PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount ,
                               PaymentType type, String studentCode) throws IOException {
        return this.paymentService.savePayment(file,date,amount,type,studentCode);

    }
    @GetMapping(path = "/paymentFile/{paymentId}")
    public byte[] getPaymentFile( @PathVariable Long paymentId) throws IOException {
       return paymentService.getPaymentFile(paymentId);
    }
}
