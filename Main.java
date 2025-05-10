public class Main{
    public static void main(String[] args) {
        System.out.println("Lütfen açılan pencereleri kapatmayınız.\nAksi takdirde program sona erecektir.");
        System.out.println();

        Menu menu = new Menu();
        menu.displayMenu();
    }
}
