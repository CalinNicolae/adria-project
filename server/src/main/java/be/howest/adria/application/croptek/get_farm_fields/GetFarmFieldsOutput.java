package be.howest.adria.application.croptek.get_farm_fields;

import be.howest.adria.domain.FarmField;
import java.util.HashSet;
import java.util.Set;

public class GetFarmFieldsOutput extends HashSet<FarmField> {

    public GetFarmFieldsOutput(Set<FarmField> fields) {
        super(fields);
    }
}
