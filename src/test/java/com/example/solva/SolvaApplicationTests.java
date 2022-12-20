package com.example.solva;

import com.example.solva.service.TransactionServiceImpl;
import com.example.solva.store.TransactionRepository;
import com.example.solva.web.transaction.TransactionDTO;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
class SolvaApplicationTests {

    @InjectMocks
    private TransactionServiceImpl service;

    @Mock
    private TransactionRepository repository;

    @Test
    void checkAllTransaction() {
        when(service.getByAccount("1234567890")).thenReturn(List.of(new TransactionDTO(),new TransactionDTO()));
        verify(repository,times(1)).findAllByAccountFrom("1234567890");
        verifyNoMoreInteractions(repository);
    }

}
