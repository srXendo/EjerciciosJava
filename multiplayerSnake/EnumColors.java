
public enum EnumColors {
    RESET("\u001B[0m"),
    ROJO("\u001B[31m"),
    VERDE("\u001B[32m"),
    AMARILLO("\u001B[33m"),
    AZUL("\u001B[34m");

    private final String codigo;

    // Constructor del enum
    EnumColors(String codigo) {
        this.codigo = codigo;
    }

    // Método para obtener el valor al imprimirlo
    @Override
    public String toString() {
        return codigo;
    }
}