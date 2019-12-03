package com.cetv.desafio2.service;

import com.cetv.desafio2.model.Client;
import com.cetv.desafio2.model.Kpi;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ClientService {

    @Autowired
    private final Firestore db;

    public ClientService(Firestore db) {
        this.db = db;
    }

    public ArrayList<Client> listUser() throws ExecutionException, InterruptedException {
        ArrayList<Client> clients = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection("clients").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            Client client = new Client();

            client.setApellido(document.getString("apellido"));
            client.setNombre(document.getString("nombre"));
            try {
                client.setEdad(Integer.valueOf(Math.toIntExact(document.getLong("edad"))));
            }catch (Exception e){
                System.out.println("ocurrio un rror");
            }
            client.setFecNac(document.getString("fecNac"));



            clients.add(client);
            //System.out.println(document.getId() + " => " + document.getString("name"));
        }
        return clients;
    }


    public Kpi getKpi() throws ExecutionException, InterruptedException {
        Kpi kpi = new Kpi();

        double promedio=0;
        double totalitems=0;
        double sd=0;


        ArrayList<Client> clients = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection("clients").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            Client client = new Client();
            client.setEdad(Integer.valueOf(Math.toIntExact(document.getLong("edad"))));
            clients.add(client);

            totalitems++;
            promedio +=Integer.valueOf(Math.toIntExact(document.getLong("edad")));
        }

        promedio=promedio/totalitems;

        for (Client c:clients){

            sd+=(c.getEdad()-promedio)*(c.getEdad()-promedio);
        }

        sd = Math.sqrt(sd/totalitems);


        kpi.setPromedio(promedio);
        kpi.setSd(sd);
        return kpi;
    }



    public void addClient(Client client) throws ExecutionException, InterruptedException {


        ApiFuture<WriteResult> future = db.collection("clients").document().create(client);
        future.get();
    }
}
