package br.edu.iftm.springapp.food;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.iftm.springapp.config.TestConfig;
import br.edu.iftm.springapp.controller.FoodController;
import br.edu.iftm.springapp.model.Food;
import br.edu.iftm.springapp.service.FoodService;

@WebMvcTest(FoodController.class)
@Import(TestConfig.class)
public class FoodControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodService foodService;

    @AfterEach
    void resetMocks(){
        reset(foodService);
    }

    private List<Food> testCreateFoodList(){
        Food foodB = new Food();
        foodB.setId(1L);
        foodB.setDescription("Descricao");
        foodB.setName("Comida B");
        foodB.setKcal(230.54f);
        foodB.setPreparationTime(180);

        return List.of(foodB);
    }

    @Test
    @DisplayName("GET /food - Listar comidas na tela index sem usuário autenticado")
    void testIndexNotAuthenticatedUser() throws Exception {
         mockMvc.perform(get("/food"))
            .andExpect(status().isUnauthorized()); // Correção aqui
    }

    @Test
    @WithMockUser
    @DisplayName("GET /food - Listar comidas na tela index com usuário logado")
    void testIndexAuthenticatedUser() throws Exception {
        when(foodService.getAllFoods()).thenReturn(testCreateFoodList());

        mockMvc.perform(get("/food"))
               .andExpect(status().isOk())
               .andExpect(view().name("food/index"))
               .andExpect(model().attributeExists("foodsList"))
               .andExpect(content().string(containsString("Listagem de Comida")))
               .andExpect(content().string(containsString("Comida B")));
    }

    @Test
    @WithMockUser(username = "aluno@iftm.edu.br", authorities = { "Admin" })
    @DisplayName("GET /food - Exibe link de acesso ao form de cadastro de comida")
    void testCreateFormAuthorizedUser() throws Exception {
        when(foodService.getAllFoods()).thenReturn(testCreateFoodList());
        mockMvc.perform(get("/food"))
                .andExpect(status().isOk())
                .andExpect(view().name("food/index"))
                .andExpect(content().string(containsString("Cadastrar Comida")));
    }

    @Test
    @WithMockUser(username = "aluno2@iftm.edu.br", authorities = { "Manager" })
    @DisplayName("GET /food - Verificar o link de cadastrar para um usuario não admin logado")
    void testCreateFormNotAuthorizedUser() throws Exception {
        when(foodService.getAllFoods()).thenReturn(testCreateFoodList());
       // Obter o HTML da página renderizada pelo controlador
       mockMvc.perform(get("/food"))
            .andExpect(status().isOk())
            .andExpect(view().name("food/index"))
            .andExpect(content().string(not(containsString("<a class=\"dropdown-item\" href=\"/food/create\">Cadastrar</a>"))));
    }

    @Test
    @WithMockUser
    @DisplayName("POST /food/save - Falha na validação e retorna para o formulário")
    void testSaveFoodValidationError() throws Exception {
        Food food = new Food(); // Comida sem nome, o que causará erro de validação

        mockMvc.perform(post("/food/save")
                        .with(csrf())
                        .flashAttr("food", food))
                .andExpect(status().isOk())
                .andExpect(view().name("food/form"))
                .andExpect(model().attributeHasErrors("food"));

        verify(foodService, never()).saveFood(any(Food.class));
    }

    @Test
    @WithMockUser(username = "aluno@iftm.edu.br", authorities = { "Admin" })
    @DisplayName("POST /food/save - Comida válido é salvo com sucesso")
    void testSaveValidFood() throws Exception {
        Food food = new Food();
        food.setName("Novo Comida");
        food.setDescription("Descrição");
        food.setKcal(230.54f);
        food.setPreparationTime(240);

        mockMvc.perform(post("/food/save")
                        .with(csrf())
                        .flashAttr("food", food))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/food"));

        verify(foodService).saveFood(any(Food.class));
    }
}
