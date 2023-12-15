package com.darzalgames.darzalcommon.time.game;

import java.util.Objects;

public class Time {
	
	public final int day;
	public final TimeOfDay timeOfDay;
	
	public Time(int day, TimeOfDay timeOfDay) {
		this.day = day;
		this.timeOfDay = timeOfDay;
	}
		
	public Time nextTime() {
		if (timeOfDay == TimeOfDay.AM) {
			return new Time(day, TimeOfDay.PM);
		} else if (timeOfDay == TimeOfDay.PM) {
			return new Time(day, TimeOfDay.MIDNIGHT);
		}
		else {
			return new Time(day+1, TimeOfDay.AM);
		}
	}
	
	@Override
	public String toString() {
		return "Day " + day + " " + timeOfDay;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, timeOfDay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return day == other.day && timeOfDay == other.timeOfDay;
	}
	
	public boolean isAtOrAfter(Time otherTime) {
		boolean earlierDay = otherTime.day < this.day;
		boolean laterDay = otherTime.day > this.day;
		if (laterDay) {
			return false;
		} else if (earlierDay) {
			return true;
		} else {
			// same day
			if (otherTime.timeOfDay == this.timeOfDay) {
				return true;
			} else if (this.timeOfDay == TimeOfDay.MIDNIGHT) {
				return true;
			} else if (this.timeOfDay == TimeOfDay.PM && otherTime.timeOfDay != TimeOfDay.MIDNIGHT) {
				return true;
			} else {
				return false;
			}
		}
	}
		
}
