package carbuilder;

public class CarRequirement {
    private String carName;
    private String requiredBodyType;
    private String requiredElectronicsType;
    private String requiredEngineType;
    private String requiredWheelType;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getRequiredBodyType() {
        return requiredBodyType;
    }

    public void setRequiredBodyType(String requiredBodyType) {
        this.requiredBodyType = requiredBodyType;
    }

    public String getRequiredElectronicsType() {
        return requiredElectronicsType;
    }

    public void setRequiredElectronicsType(String requiredElectronicsType) {
        this.requiredElectronicsType = requiredElectronicsType;
    }

    public String getRequiredEngineType() {
        return requiredEngineType;
    }

    public void setRequiredEngineType(String requiredEngineType) {
        this.requiredEngineType = requiredEngineType;
    }

    public String getRequiredWheelType() {
        return requiredWheelType;
    }

    public void setRequiredWheelType(String requiredWheelType) {
        this.requiredWheelType = requiredWheelType;
    }
}
