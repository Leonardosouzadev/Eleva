package com.example.eleva.controller;

import com.example.eleva.controller.dto.ProfileDTO;
import com.example.eleva.controller.dto.ProfileImageDTO;
import com.example.eleva.entity.Profile;
import com.example.eleva.service.ProfileService;
import com.example.eleva.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable String userId){
        Profile newProfile = profileService.getProfile(userId);
        return  ResponseEntity.ok(new ProfileDTO(newProfile.getProfileName(), newProfile.getProfileBiography(), newProfile.getProfileImage()));
    }

    @PutMapping("/image-update")
    public ResponseEntity profileImageUpdate(@RequestBody ProfileImageDTO request){
        Profile newProfile = profileService.imageUpdate(request);
        return ResponseEntity.ok("imagem adicionada!");
    }

    @DeleteMapping("/image-remove")
    public ResponseEntity profileImageRemove(@RequestBody ProfileImageDTO request){
        Profile newProfile = profileService.imageRemove(request);
        return ResponseEntity.ok("imagem removida!");
    }

}
