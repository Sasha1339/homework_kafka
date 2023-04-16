package org.rza.mpei.repositories;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.rza.mpei.dto.Measurement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class RepositoryDto {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Measurement measurement) {
        String sql =
                "insert into measurement.test (id,ia,ib, ic) " +
                        "values (:id, :Ia, :Ib, :Ic);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", measurement.getId());
        parameterSource.addValue("Ia", measurement.getIa());
        parameterSource.addValue("Ib", measurement.getIb());
        parameterSource.addValue("Ic", measurement.getIc());
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public List<Measurement> findAll() {
        return namedParameterJdbcTemplate.query("select * from measurement.test",
                new MeasurementsMappaer());

    }



    public static class MeasurementsMappaer implements RowMapper<Measurement> {
        @Override
        public Measurement mapRow(ResultSet rs, int rowNum) throws SQLException {
           return new Measurement(
                    rs.getString("id"),
                    rs.getFloat("ia"),
                    rs.getFloat("ib"),
                    rs.getFloat("ic")
            );
        }
    }

}
