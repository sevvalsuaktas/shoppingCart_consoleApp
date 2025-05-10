import java.util.ArrayList;
import java.util.Scanner;

public class Cart {
    private static Cart instance; // Singleton instance
    private final ArrayList<Product> products;
    public final ProductsList productsList;
    public final ArrayList<CreditCard> cards;

    // Cart constructor'ı
    public Cart() {
        this.productsList = new ProductsList();
        this.cards = new ArrayList<>();
        this.products = new ArrayList<>();
        cards.add(new CreditCard("irem","123456","1414"));
        cards.add(new CreditCard("su","161616","1515"));
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public ArrayList<Product> getProducts() {
        return products; // Ürünler array listini döndürüyoruz
    }

    public ArrayList<CreditCard> getCards() {
        return cards; // Kartlar array listini döndürüyoruz
    }

    // Seepete ürün ekleme metodu
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sepete eklemek istediğiniz ürünün kodunu girin: ");

        // Geçerli ürün kodu girilmediği takdirde hata döndüren try-catch bloğu
        try {
            String productId = scanner.nextLine();
            Product selectedProduct = productsList.getProductByCode(productId);
            products.add(selectedProduct);
            System.out.println(selectedProduct.getName() + "-" + selectedProduct.getPrice() + " TL" + " sepete eklendi.");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Lütfen geçerli bir ürün kodu giriniz.");
        }
    }

    // Sepetten ürün çıkarma metodu
    public void removeProduct() {
        if (products.isEmpty()) {
            System.out.println("Sepet boş. Çıkarılacak ürün yok.");
            return;
        }

        System.out.println("Sepetinizdeki ürünler:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getName() + " - " + products.get(i).getPrice() + " TL");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Çıkarmak istediğiniz ürünün numarasını girin: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= products.size()) {
            Product removedProduct = products.remove(choice - 1);
            System.out.println(removedProduct.getName() + " sepetten çıkarıldı.");
        }
        else {
            System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
        }
    }

    // Sepetteki ürünleri gösteren metot
    public void displayCart() {
        if (products.isEmpty()) {
            System.out.println("Sepetiniz boş.");
        }
        else {
            System.out.println("Sepetinizdeki Ürünler:");
            for (Product product : products) {
                product.displayDetails();
            }
        }
    }

    // Sepet tutarını hesaplayan metot
    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}
