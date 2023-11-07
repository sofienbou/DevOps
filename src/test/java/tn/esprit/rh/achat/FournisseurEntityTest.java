package tn.esprit.rh.achat;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.FournisseurDTO;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(Fournisseur.class)
public class FournisseurEntityTest {


    @Mock
    private Fournisseur fournisseur;

    @Mock
    private FournisseurDTO fournisseurDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(fournisseur).build();

    }

    @Test
    public void moniteurSetterTest() {
        Fournisseur fournisseur1 = new Fournisseur();
        fournisseur1.setCode("123");
        Assertions.assertEquals("123" , fournisseur1.getCode());
    }
}