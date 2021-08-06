package ru.voice_assistant.alikka.models;


import javax.persistence.*;

@Entity
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne( optional = false, fetch = FetchType.LAZY, mappedBy = "response")
	private Request request;

	private String text;
	private String status;

	public Response() {
	}

	public Response(Request request, String text, String type) {
		this.request = request;
		this.text = text;
		this.status = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String type) {
		this.status = type;
	}
}
