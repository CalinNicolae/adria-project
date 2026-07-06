package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Statistic;
import java.util.List;
import java.util.UUID;

public interface StatisticsRepo {

	List<Statistic> getUserStatistics(UUID userAdriaId) throws RepoAccessException;

}
