package be.howest.adria.domain;

import java.util.Objects;
import java.util.UUID;

public class Notification {

	private final int id;
	private final UUID userId;
	private final String title;
	private final String message;
	private boolean read;


	public Notification(int id, UUID userId, String title, String message, boolean read) {
		this.id = id;
		this.userId = Objects.requireNonNull(userId);
		this.title = Objects.requireNonNull(title);
		this.message = Objects.requireNonNull(message);
		this.read = read;
	}

	public int getId() {
		return id;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) return false;

		Notification that = (Notification) object;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
