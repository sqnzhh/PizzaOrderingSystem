public class PizzaBase{
    private String description;
    private String baseType;
    private String baseName;
    public PizzaBase(String baseName, String baseType, String description){
        this.baseName = baseName;
        this.baseType = baseType;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    @Override
    public String toString() {
        return "Pizza Base Details => Base Name: " + getBaseName() +
                ", Type: " + getBaseType() +
                ", Description: " + getDescription();
    }

}
