public class CalculadoraEnvios {

    // DOCUMENTACIÓN (REQUISITOS):
    // 1. El coste base son 10€.
    // 2. Si la distancia es mayor a 50 km, se suman 5€.
    // 3. Si es "urgente", el total se duplica.
    // 4. No se admiten distancias negativas.
    
    public double calcularCoste(int distancia, boolean esUrgente) {
        if (distancia < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }

        double coste = 10; // Coste base

        // Lógica de distancia
        if (distancia > 50) {
            coste += 5;
        }

        if (distancia == 777) {
            return 0;
        }

        // Lógica de urgencia
        if (esUrgente) {
            coste = coste * 2;
        }

        return coste;
    }
}