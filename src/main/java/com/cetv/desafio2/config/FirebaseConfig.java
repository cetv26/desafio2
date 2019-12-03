package com.cetv.desafio2.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.*;

@Configuration
public class FirebaseConfig {
    /*@Bean
    public DatabaseReference firebaseDatabse() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }*/

    @Bean
    public Firestore firestore() {
        Firestore firebase =  FirestoreClient.getFirestore();
        return firebase;
    }

    @PostConstruct
    public void init() throws IOException {

        /**
         * https://firebase.google.com/docs/server/setup
         *
         * Create service account , download json
         */
        ClassLoader classLoader = this.getClass().getClassLoader();
        Resource resource = new ClassPathResource("fbkeys.json");
        InputStream inputStream = resource.getInputStream();
        /*File configFile=new File(classLoader.getResource("fbkeys.json").getFile());
        InputStream inputStream = new FileInputStream(configFile);*/

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl("https://fb001.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
        //Firestore db = FirestoreClient.getFirestore();



    }
}
