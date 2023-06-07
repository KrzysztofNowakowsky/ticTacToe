import java.util.Scanner;

public class ticTac {

    public static boolean czyWygral(char ticTacToe[][], int wiersze, int kolumny, int numer) {
        // czy kolumny zaliczone
        int punkty = 0;
        char pop = ticTacToe[0][kolumny];
        for (int i = 1; i < 3; i++) {
            if (pop != ticTacToe[i][kolumny]) {
                break;
            } else {
                punkty = punkty + 1;
                if (punkty == 2) {
                    return true;
                }
            }
        }
        punkty = 0;
        // czy wiersze zaliczone
        pop = ticTacToe[wiersze][0];
        for (int i = 1; i < 3; i++) {
            if (pop != ticTacToe[wiersze][i]) {
                break;
            } else {
                punkty = punkty + 1;
                if (punkty == 2) {
                    return true;
                }
            }
        }
        // sprawdz czy na ukos dziala
        pop = ticTacToe[1][1];
        if (numer % 2 == 0) {
            // od lewy gora do prawy dol
            if (pop == ticTacToe[0][0] && pop == ticTacToe[2][2]) {
                return true;
            }
            // od prawy gora do lewy dol
            if (pop == ticTacToe[0][2] && pop == ticTacToe[2][0]) {
                return true;
            }
        }
        return false;
    }

    public static void wyswietlTablice(char ticTacToe[][]) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ticTacToe[i][j]);
                if (j < 2) {
                    System.out.print("|");
                } else {
                    System.out.print("\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int counter = 0;
        boolean[][] ticTacToeBoard = new boolean[3][3];
        char[][] ticTacToe = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToe[i][j] = (char) (49 + counter);
                ticTacToeBoard[i][j] = false;
                counter = counter + 1;
            }
        }

        do {
            ticTac.wyswietlTablice(ticTacToe);
            String wiersz = input.nextLine();
            if (wiersz.length() > 1) {
                System.out.println("PODAJ cyfre od 1-9");
                continue;
            }
            int numer = 0;
            try {
                numer = Integer.parseInt(wiersz) - 1;
            } catch (Exception e) {
                System.out.println("Podaj cyfre!");
                continue;
            }
            
            ticTacToe[numer / 3][numer % 3] = 'O';

            wyswietlTablice(ticTacToe);
            if (!czyWygral(ticTacToe, numer / 3, numer % 3, numer)) {
                numer = 0;
                while (numer == 0) {
                    System.out.println("");
                    wiersz = input.nextLine();
                    try {
                        numer = Integer.parseInt(wiersz) - 1;
                    } catch (Exception e) {
                        System.out.println("Podaj cyfre!");
                        continue;
                    }
                    ticTacToe[numer / 3][numer % 3] = 'X';
                    wyswietlTablice(ticTacToe);
                    if (czyWygral(ticTacToe, numer / 3, numer % 3, numer)) {
                        System.out.println("Wygral X");
                        break;
                    }
                }
            } else {
                System.out.println("Wygral O");
                break;
            }
        } while (true);
        input.close();

    }
}