package components;

public abstract class Component {

    private String typeCode;


    public abstract String getCategory();

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
