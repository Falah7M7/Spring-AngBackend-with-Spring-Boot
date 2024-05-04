package net.yassine.emsidemospringang;

import net.yassine.emsidemospringang.entities.Payment;
import net.yassine.emsidemospringang.entities.PaymentStatus;
import net.yassine.emsidemospringang.entities.PaymentType;
import net.yassine.emsidemospringang.entities.Student;
import net.yassine.emsidemospringang.repository.PaymentRepository;
import net.yassine.emsidemospringang.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class EmsiDemoSpringAngApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsiDemoSpringAngApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
      return args -> {
          studentRepository.save(Student.builder().id(UUID.randomUUID().toString())

                          .firstName("Mohamed").code("12988").programId("SDIA")
                  .build());
          studentRepository.save(Student.builder().id(UUID.randomUUID().toString())

                  .firstName("HAMZA").code("22025").programId("SDIA")
                  .build());
          studentRepository.save(Student.builder().id(UUID.randomUUID().toString())

                  .firstName("Yassine").code("12288").programId("SSI")
                  .build());
          studentRepository.save(Student.builder().id(UUID.randomUUID().toString())

                  .firstName("Meryem").code("15885").programId("MIAGE")
                  .build());


          PaymentType[] paymentTypes = PaymentType.values();
          Random random = new Random();
          studentRepository.findAll().forEach(st->{
               for (int i = 0 ; i< 40 ; i++){
                   int index = random.nextInt(paymentTypes.length);
                   Payment payment = Payment.builder()
                           .amount(1000+(int)(Math.random()+20000))
                           .type(paymentTypes[index])
                           .status(PaymentStatus.CREATED)
                           .date(LocalDate.now())
                           .student(st)
                           .build();

                   paymentRepository.save(payment);
               }

          });

      };

    }

}
