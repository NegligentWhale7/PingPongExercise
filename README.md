# PingPong Exercise - Jakarta EE + Swagger API Documentation

This Spring Boot project implements Jakarta EE specifications with comprehensive API documentation using Swagger/OpenAPI 3.

## Features

- ✅ **Jakarta EE Implementation**: Complete migration from `javax.*` to `jakarta.*`
- ✅ **Jakarta Bean Validation**: Comprehensive input validation with custom error messages
- ✅ **Swagger API Documentation**: Interactive API documentation with OpenAPI 3
- ✅ **Global Exception Handling**: Consistent error responses across all endpoints
- ✅ **Comprehensive Testing**: Full test coverage for validation scenarios

## Swagger API Documentation

### Accessing Swagger UI

Once the application is running, visit:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

The Swagger UI provides:
- Interactive API testing interface
- Complete endpoint documentation
- Request/response schemas with Jakarta validation constraints
- Example requests and responses
- Error response documentation

### API Documentation Features

1. **Complete Endpoint Documentation**
   - All CRUD operations for vehicles
   - Parameter descriptions and constraints
   - Response codes and descriptions
   - Jakarta validation constraints displayed

2. **Interactive Testing**
   - Test API endpoints directly from the browser
   - Pre-filled example requests
   - Real-time validation feedback
   - Jakarta validation error responses

3. **Schema Documentation**
   - `VehicleDTO` with all validation constraints
   - Field descriptions and examples
   - Validation patterns and limits

## Dependencies

### Current Dependencies

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Jakarta Bean Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Swagger/OpenAPI Documentation -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>

<!-- Database and Utilities -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

## Jakarta Bean Validation + Swagger Integration

### Key Implementation Details

1. **VehicleDTO Enhanced with Swagger Annotations**
   - `@Schema` annotations document each field
   - Jakarta validation constraints are automatically reflected in Swagger UI
   - Example values and descriptions for better API understanding

2. **Controller Documentation**
   - `@Tag` for grouping endpoints
   - `@Operation` for endpoint descriptions
   - `@ApiResponse` for documenting response codes and scenarios
   - `@Parameter` for path and query parameter documentation

3. **Swagger Configuration**
   - Custom `SwaggerConfig` class for API metadata
   - Integrated with Jakarta Bean Validation
   - Professional API documentation with contact info and versioning

### Swagger UI Features

- **Interactive Testing**: Test endpoints directly from the browser
- **Validation Preview**: See Jakarta validation constraints in the UI
- **Schema Documentation**: Complete model documentation with examples
- **Error Handling**: Document validation error responses

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
