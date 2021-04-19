package com.example.fda.demo.service;

import com.example.fda.demo.exception.DrugRecordApplicationNotFoundException;
import com.example.fda.demo.integration.fda.client.OpenFDAClient;
import com.example.fda.demo.integration.fda.model.OpenFDAResponse;
import com.example.fda.demo.model.entity.DrugRecordApplication;
import com.example.fda.demo.model.mapper.DrugRecordApplicationMapper;
import com.example.fda.demo.repository.DrugRecordApplicationRepository;
import com.example.fda.demo.repository.search.DrugRecordApplicationSpecificationBuilder;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class OpenFDAServiceImpl implements OpenFDAService {

  private final DrugRecordApplicationRepository repository;
  private final DrugRecordApplicationSpecificationBuilder specificationBuilder;
  private final OpenFDAClient openFDAClient;

  @Override
  public ResponseEntity<OpenFDAResponse> findSubmittedForApproval(
      String manufacturer, String brand, Integer page, Integer pageSize) {
    StringBuilder searchBuilder = new StringBuilder();
    if (!ObjectUtils.isEmpty(manufacturer)) {
      searchBuilder.append("sponsor_name:\"");
      searchBuilder.append(manufacturer);
      searchBuilder.append("\"");
    }
    if(!ObjectUtils.isEmpty(brand)) {
      searchBuilder.append("+AND+products.brand_name:\"");
      searchBuilder.append(brand);
      searchBuilder.append("\"");
    }
    searchBuilder.append("&limit=" + pageSize);
    searchBuilder.append("&skip=" + (pageSize * (page - 1)));
    return openFDAClient.findByManufacturerAndBrand(searchBuilder.toString());
  }

  @Override
  public DrugRecordApplication findById(Long id) throws DrugRecordApplicationNotFoundException {
    return repository.findById(id).orElseThrow(DrugRecordApplicationNotFoundException::new);
  }

  @Override
  public DrugRecordApplication findByQuery(Map<Object, Object> fields, Integer page, Integer size)
      throws DrugRecordApplicationNotFoundException, IllegalAccessException {

    DrugRecordApplication drugRecordApplication = new DrugRecordApplication();
    DrugRecordApplicationMapper.updateObjectValues(drugRecordApplication, fields);

    // Generate specification to query with
    Specification<DrugRecordApplication> specification =
        specificationBuilder.buildFrom(drugRecordApplication);

    PageRequest pageRequest = PageRequest.of(page, size);
    Page<DrugRecordApplication> allPage = repository.findAll(specification, pageRequest);

    List<DrugRecordApplication> all = allPage.getContent();
    if (all.isEmpty()) {
      throw new DrugRecordApplicationNotFoundException();
    }
    return all.get(0);
  }

  @Override
  public DrugRecordApplication create(DrugRecordApplication drugRecordApplication) {
    // Save to database
    return repository.save(drugRecordApplication);
  }

  @Override
  public void delete(String applicationNumber) throws DrugRecordApplicationNotFoundException {
    // Soft deletion
    DrugRecordApplication drugRecordApplication =
        repository
            .findByApplicationNumberAndDeletedIsFalse(applicationNumber)
            .orElseThrow(DrugRecordApplicationNotFoundException::new);
    drugRecordApplication.setDeleted(true);
    repository.save(drugRecordApplication);
  }

  @Override
  public List<DrugRecordApplication> findAll() {
    return repository.findAllByDeletedIsFalse();
  }

  @Override
  public List<DrugRecordApplication> findDeleted() {
    return repository.findAllByDeletedIsTrue();
  }
}
