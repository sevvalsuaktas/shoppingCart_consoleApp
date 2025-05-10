import java.time.LocalDate;
import java.util.Scanner;

public class Payment implements IPayment {
    private final Cart cart;

    // Cart sınıfından oluşmuş cart nesnesini parametre olarak alan Payment constructor'ı
    public Payment(Cart cart) {
        this.cart = cart;
    }

    // Kredi kartı ekleme metodu
    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        String cardHolderName;
        String cardNumber;
        String password;

        // Kart sahibinin adı için hata kontrolü
        while (true) {
            try {
                System.out.print("Kart Sahibinin Adını Girin: ");
                cardHolderName = scanner.nextLine();
                if (cardHolderName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Kart sahibinin adı boş bırakılamaz.");
                }
                if (!cardHolderName.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+")) {
                    throw new IllegalArgumentException("Kart sahibinin adı yalnızca harflerden oluşmalıdır.");
                }
                break; // Geçerli bir giriş yapıldığında döngüden çıkar
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Kart numarası için hata kontrolü
        while (true) {
            try {
                System.out.print("Kart Numarasını Girin: ");
                cardNumber = scanner.nextLine();
                if (!cardNumber.matches("\\d{6}")) { // Örneğin, kart numarası 6 rakamdan oluşmalı
                    throw new IllegalArgumentException("Kart numarası 6 haneli bir sayı olmalıdır.");
                }

                // Kart numarasının kaydedilen diğer kart numaralarından farklı olup olmadığını kontrol eden for döngüsü
                boolean isDuplicate = false;
                for (CreditCard existingCard : cart.getCards()) {
                    if (existingCard.getCardNumber().equals(cardNumber)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (isDuplicate) {
                    throw new IllegalArgumentException("Bu kart numarası zaten kayıtlı. Lütfen farklı bir kart numarası girin.");
                }

                // Şifre oluşturma
                while (true) {
                    try {
                        System.out.print("Kart şifresi belirleyiniz (4 haneli bir sayı olmalıdır): ");
                        password = scanner.nextLine();
                        if (!password.matches("\\d{4}")) { // Şifre tam olarak 4 rakamdan oluşmalı
                            throw new IllegalArgumentException("Kart şifresi 4 haneli bir sayı olmalıdır.");
                        }
                        break; // Geçerli bir şifre girildiğinde döngüden çıkar
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                break; // Geçerli bir kart numarası girildiğinde döngüden çıkar
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Kartı kayıtlı kartlar listesine ekleme
        cart.getCards().add(new CreditCard(cardHolderName, cardNumber, password));
        System.out.println("Kart başarıyla eklendi.");
    }

    // Ödeme yapma metodu
    public void makePayment() {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Sepet boş, ödeme yapılamaz.");
            return;
        }

        double total = cart.calculateTotal();
        System.out.println("Ödenecek Tutar: " + total + " TL");

        System.out.println("Kayıtlı Kartlar:");
        for (int i = 0; i < cart.getCards().size(); i++) {
            CreditCard card = cart.getCards().get(i);
            System.out.println((i + 1) + ". " + card.getCardHolderName() + " - " + card.getCardNumber());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Kullanmak istediğiniz kartın numarasını seçin: ");
        int secim = scanner.nextInt();
        scanner.nextLine();

        if (secim > 0 && secim <= cart.getCards().size()) {
            CreditCard selectedCard = cart.getCards().get(secim - 1);

            boolean isPasswordCorrect = false;
            while (!isPasswordCorrect) {
                System.out.print("Kart şifresini giriniz: ");
                String password = scanner.nextLine();

                if (password.equals(selectedCard.getPassword())) {
                    isPasswordCorrect = true;
                    System.out.println("Ödeme başarılı. Kullanılan kart: " + selectedCard.getCardHolderName() + " - " + selectedCard.getCardNumber());

                    LocalDate today = LocalDate.now(); // Bugünün tarihi
                    System.out.println(today + " tarihinde alışveriş başarıyla gerçekleşti. Bizi tercih ettiğiniz için teşekkür ederiz.");

                    // Alınan ürünleri, sepet tutarını, kullanılan kartı ve işlem tarihini dosyaya kaydetme işlemi
                    FileManager.saveTransaction(cart.getProducts(), total, selectedCard, today.toString());

                    cart.getProducts().clear();
                }
                else {
                    System.out.println("Şifre hatalı. Lütfen tekrar deneyin.");
                }
            }
        }
        else {
            System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
        }
    }
}