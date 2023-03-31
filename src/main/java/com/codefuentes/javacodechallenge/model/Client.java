package com.codefuentes.javacodechallenge.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Entity
@Table(name = "client")
@Repository
public class Client {

    @Id
    private UUID clientId;

    public UUID getClientId() {
        return clientId;
    }

    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    private UUID roomId;

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Client() {
        this.clientId = UUID.randomUUID();
    }

}
