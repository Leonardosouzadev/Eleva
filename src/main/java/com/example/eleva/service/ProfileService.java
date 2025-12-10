package com.example.eleva.service;

import com.example.eleva.controller.dto.ProfileImageDTO;
import com.example.eleva.entity.Profile;
import com.example.eleva.repository.ProfileRepository;
import com.example.eleva.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    public Profile imageUpdate(ProfileImageDTO request) {

        Profile newProfile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new RuntimeException("Profile não encontrado"));

        newProfile.setProfileImage(request.profileImage());

        return profileRepository.save(newProfile);
    }

    public Profile imageRemove(ProfileImageDTO request) {
        Profile newProfile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new RuntimeException("Profile não encontrado"));

        newProfile.setProfileImage(null);

        return profileRepository.save(newProfile);
    }
}
