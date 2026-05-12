package com.fulfilment.application.monolith.warehouses.domain;

import com.fulfilment.application.monolith.location.LocationGateway;
import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.usecases.CreateWarehouseUseCase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class WarehouseValidationTest {

  @Inject
  WarehouseRepository warehouseRepository;

  @Inject
  LocationGateway locationResolver;

  private CreateWarehouseUseCase createWarehouseUseCase;

  @BeforeEach
  public void setup() {
    createWarehouseUseCase =
            new CreateWarehouseUseCase(warehouseRepository, locationResolver);
  }

  // -----------------------------
  // LOCATION VALIDATION TEST
  // -----------------------------
  @ParameterizedTest
  @MethodSource("invalidLocationScenarios")
  void testInvalidLocationScenarios(String location, String expectedError) {

    Warehouse warehouse = new Warehouse();
    warehouse.businessUnitCode = "TEST-LOC-" + System.nanoTime();
    warehouse.location = location;
    warehouse.capacity = 10;
    warehouse.stock = 5;

    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> createWarehouseUseCase.create(warehouse));

    assertTrue(exception.getMessage().contains(expectedError));
  }

  static Stream<Arguments> invalidLocationScenarios() {
    return Stream.of(
            Arguments.of("INVALID-LOC", "not valid"),
            Arguments.of("NONEXISTENT-001", "not valid"),
            Arguments.of("", "not valid")
    );
  }

  // -----------------------------
  // DUPLICATE BUSINESS CODE TEST
  // -----------------------------
  @ParameterizedTest
  @MethodSource("duplicateBusinessCodeScenarios")
  @Transactional
  void testDuplicateBusinessUnitCode(String code) {

    Warehouse w1 = new Warehouse();
    w1.businessUnitCode = code;
    w1.location = "ZWOLLE-001";
    w1.capacity = 10;
    w1.stock = 5;

    createWarehouseUseCase.create(w1);

    Warehouse w2 = new Warehouse();
    w2.businessUnitCode = code;
    w2.location = "AMSTERDAM-001";
    w2.capacity = 20;
    w2.stock = 10;

    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> createWarehouseUseCase.create(w2));

    assertTrue(exception.getMessage().contains("already exists"));
  }

  static Stream<Arguments> duplicateBusinessCodeScenarios() {
    return Stream.of(
            Arguments.of("DUP-CODE-001"),
            Arguments.of("DUP-CODE-002"),
            Arguments.of("DUP-CODE-003")
    );
  }
}