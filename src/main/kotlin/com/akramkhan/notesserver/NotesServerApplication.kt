package com.akramkhan.notesserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.*
import javax.persistence.*

@SpringBootApplication
class NotesServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(NotesServerApplication::class.java, *args)
}

@RestController
class HomeController(@Autowired val notesRepository: NotesRepository){

    @GetMapping("/")
    fun home() = notesRepository.findAll()

    @GetMapping("/{id}")
    fun notes(@PathVariable id: Long) = notesRepository.getOne(id)

    @PostMapping("/")
    fun post(@RequestBody notes: Notes) = notesRepository.save(notes)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = notesRepository.delete(id)

}

@Entity @Table(name = "notes")
data class Notes(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var title: String = "",
        var description: String = ""
)

interface NotesRepository: JpaRepository<Notes, Long>