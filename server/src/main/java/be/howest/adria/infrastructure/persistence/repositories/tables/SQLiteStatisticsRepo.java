package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.StatisticsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Statistic;
import be.howest.adria.infrastructure.persistence.repositories.mappers.StatisticMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SQLiteStatisticsRepo implements StatisticsRepo {

	private static final int QRY_PARAMETER_COUNT = 2;
	private static final String QRY_STATISTICS =
"""
SELECT
    'Drone Activities' AS stat,
    d.currentActivity AS type,
    COUNT(*) AS value
FROM drones d
WHERE d.userId = ?
GROUP BY d.currentActivity
UNION
SELECT
    'Crop Types - ' || f.name AS stat,
    cT.name AS type,
    COUNT(*) AS value
FROM farms f
JOIN plantedCrops pC ON f.farmId = pC.farmId
JOIN cropTypes cT ON cT.typeId = pC.typeId
WHERE f.ownerId = ?
GROUP BY cT.typeId, f.farmId
""";

	private static final StatisticMapper mapper = new StatisticMapper();

	@Override
	public List<Statistic> getUserStatistics(UUID userAdriaId) throws RepoAccessException {

		try(final Connection connection = JdbcConnection.instance().getConnection();
			final PreparedStatement statement = connection.prepareStatement(QRY_STATISTICS)) {

			for (int i = 1; i < QRY_PARAMETER_COUNT + 1; i++) {
				statement.setString(i, userAdriaId.toString());
			}

			final ResultSet rs = statement.executeQuery();

			return mapper.map(rs);
		} catch (SQLException e) {
			throw new RepoAccessException(e);
		}

	}

}
