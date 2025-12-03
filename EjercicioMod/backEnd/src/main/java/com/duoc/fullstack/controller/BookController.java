package com.duoc.fullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.fullstack.implement.BookImplement;
import com.duoc.fullstack.model.Book;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador que permitira atender las peticiones HTTP (GET, POST, PUT y
 * DELETE) para ejecutar las
 * respectivas funcionalidades sobre la entidad Book
 * Las respectivas peticiones se recibiran en las URL definidas en este
 * controlador
 */

@RestController
@RequestMapping("/api/books") // URL definida como Listener para atender las peticiones HTTP
@Api(value = "Book Management System")
public class BookController {
    @Autowired
    private BookImplement bookService;

    @GetMapping // metodo GET sin parametros, para listar todos los objetos de Book
    @ApiOperation(value = "View a list of available books", response = List.class)
    public List<Book> getAllBooks() {
        Book book = new Book();
        //*** Dato de prueba */
        book.setAuthor("Baldomero Lillo");
        book.setTitle("La consentida");

        // guardamos el registro solo cuando el listado esta
        if (bookService.listar().isEmpty()){
            bookService.guardar(book);
        }

        return bookService.listar();
    }

    @GetMapping("/{id}") // metodo GET con parametro Id, para obtener un objeto de Book
    @ApiOperation(value = "Get a book by Id")
    public Book getBookById(@PathVariable Long id) {
        return bookService.obtener(id);
    }

    @PostMapping // metodo POST, para Guardar un objeto de Book (nuevo o actualizado)
    @ApiOperation(value = "Add a new book")
    public Book createBook(@RequestBody Book book) {
        return bookService.guardar(book);
    }

    @PutMapping("/{id}") // metodo PUT, para guardar un objeto de Book actualizado desde formulario
    @ApiOperation(value = "Update an existing book")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookService.obtener(id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            return bookService.guardar(existingBook);
        }
        return null;
    }

    @DeleteMapping("/{id}") // metodo delete para eliminar un obtedo de Book existente dado su Id como parametro
    @ApiOperation(value = "Delete a book")
    public void deleteBook(@PathVariable Long id) {
        Book existingBook = bookService.obtener(id);
        bookService.eliminar(existingBook);
    }

    // llamar al metodo delete por medio de un metodo GET
    @GetMapping("/borrar/{id}")
     @ApiOperation(value = "Delete a book")
    public void borrarBook(@PathVariable Long id) {
        Book existingBook = bookService.obtener(id);
        bookService.eliminar(existingBook);
    }
}
