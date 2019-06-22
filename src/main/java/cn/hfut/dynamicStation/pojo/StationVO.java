package cn.hfut.dynamicStation.pojo;
import java.util.List;
public class StationVO {
	 private List<String> time;
	 private List<Integer> nba;
	 private List<Integer> nda;
	public StationVO() {}
	@Override
	public String toString() {
		return "StationVO [time=" + time + ", nba=" + nba + ", nda=" + nda + "]";
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
	public List<Integer> getNba() {
		return nba;
	}
	public void setNba(List<Integer> nba) {
		this.nba = nba;
	}
	public List<Integer> getNda() {
		return nda;
	}
	public void setNda(List<Integer> nda) {
		this.nda = nda;
	}
}
