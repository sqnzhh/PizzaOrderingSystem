public class Topping{
    private String description;
    private String spiceLevel;
    private String toppingName;
    public Topping(String toppingName, String spiceLevel, String description){
        this.description = description;
        this.toppingName = toppingName;
        this.spiceLevel = spiceLevel;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(String spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    @Override
    public String toString() {
        return "Toppings Details => Topping Name: " + getToppingName() +
                ", Spice Level: " + getSpiceLevel() +
                ", Description: " + getDescription();
    }

}