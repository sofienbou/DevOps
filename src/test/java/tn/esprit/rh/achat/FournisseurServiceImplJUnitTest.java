package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.IFournisseurService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class FournisseurServiceImplJUnitTest {

    @Autowired
    IFournisseurService fournisseurService;
    @Autowired
    FournisseurRepository fournisseurRepository;

    static Long idFournisseur;

    @Test
    @Order(3)
    public void testRetrieveAllFournisseurs() {
        List<Fournisseur> list = fournisseurRepository.findAll();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    @Order(1)
    public void testAddFournisseur() throws ParseException {
        Fournisseur fournisseur1 = new Fournisseur("123" , "amei1");
        Fournisseur fournisseur2 = new Fournisseur("12345" , "amei2");

        Fournisseur test = fournisseurRepository.save(fournisseur1);
        fournisseurRepository.save(fournisseur2);
        idFournisseur = test.getIdFournisseur();
        Assertions.assertEquals("123" , test.getCode());
    }


    @Test
    @Order(2)
    public void testUpdateFournisseur(){
        Fournisseur fournisseur1 = fournisseurRepository.findFournisseurByIdFournisseur(idFournisseur);
        fournisseur1.setCode("12345");
        Fournisseur updateFournisseur = fournisseurRepository.save(fournisseur1);
        Assertions.assertEquals("12345", updateFournisseur.getCode());
    }


    @Test
    @Order(4)
    public void delete() {
        fournisseurRepository.deleteAll();
    }

}