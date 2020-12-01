package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTest {
    @Mock
    private StudentRepository repository;

    @Test
    void findById() {
        Student student = new Student(1,
                "voornaam",
                "familienaam",
                "e@ma.il",
                "userkey",
                "avatarpad",
                2L);
        when(repository.findById(1L)).thenReturn(Optional.of(student));
        assertThat(repository.findById(1L)).get()
                .isEqualTo(student);
    }
}
