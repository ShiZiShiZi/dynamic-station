package cn.hfut.dynamicStation.pojo;


public class StationDo {
	private int num_bikes_available;
	private int num_docks_available;
	private long  last_reported;
	@Override
	public String toString() {
		return "StationDo [num_bikes_available=" + num_bikes_available + ", num_docks_available=" + num_docks_available
				+ ", last_reported=" + last_reported + "]";
	}
    
	public int getNum_bikes_available() {
		return num_bikes_available;
	}

	public void setNum_bikes_available(int num_bikes_available) {
		this.num_bikes_available = num_bikes_available;
	}

	public int getNum_docks_available() {
		return num_docks_available;
	}

	public void setNum_docks_available(int num_docks_available) {
		this.num_docks_available = num_docks_available;
	}

	public long getLast_reported() {
		return last_reported;
	}

	public void setLast_reported(long last_reported) {
		this.last_reported = last_reported;
	}

	public StationDo() {}
}
