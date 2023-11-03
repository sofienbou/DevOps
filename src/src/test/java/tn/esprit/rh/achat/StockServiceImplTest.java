package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;



    @Test
    public void testAddStock() {
        // Créez un faux Stock pour simuler la réponse du repository
        Stock fakeStock = new Stock(3L, "Stock 3", 150, 15, null);

        // Configurez le comportement du repository mock
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(fakeStock);

        // Appelez la méthode de service que vous souhaitez tester
        Stock result = stockService.addStock(fakeStock);

        // Vérifiez si le résultat est conforme à vos attentes
        assertEquals(fakeStock, result);
    }
    @Test
    public void testDeleteStock() {
        // Identifiant du stock à supprimer
        Long stockId = 1L;

        // Appelez la méthode de service pour supprimer le stock
        stockService.deleteStock(stockId);

        // Vérifiez si la méthode du repository mock a été appelée une fois pour supprimer le stock avec l'identifiant donné
        Mockito.verify(stockRepository, Mockito.times(1)).deleteById(stockId);
    }

    @Test
    public void testUpdateStock() {
        // Créez un faux stock avec les nouvelles valeurs
        Stock fakeStock = new Stock(1L, "Updated Stock 1", 120, 12, null);

        // Configurez le comportement du repository mock pour sauvegarder le faux stock
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(fakeStock);

        // Appelez la méthode de service pour mettre à jour le stock
        Stock result = stockService.updateStock(fakeStock);

        // Vérifiez si le stock retourné par la méthode de service est égal au faux stock
        Assertions.assertEquals(fakeStock, result);
    }
    @Test
    public void testRetrieveStock() {
        // Créez un faux Stock pour simuler la réponse du repository
        Stock fakeStock = new Stock(1L, "Stock de test", 100, 10, null);

        // Configurez le comportement du repository mock
        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(fakeStock));

        // Appelez la méthode de service que vous souhaitez tester
        Stock result = stockService.retrieveStock(1L);

        // Vérifiez si le résultat est conforme à vos attentes
        assertEquals(fakeStock, result);
    }


    // Ajoutez d'autres méthodes de test au besoin
}
