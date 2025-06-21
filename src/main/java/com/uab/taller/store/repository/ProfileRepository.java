package com.uab.taller.store.repository;

import com.uab.taller.store.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Optional<Profile> findByCi(String ci);
}
