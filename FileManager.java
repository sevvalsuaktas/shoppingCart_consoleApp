import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {
    public static void saveTransaction(List<Product> products, double total, CreditCard card, String transactionDate) {
        String fileName = "islemdetayi.txt"; // Dosya adı
        try (FileWriter writer = new FileWriter(fileName, true)) { // true parametresi dosyaya ekleme yapar
            writer.write("----- İşlem Bilgileri -----\n");
            writer.write("İşlem Tarihi: " + transactionDate + "\n");
            writer.write("Kullanılan Kart: " + card.getCardHolderName() + " - " + card.getCardNumber() + "\n");
            writer.write("Alınan Ürünler:\n");
            for (Product product : products) {
                writer.write("- " + product.getName() + " - " + product.getPrice() + " TL\n");
            }
            writer.write("Toplam Tutar: " + total + " TL\n");
            writer.write("--------------------------\n\n");
            System.out.println("İşlem bilgileri '" + fileName + "' dosyasına kaydedildi.");
        }
        catch (IOException e) {
            System.out.println("Dosya yazılırken bir hata oluştu: " + e.getMessage());
        }
    }
}