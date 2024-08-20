package br.com.almavivasolutions.integrador.model.exception;

public class HttpResponseMessage {
    private int status;
    private String message;
    private long timestamp;

    public HttpResponseMessage(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

	@Override
	public String toString() {
		return message;
	}
}
