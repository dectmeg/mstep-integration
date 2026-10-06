package org.nicmeg.mstep.integration.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DynamicDataFetchService {

    private final JdbcTemplate jdbcTemplate;

    public Map<String, Object> getValues(
            String schema,
            String table,
            List<String> columns,
            String keyColumn,
            Object keyValue) {

        String sql =
                "SELECT " +
                        String.join(", ", columns) +
                        " FROM " +
                        schema + "." + table +
                        " WHERE " + keyColumn + " = ?";

        log.info("Executing Query: {}", sql);
        log.info("Key Value: {}", keyValue);

        return jdbcTemplate.queryForMap(
                sql,
                keyValue);
    }
}


// package org.nicmeg.mstep.integration.service;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class DynamicDataFetchService {

//     private final JdbcTemplate jdbcTemplate;

//     public Object getValue(
//             String schema,
//             String table,
//             String column,
//             String keyColumn,
//             Object keyValue) {

//         String sql = "SELECT " + column +
//                 " FROM " + schema + "." + table +
//                 " WHERE " + keyColumn + " = ?";

//         return jdbcTemplate.queryForObject(
//                 sql,
//                 Object.class,
//                 keyValue);
//     }

    

// }
