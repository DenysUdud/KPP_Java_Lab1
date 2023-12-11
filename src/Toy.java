public class Toy {
    String name;
    int price;
    String ageRange;
    String specialAttribute;

    public Toy(String name, int price, String ageRange, String specialAttribute) {
        this.name = name;
        this.price = price;
        this.ageRange = ageRange;
        this.specialAttribute = specialAttribute;
    }

    public boolean ageRangeContains(int minAge, int maxAge) {
        String[] ageParts = ageRange.split("-");
        if (ageParts.length == 2) {
            try {
                int toyMinAge = Integer.parseInt(ageParts[0].trim());
                int toyMaxAge = Integer.parseInt(ageParts[1].trim().split(" ")[0]);  // Extract the number part and parse
                return toyMinAge >= minAge && toyMaxAge >= maxAge;
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails (non-numeric characters in the age range)
                return false;
            }
        } else {
            // Handle the case where the age range is not in the expected format
            return false;
        }
    }

    public boolean priceInRange(int minPrice, int maxPrice) {
        return price >= minPrice && price <= maxPrice;
    }

    public int getPrice() {
        return price;
    }
}
