package com.firstapi.firstapi.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
class UserResource {

    @Autowired
    private lateinit var service: UserDaoService

    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> {
        return service.findAll()
    }

    @GetMapping("/users/{id}")
    fun retrieveUser(@PathVariable id: Int): EntityModel<User>? {
        val user = service.findOne(id) ?: throw UserNotFoundException("id- $id")
        val model = EntityModel.of(user)
        val linkToUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.javaClass).retrieveAllUsers())
        model.add(linkToUser.withRel("users"))
        return model
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Int) {
        val user = service.deleteById(id)
        if (user == null) {
            throw UserNotFoundException("id- $id")
        }
    }

    @PostMapping("/users")
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<Any> {
        val savedUser = service.save(user)
        val uri = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(savedUser.id)
            .toUri()
        return ResponseEntity.created(uri).build()
    }
}
