package com.example.controller;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value="/all/{page}/{size}", method= RequestMethod.GET)
    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1, size);
        return bookRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Book book){
        Book save = bookRepository.save(book);
        if(save != null){
            return "success";
        }
        return "error";
    }

    @GetMapping("/find/{id}")
    public Book findById(@PathVariable("id") Integer id){
        return bookRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Book book){
        Book save = bookRepository.save(book);
        if(save != null){
            return "success";
        }
        return "error";
    }

    @DeleteMapping("/remove/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        bookRepository.deleteById(id);
    }
}
