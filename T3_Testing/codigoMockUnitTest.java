public class codigoMockUnitTest {
    private UsuarioRepository repository;

    public codigoMockUnitTest(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario registrarUsuario(String email, String password) {
        // No permitimos emails vacíos
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        Usuario nuevoUsuario = new Usuario(email, password);
        
        // Llamada al BD (esto es lo que mockearemos)
        return repository.save(nuevoUsuario);
    }
}