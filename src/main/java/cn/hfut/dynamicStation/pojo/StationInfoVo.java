package cn.hfut.dynamicStation.pojo;

public class StationInfoVo {
	private int Station_id;
	private String name;
	public StationInfoVo() {
		super();
	}
	public StationInfoVo(int station_id, String name) {
		super();
		Station_id = station_id;
		this.name = name;
	}
	public int getStation_id() {
		return Station_id;
	}
	public void setStation_id(int station_id) {
		Station_id = station_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "StationInfoVo [Station_id=" + Station_id + ", name=" + name + "]";
	}
	
}
