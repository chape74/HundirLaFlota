import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class HundirLaFlotaBeta {
    public static void main(String[] args) {
        boolean[][] tiroJ1 = new boolean[10][10];
        boolean[][] barcoJ1 = new boolean[10][10];
        boolean[][] tiroJ2 = new boolean[10][10];
        boolean[][] barcoJ2 = new boolean[10][10];
        boolean turno = false; //false = J1, true = J2
        Scanner sc = new Scanner(System.in);
        String tiro;
        int toques = 0;
        int puntos = 0;


        for (int i = 4; i > 0; i--) {
            for (int j = 4; j >= i; j--) {
                System.out.print("J1");
                puntos += colocar(barcoJ1,i);
            }
        }
        for (int i = 4; i > 0; i--) {
            for (int j = 4; j >= i; j--) {
                System.out.print("J2");
                colocar(barcoJ2,i);
            }
        }
        toques = display(turno, tiroJ1, barcoJ1, tiroJ2, barcoJ2);
        while (toques < puntos) {

            if (!turno) {
                System.out.print("Jugador 1, dispare: ");
                tiro = sc.next().toUpperCase()+" ";
                if (tiro.charAt(2)=='0') tiroJ1[9][(tiro.charAt(0) - 'A')]=true;
                else tiroJ1[(tiro.charAt(1)- '1')][(tiro.charAt(0) - 'A')]=true;
            }
            else {
                System.out.print("Jugador 2, dispare: ");
                tiro = sc.next().toUpperCase()+" ";
                if (tiro.charAt(2)=='0') tiroJ2[9][(tiro.charAt(0) - 'A')]=true;
                else tiroJ2[(tiro.charAt(1)- '1')][(tiro.charAt(0) - 'A')]=true;
            }
            toques = display(turno, tiroJ1, barcoJ1, tiroJ2, barcoJ2);
            turno = !turno;
        }
        if (turno) System.out.println("GANA EL JUGADOR 1");
        else System.out.println("GANA EL JUGADOR 2");
    }


    //Mostrar el agua de ambos jugadores, sus tiros, y comprobando el turno para devolver valor
    public static int display(boolean turno,boolean[][] tiroJ1, boolean[][] barcoJ1, boolean[][] tiroJ2, boolean[][] barcoJ2) {
        int toquesJ1 = 0;
        int toquesJ2 = 0;
        System.out.println("J1  A  B  C  D  E  F  G  H  I  J       J2  A  B  C  D  E  F  G  H  I  J");
        for (int i = 0; i < tiroJ1.length; i++) {
            if (i < 9) System.out.print(" ");
            System.out.print(i + 1 + " ");
            for (int j = 0; j < tiroJ1.length; j++) {
                if (!tiroJ1[i][j]) System.out.print(" - ");
                if (tiroJ1[i][j] && !barcoJ1[i][j]) System.out.print(" W ");
                if (tiroJ1[i][j] && barcoJ1[i][j]) {
                    System.out.print(" T ");
                    toquesJ1++;
                }
            }
            if (i < 9) System.out.print(" ");
            System.out.print("      "+ (i + 1) + " ");
            for (int j = 0; j < tiroJ1.length; j++) {
                if (!tiroJ2[i][j]) System.out.print(" - ");
                if (tiroJ2[i][j] && !barcoJ2[i][j]) System.out.print(" W ");
                if (tiroJ2[i][j] && barcoJ2[i][j]) {
                    System.out.print(" T ");
                    toquesJ2++;
                }
            }
            System.out.println();
        }
        if (!turno) return toquesJ1;
        else return toquesJ2;
    }
    public static void mostrar(boolean[][] barco) {
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int i = 0; i < barco.length; i++) {
            if (i < 9) System.out.print(" ");
            System.out.print(i + 1 + " ");
            for (int j = 0; j < barco.length; j++) {
                if (!barco[i][j]) System.out.print(" - ");
                if (barco[i][j]) System.out.print(" B ");
                }
            System.out.println();
        }
    }

    public static int colocar(boolean[][] barco,int tipo) {
        mostrar(barco);
        String tiponom = "0";
        switch (tipo){
            case 1:
                tiponom= "Submarino";
                break;
            case 2:
                tiponom= "Destructor";
                break;
            case 3:
                tiponom= "Crucero";
                break;
            case 4:
                tiponom= "Acorazado";
                break;
        }
        System.out.println("coloque su "+tiponom);
        Scanner sc = new Scanner(System.in);
        char direccion;
        if (tipo!=1) {
            System.out.print("V = vertical, H = Horizontal: ");
            direccion = sc.next().toUpperCase().charAt(0);
        } else direccion='H';
        System.out.print("posicion: ");
        String pos = sc.next().toUpperCase()+" ";
        if (direccion=='H')
            if (pos.charAt(2)=='0') {
                for (int i = 0; i < tipo; i++) {
                    barco[9][(pos.charAt(0) - 'A' + i)] = true;
                }
            } else {
                for (int i = 0; i < tipo; i++) {
                    barco[(pos.charAt(1)- '1')][(pos.charAt(0) - 'A'+i)]=true;
                }
            }
        else if (direccion=='V')
            if (pos.charAt(2)=='0') {
                for (int i = 0; i < tipo; i++) {
                    barco[9+i][(pos.charAt(0) - 'A')] = true;
                }
            } else {
                for (int i = 0; i < tipo; i++) {
                    barco[(pos.charAt(1)- '1'+i)][(pos.charAt(0) - 'A')]=true;
                }
            }
        else {
            System.out.println("Error, repita por favor");
            colocar(barco,tipo);
        }
        return tipo;
    }
}
