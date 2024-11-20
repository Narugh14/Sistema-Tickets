package com.montelonogo.Tickets;

import com.montelonogo.Tickets.presentacion.SistemaTicketsFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TicketsApplication.class, args);
		Application.launch(SistemaTicketsFx.class, args);
	}

}
