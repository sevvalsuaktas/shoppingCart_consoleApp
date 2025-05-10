import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductsList implements IProductsList {
    private final ArrayList<Product> products;

    public ProductsList() {
        products = new ArrayList<>();
        // Ürünleri ekleme
        products.add(new MarketProduct("0", "Deterjan", 45.99));
        products.add(new MarketProduct("1", "Yumuşatıcı", 25.50));
        products.add(new MarketProduct("2", "Çamaşır Suyu", 19.75));
        products.add(new MarketProduct("3", "Tuvalet Kağıdı", 65.99));
        products.add(new MarketProduct("4", "Peçete", 12.90));
        products.add(new MarketProduct("5", "Islak Mendil", 8.45));
        products.add(new ElectronicProduct("6", "Telefon", 25000.0));
        products.add(new ElectronicProduct("7", "Bilgisayar", 50000.0));
        products.add(new ElectronicProduct("8", "Tablet", 10000.0));
    }

    // Ürünleri bir JFrame içinde göstermek için metot
    public void viewProductsInFrame() {
        // JFrame oluşturma
        JFrame frame = new JFrame("Ürün Listesi");
        frame.setSize(400, 300);
        frame.setLocation(800,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel oluşturma ve ürün bilgilerini tablo şeklinde gösterme işlemi
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID", "İsim", "Fiyat"};
        String[][] data = new String[products.size()][3];

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            data[i][0] = product.getId();
            data[i][1] = product.getName();
            data[i][2] = String.format("%.2f", product.getPrice());
        }

        // JTable oluşturma
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Paneli frame'e ekleme
        frame.add(panel);
        frame.setVisible(true);

        frame.requestFocus();
        frame.toFront();
        frame.setAlwaysOnTop(true);
    }

    public Product getProductByCode(String id) {
        try {
            int index = Integer.parseInt(id);
            if (index >= 0 && index < products.size()) {
                return products.get(index);
            } else {
                throw new IllegalArgumentException("Geçersiz ürün ID'si: " + id);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID bir sayı olmalıdır: " + id, e);
        }
    }
}
