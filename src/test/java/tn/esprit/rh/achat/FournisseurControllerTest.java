package tn.esprit.rh.achat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.rh.achat.controllers.FournisseurRestController;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.FournisseurDTO;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.IFournisseurService;

import java.time.Instant;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(FournisseurRestController.class)
public class FournisseurControllerTest {

    private MockMvc mockMvc;

    static ObjectMapper objectMapper = new ObjectMapper();
    static ObjectWriter objectWriter = objectMapper.writer();
    public static String asJsonString(final Object obj) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Mock
    private IFournisseurService fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurServiceImpl;

    @InjectMocks
    private FournisseurRestController fournisseurController;


    Fournisseur fournisseur = Fournisseur.builder().idFournisseur(1L).code("123").libelle("ameni").build();

    FournisseurDTO fournisseurDTO = FournisseurDTO.builder().idFournisseur(1L).code("123").libelle("ameni").build();


    List<Fournisseur> records = new ArrayList<Fournisseur>() {
        {
            add(
                    Fournisseur.builder().idFournisseur(1L).code("123").libelle("ameni1").build()
            );
            add(
                    Fournisseur.builder().idFournisseur(2L).code("12345").libelle("ameni2").build()
            );
            add(
                    Fournisseur.builder().idFournisseur(3L).code("1234567").libelle("ameni3").build()
            );
        }
    };

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fournisseurController).build();
    }

    @Test
    public void retriveFournisseurTest() throws Exception {

        Mockito.when(fournisseurService.retrieveAllFournisseurs()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fournisseur/retrieve-all-fournisseurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$" , hasSize(3)))
                .andExpect(jsonPath("$[2].code" , is("1234567")));
    }

    @Test
    public void createFournisseurTest() throws Exception
    {

        Mockito.when(fournisseurServiceImpl.addFournisseur(fournisseurDTO)).thenReturn(fournisseur);
        mockMvc.perform(MockMvcRequestBuilders.post("/fournisseur/add-fournisseur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(fournisseurDTO)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    public void deleteFournisseurTest() throws Exception
    {
        Mockito.when(fournisseurRepository.findById(fournisseur.getIdFournisseur())).thenReturn(Optional.ofNullable(fournisseur));
        mockMvc.perform(MockMvcRequestBuilders.delete("/fournisseur/remove-fournisseur/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void retrieveFournisseurTest() throws Exception
    {
        Mockito.when(fournisseurService.retrieveFournisseur(Mockito.anyLong())).thenReturn(fournisseur);
        mockMvc.perform(MockMvcRequestBuilders.get("/fournisseur/retrieve-fournisseur/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.code" , is("123")))
                .andReturn();
    }


}