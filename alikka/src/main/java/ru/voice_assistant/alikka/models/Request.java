package ru.voice_assistant.alikka.models;


import ru.voice_assistant.alikka.configs.VoiceAssistantSettings;

import javax.persistence.*;

@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String text;
	private String deviceID;
	private String command;

	@OneToOne( optional = false, orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinColumn(name = "response_id")
	private Response response;

	public Request() {
	}

	public Request(String text, String deviceID) {
		this.text = text;
		this.deviceID = deviceID;
		this.command = parseText();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getCommand() {
		if (command == null) {
			command = parseText();
		}

		return command;
	}

	private String parseText() {
		String result = text;
		for (String name : VoiceAssistantSettings.assitantNames) {
			result = result.replace(name, "").strip();
		}

		for (String action : VoiceAssistantSettings.actions) {
			result = result.replace(action, "").strip();
		}

		for (String word : VoiceAssistantSettings.words) {
			result = result.replace(word, "").strip();
		}

		return result;
	}

}
