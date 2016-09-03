package goeuro.test.model;

public class GEObjectBuilder {
	private GEObject object = new GEObject();
	
	public void buildId(String id) {
		this.object.setId(id);
	}
	
	public void buildName(String name) {
		this.object.setName(name);
	}
	
	public void buildType(String type) {
		this.object.setType(type);
	}
	
	public void buildLatitude(String latitude) {
		this.object.setLatitude(latitude);
	}
	
	public void buildLongitude(String longitude) {
		this.object.setLongitude(longitude);
	}
	
	public GEObject buildObject() {
		return new GEObject(this.object);
	}
	
	public void eraseData() {
		this.object.eraseData();
	}

}
