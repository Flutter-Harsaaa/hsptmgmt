package com.hsptMgmt.service;

import com.hsptMgmt.dao.PaymentDAO;
import com.hsptMgmt.entity.Payment;

import java.util.List;

import org.hibernate.SessionFactory;

public class PaymentService {
    private final PaymentDAO paymentDAO;

    public PaymentService(SessionFactory sessionFactory) {
        this.paymentDAO = new PaymentDAO(sessionFactory);
    }
    public double totalRevenue() {
        return paymentDAO.totalRevenue();
    }

    public Payment recordPayment(Payment p) { return paymentDAO.save(p); }

    public Payment getPayment(Long id) { return paymentDAO.findById(id); }

    public List<Payment> getAllPayments() { return paymentDAO.findAll(); }

    public List<Payment> getPendingPayments() { return paymentDAO.findPending(); }

    public void deletePayment(Long id) { paymentDAO.delete(id); }
}
