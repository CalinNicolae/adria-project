package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.contracts.repositories.croptek.StatisticsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Farm;
import be.howest.adria.domain.Statistic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class TestStatisticsRepo implements StatisticsRepo {

	private final DronesRepo dronesRepo;
	private final FarmsRepo farmsRepo;
	private final PlantedCropsRepo plantedCropsRepo;
	private final CropTypesRepo cropTypesRepo;

	public TestStatisticsRepo(DronesRepo dronesRepo, FarmsRepo farmsRepo,
							  PlantedCropsRepo plantedCropsRepo, CropTypesRepo cropTypesRepo) {
		this.dronesRepo = Objects.requireNonNull(dronesRepo);
		this.farmsRepo = Objects.requireNonNull(farmsRepo);
		this.plantedCropsRepo = Objects.requireNonNull(plantedCropsRepo);
		this.cropTypesRepo = Objects.requireNonNull(cropTypesRepo);
	}

	@Override
	public List<Statistic> getUserStatistics(UUID userAdriaId) throws RepoAccessException {
		final List<Statistic> statistics = new ArrayList<>();

		addUserDronesStatistic(statistics, userAdriaId);

		farmsRepo.getFarms(userAdriaId).forEach(farm ->
				addPlantedCropsStatistics(statistics, farm));

		return statistics;
	}

	private void addUserDronesStatistic(List<Statistic> accumulator, UUID userAdriaId) throws RepoAccessException {
		final Map<String,Integer> values = new HashMap<>();

		dronesRepo.getAllDronesForUserId(userAdriaId)
				.forEach(drone -> {
					final String strActivity = drone.getActivity().toString();
					if(values.containsKey(strActivity)) {
						values.put(strActivity, values.get(strActivity) + 1);
					}else{
						values.put(strActivity, 1);
					}
				});

		accumulator.add(new Statistic("Drone Activities", values));
	}

	private void addPlantedCropsStatistics(List<Statistic> accumulator, Farm farm) throws RepoAccessException {
		final Map<String,Integer> values = new HashMap<>();

		plantedCropsRepo.getPlantedCrops(farm.getId())
				.forEach(crop -> {
					final String strCropType =
							cropTypesRepo.getCropType(crop.getTypeId()).toString();

					if(values.containsKey(strCropType)) {
						values.put(strCropType, values.get(strCropType) + 1);
					}else{
						values.put(strCropType, 1);
					}
				});

		accumulator.add(new Statistic("Statistics - " + farm.getName(), values));
	}

}
