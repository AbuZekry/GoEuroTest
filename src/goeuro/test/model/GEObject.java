package goeuro.test.model;

public class GEObject {
	private String _id = "";
	private String name = "";
	private String type = "";
	private String latitude = "";
	private String longitude = "";
	
	public GEObject() {
	}
	
	public GEObject(GEObject object) {
		this._id = object.getId();
		this.name = object.getName();
		this.type = object.getType();
		this.latitude = object.getLatitude();
		this.longitude = object.getLongitude();
	}
	
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		this._id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void eraseData() {
		this._id = "";
		this.name = "";
		this.type = "";
		this.latitude = "";
		this.longitude = "";
	}
}
