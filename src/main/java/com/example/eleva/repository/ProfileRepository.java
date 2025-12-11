package com.example.eleva.repository;

import com.example.eleva.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Profile findByUser_Id(String userId);
}
