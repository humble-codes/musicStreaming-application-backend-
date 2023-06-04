package com.rudra.musicStreaming.repositories;

import com.rudra.musicStreaming.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User , Long> {
}
