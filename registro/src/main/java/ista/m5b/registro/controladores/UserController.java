package ista.m5b.registro.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ista.m5b.registro.servicio.UserService;
import ista.m5b.registro.modelos.User;
@RestController
@RequestMapping("/api/users")
public class UserController {
    /*
    HTTP GET -> obtener: Listar
    HTTP POST -> enviar: Crear/Insertar
    HTTP PUT -> modifica información: Actualizar
    HTTP DELETE -> elimina información: Eliminar 
    */ 
    @Autowired
    private UserService userService;

    //CREAR NUEVO USUARIO
    @PostMapping("/usuariosCrear")
    public ResponseEntity<?> create (@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    //LISTAR
    @GetMapping("/listar-ID/{id}")
    public ResponseEntity<?> read(@PathVariable(value ="id")Long userId){

        Optional<User> oUser = userService.findById(userId);

        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oUser);

    }
    //lISTA -TODOS LOS USUARIOS
    @GetMapping("/listar-usuarios")
    public List<User>readAll(){

        List<User> users =StreamSupport
        .stream(userService.findAll().spliterator(),false)
        .collect(Collectors.toList());
        return  users;

        
    }
    
    //ACTUALIZAR
    @PutMapping("/usuariosModificar/{id}")
    public ResponseEntity<?> update(@RequestBody User userDetails,@PathVariable(value ="id") long userId){

        Optional<User> user = userService.findById(userId);

        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        user.get().setName(userDetails.getName());
        user.get().setPass(userDetails.getPass());
        user.get().setEmail(userDetails.getEmail());
        user.get().setEnable(userDetails.getEnable());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));

    }
    //BORRAR
    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<?> delete(@PathVariable(value ="id")Long userId){

        if(!userService.findById(userId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(userId);
        return ResponseEntity.ok().build();

    }






    
   
}
