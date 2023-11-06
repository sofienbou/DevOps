package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
import tn.esprit.rh.achat.entities.Operateur;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class OperateurServiceImlpMock {

    @Autowired
    IOperateurService op;




    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
        List<Operateur> listOprateurs = op.retrieveAllOperateurs();
        Assertions.assertEquals(0, listOprateurs.size());
    }
    @InjectMocks
    OperateurServiceImpl operateurService;

    Operateur operateur = new Operateur();
    List<Operateur> listOperateur = new ArrayList<Operateur>() {
        {
            add(new Operateur());
            add(new Operateur());
        }
    };
    @Mock
    OperateurRepository OperateurRepository;
}
