package com.example.fda.demo.model.mapper;

import com.example.fda.demo.model.dto.DrugRecordApplicationCreateRequest;
import com.example.fda.demo.model.dto.DrugRecordApplicationResource;
import com.example.fda.demo.model.entity.DrugRecordApplication;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

/**
 * Interface using Mapstruct library for mappings related to {@link
 * com.example.fda.demo.model.entity.DrugRecordApplication}
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE) // Creates a Spring Bean automatically
public interface DrugRecordApplicationMapper {

  @Mapping(target = "applicationNumber", source = "applicationNumber")
  @Mapping(target = "manufacturerName", source = "manufacturerName")
  @Mapping(target = "substanceName", source = "substanceName")
  @Mapping(source = "productNumbers", target = "productNumbers", qualifiedByName = "arrayToString")
  DrugRecordApplication toDrugRecordApplication(
      DrugRecordApplicationCreateRequest drugRecordApplicationCreateRequest);

  @Mapping(target = "applicationNumber", source = "applicationNumber")
  @Mapping(target = "manufacturerName", source = "manufacturerName")
  @Mapping(target = "substanceName", source = "substanceName")
  DrugRecordApplicationResource toDrugRecordApplicationResponse(
      DrugRecordApplicationCreateRequest drugRecordApplicationCreateRequest);

  static void updateObjectValues(Object object, Map<Object, Object> fields) {
    fields.forEach(
        (k, v) -> {
          // use reflection to get field k on manager and set it to value k
          Field field = ReflectionUtils.findField(object.getClass(), String.valueOf(k));
          field.setAccessible(true);
          ReflectionUtils.setField(field, object, v);
        });
  }

  @Named("arrayToString")
  static String arrayToString(List<String> list) {
    if (ObjectUtils.isEmpty(list)) {
      return null;
    }
    return String.join(",", list);
  }

  @Named("stringToArray")
  static List<String> stringToArray(String string) {
    if (ObjectUtils.isEmpty(string)) {
      return new ArrayList<>();
    }
    return Arrays.asList(string.split(","));
  }
}
