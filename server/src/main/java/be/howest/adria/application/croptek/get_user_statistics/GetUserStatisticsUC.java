package be.howest.adria.application.croptek.get_user_statistics;

import be.howest.adria.application.contracts.repositories.croptek.StatisticsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;
import java.util.Objects;

public class GetUserStatisticsUC implements UseCase<GetUserStatisticsInput> {

	private final OutputPort<GetUserStatisticsOutput> outputPort;
	private final StatisticsRepo statisticsRepo;

	public GetUserStatisticsUC(OutputPort<GetUserStatisticsOutput> outputPort, StatisticsRepo statisticsRepo) {
		this.outputPort = Objects.requireNonNull(outputPort);
		this.statisticsRepo = Objects.requireNonNull(statisticsRepo);
	}

	@Override
	public void execute(GetUserStatisticsInput input) {
		outputPort.present(
				new GetUserStatisticsOutput(
						statisticsRepo.getUserStatistics(input.getUserAdriaId())
				)
		);
	}

	public StatisticsRepo getStatisticsRepo() {
		return statisticsRepo;
	}

	public OutputPort<GetUserStatisticsOutput> getOutputPort() {
		return outputPort;
	}

}
