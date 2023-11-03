package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class ReglementServiceImplMock {
    @Mock
    ReglementRepository RegelementRepository;

    @InjectMocks
    ReglementServiceImpl ReglementService;

    Reglement reglement= new Reglement(1L,500.1f,400.1f,true,new Date(9), new Facture());

    List<Reglement> ListRegl = new ArrayList<Reglement>(){
        {
            add(new Reglement(2L,530.1f,410.1f,true,new Date(9), new Facture()));
        }
    };
    /*@Test
    public void testRetrieveAllReglement(){
        Mockito.when(RegelementRepository.findAll()).thenReturn(ListRegl);
        List<Reglement> reglements = ReglementService.retrieveAllReglements();

        Assertions.assertNotNull(reglements);
    }
    @Test
    public void testRetrieveReglementByFacture(){
        Mockito.when(RegelementRepository.retrieveReglementByFacture(Mockito.anyLong())).thenReturn(Optional.of(ListRegl));
        List<Reglement> reglement1=ReglementService.retrieveReglementByFacture(2L);

        Assertions.assertNotNull(reglement1);
    }*/
    @Test
    public void testRetrieveReglement(){
        Mockito.when(RegelementRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(reglement));
        Reglement reglement1=ReglementService.retrieveReglement(2L);

        Assertions.assertNotNull(reglement1);
    }
}