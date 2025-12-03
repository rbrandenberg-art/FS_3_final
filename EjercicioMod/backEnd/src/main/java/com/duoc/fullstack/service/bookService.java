package com.duoc.fullstack.service;

import java.util.List;

import com.duoc.fullstack.model.Book;

public interface bookService {

    // listar todos los book registrados
    public List<Book> listar();
    
    // guardar (nuevo) o actualizar un book 
    public Book guardar(Book book);
    
    // eliminar un book
    public void eliminar(Book book);
    
    // obtener un objeto de un Book registrado a partir de su id
    public Book obtener(Long idBook);
}
