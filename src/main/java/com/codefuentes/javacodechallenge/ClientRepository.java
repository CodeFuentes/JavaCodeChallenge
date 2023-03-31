package com.codefuentes.javacodechallenge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefuentes.javacodechallenge.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByUserIdAndRoomId(UUID userId, UUID roomId);

    @Override
    <S extends Client> S save(S entity);

    @Override
    List<Client> findAll();

    @Override
    void delete(Client entity);
}


