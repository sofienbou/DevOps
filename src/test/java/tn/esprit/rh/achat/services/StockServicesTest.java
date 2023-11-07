package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;
import tn.esprit.rh.achat.services.StockServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.*;


@ExtendWith(MockitoExtension.class)

public class StockServicesTest {
    @Mock
    StockRepository StockRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    @InjectMocks
    StockServiceImpl stockService ;

    @Test
    public void testRetrieveAllStock(){

        Stock premierstock = new Stock();
        premierstock.setIdStock(1l);
        premierstock.setLibelleStock("King");
        premierstock.setQte(12);


        Stock stock2 = new Stock();
        stock2.setIdStock(2l);
        stock2.setLibelleStock("devops");
        stock2.setQte(10);


        List<Stock> mockstocks = new ArrayList<>();
        mockstocks.add(premierstock);
        mockstocks.add(stock2);


        Mockito.when(StockRepository.findAll()).thenReturn(mockstocks);
        List<Stock> result = stockService.retrieveAllStocks();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    void testAddStock() {
        Stock mockStock = new Stock();
        mockStock.setIdStock(1L);
        mockStock.setLibelleStock("ok");
        mockStock.setQte(10);

        Mockito.when(StockRepository.save(Mockito.any(Stock.class))).thenReturn(mockStock);

        Stock result = stockService.addStock(mockStock);

        assertNotNull(result);
        assertEquals(1L, result.getIdStock());
        assertEquals(10, result.getQte());
        assertEquals("ok", result.getLibelleStock());
    }

    @Test
    void testDeleteStock() {
        Long idToDelete = 1L;

        stockService.deleteStock(idToDelete);

        Mockito.verify(StockRepository).deleteById(idToDelete);
    }

    @Test
    void testUpdateStock() {
        Stock mockStock = new Stock();
        mockStock.setIdStock(1L);
        mockStock.setLibelleStock("Update");

        Mockito.when(StockRepository.save(Mockito.any(Stock.class))).thenReturn(mockStock);

        Stock result = stockService.updateStock(mockStock);

        assertNotNull(result);
        assertEquals(1L, result.getIdStock());
        assertEquals("Update", result.getLibelleStock());
    }



    @Test
    void testRetrieveNonExistentStock() {
        Long idToRetrieve = 1L;

        Mockito.when(StockRepository.findById(idToRetrieve)).thenReturn(Optional.empty());

        Stock result = stockService.retrieveStock(idToRetrieve);

        assertNull(result);
    }

}