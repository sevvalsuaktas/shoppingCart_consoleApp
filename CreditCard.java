public class CreditCard {
    private final String cardHolderName;
    private final String cardNumber;
    private final String password;

    // Kredi kartı constructor'ı
    public CreditCard(String cardHolderName, String cardNumber,String password) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.password= password;
    }

    // Kart sahibinin ismini döndüren metot
    public String getCardHolderName() {
        return cardHolderName;
    }

    // Kart numarasını döndüren metot
    public String getCardNumber() {
        return cardNumber;
    }

    // Kart şifresini döndüren metot
    public String getPassword() {
        return password;
    }
}
