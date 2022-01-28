package ista.m5b.registro.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import ista.m5b.registro.modelos.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
