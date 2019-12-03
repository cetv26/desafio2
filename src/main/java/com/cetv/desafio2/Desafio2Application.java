package com.cetv.desafio2;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class Desafio2Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio2Application.class, args);
	}

	@Component
	public class FirebaseAuthenticationProvider{

		public void init() throws IOException {
			FileInputStream serviceAccount = new FileInputStream("fbkeys1.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.getApplicationDefault())
					.setDatabaseUrl("https://fb001.firebaseio.com/")
					.build();

			FirebaseApp.initializeApp(options);
		}
	}

}
