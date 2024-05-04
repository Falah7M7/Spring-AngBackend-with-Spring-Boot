package net.yassine.emsidemospringang.repository;

import net.yassine.emsidemospringang.entities.Payment;
import net.yassine.emsidemospringang.entities.PaymentStatus;
import net.yassine.emsidemospringang.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);


}
