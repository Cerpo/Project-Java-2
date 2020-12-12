package components;

public class Body extends Component {
    final static String category = "Body";
    
    private String color;
    private String colorType;
    private byte bodySize;
    private byte numberOfSeats;
    private byte numberOfDoors;
    private String carpetMaterial;

    public Body() {}

    public Body(Body other) {
    	this.color = other.color;
    	this.colorType = other.colorType;
    	this.bodySize = other.bodySize;
    	this.numberOfSeats = other.numberOfSeats;
    	this.numberOfDoors = other.numberOfDoors;
    	this.carpetMaterial = other.carpetMaterial;
	}

    public String getColor() {
		return color;
	}

	public String getColorType() {
		return colorType;
	}

	public byte getBodySize() {
		return bodySize;
	}

	public byte getNumberOfSeats() {
		return numberOfSeats;
	}

	public byte getNumberOfDoors() {
		return numberOfDoors;
	}

	public String getCarpetMaterial() {
		return carpetMaterial;
	}

	@Override
    public String getCategory() {
        return category;
    }
}
