package com.montelonogo.Tickets.servicio;

import com.montelonogo.Tickets.modelo.ticket;
import java.util.List;

public interface ITicketServicio {

    public List<ticket> listarTicket();

    public ticket buscarTicketID(Integer IdTicket);

    public void agregarTicket(ticket ticketAdd);

    public void eliminarTicket(ticket ticketDelete);
}
