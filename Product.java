public abstract class Product {
    private final String id;
    private final String name;
    private final double price;
    private static int productCount = 0;

    // Product constructor'ı
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        productCount++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static int getProductCount() {
        return productCount;
    }

    public double getPrice() {
        return price;
    }

    // Ürün detaylarını gösteren metot
    public void displayDetails() {
        System.out.println("Ürün kodu: " + id + ", Ürün ismi: " + name + ", Ürün fiyatı: " + price + "TL");
    }

    @Override
    public String toString() {
        return "Ürün ismi: " + name + ", Ürün fiyatı: " + price;
    }
}