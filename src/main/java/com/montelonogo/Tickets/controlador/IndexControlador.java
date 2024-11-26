package com.montelonogo.Tickets.controlador;

import com.montelonogo.Tickets.modelo.ticket;
import com.montelonogo.Tickets.servicio.TicketServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexControlador implements Initializable {

    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private TicketServicio ticketServicio;

    @FXML
    private TableView<ticket> ticketTabla;

    @FXML
    private TableColumn<ticket, Integer> idTicketColumna;
    @FXML
    private TableColumn<ticket, String> ticketColumna;
    @FXML
    private TableColumn<ticket, String> responsableColumna;
    @FXML
    private TableColumn<ticket, String> estatusColumna;

    private final ObservableList<ticket> ticketList =
            FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ticketTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
         configurarColumna();
         listarTickets();
    }

    private void listarTickets() {

        logger.info("Ejecutando listado de tickets");
        ticketList.clear();
        ticketList.addAll(ticketServicio.listarTicket());
        ticketTabla.setItems(ticketList);
    }

    private void configurarColumna() {
        idTicketColumna.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
        ticketColumna.setCellValueFactory(new PropertyValueFactory<>("actividadTicket"));
        responsableColumna.setCellValueFactory(new PropertyValueFactory<>("resposableTicket"));
        estatusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }
}
