package br.com.almavivasolutions.integrador.model.exception;

public class HttpResponse {
    private int status;
    private String message;
    private long timestamp;

    public HttpResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

	@Override
	public String toString() {
		return message;
	}
}
