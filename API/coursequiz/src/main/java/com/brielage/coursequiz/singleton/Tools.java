package com.brielage.coursequiz.singleton;

import com.brielage.coursequiz.domain.Hoofdstuk;
import com.brielage.coursequiz.domain.Quiz;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.HoofdstukService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.QuizService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.brielage.coursequiz.services.VraagService;

import java.util.Optional;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public enum Tools {
    INSTANCE;

    public static boolean userExists(String userkey,
                                     UserService userService) {
        return userService.findByUserkey(userkey).size() > 0;
    }

    public static boolean userExists(long userid,
                                     UserService userService) {
        return userService.findById(userid).isPresent();
    }

    public static boolean isUserAdminOrDocent(User user,
                                              UserRolService userRolService) {
        Rol rol = getRol(user, userRolService);
        return rol == Rol.ADMIN || rol == Rol.DOCENT;
    }

    public static boolean isDocent(User user,
                                   UserRolService userRolService) {
        return getRol(user, userRolService) == Rol.DOCENT;
    }

    public static boolean isDocentVanVak(User user,
                                         long vakid,
                                         DocentVakService docentVakService) {
        return docentVakService.findByDocentIdAndVakId(user.getId(), vakid).size() > 0;
    }

    public static boolean isDocentVanVakVanQuiz(User user,
                                                DocentVakService docentVakService,
                                                Quiz q) {
        return docentVakService.findByDocentIdAndVakId(user.getId(), q.getVakid()).size() > 0;
    }

    public static boolean isDocentVanHoofdstuk(User user,
                                               DocentVakService docentVakService,
                                               Hoofdstuk hoofdstuk) {
        return docentVakService.findByDocentIdAndVakId(user.getId(), hoofdstuk.getVakId()).size() > 0;
    }

    public static boolean doesUserEmailExist(String email,
                                             UserService userService) {
        return userService.findByEmail(email).size() > 0;
    }

    public static boolean doesOpleidingExist(long id,
                                             String opleidingnaam,
                                             OpleidingService opleidingService) {
        if (id > 0) {
            return opleidingService.findById(id).isPresent();
        } else {
            return opleidingService.findByNaam(opleidingnaam).size() > 0;
        }
    }

    public static boolean doesOpleidingExist(long id,
                                             OpleidingService opleidingService) {
        return opleidingService.findById(id).isPresent();
    }

    public static boolean doesVakExist(long id,
                                       VakService vakService) {
        return vakService.findById(id).isPresent();
    }

    public static boolean doesHoofdstukExist(long id,
                                             HoofdstukService hoofdstukService) {
        return hoofdstukService.findById(id).isPresent();
    }

    public static boolean doesQuizExist(long quizid,
                                        QuizService quizService) {
        return quizService.findById(quizid).isPresent();
    }

    public static boolean doesVraagExist(String vraag,
                                         VraagService vraagService) {
        return vraagService.findByVraag(vraag).size() > 0;
    }

    public static boolean doesVraagExist(long vraagid,
                                         VraagService vraagService) {
        return vraagService.findById(vraagid).isPresent();
    }

    private static Rol getRol(User user,
                              UserRolService userRolService) {
        Optional<UserRol> optionalUserRol = userRolService.findByUserId(user.getId());
        if (optionalUserRol.isEmpty()) return null;
        return optionalUserRol.get().getRol();
    }
}
