package com.cityconcert.controller;


import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@Tag(name = "Пользователи", description = "Методы, взаимодействующие с пользователями")
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl usi;

    public UserController(UserServiceImpl usi) {
        this.usi = usi;
    }

    @PutMapping(value = "/update/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Обновление экземпляра пользователя")
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return usi.updateUser(user);
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Возвращение экземпляра пользователя по его Id")
    public UserDTO getUser(@PathVariable Long id) {
        return usi.findOne(id).orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping(value = "/delete/{id}",
            produces = {"application/json", "application/xml"})
    @Operation(summary = "Удаление экземпляра пользователя по его Id")
    public void deleteUser(@PathVariable String id) {
        usi.deleteUser(id);
    }

}
