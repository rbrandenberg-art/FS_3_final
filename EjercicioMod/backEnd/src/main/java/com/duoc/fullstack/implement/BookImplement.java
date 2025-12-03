package com.duoc.fullstack.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.fullstack.model.Book;
import com.duoc.fullstack.repository.BookRepository;
import com.duoc.fullstack.service.bookService;

/**
 * Esta clase tiene por finalidad implementar el alcance definido en la interfaz de servicio bookService.
 * Dicha interfaz tiene definido los metodos que necesitamos implementar.
 * La implementacion se realizara seleccinando aquellos metodosdisponibles de JPA que necesitemos.
 * Por esta razon es necesario pasar por la capa Repository para poder hacer la invocacion de dichos metodos.
 */

@Service // Anotacion paradefinir que esta clase es la capa de servicio de la App en Springboot
public class BookImplement implements bookService{
    @Autowired // esta anotacion es una inyeccion de dependencia que usaremos para relacionar con la capa Repository y obtener los metodos de BookRepository para implementarlos
    private BookRepository bookRepository;

    @Override
    public List<Book> listar() {
        // implementaciòn del metodo listar Book, disponibilizado por JPA
        return bookRepository.findAll();
    }

    @Override
    public Book guardar(Book book) {
        // Implementaciòn del metodo guardar Book, el cual sera usado tanto en la funcionalidad de crecion como en la actualizacion
        return bookRepository.save(book);
    }

    @Override
    public void eliminar(Book book) {
        // Implementacion del metodo para eliminar un objeto de Book que este registrado, que previamente fue seleccionado de algun listado de registros
        bookRepository.delete(book);
    }

    @Override
    public Book obtener(Long idBook) {
        // Implementacon del metodo para obtener un objeto e Book a partir de un Id que se seleccino de algun listado 
        return bookRepository.findById(idBook).orElse(null);
    }
    
}
