package com.montelonogo.Tickets.servicio;

import com.montelonogo.Tickets.modelo.ticket;
import com.montelonogo.Tickets.repositorio.TicketRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServicio implements ITicketServicio{

    @Autowired
    private TicketRepositorio ticketRepo;

    @Override
    public List<ticket> listarTicket() {
        return ticketRepo.findAll();
    }

    @Override
    public ticket buscarTicketID(Integer IdTicket) {
        ticket ticketBuscar =  ticketRepo.findById(IdTicket).orElse(null);
        return ticketBuscar;

    }

    @Override
    public void agregarTicket(ticket ticketAdd) {
        ticketRepo.save(ticketAdd);
    }

    @Override
    public void eliminarTicket(ticket ticketDelete) {
        ticketRepo.delete(ticketDelete);
    }
}
