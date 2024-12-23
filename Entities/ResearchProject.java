package Entities;

import People.Researcher;
import java.util.Vector;

public class ResearchProject {
	private String topic;
	private Vector<Researcher> participants;

	public ResearchProject(String topic) {
		this.topic = topic;
		this.participants = new Vector<>();
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Vector<Researcher> getParticipants() {
		return participants;
	}

	public void addParticipant(Researcher researcher) {
		if (!participants.contains(researcher)) {
			participants.add(researcher);
		}
	}
}
