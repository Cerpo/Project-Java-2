/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author Cerpo
 */
public class Body extends Component {
    final static String category = "Body";
    
    private String color;
    private String colorType;
    private byte bodySize;
    private byte numberOfSeats;
    private byte numberOfDoors;
    private String carpetMaterial;
    
    

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
