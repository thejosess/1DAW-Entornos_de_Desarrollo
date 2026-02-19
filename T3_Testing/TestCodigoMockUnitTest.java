import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Test
    void cuandoRegistroUsuario_entoncesSeGuardaCorrectamente() {
        // Creamos el Mock del repositorio
        UsuarioRepository repoMock = mock(UsuarioRepository.class);
        
        // Configuramos el Mock: "Cuando alguien llame a save(), devuelve un usuario con ID"
        Usuario usuarioEsperado = new Usuario("test@mail.com", "1234");
        usuarioEsperado.setId(100L); 
        when(repoMock.save(any(Usuario.class))).thenReturn(usuarioEsperado);

        codigoMockUnitTest service = new codigoMockUnitTest(repoMock);

        // Llamada con el mock
        Usuario resultado = service.registrarUsuario("test@mail.com", "1234");

        // Verificar
        assertNotNull(resultado.getId());
        assertEquals(100L, resultado.getId());
        assertEquals("test@mail.com", resultado.getEmail());

        // ¿se llamó al método save una vez?
        verify(repoMock, times(1)).save(any(Usuario.class));
    }
}