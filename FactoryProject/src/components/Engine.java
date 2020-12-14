package components;

public class Engine extends Component {
    final static String category = "Engine";
    
    private int cylinderCapacity;
    private boolean isGasoline;
    private int horsePower;
    private int numberOfCylinders;

	public Engine() {}

	public Engine(Engine other) {
		this.cylinderCapacity = other.cylinderCapacity;
		this.isGasoline = other.isGasoline;
		this.horsePower = other.horsePower;
		this.numberOfCylinders = other.numberOfCylinders;
                this.setTypeCode(other.getTypeCode());
	}

	public int getCylinderCapacity() {
		return cylinderCapacity;
	}

	public boolean isGasoline() {
		return isGasoline;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public int getNumberOfCylinders() {
		return numberOfCylinders;
	}

	@Override
    public String getCategory() {
        return category;
    }

}
