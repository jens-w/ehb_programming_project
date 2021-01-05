package com.brielage.coursequiz.singleton;

import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

public enum Tools {
    INSTANCE;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean doesUserHaveRights(String userkey,
                                             UserService userService,
                                             UserRolService userRolService) {
        List<User> userListByUserkey = userService.findByUserkey(userkey);
        if (userListByUserkey.size() != 1) return false;

        Optional<UserRol> optionalUserRol = userRolService.findByUserId(userListByUserkey.get(0).getId());
        if (optionalUserRol.isEmpty()) return false;

        Rol rol = optionalUserRol.get().getRol();

        return rol == Rol.ADMIN || rol == Rol.DOCENT;
    }

    public static boolean doesOpleidingExist(long id,
                                             String opleidingnaam,
                                             OpleidingService opleidingService) {
        if (id > 0) {
            Optional<Opleiding> optionalOpleiding = opleidingService.findById(id);
            return optionalOpleiding.isPresent();
        } else {
            List<Opleiding> opleidingListByNaam = opleidingService.findByNaam(opleidingnaam);
            return opleidingListByNaam.size() > 0;
        }
    }
}
