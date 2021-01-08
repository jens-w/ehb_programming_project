package com.brielage.coursequiz.singleton;

import com.brielage.coursequiz.domain.DocentVak;
import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

public enum Tools {
    INSTANCE;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isUserAdminOrDocent(String userkey,
                                              UserService userService,
                                              UserRolService userRolService) {
        Rol rol = getRol(userkey, userService, userRolService);

        return rol == Rol.ADMIN || rol == Rol.DOCENT;
    }

    public static boolean isDocent(String userkey,
                                   UserService userService,
                                   UserRolService userRolService) {
        return getRol(userkey, userService, userRolService) == Rol.DOCENT;
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

    public static boolean isDocentVanVak(String userkey,
                                         long vakid,
                                         UserService userService,
                                         DocentVakService docentVakService) {
        List<User> userListByUserkey = userService.findByUserkey(userkey);
        User u = userListByUserkey.get(0);
        List<DocentVak> dv = docentVakService.findByDocentIdAndVakId(u.getId(), vakid);

        return dv.size() == 1;
    }

    public static boolean doesVakExist(long id,
                                       VakService vakService) {
        return vakService.findById(id).isPresent();
    }

    /*
    public static boolean doesHoofdstukExist(long id,
                                             HoofdstukService hoofdstukService) {
        return hoofdstukService.findById(id).isPresent();
    }
     */

    private static Rol getRol(String userkey,
                              UserService userService,
                              UserRolService userRolService) {
        List<User> userListByUserkey = userService.findByUserkey(userkey);
        if (userListByUserkey.size() != 1) return null;

        Optional<UserRol> optionalUserRol = userRolService.findByUserId(userListByUserkey.get(0).getId());
        if (optionalUserRol.isEmpty()) return null;

        return optionalUserRol.get().getRol();
    }
}
