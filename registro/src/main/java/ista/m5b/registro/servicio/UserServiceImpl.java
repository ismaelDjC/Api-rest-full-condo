package ista.m5b.registro.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ista.m5b.registro.modelos.User;
import ista.m5b.registro.repositorio.UserRepository;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository usersRepository;


    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        
        return usersRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
       
        return usersRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        
        return usersRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        
        return usersRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
        
    }
    
}
