@SpringBootTest 
@AutoConfigureMockMvc // Configura la herramienta para simular peticiones HTTP
class LoginIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void cuandoLoginEsExitoso_entoncesRedirigeAHome() throws Exception {
        mockMvc.perform(post("/login") // Simula un POST HTTP
                .param("username", "admin")
                .param("password", "1234"))
                .andExpect(status().is3xxRedirection()) // Verifica que hubo redirección (302)
                .andExpect(redirectedUrl("/home"));    // Verifica el destino exacto
    }

    @Test
    void cuandoLoginEsFallido_entoncesRedirigeALoginConError() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "usuario_falso")
                .param("password", "error"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}