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

import javax.swing.*;

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

    @FXML
    private TextField nombreTicketTexto;
    @FXML
    private TextField responsableTexto;
    @FXML
    private TextField estatusTexto;

    private Integer idTicketSelect;

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

    public void agregarTicket(){

        if(idTicketSelect != null){
            mostrarMensaje("Error Validacion","Ticket duplicado");
            return;
        }
        if(nombreTicketTexto.getText().isEmpty()){
            mostrarMensaje("Error validacion", "Rellenar los campos vacios");
            nombreTicketTexto.requestFocus();
            return;
        } else if (estatusTexto.getText().isEmpty()) {
            mostrarMensaje("Error validacion", "Rellenar los campos vacios");
            responsableTexto.requestFocus();
            return;
        } else if (responsableTexto.getText().isEmpty()) {
            mostrarMensaje("Error validacion", "Rellenar los campos vacios");
            estatusTexto.requestFocus();
            return;
        }else {
            var ticket = new ticket();
            GuardarDatos(ticket);
            ticket.setIdTicket(null);
            ticketServicio.agregarTicket(ticket);
            mostrarMensaje("Informacion","Ticket Guardado");
            resetFormulario();
            listarTickets();
        }
    }

    public void modificarTicket(){
        if(idTicketSelect == null){
            mostrarMensaje("Informacion", "Seleccionar ticket de la lista");
            return;
        }
        if(nombreTicketTexto.getText().isEmpty()){
            mostrarMensaje("Error Validacion", "Debe proporcionar un ticket");
            nombreTicketTexto.requestFocus();
            return;
        }
        var ticket = new ticket();
        GuardarDatos(ticket);
        ticketServicio.agregarTicket(ticket);
        mostrarMensaje("Informacion","ticket Modificado correctamente");
        resetFormulario();
        listarTickets();
    }

    public void eliminarTicket(){
        var ticket = ticketTabla.getSelectionModel().getSelectedItem();
        if( ticket != null && !nombreTicketTexto.getText().isEmpty()){
            logger.info("Registro a eliminar: " + ticket.getIdTicket());
            ticketServicio.eliminarTicket(ticket);
            mostrarMensaje("Informacion", "Tarea eliminada: " + ticket.getActividadTicket());
            resetFormulario();
            listarTickets();
        }else{
            mostrarMensaje("Error", "No se a seleccionado ningun ticket");
        }
    }

    public void selectTicketList(){
        var ticket = ticketTabla.getSelectionModel().getSelectedItem();
        if(ticket != null){
            idTicketSelect = ticket.getIdTicket();
            nombreTicketTexto.setText(ticket.getActividadTicket());
            responsableTexto.setText(ticket.getResposableTicket());
            estatusTexto.setText(ticket.getEstatus());
        }
    }

    public void resetFormulario(){
         idTicketSelect = null;
        nombreTicketTexto.clear();
        estatusTexto.clear();
        responsableTexto.clear();
    }

    private void GuardarDatos(ticket ticket) {
        if(idTicketSelect != null)
            ticket.setIdTicket(idTicketSelect);
        ticket.setActividadTicket(nombreTicketTexto.getText());
        ticket.setResposableTicket(responsableTexto.getText());
        ticket.setEstatus(estatusTexto.getText());
    }

    private void mostrarMensaje(String titulo, String mensaje) {

        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle(titulo);
        aviso.setHeaderText(null);
        aviso.setContentText(mensaje);
        aviso.showAndWait();
    }
}
