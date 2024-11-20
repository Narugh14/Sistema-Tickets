package com.montelonogo.Tickets.repositorio;

import com.montelonogo.Tickets.modelo.ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepositorio extends JpaRepository<ticket, Integer> {
}
