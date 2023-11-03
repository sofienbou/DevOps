package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ProduitServiceImplTest {

    @Mock
    ProduitRepository produitRepository;
    @Mock
    StockRepository stockRepository;
    @Mock
    CategorieProduitRepository categorieProduitRepository;
    @InjectMocks
    ProduitServiceImpl produitService;


    Produit produit = new Produit(1L, "Test1", "test2",4f, new Date(), new Date(),new Stock() , new HashSet<DetailFacture>(),new CategorieProduit());
    Stock stock = new Stock (1L ,"test",2,3,new HashSet<Produit>());
   CategorieProduit  categorieProduit = new CategorieProduit(1L, "code123", "lib123", new HashSet<Produit>());




    @BeforeEach
    void setUp() {
        produitService  = new ProduitServiceImpl(produitRepository,stockRepository,categorieProduitRepository);  // No need to manually create a StockServiceImpl instance since it's injected.
    }



    @Test
    void retrieveProduit() {
        when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));

        // Call the method being tested
        System.out.println("Before calling testRetrieveStock()");
        Produit pr = produitService.retrieveProduit(5L);
        System.out.println("After calling testRetrieveStock() => " + pr.getLibelleProduit());

        // Assert the result
        assertNotNull(pr);
    }


}