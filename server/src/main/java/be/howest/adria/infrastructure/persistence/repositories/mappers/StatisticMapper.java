package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.Statistic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticMapper implements ResultSetMapper<List<Statistic>> {

    @Override
    public List<Statistic> map(ResultSet resultSet) throws SQLException {

        final List<Statistic> statistics = new ArrayList<>();

        Map<String,Integer> createdStatValues = null;
        String lastStat = null;

        while(resultSet.next()) {
            final String stat = resultSet.getString("stat");

            if(stat.equals(lastStat)) {
                addRecord(createdStatValues, resultSet);
            }else{
                if(createdStatValues != null) {
                    statistics.add(new Statistic(lastStat, createdStatValues));
                }
                createdStatValues = new HashMap<>();
                lastStat = stat;
                addRecord(createdStatValues, resultSet);
            }

        }

        statistics.add(new Statistic(lastStat, createdStatValues));
        return statistics;
    }

    public void addRecord(Map<String,Integer> createdStatValues, ResultSet resultSet) throws SQLException {
        final String type = resultSet.getString("type");
        final int value = resultSet.getInt("value");
        createdStatValues.put(type, value);
    }

}
