// package org.nicmeg.mstep.integration.service;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.nicmeg.mstep.integration.entity.ApiParameter;
// import org.nicmeg.mstep.integration.repository.ApiParameterRepository;
// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class PayloadBuilderService {

//     private final ApiParameterRepository parameterRepository;

//     private final DynamicDataFetchService dynamicDataFetchService;

//     public Map<String, Object> buildPayload(Long apiId, Object keyValue) {

//         List<ApiParameter> parameters = parameterRepository.findByApi_Id(apiId);

        

//         Map<String, Object> payload = new HashMap<>();

//         for (ApiParameter parameter : parameters) {

//             Object value = dynamicDataFetchService.getValue(
//                     parameter.getSourceSchema(),
//                     parameter.getSourceTable(),
//                     parameter.getSourceColumn(),
//                     parameter.getSourceKeyColumn(),
//                     keyValue);

//             payload.put(
//                     parameter.getParamName(),
//                     value);
//         }

//         return payload;
//     }

// }




package org.nicmeg.mstep.integration.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nicmeg.mstep.integration.entity.ApiParameter;
import org.nicmeg.mstep.integration.repository.ApiParameterRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayloadBuilderService {

    private final ApiParameterRepository parameterRepository;
    private final DynamicDataFetchService dynamicDataFetchService;

    public Map<String, Object> buildPayload(
            Long apiId,
            Object keyValue) {

        List<ApiParameter> parameters =
                parameterRepository.findByApi_Id(apiId);

        Map<String, Object> payload =
                new HashMap<>();

        Map<String, List<ApiParameter>> groupedParameters =
                parameters.stream()
                        .collect(Collectors.groupingBy(
                                parameter ->
                                        parameter.getSourceSchema()
                                                + "|"
                                                + parameter.getSourceTable()
                                                + "|"
                                                + parameter.getSourceKeyColumn()));

        for (List<ApiParameter> group : groupedParameters.values()) {

            ApiParameter first = group.get(0);

            List<String> columns =
                    group.stream()
                            .map(ApiParameter::getSourceColumn)
                            .distinct()
                            .toList();

            Map<String, Object> dbValues =
                    dynamicDataFetchService.getValues(
                            first.getSourceSchema(),
                            first.getSourceTable(),
                            columns,
                            first.getSourceKeyColumn(),
                            keyValue);

            for (ApiParameter parameter : group) {

                payload.put(
                        parameter.getParamName(),
                        dbValues.get(
                                parameter.getSourceColumn()));
            }
        }

        return payload;
    }
}