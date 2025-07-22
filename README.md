# PingPong Exercise - Jakarta EE Implementation

This Spring Boot project has been migrated to use Jakarta EE (formerly Java EE) specifications, specifically Jakarta Bean Validation.

## Jakarta EE Migration Summary

### What Changed

1. **Validation API Migration**: Migrated from `javax.validation` to `jakarta.validation`
2. **Enhanced Validation Rules**: Added comprehensive validation annotations to `VehicleDTO`
3. **Global Exception Handling**: Implemented proper error handling for validation failures
4. **Configuration**: Added Jakarta Bean Validation configuration
5. **Testing**: Created comprehensive test suite for validation scenarios

### Key Features

#### 1. Jakarta Bean Validation in VehicleDTO

The `VehicleDTO` class now includes comprehensive Jakarta validation annotations:

```java
@NotNull(message = "El modelo es obligatorio")
@Size(min = 2, max = 100, message = "El modelo debe tener entre 2 y 100 caracteres")
private String model;

@NotNull(message = "La marca es obligatoria")
@Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 50 caracteres")
private String brand;

@NotNull(message = "El año es obligatorio")
@Pattern(regexp = "\\d{4}", message = "El año debe ser un número de 4 dígitos")
private String anio;

@Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "El VIN debe tener exactamente 17 caracteres alfanuméricos válidos")
private String vin;

@NotNull(message = "El ID del taller es obligatorio")
@Positive(message = "El ID del taller debe ser un número positivo")
private Long workshopId;
```

#### 2. Controller Validation

Controllers now use `@Valid` annotation to trigger validation:

```java
@PostMapping("/save")
public Vehicle saveVehicle(@Valid @RequestBody VehicleDTO vehicle) {
    return vehicleService.saveVehicle(vehicle);
}

@PutMapping("/{id}")
public Vehicle updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
    return vehicleService.updateVehicle(id, vehicleDTO);
}
```

#### 3. Global Exception Handler

The `GlobalExceptionHandler` class provides consistent error responses for validation failures:

- **MethodArgumentNotValidException**: Handles `@Valid` annotation validation errors
- **ConstraintViolationException**: Handles direct constraint violations
- **Generic Exception**: Handles unexpected errors

Example validation error response:
```json
{
  "timestamp": "2025-07-22T09:24:16",
  "status": 400,
  "error": "Validation Failed",
  "message": "Los datos proporcionados no son válidos",
  "fieldErrors": {
    "model": "El modelo es obligatorio",
    "brand": "La marca es obligatoria"
  }
}
```

#### 4. Validation Configuration

The `ValidationConfig` class ensures proper Jakarta Bean Validation setup in the Spring Boot application.

#### 5. Comprehensive Testing

The `VehicleDTOValidationTest` class includes tests for:
- Valid DTO scenarios
- Null field validation
- Size constraint validation
- Pattern validation (year, VIN)
- Positive number validation

## Dependencies

### Added Jakarta Dependencies

```xml
<!-- Jakarta Bean Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

## Running the Project

### Prerequisites

- Java 21 (required by Spring Boot 3.x)
- Maven 3.9.x

### Running with Correct Java Version

Due to multiple Java installations, use the provided script:

#### Windows
```cmd
mvn-java21.cmd spring-boot:run
```

Or set JAVA_HOME manually:
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-21
mvnw.cmd spring-boot:run
```

#### PowerShell
```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-21"
.\mvnw.cmd spring-boot:run
```

### Testing

Run all tests including validation tests:
```cmd
mvn-java21.cmd test
```

## API Endpoints

### Vehicle Management

- `GET /api/v1/vehicles` - Get all vehicles
- `GET /api/v1/vehicles/{id}` - Get vehicle by ID
- `POST /api/v1/vehicles/save` - Create new vehicle (with validation)
- `PUT /api/v1/vehicles/{id}` - Update vehicle (with validation)
- `DELETE /api/v1/vehicles/{id}` - Delete vehicle

### Validation Examples

#### Valid Request
```json
{
  "model": "Toyota Corolla",
  "brand": "Toyota",
  "anio": "2023",
  "color": "Rojo",
  "vin": "1HGBH41JXMN109186",
  "workshopId": 1,
  "partIds": [1, 2, 3]
}
```

#### Invalid Request (triggers validation)
```json
{
  "model": "A",
  "brand": "",
  "anio": "23",
  "vin": "INVALID",
  "workshopId": -1
}
```

## Jakarta EE Migration Benefits

1. **Future Compatibility**: Jakarta EE is the active specification, ensuring long-term support
2. **Spring Boot 3.x Compatibility**: Full compatibility with modern Spring Boot versions
3. **Enhanced Validation**: More robust validation with better error handling
4. **Standardization**: Uses industry-standard Jakarta specifications
5. **Better Error Messages**: User-friendly Spanish error messages

## Project Structure

```
src/
├── main/java/com/josealam/pingpongexercise/
│   ├── config/
│   │   └── ValidationConfig.java          # Jakarta validation configuration
│   ├── controllers/
│   │   └── VehicleController.java         # Enhanced with @Valid annotations
│   ├── exception/
│   │   └── GlobalExceptionHandler.java    # Validation error handling
│   ├── models/
│   │   └── VehicleDTO.java               # Enhanced with Jakarta validation
│   └── ...
└── test/java/com/josealam/pingpongexercise/
    └── validation/
        └── VehicleDTOValidationTest.java  # Comprehensive validation tests
```

## Next Steps

1. Consider adding validation to other DTOs (Part, Workshop)
2. Implement custom validation annotations for business rules
3. Add integration tests for validation scenarios
4. Consider adding Jakarta Persistence (JPA) enhancements
5. Implement Jakarta Security features if needed

---

**Note**: This project uses Jakarta EE specifications, which are the evolution of Java EE. All `javax.*` packages have been migrated to `jakarta.*` as required by Spring Boot 3.x and modern Jakarta EE implementations.
