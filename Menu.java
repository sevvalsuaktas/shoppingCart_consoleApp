import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Menu implements IProductsList, IPayment {
    private final Cart cart;  // Cart nesnesini burada saklıyoruz
    private final Payment payment; // Payment nesnesini burada saklıyoruz

    // Menu constructor'ı
    public Menu() {
        this.cart = Cart.getInstance(); // Singleton instance
        this.payment = new Payment(cart); // Cart instance'ını Payment nesnesine geçiriyoruz
    }

    // viewProductsInFrame metodunu IProductsList interface'inden override ettik
    @Override
    public void viewProductsInFrame() {
        cart.productsList.viewProductsInFrame();
    }

    // add metodunu IPayment interface'inden override ettik
    @Override
    public void add() {
        payment.add();
    }

    // makePayment metodunu IPayment interface'inden override ettik
    @Override
    public void makePayment() {
        payment.makePayment(); // Payment sınıfındaki makePayment çağrılır
    }

    // Seçim ekranını gösteren metot
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        int choice;
        System.out.println("--- Alışveriş Sepeti ---");
        // Ana pencere (Frame)
        JFrame frame = new JFrame("Menü");
        frame.setSize(400, 300);
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Başlık
        JLabel title = new JLabel("Alışveriş Menüsü", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        // Menü yazısını göstermek için bir JTextArea
        JTextArea menuText = new JTextArea();
        menuText.setLocation(400, 200);
        menuText.setText(
                        "1. Ürünleri Görüntüle\n" +
                        "2. Sepete Ürün Ekle\n" +
                        "3. Sepetten Ürün Çıkar\n" +
                        "4. Sepeti Görüntüle\n" +
                        "5. Sepet Tutarı\n" +
                        "6. Kart Ekle\n" +
                        "7. Ödeme Yap\n" +
                        "8. Çıkış"
        );
        menuText.setFont(new Font("Arial", Font.PLAIN, 16));
        menuText.setEditable(false); // Kullanıcı yazıyı değiştiremesin
        JScrollPane scrollPane = new JScrollPane(menuText);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.requestFocus();
        frame.setAlwaysOnTop(true);

        System.out.println("Stoktaki ürün sayısı " + Product.getProductCount());
        do {
            choice = -1;
            try {
                System.out.print("Yapmak istediğiniz işlemi seçiniz: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        cart.productsList.viewProductsInFrame();// Ürünler listesini gösterir
                        break;
                    case 2:
                        cart.add(); // Sepete ürün ekleme metodu çalışır
                        break;
                    case 3:
                        cart.removeProduct(); // Sepetten ürün çıkarma metodu çalışır
                        break;
                    case 4:
                        cart.displayCart(); // Sepeti görüntüleme metodu çalışır
                        break;
                    case 5:
                        System.out.println("Toplam Sepet Tutarı: " + cart.calculateTotal() + " TL"); // Sepet tutarını hesaplayan metot çalışır
                        break;
                    case 6:
                        add(); // Kredi kartı ekleme metodu çalışır
                        break;
                    case 7:
                        makePayment(); // Ödeme yapma metodu çalışır
                        break;
                    case 8:
                        System.out.println("Çıkış yapılıyor.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Hatalı seçim. Lütfen 1-8 arasında bir sayı giriniz.");
                }
            }
            catch (InputMismatchException e) { // try-catch ile sayı dışı bir şey girildiğinde hata mesajı döndürür
                System.out.println("Hatalı giriş! Lütfen yalnızca sayı giriniz.");
                scanner.nextLine(); // Hatalı giriş yapılan değeri temizler
            }
        }
        while (choice != 8);
        scanner.close();
    }
}
