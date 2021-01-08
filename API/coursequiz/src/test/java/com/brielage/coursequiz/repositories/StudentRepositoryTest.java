package com.brielage.coursequiz.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTest {
    @Mock
    private StudentRepository repository;

    @Test
    void findById() {
        /*
        Student student = new Student("voornaam",
                "familienaam",
                "e@ma.il",
                "userkey",
                "avatarpad",
                2L);
        when(repository.findById(1L)).thenReturn(Optional.of(student));
        assertThat(repository.findById(1L)).get()
                .isEqualTo(student);
         */
    }
}
