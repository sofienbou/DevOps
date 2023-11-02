package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;
@Test 
    public void testRetrieveAllStocks(){
    List<Stock> fakeStocks =  Arrays.asList(
            new Stock(1L, "Stock 1", 100, 10, null),
            new Stock(2L, "Stock 2", 200, 20, null)
    );

    Mockito.when(stockRepository.findAll()).thenReturn(fakeStocks);
    List<Stock> result = stockService.retrieveAllStocks();
    Assertions.assertEquals(fakeStocks.size(), result.size());

    }
    @Test
    public void testAddStock() {
        Stock fakeStock = new Stock(3L, "Stock 3", 150, 15, null);

        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(fakeStock);

        Stock result = stockService.addStock(fakeStock);

        Assertions.assertEquals(fakeStock, result);
    }

    @Test
    public void testDeleteStock() {
        Long stockId = 1L;

        stockService.deleteStock(stockId);

        Mockito.verify(stockRepository, Mockito.times(1)).deleteById(stockId);
    }

    @Test
    public void testUpdateStock() {
        Stock fakeStock = new Stock(1L, "Updated Stock 9", 120, 12, null);

        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(fakeStock);

        Stock result = stockService.updateStock(fakeStock);

        Assertions.assertEquals(fakeStock, result);
    }

    @Test
    public void testRetrieveStock() {
        Long stockId = 1L;
        Stock fakeStock = new Stock(stockId, "Stock 8", 100, 10, null);

        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(fakeStock));

        Stock result = stockService.retrieveStock(stockId);

        Assertions.assertEquals(fakeStock, result);
    }

    @Test
    public void testRetrieveStatusStock() {
        // Mettez en place votre test avec les données appropriées
        // Assurez-vous de couvrir différents cas de votre méthode

        // Exemple :
        Stock fakeStock = new Stock(1L, "Stock 7", 5, 10, null);

        Mockito.when(stockRepository.retrieveStatusStock()).thenReturn(Arrays.asList(fakeStock));

        String result = stockService.retrieveStatusStock();

        Assertions.assertNotNull(result);
    }

}
