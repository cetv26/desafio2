package com.cetv.desafio2.controllers;

import com.cetv.desafio2.model.Client;
import com.cetv.desafio2.model.Kpi;
import com.cetv.desafio2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

@Validated

@RestController
public class ClientController {




    @Autowired
    ClientService clientService;
    @CrossOrigin
    @RequestMapping(value="/creacliente", method = RequestMethod.POST)
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) throws ExecutionException, InterruptedException, ParseException {


        /*LocalDate bday = LocalDate.of(client.getFecNac().split("-")[0], client.getFecNac().split("-")[0], client.getFecNac().split("-")[0]); LocalDate today = LocalDate.now();

        Read more: https://javarevisited.blogspot.com/2016/10/how-to-get-number-of-months-and-years-between-two-dates-in-java.html#ixzz66to7DDhE


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(client.getFecNac());
        long diff =(new Date()).getTime()-date.getTime();

        System.out.println(sdf.format(new Date()));

        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        if(diffDays>0) {
            System.out.println("difference between days: " + (float)diffDays/365);
        }*/
        clientService.addClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value="/kpideclientes", method = RequestMethod.GET)
    public ResponseEntity<Kpi> kpideclientes() throws ExecutionException, InterruptedException {
        Kpi kpi = clientService.getKpi();
        return new ResponseEntity<>(kpi, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value="/listclientes", method = RequestMethod.GET)
    public ArrayList<Client>  listclientes() throws ExecutionException, InterruptedException, ParseException {
        //74.98 años
        ArrayList<Client> clients = clientService.listUser();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Client u:clients){
            try {
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(u.getFecNac()));
                c.add(Calendar.YEAR, 75);

                u.setFecMuerte(sdf.format(c.getTime()));
            }catch (Exception e){
                System.out.println("ocurrio un error al recuperar la fecha de: "+u.getFecNac());
            }
        }
        return clients;
    }
}
