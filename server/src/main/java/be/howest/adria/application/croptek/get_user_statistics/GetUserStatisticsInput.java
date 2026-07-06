package be.howest.adria.application.croptek.get_user_statistics;

import java.util.Objects;
import java.util.UUID;

public class GetUserStatisticsInput {

	private final UUID userAdriaId;

	public GetUserStatisticsInput(UUID userAdriaId) {
		this.userAdriaId = Objects.requireNonNull(userAdriaId);
	}

	public UUID getUserAdriaId() {
		return userAdriaId;
	}

}
