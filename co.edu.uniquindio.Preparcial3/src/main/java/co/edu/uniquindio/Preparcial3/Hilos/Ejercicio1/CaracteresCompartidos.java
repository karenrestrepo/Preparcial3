package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1;

public class CaracteresCompartidos {
    public static final char[] TODOS_CARACTERES = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '@', '#', '-', '*', '$', '(', ')', '/', '%', '+', ':', ';'
    };

    public static boolean esVocal(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    public static boolean esConsonante(char c) {
        return Character.isLetter(c) && !esVocal(c);
    }

    public static boolean esNumero(char c) {
        return Character.isDigit(c);
    }

    public static boolean esEspecial(char c) {
        return "!@#$%^&*()+-=[]{}|;:,.<>?/".indexOf(c) != -1;
    }
}
