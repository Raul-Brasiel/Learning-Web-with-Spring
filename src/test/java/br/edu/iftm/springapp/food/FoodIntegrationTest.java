package br.edu.iftm.springapp.food;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iftm.springapp.model.Food;
import br.edu.iftm.springapp.repository.FoodRepository;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") //Usa application-test.properties
@Transactional //limpa o banco após cada teste
public class FoodIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodRepository foodRepository;

    @Test
    @WithMockUser(authorities = {"admin"})
    void testSaveFoodIntegration() throws Exception{
        Food foodA = new Food();
        foodA.setDescription("Descricao");
        foodA.setName("Comida A");
        foodA.setKcal(230.54f);
        foodA.setPreparationTime(180);


        mockMvc.perform(post("/food/save")
                .with(csrf())
                .flashAttr("food", foodA))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/food"));

        // Verifica no banco se foi salvo
        assertTrue(foodRepository.findAll()
                .stream()
                .anyMatch(p -> "Comida A".equals(p.getName())));
    }
}
