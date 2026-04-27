package com.turkcell.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Annotation => Bulunduğu class,fonk,değişken'e özellik kazandıran yapıdır.
public class LibraryApplication {

	// Entrypoint
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
